package ksr.grupa3.lingSummary;

import java.util.List;

import ksr.grupa3.fuzzy.FuzzySet;

public class Summarizer {

    public FuzzySet fuzzySet;


    public Summarizer intersect(Summarizer second){
        return new Summarizer(this.fuzzySet.setIntersect(second.fuzzySet));

    }

    public Summarizer(FuzzySet fuzzySet)
    {
        this.fuzzySet= fuzzySet;

    }

    public static Summary combineSummaries(List<Summary> summaries) {
        return new Summary("", 0);
    }
}
