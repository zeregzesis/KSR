package ksr.grupa3.ling;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ksr.grupa3.fuzzy.FoodItem;
import ksr.grupa3.fuzzy.FuzzySet;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Summary {
    private Quantifier quantifier;
    private Subject subject;
    private Subject secondSubject;
    private Agregator qualifier;
    private Agregator summarizer;
    private SummaryType summaryType;

    public double T1() {

        if (qualifier.getSetList().size() == 0) 
            return quantifier.getValue(summarizer.cardinality() / summarizer.size());

        FuzzySet union = summarizer.asSet().setIntersect(qualifier.asSet());
        return quantifier.getValue(union.cardinality() / union.getFoodItems().size());

    }

    public double T2() {

        double ret = 1.0;

        for (FuzzySet fuzzySet : summarizer.getSetList()) {
            ret *= ( (fuzzySet.getMembershipFuction().getEnd() - fuzzySet.getMembershipFuction().getBegin()) / fuzzySet.getMembershipFuction().getUpperBound());
        }

        ret = 1 - Math.pow(ret, 1/summarizer.getSetList().size());

        return ret;

    }
    
    public double T3() {
        
        int topSum = 0;
        int bottomSum = 0;

        if (qualifier.getSetList().size() == 0)
            return summarizer.asSet().support().size() / summarizer.asSet().getFoodItems().size();
        
        for (FoodItem foodItem : summarizer.asSet().getFoodItems()) {

            int temp = (qualifier.DoM(foodItem) > 0) ? 1 : 0;

            topSum += (summarizer.DoM(foodItem) > 0 && temp == 1) ? 1 : 0;
            bottomSum += temp;

        }

        return (double) topSum / bottomSum;
        
    }


    public double T4(){

        double agregate = 1;
        for (FuzzySet fuzzySet : summarizer.getSetList()) {

            double r = 0;
            for (FoodItem foodItem : summarizer.asSet().getFoodItems()) {
                r += (fuzzySet.DoM(foodItem) > 0 ? 1 : 0);
            }

            r /= summarizer.asSet().getFoodItems().size();
            agregate *= r;

        }

        return Math.abs(agregate - T3());

    }

    public double T5(){

        return 2 * Math.pow(0.5, summarizer.getSetList().size());

    }

    public double optimalSummaryMetric(List<Double> weights) {

        if (weights.size() == 5 && Math.abs(1.0 - weights.stream().collect(Collectors.summingDouble(Double::doubleValue))) < 0.0001d) {
            return T1() * weights.get(0) +
                T2() * weights.get(1) +
                T3() * weights.get(2) +
                T4() * weights.get(3) +
                T5() * weights.get(4);
        }

        return -1;

    }

    public double T6() {

        double start = quantifier.getMemberFunc().getBegin();
        double end = quantifier.getMemberFunc().getEnd();

        return 1 - Math.abs(end - start) / quantifier.getMemberFunc().getUpperBound();

    }

    public double T7() {

        return 1 - quantifier.getMemberFunc().getIntegral() / quantifier.getMemberFunc().getUpperBound();

    }

    public double T8() {

        double agregate = 1;
        for (FuzzySet fuzzySet : summarizer.getSetList()) {
            agregate *= fuzzySet.getMembershipFuction().getIntegral() / fuzzySet.getMembershipFuction().getUpperBound();
            //agregate *= ((double) (fuzzySet.cardinality() / summarizer.size()));
        }

        return 1 - Math.pow(agregate, 1.0 / summarizer.getSetList().size());

    }

    public double T9() {

        if (qualifier.getSetList().size() == 0)
            return 0;

        double agregate = 1;
        for (FuzzySet qual : qualifier.getSetList()) {
            agregate *= qual.getMembershipFuction().getEnd() - qual.getMembershipFuction().getBegin() / qual.getMembershipFuction().getUpperBound();
            //agregate *= ((double) qual.getMembershipFuction().getEnd() - qual.getMembershipFuction().getBegin() / qual.getMembershipFuction().getUpperBound());
        }

        return 1 - Math.pow(agregate, 1.0 / qualifier.getSetList().size());

    }

    public double T10() {

        if (qualifier.getSetList().size() == 0)
            return 0;

        double agregate = 1;
        for (FuzzySet divisor : qualifier.getSetList()) {
            agregate *= divisor.getMembershipFuction().getIntegral() / divisor.getMembershipFuction().getUpperBound();
            //agregate *= ((double) divisor.cardinality() / qualifier.size());
        }

        return 1 - Math.pow(agregate, 1.0 / qualifier.getSetList().size());

    }

    public double T11(){

        if (qualifier.getSetList().size() == 0)
            return 1;

        return 2 * Math.pow(0.5, qualifier.getSetList().size());

    }

    public double ExtendedOptimalSummaryMetric(List<Double> weights) {

        if (weights.size() == 11 && Math.abs(1.0 - weights.stream().collect(Collectors.summingDouble(Double::doubleValue))) < 0.0001d) {
            return T1() * weights.get(0) +
                T2() * weights.get(1) +
                T3() * weights.get(2) +
                T4() * weights.get(3) +
                T5() * weights.get(4) +
                T6() * weights.get(5) +
                T7() * weights.get(6) +
                T8() * weights.get(7) +
                T9() * weights.get(8) +
                T10() * weights.get(9) +
                T11() * weights.get(10);
        }

        return -1;

    }

    public List<Double> getStandardMeasures(List<Double> weights) {

        List<Double> ret = new ArrayList<>();

        ret.add((double) BigDecimal.valueOf(T1()).setScale(2, RoundingMode.HALF_UP).doubleValue());

        ret.add((double) BigDecimal.valueOf(T2()).setScale(2, RoundingMode.HALF_UP).doubleValue());

        ret.add((double) BigDecimal.valueOf(T3()).setScale(2, RoundingMode.HALF_UP).doubleValue());

        ret.add((double) BigDecimal.valueOf(T4()).setScale(2, RoundingMode.HALF_UP).doubleValue());

        ret.add((double) BigDecimal.valueOf(T5()).setScale(2, RoundingMode.HALF_UP).doubleValue());

        ret.add((double) BigDecimal.valueOf(T6()).setScale(2, RoundingMode.HALF_UP).doubleValue());

        ret.add((double) BigDecimal.valueOf(T7()).setScale(2, RoundingMode.HALF_UP).doubleValue());

        ret.add((double) BigDecimal.valueOf(T8()).setScale(2, RoundingMode.HALF_UP).doubleValue());

        ret.add((double) BigDecimal.valueOf(T9()).setScale(2, RoundingMode.HALF_UP).doubleValue());

        ret.add((double) BigDecimal.valueOf(T10()).setScale(2, RoundingMode.HALF_UP).doubleValue());

        ret.add((double) BigDecimal.valueOf(T11()).setScale(2, RoundingMode.HALF_UP).doubleValue());

        if (weights.size() == 5) {
            ret.add((double) BigDecimal.valueOf(
                ret.get(0) * weights.get(0) +
                ret.get(1) * weights.get(1) +
                ret.get(2) * weights.get(2) +
                ret.get(3) * weights.get(3) +
                ret.get(4) * weights.get(4)).setScale(2, RoundingMode.HALF_UP).doubleValue());
        }

        if (weights.size() == 11) {

            ret.add((double) BigDecimal.valueOf(
                ret.get(0) * 0.2 +
                ret.get(1) * 0.2 +
                ret.get(2) * 0.2 +
                ret.get(3) * 0.2 +
                ret.get(4) * 0.2).setScale(2, RoundingMode.HALF_UP).doubleValue());

            ret.add((double) BigDecimal.valueOf(
                ret.get(0) * weights.get(0) +
                ret.get(1) * weights.get(1) +
                ret.get(2) * weights.get(2) +
                ret.get(3) * weights.get(3) +
                ret.get(4) * weights.get(4) +
                ret.get(5) * weights.get(5) +
                ret.get(6) * weights.get(6) +
                ret.get(7) * weights.get(7) +
                ret.get(8) * weights.get(8) +
                ret.get(9) * weights.get(9) +
                ret.get(10) * weights.get(10)).setScale(2, RoundingMode.HALF_UP).doubleValue());
        }
        
        return ret;

    }

    public List<Double> getCompoundMeasure() {

        List<FoodItem> items = summarizer.asSet().getFoodItems();

        List<FoodItem> firstSubjectItems = subject.filter(items);
        List<FoodItem> secondSubjectItems = secondSubject.filter(items);

        switch(summaryType) {
            case FIRST_FORM:
                double top = (1.0 / firstSubjectItems.size()) * summarizer.cardinality();
                double bottom =  (top + ((1.0 / secondSubjectItems.size()) * summarizer.cardinality()));
                double T = quantifier.getValue(top / bottom);
                T = (double) BigDecimal.valueOf(T).setScale(2, RoundingMode.HALF_UP).doubleValue();
                return List.of(T);
            case SECOND_FORM:
                // double combCard = 0;
                // for (FoodItem foodItem : secondSubjectItems) {
                //     combCard += Math.min(summarizer.DoM(foodItem), qualifier.DoM(foodItem));
                // }

                top = (1.0 / firstSubjectItems.size()) * summarizer.cardinality();
                bottom =  (top + ((1.0 / secondSubjectItems.size()) * summarizer.asSet().setIntersect(qualifier.asSet()).cardinality()));

                T = quantifier.getValue(top / bottom);
                T = (double) BigDecimal.valueOf(T).setScale(2, RoundingMode.HALF_UP).doubleValue();
                return List.of(T);
            case THIRD_FORM:
                // combCard = 0;
                // for (FoodItem foodItem : firstSubjectItems) {
                //     combCard += Math.min(summarizer.DoM(foodItem), qualifier.DoM(foodItem));
                // }

                top = (1.0 / firstSubjectItems.size()) * summarizer.asSet().setIntersect(qualifier.asSet()).cardinality();
                bottom =  (top + ((1.0 / secondSubjectItems.size()) * summarizer.cardinality()));

                T = quantifier.getValue(top / bottom);
                T = (double) BigDecimal.valueOf(T).setScale(2, RoundingMode.HALF_UP).doubleValue();
                return List.of(T);

            // nie mam pojęcia czy dobrze, trzeba będzie przetestować
            case FOURTH_FORM:
                double incl = 0;
                for (FoodItem foodItem : summarizer.asSet().getFoodItems()) {
                    incl += Summarizer.implication( (secondSubjectItems.indexOf(foodItem) != -1 ? summarizer.DoM(foodItem) : 0), (firstSubjectItems.indexOf(foodItem) != -1 ? summarizer.DoM(foodItem) : 0));
                }
                incl /= (double) (secondSubjectItems.size() + firstSubjectItems.size());
                return List.of((double) BigDecimal.valueOf(1.0 - incl).setScale(2, RoundingMode.HALF_UP).doubleValue());
            default:
                return new ArrayList<>();

        }
    }

    public String generateLinguisticSummary() {

        String quant = quantifier.getName();
        List<Double> measures = (summaryType == SummaryType.SINGLE_SUBJECT ? getStandardMeasures(List.of(1.0/11.0, 1.0/11.0, 1.0/11.0, 1.0/11.0, 1.0/11.0, 1.0/11.0, 1.0/11.0, 1.0/11.0, 1.0/11.0, 1.0/11.0, 1.0/11.0)) : getCompoundMeasure());

        //List<FoodItem> firstSubjectItems = subjects.get(0).filter(List.copyOf(foodItems));
        //List<FoodItem> secondSubjectItems = (summaryType != SummaryType.SINGLE_SUBJECT ? subjects.get(1).filter(List.copyOf(foodItems)) : new ArrayList<>());

        String qual = (qualifier.getSetList().size() > 0) ? "which have " + qualifier.getVariableValue(0) + " " + qualifier.getVariableName(0) : "";

        for (int i = 0; i < qualifier.getAndList().size(); i++) {

            if (qualifier.getAndList().get(i)) qual += " and ";
            else qual += " or ";

            qual += qualifier.getVariableValue(i+1) + " " + qualifier.getVariableName(i+1);

        }

        String summ = "have " + summarizer.getVariableValue(0) + " " + summarizer.getVariableName(0);

        for (int i = 0; i < summarizer.getAndList().size(); i++) {

            if (summarizer.getAndList().get(i)) summ += " and ";
            else summ += " or ";

            summ += summarizer.getVariableValue(i+1) + " " + summarizer.getVariableName(i+1);

        }
        summ += ".";

        String subj = " of " + subject.getName();
        String secondSubj = (summaryType != SummaryType.SINGLE_SUBJECT ? secondSubject.getName() : "");
        String connector = (summaryType == SummaryType.SINGLE_SUBJECT ? "" : (summaryType == SummaryType.FOURTH_FORM ? " than " : ", comparing to "));
        String summary = "";

        summary = quant + subj + (summaryType == SummaryType.SINGLE_SUBJECT || summaryType == SummaryType.THIRD_FORM ? " " + qual : connector + secondSubj) + ( summaryType == SummaryType.SECOND_FORM ? " " + qual : (summaryType == SummaryType.THIRD_FORM ? connector + secondSubj : "")) + " " + summ;

        return summary + measures.toString();

    }
}
