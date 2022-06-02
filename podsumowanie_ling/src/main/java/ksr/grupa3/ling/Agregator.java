package ksr.grupa3.ling;

import java.util.List;

import ksr.grupa3.fuzzy.FoodItem;

public interface Agregator {
    public double tNorm(Double a, Double b);
    public double DoM(FoodItem foodItem);
    public double cardinality(List<FoodItem> foodItems);
    

}
