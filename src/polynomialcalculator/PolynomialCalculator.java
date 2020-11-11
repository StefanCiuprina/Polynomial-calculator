package polynomialcalculator;

import polynomialcalculator.controller.Controller;
import polynomialcalculator.model.Model;
import polynomialcalculator.view.View;

public class PolynomialCalculator {

    public static void main(String[] args) {
        new Controller(new View(), new Model());
    }

}
