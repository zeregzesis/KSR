package ksr.grupa3.lingSummary;

import java.util.List;

import ksr.grupa3.fuzzy.FoodItem;
import ksr.grupa3.fuzzy.oldSet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleSummary implements LingSummary {

    private oldSet fuzzySet;
    private LingQuantifier lingQuantifier;
    
    

    public SingleSummary(oldSet fuzzySet, LingQuantifier lingQuantifier) {

        this.fuzzySet = fuzzySet;
        this.lingQuantifier = lingQuantifier;

    }

    public Summary getFirstFormSummary(List<FoodItem> foodItems) {

        String summary =
            lingQuantifier.getName().toLowerCase() +
            " of all food products have " + fuzzySet.getValue().toLowerCase() +
            " " + fuzzySet.getVariable().getName().toLowerCase()
            + ".";

        summary = summary.substring(0, 1).toUpperCase() + summary.substring(1);

        //List<Double> measures = QualityMeasure.getMeasures();

        //return new Summary(summary, measures);

        return new Summary(summary, null);

    }

    public Summary getSecondFormSummary(List<FoodItem> foodItems, oldSet secondSet){
        
        String summary =
            lingQuantifier.getName().toLowerCase() +
            " of all food products, which have " +
            secondSet.getValue().toLowerCase() + " " +
            secondSet.getVariable().getName().toLowerCase() +
            ", have " + fuzzySet.getValue().toLowerCase() + " " +
            fuzzySet.getVariable().getName().toLowerCase() + ".";

            summary = summary.substring(0, 1).toUpperCase() + summary.substring(1);

            //List<Double> measures = QualityMeasure.getMeasures();

            //return new Summary(summary, measures);
            
            return new Summary(summary, null);

    }

    public Summary getThirdFormSummary(List<FoodItem> foodItems, oldSet secondSet){

        return null;
        
    }
    
}
