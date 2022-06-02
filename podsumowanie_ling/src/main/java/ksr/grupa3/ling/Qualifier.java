package ksr.grupa3.ling;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ksr.grupa3.fuzzy.FoodItem;
import ksr.grupa3.fuzzy.FuzzySet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Qualifier implements Agregator, Serializable {
    List<FuzzySet> qualifierSets = new ArrayList<>();
    List<Boolean> qualifierAnd = new ArrayList<>();

    public Qualifier(List<FuzzySet> qualifierSets, List<Boolean> qualifierAnd) {
        if (qualifierSets.size() == 1 && qualifierAnd.size() > 0) {
            throw new IllegalArgumentException("One set qualifier cannot have any operations");
        }

        if (qualifierSets.size() != qualifierAnd.size() + 1) {
            throw new IllegalArgumentException("Invalid number of sets and operations");
        }
        this.qualifierSets = qualifierSets;
        this.qualifierAnd = qualifierAnd;
    }

    public double tNorm(Double a, Double b) {

        return Math.min(a, b);

    }

    public double DoM(FoodItem foodItem) {


        double DoM = qualifierSets.get(0).DoM(foodItem);

        if (qualifierSets.size() > 1) {
            for (int i = 0; i < qualifierAnd.size(); i++) {
                DoM = (qualifierAnd.get(i) ? tNorm(DoM, qualifierSets.get(i+1).DoM(foodItem)) : 1 - tNorm(1 - DoM, 1 - qualifierSets.get(i+1).DoM(foodItem)));
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
