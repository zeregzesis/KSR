package ksr.grupa3.lingSummary;

import java.util.List;

import ksr.grupa3.fuzzy.FoodItem;
import ksr.grupa3.fuzzy.FuzzySet;

public interface LingSummary {

    public Summary getFirstFormSummary(List<FoodItem> foodItems);

    public Summary getSecondFormSummary(List<FoodItem> foodItems, FuzzySet secondSet);

    public Summary getThirdFormSummary(List<FoodItem> foodItems, FuzzySet secondSet);

    //public Summary getFourthFormSummary(List<FoodItem> foodItems, FuzzySet secondSet);   
    

}
