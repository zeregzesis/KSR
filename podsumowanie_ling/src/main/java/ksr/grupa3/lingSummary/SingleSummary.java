package ksr.grupa3.lingSummary;

import java.util.List;

import ksr.grupa3.fuzzy.FoodItem;
import ksr.grupa3.fuzzy.FuzzySet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleSummary implements LingSummary {

    private FuzzySet fuzzySet;
    private LingQuantifier lingQuantifier;
    
    

    public SingleSummary(FuzzySet fuzzySet, LingQuantifier lingQuantifier) {
        this.fuzzySet = fuzzySet;
        this.lingQuantifier = lingQuantifier;
    }

    public Summary getFirstFormSummary(List<FoodItem> foodItems) {

        double maxDoT = foodItems.size();
        double actualDoT = 0;

        for (FoodItem foodItem : foodItems) {
            actualDoT += fuzzySet.DoM(foodItem);
        }

        double DoT = 0.0;

        if (lingQuantifier.getIsAbsolute()){
            DoT = QualityMeasure.T1(actualDoT, 1, lingQuantifier.getMemberFunc());
        }
        else {
            DoT = QualityMeasure.T1(actualDoT, maxDoT, lingQuantifier.getMemberFunc());
        }

        //double summary = lingQuantifier.compatibility(actualDoT / maxDoT);

        return new Summary("", 0);
        //return new Summary(
    }

    public Summary getSecondFormSummary(List<FoodItem> foodItems, FuzzySet secondSet){
        return new Summary("", 0);
    }
    
}
