package ksr.grupa3.lingSummary;

import ksr.grupa3.fuzzy.FuzzySet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LingSummary {

    private FuzzySet fuzzySet;

    public LingSummary(FuzzySet fuzzySet) {
        this.fuzzySet = fuzzySet;
    }
    
    public Summary getFirstFormSummary(){
        return new Summary("", 0);
    }

    public Summary getSecondFormSummary(){
        return new Summary("", 0);
    }

}
