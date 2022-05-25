package ksr.grupa3.lingSummary;

import ksr.grupa3.fuzzy.FuzzySet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LingQuantifier {
    private String name;
    private FuzzySet fuzzySet;
    private QuantifierType quantifierType;

    public double compatibility(double value){
        return 0;
    }
}
