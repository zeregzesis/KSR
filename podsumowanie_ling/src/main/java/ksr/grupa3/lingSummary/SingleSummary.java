package ksr.grupa3.lingSummary;

import ksr.grupa3.fuzzy.FuzzySet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleSummary extends LingSummary {
    
    private LingQuantifier lingQuantifier;

    public SingleSummary(FuzzySet fuzzySet, LingQuantifier lingQuantifier) {
        super(fuzzySet);
        this.lingQuantifier = lingQuantifier;
    }
    
}
