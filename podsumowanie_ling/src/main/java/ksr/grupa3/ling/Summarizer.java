package ksr.grupa3.ling;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ksr.grupa3.fuzzy.FoodItem;
import ksr.grupa3.fuzzy.FuzzySet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Summarizer implements Agregator, Serializable {
    List<FuzzySet> summarizedSets = new ArrayList<>();
    List<Boolean> summarizedAnd = new ArrayList<>();

    public Summarizer(List<FuzzySet> summarizedSets, List<Boolean> summarizedAnd) {
        if (summarizedSets.size() == 1 && summarizedAnd.size() > 0) {
            throw new IllegalArgumentException("One set qualifier cannot have any operations");
        }

        if (summarizedSets.size() != summarizedAnd.size() + 1) {
            throw new IllegalArgumentException("Invalid number of sets and operations");
        }
        this.summarizedSets = summarizedSets;
        this.summarizedAnd = summarizedAnd;
    }

    public void addSet(FuzzySet set, boolean and) {
        summarizedSets.add(set);
        summarizedAnd.add(and);
    }

    // t-norma Gödela
    public double tNorm(Double a, Double b) {

        return Math.min(a, b);

    }

    // implikacja Łukasiewicza
    public static double implication(Double a, Double b) {

        return Math.min(1, 1-a+b);

    }

    public double DoM(FoodItem foodItem) {


        double DoM = summarizedSets.get(0).DoM(foodItem);

        if (summarizedSets.size() > 1) {
            for (int i = 0; i < summarizedAnd.size(); i++) {
                DoM = (summarizedAnd.get(i) ? tNorm(DoM, summarizedSets.get(i+1).DoM(foodItem)) : 1 - tNorm(1 - DoM, 1 - summarizedSets.get(i+1).DoM(foodItem)));
            }
        }

        return DoM;

    }

    public double cardinality(List<FoodItem> foodItems) {
        double cardinality = 0;
        for (FoodItem foodItem : foodItems) {
            cardinality += DoM(foodItem);
        }
        return cardinality;
    }

    

}
