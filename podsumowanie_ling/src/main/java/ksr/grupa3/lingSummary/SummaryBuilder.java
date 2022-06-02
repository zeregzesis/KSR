package ksr.grupa3.lingSummary;

import java.util.List;

import ksr.grupa3.fuzzy.FuzzySet;

public class SummaryBuilder {

    public static Summarizer build(LingQuantifier lingQuantifier, List<FuzzySet> fuzzySets, List<Boolean> setAnd, List<FuzzySet> divisors, List<Boolean> divisorAnd, List<Qualifier> qualifiers) {
        if (
            (fuzzySets.size() == 1 && setAnd.size() != 0) || 
            (fuzzySets.size() - 1 != setAnd.size()) ||
            (divisors.size() == 1 && divisorAnd.size() != 0) ||
            (divisors.size() != 0 && divisors.size() - 1 != divisorAnd.size()) ||
            (qualifiers.size() > 2 || qualifiers.size() < 1)
            )
        {
            throw new IllegalArgumentException("Invalid arguments");
        }


        Summarizer ret = new Summarizer();
        ret.setLingQuantifier(lingQuantifier);
        ret.setFuzzySets(fuzzySets);
        ret.setSetAnd(setAnd);
        ret.setDivisors(divisors);
        ret.setDivisorAnd(divisorAnd);
        ret.setQualifiers(qualifiers);

        return ret;

    }
    
}
