package ksr.grupa3.lingSummary;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ksr.grupa3.fuzzy.FoodItem;
import ksr.grupa3.fuzzy.FuzzySet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class Summarizer {

    private List<FuzzySet> fuzzySets = new ArrayList<>();
    private List<Boolean> setAnd = new ArrayList<>();

    private List<FuzzySet> divisors = new ArrayList<>();
    private List<Boolean> divisorAnd = new ArrayList<>();

    private List<Qualifier> qualifiers;

    private LingQuantifier lingQuantifier;

    public static double tNorm(Double a, Double b) {

        return Math.min(a, b);

    }

    public void addFuzzySet(FuzzySet fuzzySet, boolean and) {

        fuzzySets.add(fuzzySet);
        setAnd.add(and);

    }

    public void addDivisor(FuzzySet fuzzySet, boolean and) {

        divisors.add(fuzzySet);
        if (divisors.size() > 1)
            divisorAnd.add(and);

    }

    public double DoM(FoodItem foodItem, boolean onlyDiv) {

        double setDoM = fuzzySets.get(0).DoM(foodItem);

        if (fuzzySets.size() > 1) {
            for (int i = 0; i < setAnd.size(); i++) {
                setDoM = (setAnd.get(i) ? tNorm(setDoM, fuzzySets.get(i+1).DoM(foodItem)) : 1 - tNorm(1 - setDoM, 1 - fuzzySets.get(i+1).DoM(foodItem)));
            }
        }

        if (divisors.size() == 0) {
            if (onlyDiv) {
                return 1;
            }
            return setDoM;
        }
        double divisorDoM = divisors.get(0).DoM(foodItem);

        if (divisors.size() > 1) {
            for (int i = 0; i < divisorAnd.size(); i++) {
                divisorDoM = (divisorAnd.get(i) ? tNorm(divisorDoM, divisors.get(i+1).DoM(foodItem)) : 1 - tNorm(1 - divisorDoM, 1 - divisors.get(i+1).DoM(foodItem)));
            }
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

        foodItems = qualifiers.get(0).filter(foodItems);

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

        String qualifier = qualifiers.get(0).getName() + (qualifiers.size() > 1 ? " compared to " + qualifiers.get(1).getToFind() : "") + " ";

        description += ".";
        
        String summary =
            lingQuantifier.getName().toLowerCase() +
            " of " +
            qualifier +
            division +
            description;

        List<Double> temp = new ArrayList<>();
        temp.add((double) BigDecimal.valueOf(QualityMeasure.T1(this, foodItems)).setScale(2, RoundingMode.HALF_UP).doubleValue());
        temp.add((double) BigDecimal.valueOf(QualityMeasure.T2(this, foodItems)).setScale(2, RoundingMode.HALF_UP).doubleValue());
        temp.add((double) BigDecimal.valueOf(QualityMeasure.T3(this, foodItems)).setScale(2, RoundingMode.HALF_UP).doubleValue());
        temp.add((double) BigDecimal.valueOf(QualityMeasure.T4(this, foodItems)).setScale(2, RoundingMode.HALF_UP).doubleValue());
        temp.add((double) BigDecimal.valueOf(QualityMeasure.T5(this, foodItems)).setScale(2, RoundingMode.HALF_UP).doubleValue());
        temp.add((double) BigDecimal.valueOf(QualityMeasure.T6(this, foodItems)).setScale(2, RoundingMode.HALF_UP).doubleValue());
        temp.add((double) BigDecimal.valueOf(QualityMeasure.T7(this, foodItems)).setScale(2, RoundingMode.HALF_UP).doubleValue());
        temp.add((double) BigDecimal.valueOf(QualityMeasure.T8(this, foodItems)).setScale(2, RoundingMode.HALF_UP).doubleValue());
        try {temp.add((double) BigDecimal.valueOf(QualityMeasure.T9(this, foodItems)).setScale(2, RoundingMode.HALF_UP).doubleValue());} catch (Exception e) {temp.add(-1.0);};
        try {temp.add((double) BigDecimal.valueOf(QualityMeasure.T10(this, foodItems)).setScale(2, RoundingMode.HALF_UP).doubleValue());} catch (Exception e) {temp.add(-1.0);};
        //temp.add((double)((BigDecimal)QualityMeasure.optimalSummaryMetric(this, foodItems, List.of(0.2, 0.2, 0.2, 0.2, 0.2) )));
        //temp.add((double)((BigDecimal)QualityMeasure.ExtendedOptimalSummaryMetric(this, foodItems, List.of(0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.1))));

        //return new Summary(summary, QualityMeasure.getMeasures(this, foodItems));

        return new Summary(summary, temp);

    }

}
