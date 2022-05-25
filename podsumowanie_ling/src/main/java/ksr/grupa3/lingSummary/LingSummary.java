package ksr.grupa3.lingSummary;

import java.util.List;

import ksr.grupa3.fuzzy.FoodItem;
import ksr.grupa3.fuzzy.FuzzySet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LingSummary {

    private FuzzySet fuzzySet;
    private LingQuantifier lingQuantifier;

    public LingSummary(FuzzySet fuzzySet, LingQuantifier lingQuantifier) {
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

        if (lingQuantifier.getQuantifierType().getIsAbsolute()){
            DoT = QualityMeasure.T1(actualDoT, 1, lingQuantifier.getQuantifierType().getMemberFunc());
        }
        else {
            DoT = QualityMeasure.T1(actualDoT, maxDoT, lingQuantifier.getQuantifierType().getMemberFunc());
        }

        //double summary = lingQuantifier.compatibility(actualDoT / maxDoT);

        return new Summary("", 0);
        //return new Summary(
    }

    public Summary getSecondFormSummary(FuzzySet secondSet){
        return new Summary("", 0);
    }

}
