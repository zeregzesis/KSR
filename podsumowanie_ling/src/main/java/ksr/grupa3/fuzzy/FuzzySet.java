package ksr.grupa3.fuzzy;

import java.util.ArrayList;
import java.util.List;

public class FuzzySet {
    private LingVariable variable;
    private String value;

    public double DoM(FoodItem foodItem) {
        return variable.getMemberFuncList().get(variable.getValues().indexOf(value)).getValue(foodItem.getProperty(variable.getFoodProperty()));
    }

    public double cardinality(List<FoodItem> foodItems) {
        double ret = 0.0;
        for (FoodItem foodItem : foodItems) {
            ret += DoM(foodItem);
        }
        return ret;
    }

    public List<FoodItem> support(List<FoodItem> foodItems) {
        List<FoodItem> ret = new ArrayList<>();
        for (FoodItem foodItem : foodItems) {
            if (DoM(foodItem) > 0) {
                ret.add(foodItem);
            }
        }
        return ret;
    }

    public int supportCount(List<FoodItem> foodItems) {
        return support(foodItems).size();
    }

    public List<FoodItem> alphaCut(List<FoodItem> foodItems, double alpha){
        List<FoodItem> ret = new ArrayList<>();
        for (FoodItem foodItem : foodItems) {
            if (DoM(foodItem) >= alpha) {
                ret.add(foodItem);
            }
        }
        return ret;
    }

}
