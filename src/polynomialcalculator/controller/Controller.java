/**The Controller class is explained in detail in the documentation, in chapter 4*/
package polynomialcalculator.controller;

import polynomialcalculator.model.Model;
import polynomialcalculator.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    private View theView;
    private Model theModel;

    public Controller(View theView, Model theModel) {
        this.theView = theView;
        this.theModel = theModel;
        this.theView.addCalculateListener(new CalculateListener());
    }

    class CalculateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String p1String = theView.getPolynomial1();
            verifyInputAndCreatePolynomial(p1String, '1');
            if (!(theView.getOperation().equals("Derivative") || theView.getOperation().equals("Integration"))) {
                String p2String = theView.getPolynomial2();
                verifyInputAndCreatePolynomial(p2String, '2');
            }
            switch (theView.getOperation()) {
                case "Addition":
                    theModel.setP3(Model.addition_subtraction(theModel.getP1(), theModel.getP2(), '+'));
                    break;
                case "Subtraction":
                    theModel.setP3(Model.addition_subtraction(theModel.getP1(), theModel.getP2(), '-'));
                    break;
                case "Multiplication":
                    theModel.setP3(Model.multiplication(theModel.getP1(), theModel.getP2()));
                    break;
                case "Division":
                    theModel.setP3(Model.division(theModel.getP1(), theModel.getP2()));
                    break;
                case "Derivative":
                    theModel.setP3(Model.derivative(theModel.getP1()));
                    break;
                case "Integration":
                    theModel.setP3(Model.integration(theModel.getP1()));
                    break;
            }
            theView.setResult(theModel.toString());
            theModel.resetPolynomials();
        }
    }

        public void verifyInputAndCreatePolynomial(String polynomial, char polynomialNumber) {
            Pattern p;
            Matcher m;
            try {
                String[] monomials = polynomial.split("(?=[+-])");
                for (String s : monomials) {
                    if (s.matches("([+-])\\b(\\b\\d+)?[xX]\\^(-?\\d+\\b)")) { //ax^b
                        p = Pattern.compile( "([+-])\\b(\\b\\d+)?[xX]\\^(-?\\d+\\b)" );
                        m = p.matcher(s);
                        m.find();
                        int sign = m.group(1).equals("-") ? -1 : 1;
                        String coef = m.group(2) != null ? m.group(2) : "1";
                        theModel.addMonomialToP(Double.parseDouble(coef) * sign, Integer.parseInt(m.group(3)), polynomialNumber);
                    } else if (s.matches("([+-])\\b?(\\b\\d+)?[xX](?!\\^)")) { //ax
                        p = Pattern.compile( "([+-])\\b?(\\b\\d+)?[xX](?!\\^)" );
                        m = p.matcher(s);
                        m.find();
                        int sign = m.group(1).equals("-") ? -1 : 1;
                        String coef = m.group(2) != null ? m.group(2) : "1";
                        theModel.addMonomialToP(Double.parseDouble(coef) * sign, 1, polynomialNumber);
                    } else if (s.matches("(?!\\^)([+-]?\\b\\d+)?(?![xX])")) { //a
                        p = Pattern.compile( "(?!\\^)([+-]?\\b\\d+)?(?![xX])");
                        m = p.matcher(s);
                        m.find();
                        theModel.addMonomialToP(Double.parseDouble(m.group(1)), 0, polynomialNumber);
                    } else if (s.matches("(\\b\\d+)?[xX]\\^(-?\\d+\\b)")) { //x^b
                        p = Pattern.compile( "(\\b\\d+)?[xX]\\^(-?\\d+\\b)" );
                        m = p.matcher(s);
                        m.find();
                        String coef = m.group(1) != null ? m.group(1) : "1";
                        theModel.addMonomialToP(Double.parseDouble(coef), Integer.parseInt(m.group(2)), polynomialNumber);
                    } else if (s.matches("(\\b\\d+)?[xX](?!\\^)")) {  //x
                        p = Pattern.compile("(\\b\\d+)?[xX](?!\\^)");
                        m = p.matcher(s);
                        m.find();
                        String coef = m.group(1) != null ? m.group(1) : "1";
                        theModel.addMonomialToP(Double.parseDouble(coef), 1, polynomialNumber);
                    } else {
                        throw new Exception();
                    }
                }
            } catch(Exception invalidInput) {
                theView.showError("Invalid input at Polynomial " + polynomialNumber + ". Should be of type 3x^2+4x+9.");
            }
        }

        //this method is used only for testing (that's why it was set to package private)
        //it has the purpose of reducing the size of p1 and returning it as a string by adding p1 with p2, which at the time of testing is an empty polynomial; the size reduction is being done in the addition algorithm;
        String getPolynomial1ForTesting() {
            theModel.setP3(Model.addition_subtraction(theModel.getP1(), theModel.getP2(), '+'));
            return theModel.toString();
        }

}
