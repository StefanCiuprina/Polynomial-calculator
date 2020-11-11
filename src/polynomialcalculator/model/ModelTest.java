package polynomialcalculator.model;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    @org.junit.jupiter.api.Test
    void testToString() {

        Polynomial p = new Polynomial();
        p.addMonomial(new Monomial(3, 2));
        p.addMonomial(new Monomial(2, 1));
        p.addMonomial(new Monomial(1, 0));
        Model theModel = new Model();
        theModel.setP3(p);
        assertEquals("3x^2+2x+1", theModel.toString());

        p = new Polynomial();
        p.addMonomial(new Monomial(3, 2));
        theModel.setP3(p);
        assertEquals("3x^2", theModel.toString());

        p = new Polynomial();
        p.addMonomial(new Monomial(3, 1));
        theModel.setP3(p);
        assertEquals("3x", theModel.toString());

        p = new Polynomial();
        p.addMonomial(new Monomial(1, 1));
        theModel.setP3(p);
        assertEquals("x", theModel.toString());

        p = new Polynomial();
        p.addMonomial(new Monomial(1, 5));
        theModel.setP3(p);
        assertEquals("x^5", theModel.toString());

        p = new Polynomial();
        p.addMonomial(new Monomial(1, 0));
        theModel.setP3(p);
        assertEquals("1", theModel.toString());

        p = new Polynomial();
        p.addMonomial(new Monomial(-2, 1));
        theModel.setP3(p);
        assertEquals("-2x", theModel.toString());

        Polynomial p2 = new Polynomial();
        p2.addMonomial(new Monomial(2, 0));
        Polynomial[] p3 = new Polynomial[2];
        p3[0] = p;
        p3[1] = p2;
        theModel.setP3(p3);
        assertEquals("Division: -2x Remainder: 2", theModel.toString());
    }

    @org.junit.jupiter.api.Test
    void addition_subtraction() {

        Polynomial p1 = new Polynomial();
        Polynomial p2 = new Polynomial();
        p1.addMonomial(new Monomial(5, 3));
        p1.addMonomial(new Monomial(-2, 2));
        p1.addMonomial(new Monomial(1, 1));
        p1.addMonomial(new Monomial(2, 0));

        p2.addMonomial(new Monomial(3, 3));
        p2.addMonomial(new Monomial(2, 2));
        p2.addMonomial(new Monomial(8, 0));

        Model theModel = new Model();
        theModel.setP3(Model.addition_subtraction(p1, p2, '+'));
        assertEquals("8x^3+x+10", theModel.toString());
        theModel.setP3(Model.addition_subtraction(p1, p2, '-'));
        assertEquals("2x^3-4x^2+x-6", theModel.toString());


    }

    @org.junit.jupiter.api.Test
    void multiplication() {

        Polynomial p1 = new Polynomial();
        Polynomial p2 = new Polynomial();
        p1.addMonomial(new Monomial(3, 2));
        p1.addMonomial(new Monomial(2, 1));
        p1.addMonomial(new Monomial(1, 0));

        p2.addMonomial(new Monomial(1, 3));
        p2.addMonomial(new Monomial(-1, 0));
        Model theModel = new Model();
        theModel.setP3(Model.multiplication(p1, p2));
        assertEquals("3x^5+2x^4-3x^2+x^3-2x-1", theModel.toString());

    }

    @org.junit.jupiter.api.Test
    void division() {
        Polynomial p1 = new Polynomial();
        Polynomial p2 = new Polynomial();
        p1.addMonomial(new Monomial(1, 4));
        p1.addMonomial(new Monomial(1, 0));

        p2.addMonomial(new Monomial(1, 1));
        p2.addMonomial(new Monomial(1, 0));
        Model theModel = new Model();
        theModel.setP3(Model.division(p1, p2));
        assertEquals("Division: x^3-x^2+x-1 Remainder: 2", theModel.toString());
    }

    @org.junit.jupiter.api.Test
    void derivative() {
        Polynomial p = new Polynomial();
        p.addMonomial(new Monomial(3, 2));
        p.addMonomial(new Monomial(2, 1));
        p.addMonomial(new Monomial(1, 0));
        Model theModel = new Model();
        theModel.setP3(Model.derivative(p));
        assertEquals("6x+2", theModel.toString());
    }

    @org.junit.jupiter.api.Test
    void integration() {
        Polynomial p = new Polynomial();
        p.addMonomial(new Monomial(3, 2));
        p.addMonomial(new Monomial(2, 1));
        p.addMonomial(new Monomial(1, 0));
        Model theModel = new Model();
        theModel.setP3(Model.integration(p));
        assertEquals("x^3+x^2+x", theModel.toString());
    }
}