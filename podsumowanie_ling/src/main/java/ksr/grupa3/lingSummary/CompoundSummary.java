package ksr.grupa3.lingSummary;

import java.util.List;

import ksr.grupa3.fuzzy.FoodItem;
import ksr.grupa3.fuzzy.oldSet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompoundSummary implements LingSummary {

    private oldSet fuzzySet;
    private LingQuantifier lingQuantifier;
    
    public CompoundSummary(oldSet fuzzySet, LingQuantifier lingQuantifier) {
        this.fuzzySet = fuzzySet;
        this.lingQuantifier = lingQuantifier;
    }

    

    public Summary getFirstFormSummary(List<FoodItem> foodItems) {
        return new Summary("", null);
    }

    public Summary getSecondFormSummary(List<FoodItem> foodItems, oldSet secondSet){
        return new Summary("", null);
    }

    public Summary getThirdFormSummary(List<FoodItem> foodItems, oldSet secondSet) {
        return new Summary("", null);
    }
    
}
