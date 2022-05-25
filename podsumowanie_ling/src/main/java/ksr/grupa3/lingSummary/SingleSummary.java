package ksr.grupa3.lingSummary;

import ksr.grupa3.fuzzy.FuzzySet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleSummary extends LingSummary {
    
    

    public SingleSummary(FuzzySet fuzzySet, LingQuantifier lingQuantifier) {
        super(fuzzySet, lingQuantifier);
    }
    
}
