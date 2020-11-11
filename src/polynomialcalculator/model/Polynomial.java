package polynomialcalculator.model;

import java.util.ArrayList;
import java.util.List;

public class Polynomial {

    private int degree;
    private List<Monomial> monomials;

    public Polynomial() { //this constructor will only create an empty polynomial, to which monomials will be added later
        this.degree = 0;
        this.monomials = new ArrayList<>();
    }

    public Polynomial(Polynomial p1) { //this constructor creates a copy of the Polynomial given as argument
        this.degree = p1.getDegree();
        this.monomials = p1.getMonomials();
    }

    //method that adds a monomial to the current polynomial, making sure they will be sorted in descending order of the power
    public void addMonomial(Monomial monomial) {
        if(monomial.getPower() > degree) {
            degree = monomial.getPower();
        }

        if(monomials.isEmpty()) {
            monomials.add(monomial);
        } else {
            for (int i = monomials.size() - 1; i >= 0; i--) { //i used an iterator integer instead of foreach since i need to use the iterator
                if (monomials.get(i).getPower() < monomial.getPower()) {
                    monomials.add(i, monomial);
                    break;
                } else if (i == 0) {
                    monomials.add(monomial);
                }
            }
        }
    }

    //method that reduces the size of the polynomial (ex: turns x+x into 2x)
    public void reduceSize() {

        //i used 2 indices in these 2 fors instead of foreach since i need to use the indices; more on this in the documentation (section 4)
        for(int i = 0; i < monomials.size(); i++) {
            for(int j = i+1; j < monomials.size(); j++) {
                if(monomials.get(i).getPower() == monomials.get(j).getPower()) {
                    monomials.get(i).addToCoefficient(monomials.get(j).getCoefficient());
                    monomials.remove(j);
                    j--;
                }
            }
            if(monomials.get(i).getCoefficient() == 0) {
                monomials.remove(i);
                i--;
            }
        }

    }

    //method that negates all coefficients
    public void negate() {
        for(Monomial m : monomials) {
            m.negateCoefficient();
        }
    }

    public int getDegree() {
        return degree;
    }

    //instead of just returning the list of monomials, this method returns a copy to avoid unwanted changes
    public List<Monomial> getMonomials() {
        List<Monomial> newList = new ArrayList<>();
        for(Monomial m : monomials) {
            newList.add(new Monomial(m.getCoefficient(), m.getPower()));
        }
        return newList;
    }

    public Monomial getFirstMonomial() {
        return monomials.get(0);
    }
}
