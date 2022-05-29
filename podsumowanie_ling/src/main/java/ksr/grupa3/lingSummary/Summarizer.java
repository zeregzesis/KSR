package ksr.grupa3.lingSummary;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ksr.grupa3.fuzzy.FoodItem;
import ksr.grupa3.fuzzy.FuzzySet;
import lombok.Getter;
@Getter
public class Summarizer {

    private List<FuzzySet> fuzzySets = new ArrayList<>();
    private List<Boolean> setAnd = new ArrayList<>();
    private List<FuzzySet> divisors = new ArrayList<>();
    private List<Boolean> divisorAnd = new ArrayList<>();

    private LingQuantifier lingQuantifier;

    public Summarizer(LingQuantifier lingQuantifier, FuzzySet fuzzySet) {

        this.lingQuantifier = lingQuantifier;
        this.fuzzySets.add(fuzzySet);

    }

    public Summarizer(LingQuantifier lingQuantifier, List<FuzzySet> fuzzySets, List<Boolean> setAnd) {

        this.lingQuantifier = lingQuantifier;
        this.fuzzySets = fuzzySets;
        this.setAnd = setAnd;

    }

    public void addFuzzySet(FuzzySet fuzzySet, boolean and) {

        fuzzySets.add(fuzzySet);
        setAnd.add(and);

    }

    public void addDivisor(FuzzySet fuzzySet, boolean and) {

        divisors.add(fuzzySet);
        divisorAnd.add(and);

    }

    public double DoM(FoodItem foodItem, boolean onlyDiv) {

        double setDoM = fuzzySets.get(0).DoM(foodItem);
        for (int i = 0; i < setAnd.size(); i++) {
            setDoM = (setAnd.get(i) ? Math.min(setDoM, fuzzySets.get(i+1).DoM(foodItem)) : Math.max(setDoM, fuzzySets.get(i+1).DoM(foodItem)));
        }

        if (divisors.size() == 0) {
            return setDoM;
        }

        double divisorDoM = divisors.get(0).DoM(foodItem);
        for (int i = 0; i < divisorAnd.size(); i++) {
            divisorDoM = (divisorAnd.get(i) ? Math.min(divisorDoM, divisors.get(i+1).DoM(foodItem)) : Math.max(divisorDoM, divisors.get(i+1).DoM(foodItem)));
        }
        
        return (onlyDiv ? divisorDoM : Math.min(divisorDoM, setDoM));

    }

    public double cardinality(List<FoodItem> foodItems) {

        double ret = 0.0;
        for (FoodItem foodItem : foodItems) {
            ret += DoM(foodItem, false);
        }
        return ret;

    }

    public double divisorCardinality(List<FoodItem> foodItems) {

        double ret = 0.0;
        for (FoodItem foodItem : foodItems) {
            ret += DoM(foodItem, true);
        }
        return ret;

    }

    public List<FoodItem> summarizedSupport(List<FoodItem> foodItems) {

        Set<FoodItem> support = new LinkedHashSet<>(fuzzySets.get(0).support(foodItems));

        for (int i = 0; i < setAnd.size(); i++) {

            if (setAnd.get(i)) {

                support = support.stream()
                .distinct()
                .filter(fuzzySets.get(i+1).support(foodItems)::contains)
                .collect(Collectors.toSet());

            }
            else support.addAll(fuzzySets.get(i+1).support(foodItems));

        }

        return new ArrayList<>(support);

    }

    public int summarizedSupportCount(List<FoodItem> foodItems) {

        return summarizedSupport(foodItems).size();

    }

    public Summary constructSummary(List<FoodItem> foodItems) {

        String division = (divisors.size() > 0) ? ", which have " + divisors.get(0).getValue() + " " + divisors.get(0).getVariable().getName() : "";

        for (int i = 0; i < divisorAnd.size(); i++) {

            if (divisorAnd.get(i)) division += "and ";
            else division += "or ";

            division += divisors.get(i+1).getValue() + " " + divisors.get(i+1).getVariable().getName();

        }

        division += (division.length() > 0) ? ", " : "";

        String description = "have " + fuzzySets.get(0).getValue() + " " + fuzzySets.get(0).getVariable().getName();

        for (int i = 0; i < setAnd.size(); i++) {

            if (setAnd.get(i)) description += "and ";
            else description += "or ";

            description += fuzzySets.get(i+1).getValue() + " " + fuzzySets.get(i+1).getVariable().getName();

        }

        description += ".";
        
        String summary =
            lingQuantifier.getName().toLowerCase() +
            " of all food products " +
            division +
            description;

        return new Summary(summary, QualityMeasure.getMeasures(this, foodItems));

    }

}
