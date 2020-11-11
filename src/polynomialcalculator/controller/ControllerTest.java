package polynomialcalculator.controller;

import org.junit.jupiter.api.Test;
import polynomialcalculator.model.Model;
import polynomialcalculator.view.View;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void verifyInputAndCreatePolynomial() {

        Controller controller = new Controller(new View(), new Model());
        controller.verifyInputAndCreatePolynomial("3x^2", '1');
        controller.verifyInputAndCreatePolynomial("-3x^2", '1');
        controller.verifyInputAndCreatePolynomial("3", '1');
        controller.verifyInputAndCreatePolynomial("-3", '1');
        controller.verifyInputAndCreatePolynomial("x^2", '1');
        controller.verifyInputAndCreatePolynomial("-x^2", '1');
        controller.verifyInputAndCreatePolynomial("x", '1');
        controller.verifyInputAndCreatePolynomial("-x", '1');
        controller.verifyInputAndCreatePolynomial("3x", '1');
        controller.verifyInputAndCreatePolynomial("-3x", '1');
        controller.verifyInputAndCreatePolynomial("10", '1');
        assertEquals("10", controller.getPolynomial1ForTesting());


    }
}