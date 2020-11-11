package polynomialcalculator.model;

import java.text.DecimalFormat;

public class Model {

    //input polynomials
    private Polynomial p1;
    private Polynomial p2;

    //output polynomials
    private Polynomial p3;
    private Polynomial p3RemainderOfDivision;

    //will be used to let the program know if it needs to display the remainder of division as well (alongside the result)
    private boolean hasRemainder;

    public Model() { //creates 2 empty polynomials and sets hasRemainder to false
        this.p1 = new Polynomial();
        this.p2 = new Polynomial();
        this.hasRemainder = false;
    }

    public Polynomial getP1() {
        return p1;
    }

    public Polynomial getP2() {
        return p2;
    }

    public void resetPolynomials() { //to make multiple operations one after another, the polynomials will need to be erased and brought to the initial form
        this.p1 = new Polynomial();
        this.p2 = new Polynomial();
        this.hasRemainder = false;
    }

    //method that adds a monomial to a specific polynomial, given by the polynomialNumber argument
    public void addMonomialToP(double coef, int power, char polynomialNumber) throws Exception {
        switch(polynomialNumber) {
            case '1':
                p1.addMonomial(new Monomial(coef, power));
                break;
            case '2':
                p2.addMonomial(new Monomial(coef, power));
                break;
            default:
                throw new Exception();
        }
    }

    //converts to String the result that will have to be displayed to the user (based on hasRemainder)
    @Override
    public String toString() {
        if(hasRemainder) {
            return "Division: " + buildString(p3) + " Remainder: " + buildString(p3RemainderOfDivision);
        } else {
            return buildString(p3);
        }

    }

    //builds the String for the toString() method; is private since this method shouldn't be used somewhere else in this program;
    private String buildString(Polynomial p) {
        StringBuilder s = new StringBuilder();
        DecimalFormat format = new DecimalFormat("0.##");
        boolean first = true;
        for(Monomial m : p.getMonomials()) {
            double coef = m.getCoefficient();
            int power = m.getPower();
            if ((coef > 0) && (!first)) { s.append("+"); }
            if(coef != 1) {
                if(power > 1) { s.append((coef != -1 ? format.format(coef) : "-") + "x^" + power);
                } else if(power == 1) { s.append((coef != -1 ? format.format(coef) : "-") + "x");
                } else { s.append(format.format(coef));
                }
            } else {
                if(power > 1) { s.append("x^" + power);
                } else if(power == 1) { s.append("x");
                } else { s.append("1");
                }
            }
            first = false;
        }
        return s.toString();
    }

    public void setP3(Polynomial p3) {
        this.p3 = p3;
    }

    //this method overloads the setter in case we also have a remainder that has to be set (a polynomial of size 2 will be given as argument)
    public void setP3(Polynomial[] p3) {
        this.p3 = p3[0];
        this.p3RemainderOfDivision = p3[1];
        this.hasRemainder = true;
    }

    ///the following code is the implementation of the algorithms performing the operations; they are explained in detail in the documentation (section 3.5 and section 4)

    public static Polynomial addition_subtraction(Polynomial p1, Polynomial p2, char operator) {
        Polynomial result =  new Polynomial(p1);
        Polynomial p2Copy = new Polynomial(p2);
        if(operator == '-') {
            p2Copy.negate();
        }
        for(Monomial m : p2Copy.getMonomials()) {
            result.addMonomial(m);
        }
        result.reduceSize();
        return result;
    }

    public static Polynomial multiplication(Polynomial p1, Polynomial p2) {
        Polynomial product = new Polynomial();
        for(Monomial m1 : p1.getMonomials()) {
            for(Monomial m2 : p2.getMonomials()) {
                double coef = m1.getCoefficient() * m2.getCoefficient();
                int power = m1.getPower() + m2.getPower();
                product.addMonomial(new Monomial(coef, power));
            }
        }
        product.reduceSize();
        return product;
    }

    public static Polynomial[] division(Polynomial p1, Polynomial p2) {
        Polynomial division = new Polynomial();
        Polynomial temp = new Polynomial(p1);
        double coefDiv = (p1.getFirstMonomial().getCoefficient() / p2.getFirstMonomial().getCoefficient());
        int powDif = p1.getFirstMonomial().getPower() - p2.getFirstMonomial().getPower();
        while(powDif >= 0) {
            division.addMonomial(new Monomial(coefDiv, powDif));
            Polynomial toMultiply = new Polynomial();
            toMultiply.addMonomial(new Monomial(coefDiv, powDif));
            Polynomial toSubtract = multiplication(p2, toMultiply);
            temp = addition_subtraction(temp, toSubtract, '-');
            if(temp.getMonomials().isEmpty())
                break;
            coefDiv = (temp.getFirstMonomial().getCoefficient() / p2.getFirstMonomial().getCoefficient());
            powDif = temp.getFirstMonomial().getPower() - p2.getFirstMonomial().getPower();
        }
        Polynomial[] result = new Polynomial[2];
        result[0] = division;
        result[1] = temp;
        return result;
    }

    public static Polynomial derivative(Polynomial p) {
        Polynomial derivative = new Polynomial();
        for(Monomial m : p.getMonomials()) {
            if(m.getPower() != 0) {
                double coef = m.getCoefficient() * m.getPower();
                int pow = m.getPower() - 1;
                derivative.addMonomial(new Monomial(coef, pow));
            }
        }
        return derivative;
    }

    public static Polynomial integration(Polynomial p) {
        Polynomial integration = new Polynomial();
        for(Monomial m : p.getMonomials()) {
            double coef = m.getCoefficient() / (m.getPower() + 1);
            int pow = m.getPower() + 1;
            integration.addMonomial(new Monomial(coef, pow));
        }
        return integration;
    }

}
