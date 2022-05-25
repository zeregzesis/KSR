package ksr.grupa3.lingSummary;

import java.util.List;

import ksr.grupa3.fuzzy.FuzzySet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompoundSummary extends LingSummary {
    
    public CompoundSummary(FuzzySet fuzzySet, LingQuantifier lingQuantifier) {
        super(fuzzySet, lingQuantifier);
    }

    public Summary getThirdFormSummary(FuzzySet secondSet, LingQuantifier secondQuantifier) {
        return new Summary("", 0);
    }
    
}
