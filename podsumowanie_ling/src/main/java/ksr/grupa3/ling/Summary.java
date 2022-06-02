package ksr.grupa3.ling;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ksr.grupa3.fuzzy.FoodItem;
import ksr.grupa3.fuzzy.FuzzySet;
import ksr.grupa3.fuzzy.SubFunc;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Summary {
    private Quantifier quantifier;
    private List<Subject> subjects;
    private Qualifier qualifier;
    private Summarizer summarizer;
    private SummaryType summaryType;

    public double T1(List<FoodItem> foodItems) {

        double card = summarizer.cardinality(foodItems);

        if (qualifier.getQualifierSets().size() == 0) 
            return quantifier.getValue(card / (quantifier.getIsAbsolute() ? 1 :  foodItems.size()));

        double qualCard = qualifier.cardinality(foodItems);
        return quantifier.getValue(Math.min(card, qualCard) / qualCard);

    }

    public double T2(List<FoodItem> foodItems) {

        double ret = 1.0;

        for (FuzzySet fuzzySet : summarizer.getSummarizedSets()) {
            ret *= ( (double) fuzzySet.supportCount(foodItems) / foodItems.size());
        }

        ret = 1 - Math.pow(ret, 1/summarizer.getSummarizedSets().size());

        return ret;

    }
    
    public double T3(List<FoodItem> foodItems) {
        
        int topSum = 0;
        int bottomSum = 0;
        
        for (FoodItem foodItem : foodItems) {

            int temp = (qualifier.getQualifierSets().size() == 0 || qualifier.DoM(foodItem) > 0) ? 1 : 0;

            topSum += (summarizer.DoM(foodItem) > 0 && temp == 1) ? 1 : 0;
            bottomSum += temp;

        }

        return (double) topSum / bottomSum;
        
    }


    public double T4(List<FoodItem> foodItems){

        double agregate = 1;
        for (FuzzySet fuzzySet : summarizer.getSummarizedSets()) {

            double r = 0;
            for (FoodItem foodItem : foodItems) {
                r += (fuzzySet.DoM(foodItem) > 0 ? 1 : 0);
            }

            r /= foodItems.size();
            agregate *= r;

        }

        return Math.abs(agregate - T3(foodItems));

    }

    public double T5(List<FoodItem> foodItems){

        return 2 * Math.pow(0.5, summarizer.getSummarizedSets().size());

    }

    public double optimalSummaryMetric(List<FoodItem> foodItems, List<Double> weights) {

        if (weights.size() == 5 && Math.abs(1.0 - weights.stream().collect(Collectors.summingDouble(Double::doubleValue))) < 0.0001d) {
            return T1(foodItems) * weights.get(0) +
                T2(foodItems) * weights.get(1) +
                T3(foodItems) * weights.get(2) +
                T4(foodItems) * weights.get(3) +
                T5(foodItems) * weights.get(4);
        }

        return -1;

    }

    public double T6(List<FoodItem> foodItems) {

        double start = quantifier.getMemberFunc().getSubFuncs().get(0).getEnd();
        double end = quantifier.getMemberFunc().getSubFuncs().get(quantifier.getMemberFunc().getSubFuncs().size() - 1).getStart();

        return 1 - Math.abs(end - start) / (quantifier.getIsAbsolute() ? foodItems.size() : 1);

    }

    public double T7(List<FoodItem> foodItems) {

        double intSum = 0;

        List<SubFunc> temp = quantifier.getMemberFunc().getSubFuncs();

        for (int i = 1; i < temp.size() - 1; i++) {
            intSum += temp.get(i).getIntegral();
        }

        return 1 - (double) (Math.abs(intSum) / (double) (quantifier.getIsAbsolute() ? foodItems.size() : 1));

    }

    public double T8(List<FoodItem> foodItems) {

        double agregate = 1;
        for (FuzzySet fuzzySet : summarizer.getSummarizedSets()) {
            agregate *= ((double) (fuzzySet.cardinality(foodItems) / foodItems.size()));
        }

        return 1 - Math.pow(agregate, 1.0 / summarizer.getSummarizedSets().size());

    }

    public double T9(List<FoodItem> foodItems) {

        if (qualifier.getQualifierSets().size() == 0)
            return -1;

        double agregate = 1;
        for (FuzzySet qual : qualifier.getQualifierSets()) {
            agregate *= ((double) qual.supportCount(foodItems) / foodItems.size());
        }

        return 1 - Math.pow(agregate, 1.0 / qualifier.getQualifierSets().size());

    }

    public double T10(List<FoodItem> foodItems) {

        if (qualifier.getQualifierSets().size() == 0)
            return -1;

        double agregate = 1;
        for (FuzzySet divisor : qualifier.getQualifierSets()) {
            agregate *= ((double) divisor.cardinality(foodItems) / foodItems.size());
        }

        return 1 - Math.pow(agregate, 1.0 / qualifier.getQualifierSets().size());

    }

    public double T11(List<FoodItem> foodItems){

        if (qualifier.getQualifierSets().size() == 0)
            return 0;

        return 2 * Math.pow(0.5, qualifier.getQualifierSets().size());

    }

    public double ExtendedOptimalSummaryMetric(List<FoodItem> foodItems, List<Double> weights) {

        if (weights.size() == 11 && Math.abs(1.0 - weights.stream().collect(Collectors.summingDouble(Double::doubleValue))) < 0.0001d) {
            return T1(foodItems) * weights.get(0) +
                T2(foodItems) * weights.get(1) +
                T3(foodItems) * weights.get(2) +
                T4(foodItems) * weights.get(3) +
                T5(foodItems) * weights.get(4) +
                T6(foodItems) * weights.get(5) +
                T7(foodItems) * weights.get(6) +
                T8(foodItems) * weights.get(7) +
                T9(foodItems) * weights.get(8) +
                T10(foodItems) * weights.get(9) +
                T11(foodItems) * weights.get(10);
        }

        return -1;

    }

    // to będzie do wywalenia/kompletnego przerobienia bo każda funkcja będzie miała inne parametry(chyba)
    public List<Double> getStandardMeasures(List<FoodItem> foodItems) {

        List<Double> ret = new ArrayList<>();

        ret.add((double) BigDecimal.valueOf(T1(foodItems)).setScale(2, RoundingMode.HALF_UP).doubleValue());
        ret.add((double) BigDecimal.valueOf(T2(foodItems)).setScale(2, RoundingMode.HALF_UP).doubleValue());
        ret.add((double) BigDecimal.valueOf(T3(foodItems)).setScale(2, RoundingMode.HALF_UP).doubleValue());
        ret.add((double) BigDecimal.valueOf(T4(foodItems)).setScale(2, RoundingMode.HALF_UP).doubleValue());
        ret.add((double) BigDecimal.valueOf(T5(foodItems)).setScale(2, RoundingMode.HALF_UP).doubleValue());
        ret.add((double) BigDecimal.valueOf(T6(foodItems)).setScale(2, RoundingMode.HALF_UP).doubleValue());
        ret.add((double) BigDecimal.valueOf(T7(foodItems)).setScale(2, RoundingMode.HALF_UP).doubleValue());
        ret.add((double) BigDecimal.valueOf(T8(foodItems)).setScale(2, RoundingMode.HALF_UP).doubleValue());
        ret.add((double) BigDecimal.valueOf(T9(foodItems)).setScale(2, RoundingMode.HALF_UP).doubleValue());
        ret.add((double) BigDecimal.valueOf(T10(foodItems)).setScale(2, RoundingMode.HALF_UP).doubleValue());
        ret.add((double) BigDecimal.valueOf(T11(foodItems)).setScale(2, RoundingMode.HALF_UP).doubleValue());
        
        return ret;

    }

    public String constructSummary(List<FoodItem> foodItems) {

        String quant = quantifier.getName();
        List<Double> measures = new ArrayList<>();

        List<FoodItem> firstSubjectItems = subjects.get(0).filter(List.copyOf(foodItems));
        List<FoodItem> secondSubjectItems = (summaryType != SummaryType.SINGLE_SUBJECT ? subjects.get(1).filter(List.copyOf(foodItems)) : new ArrayList<>());

        String qual = (qualifier.getQualifierSets().size() > 0) ? ", which have " + qualifier.getQualifierSets().get(0).getValue() + " " + qualifier.getQualifierSets().get(0).getVariable().getName() : "";

        for (int i = 0; i < qualifier.getQualifierAnd().size(); i++) {

            if (qualifier.getQualifierAnd().get(i)) qual += " and ";
            else qual += " or ";

            qual += qualifier.getQualifierSets().get(i+1).getValue() + " " + qualifier.getQualifierSets().get(i+1).getVariable().getName();

        }

        qual += (qual.length() > 0) ? ", " : "";

        String summ = "have " + summarizer.getSummarizedSets().get(0).getValue() + " " + summarizer.getSummarizedSets().get(0).getVariable().getName();

        for (int i = 0; i < summarizer.getSummarizedAnd().size(); i++) {

            if (summarizer.getSummarizedAnd().get(i)) summ += "and ";
            else summ += "or ";

            summ += summarizer.getSummarizedSets().get(i+1).getValue() + " " + summarizer.getSummarizedSets().get(i+1).getVariable().getName();

        }
        summ += ".";

        String subj = " of " + subjects.get(0).getName() + " ";
        String secondSubj = "";
        String connector = "";
        String summary = "";

        double top;
        double bottom;
        double T;
        double combCard;
        double incl;

        switch(summaryType) {

            case SINGLE_SUBJECT:
                measures = getStandardMeasures(firstSubjectItems);
                summary = quant + subj + qual + summ;
                break;
                

            case FIRST_FORM:
                connector = " , comparing to ";
                secondSubj = subjects.get(1).getName();

                top = (1 / firstSubjectItems.size()) * summarizer.cardinality(firstSubjectItems);
                bottom =  (top + ((1 / secondSubjectItems.size()) * summarizer.cardinality(secondSubjectItems)));

                T = quantifier.getValue(top / bottom);
                measures.add(T);

                summary = quant + subj + connector + secondSubj + summ;
                break;


            case SECOND_FORM:
                connector = " , comparing to ";
                secondSubj = subjects.get(1).getName();

                combCard = 0;
                for (FoodItem foodItem : secondSubjectItems) {
                    combCard += Math.min(summarizer.DoM(foodItem), qualifier.DoM(foodItem));
                }

                top = (1 / firstSubjectItems.size()) * summarizer.cardinality(firstSubjectItems);
                bottom =  (top + ((1 / secondSubjectItems.size()) * combCard));

                T = quantifier.getValue(top / bottom);
                measures.add(T);

                summary = quant + subj + connector + secondSubj + qual + summ;
                break;

            case THIRD_FORM:
                connector = " , comparing to ";
                secondSubj = subjects.get(1).getName();

                combCard = 0;
                for (FoodItem foodItem : firstSubjectItems) {
                    combCard += Math.min(summarizer.DoM(foodItem), qualifier.DoM(foodItem));
                }

                top = (1 / firstSubjectItems.size()) * combCard;
                bottom =  (top + ((1 / secondSubjectItems.size()) * (1 / secondSubjectItems.size()) * summarizer.cardinality(secondSubjectItems)));

                T = quantifier.getValue(top / bottom);
                measures.add(T);

                summary = quant + subj + qual + connector + secondSubj + summ;
                break;

            case FOURTH_FORM:

                incl = 0;
                for (FoodItem foodItem : foodItems) {
                    incl += Summarizer.implication(summarizer.DoM(foodItem), summarizer.DoM(foodItem));
                }
                break;

            default:
                break;
            
            
        }
        
        return summary + measures.toString();

    }
}
