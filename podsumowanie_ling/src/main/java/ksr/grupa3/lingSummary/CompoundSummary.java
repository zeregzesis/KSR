package ksr.grupa3.lingSummary;

import java.util.List;

import ksr.grupa3.fuzzy.FoodItem;
import ksr.grupa3.fuzzy.FuzzySet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompoundSummary implements LingSummary {

    private FuzzySet fuzzySet;
    private LingQuantifier lingQuantifier;
    
    public CompoundSummary(FuzzySet fuzzySet, LingQuantifier lingQuantifier) {
        this.fuzzySet = fuzzySet;
        this.lingQuantifier = lingQuantifier;
    }

    

    public Summary getFirstFormSummary(List<FoodItem> foodItems) {
        return new Summary("", 0);
    }

    public Summary getSecondFormSummary(List<FoodItem> foodItems, FuzzySet secondSet){
        return new Summary("", 0);
    }

    public Summary getThirdFormSummary(FuzzySet secondSet, LingQuantifier secondQuantifier) {
        return new Summary("", 0);
    }
    
}
