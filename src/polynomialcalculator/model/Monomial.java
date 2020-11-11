package polynomialcalculator.model;

public class Monomial {

    private double coefficient;
    private int power;

    public Monomial(double coefficient, int power) {
        this.coefficient = coefficient;
        this.power = power;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public int getPower() {
        return power;
    }

    //method that adds a specific value to the coefficient
    public void addToCoefficient(double value) {
        this.coefficient += value;
    }

    //method that negates the coefficient
    public void negateCoefficient() {
        this.coefficient = -coefficient;
    }
}
