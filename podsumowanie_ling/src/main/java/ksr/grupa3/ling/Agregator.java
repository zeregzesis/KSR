package ksr.grupa3.ling;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ksr.grupa3.fuzzy.FoodItem;
import ksr.grupa3.fuzzy.newSet;
import ksr.grupa3.fuzzy.newVariable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Agregator implements Serializable {
    List<newSet> setList = new ArrayList<>();
    List<Boolean> andList = new ArrayList<>();
    List<newVariable> variableList = new ArrayList<>();

    public Agregator(newSet qualifier) {
        this.setList.add(qualifier);
    }

    public Agregator(List<newSet> summarizedSets, List<Boolean> summarizedAnd, List<newVariable> summarizedVariables) {
        if (summarizedSets.size() == 1 && summarizedAnd.size() > 0) {
            throw new IllegalArgumentException("One set agregator cannot have any operations");
        }

        if (summarizedSets.size() != summarizedAnd.size() + 1) {
            throw new IllegalArgumentException("Invalid number of sets and operations");
        }

        if (summarizedVariables.size() != summarizedSets.size()) {
            throw new IllegalArgumentException("Invalid number of variables");
        }
        this.setList = summarizedSets;
        this.andList = summarizedAnd;
        this.variableList = summarizedVariables;
    }

    public void addSet(newSet set, boolean and) {
        setList.add(set);
        andList.add(and);
    }

    // t-norma Gödela
    public double tNorm(Double a, Double b) {

        return Math.min(a, b);

    }

    // implikacja Łukasiewicza (nie powinna chyba być tutaj, ale nie mam pomysłu gdzie ją dać żeby miało to sens)
    public static double implication(Double a, Double b) {

        return Math.min(1, 1-a+b);

    }

    public double DoM(FoodItem foodItem) {


        double dom = setList.get(0).DoM(foodItem);

        if (setList.size() > 1) {
            for (int i = 0; i < andList.size(); i++) {
                dom = (andList.get(i) ? tNorm(dom, setList.get(i+1).DoM(foodItem)) : 1 - tNorm(1 - dom, 1 - setList.get(i+1).DoM(foodItem)));
            }
        }

        return dom;

    }

    public List<FoodItem> UoD() {
        List<FoodItem> foodItems = new ArrayList<>();
        for (newSet set : setList) {
            foodItems.addAll(set.getFoodItems());
        }
        return foodItems.stream().distinct().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public double cardinality() {
        
        List<FoodItem> foodItems = UoD();

        double cardinality = 0;
        for (FoodItem foodItem : foodItems) {
            cardinality += DoM(foodItem);
        }

        return cardinality;
    }

    public double cardinality(List<FoodItem> foodItems) {


        double cardinality = 0;
        for (FoodItem foodItem : foodItems) {
            cardinality += DoM(foodItem);
        }

        return cardinality;
    }

    public int size() {
        return UoD().size();
    }

    public String getVariableName(int index) {
        return variableList.get(index).getName();
    }

    public String getVariableValue(int index) {
        return variableList.get(index).getValue();
    }

}