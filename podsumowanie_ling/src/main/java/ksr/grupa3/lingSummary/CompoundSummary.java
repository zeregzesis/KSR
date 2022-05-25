package ksr.grupa3.lingSummary;

import java.util.List;

import ksr.grupa3.fuzzy.FuzzySet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompoundSummary extends LingSummary {
    
    private List<LingQuantifier> lingQuantifiers;

    public CompoundSummary(FuzzySet fuzzySet, List<LingQuantifier> lingQuantifiers) {
        super(fuzzySet);
        this.lingQuantifiers = lingQuantifiers;
    }

    public Summary getThirdFormSummary(FuzzySet secondSet) {
        return new Summary("", 0);
    }
    
}
