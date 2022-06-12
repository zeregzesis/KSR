package ksr.grupa3.ling;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ksr.grupa3.fuzzy.FoodItem;
import ksr.grupa3.fuzzy.newSet;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Summary {
    private Quantifier quantifier;
    private List<Subject> subjects;
    private Agregator qualifier;
    private Agregator summarizer;
    private SummaryType summaryType;

    public double T1() {

        double card = summarizer.cardinality();

        if (qualifier.getSetList().size() == 0) 
            return quantifier.getValue(card / (quantifier.getIsAbsolute() ? 1 :  summarizer.size()));

        double qualCard = qualifier.cardinality();
        return quantifier.getValue(Math.min(card, qualCard) / qualCard);

    }

    public double T2() {

        double ret = 1.0;

        for (newSet fuzzySet : summarizer.getSetList()) {
            ret *= ( (double) fuzzySet.height() / fuzzySet.getFoodItems().size());
        }

        ret = 1 - Math.pow(ret, 1/summarizer.getSetList().size());

        return ret;

    }
    
    public double T3() {
        
        int topSum = 0;
        int bottomSum = 0;
        
        for (FoodItem foodItem : summarizer.UoD()) {

            int temp = (qualifier.getSetList().size() == 0 || qualifier.DoM(foodItem) > 0) ? 1 : 0;

            topSum += (summarizer.DoM(foodItem) > 0 && temp == 1) ? 1 : 0;
            bottomSum += temp;

        }

        return (double) topSum / bottomSum;
        
    }


    public double T4(){

        double agregate = 1;
        for (newSet fuzzySet : summarizer.getSetList()) {

            double r = 0;
            for (FoodItem foodItem : summarizer.UoD()) {
                r += (fuzzySet.DoM(foodItem) > 0 ? 1 : 0);
            }

            r /= summarizer.size();
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

        return 1 - Math.abs(end - start) / (quantifier.getIsAbsolute() ? summarizer.size() : 1);

    }

    public double T7() {

        // double intSum = 0;

        // List<SubFunc> temp = quantifier.getMemberFunc().getSubFuncs();

        // for (int i = 1; i < temp.size() - 1; i++) {
        //     intSum += temp.get(i).getIntegral();
        // }

        return 1 - (double) (Math.abs(quantifier.getMemberFunc().getIntegral()) / (double) (quantifier.getIsAbsolute() ? summarizer.size() : 1));

    }

    public double T8() {

        double agregate = 1;
        for (newSet fuzzySet : summarizer.getSetList()) {
            agregate *= ((double) (fuzzySet.cardinality() / summarizer.size()));
        }

        return 1 - Math.pow(agregate, 1.0 / summarizer.getSetList().size());

    }

    public double T9() {

        if (qualifier.getSetList().size() == 0)
            return -1;

        double agregate = 1;
        for (newSet qual : qualifier.getSetList()) {
            agregate *= ((double) qual.height() / qualifier.size());
        }

        return 1 - Math.pow(agregate, 1.0 / qualifier.getSetList().size());

    }

    public double T10() {

        if (qualifier.getSetList().size() == 0)
            return -1;

        double agregate = 1;
        for (newSet divisor : qualifier.getSetList()) {
            agregate *= ((double) divisor.cardinality() / qualifier.size());
        }

        return 1 - Math.pow(agregate, 1.0 / qualifier.getSetList().size());

    }

    public double T11(){

        if (qualifier.getSetList().size() == 0)
            return 0;

        return 2 * Math.pow(0.5, qualifier.getSetList().size());

    }

    public double ExtendedOptimalSummaryMetric(List<FoodItem> foodItems, List<Double> weights) {

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

    // to będzie do wywalenia/kompletnego przerobienia bo każda funkcja będzie miała inne parametry(chyba)
    public List<Double> getStandardMeasures() {

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

        //System.out.println("Measures done");
        
        return ret;

    }

    public List<Double> getCompoundMeasure() {

        List<FoodItem> firstSubjectItems = subjects.get(0).filter(summarizer.UoD());
        List<FoodItem> secondSubjectItems = subjects.get(1).filter(summarizer.UoD());

        switch(summaryType) {
            case FIRST_FORM:
                double top = (1.0 / firstSubjectItems.size()) * summarizer.cardinality(firstSubjectItems);
                double bottom =  (top + ((1.0 / secondSubjectItems.size()) * summarizer.cardinality(secondSubjectItems)));
                double T = quantifier.getValue(top / bottom);
                T = (double) BigDecimal.valueOf(T).setScale(2, RoundingMode.HALF_UP).doubleValue();
                return List.of(T);
            case SECOND_FORM:
                double combCard = 0;
                for (FoodItem foodItem : secondSubjectItems) {
                    combCard += Math.min(summarizer.DoM(foodItem), qualifier.DoM(foodItem));
                }

                top = (1.0 / firstSubjectItems.size()) * summarizer.cardinality(firstSubjectItems);
                bottom =  (top + ((1.0 / secondSubjectItems.size()) * combCard));

                T = quantifier.getValue(top / bottom);
                T = (double) BigDecimal.valueOf(T).setScale(2, RoundingMode.HALF_UP).doubleValue();
                return List.of(T);
            case THIRD_FORM:
                combCard = 0;
                for (FoodItem foodItem : firstSubjectItems) {
                    combCard += Math.min(summarizer.DoM(foodItem), qualifier.DoM(foodItem));
                }

                top = (1.0 / firstSubjectItems.size()) * combCard;
                bottom =  (top + ((1.0 / secondSubjectItems.size()) * (1.0 / secondSubjectItems.size()) * summarizer.cardinality(secondSubjectItems)));

                T = quantifier.getValue(top / bottom);
                T = (double) BigDecimal.valueOf(T).setScale(2, RoundingMode.HALF_UP).doubleValue();
                return List.of(T);

            // nie mam pojęcia czy dobrze, trzeba będzie przetestować
            case FOURTH_FORM:
                double incl = 0;
                for (FoodItem foodItem : summarizer.UoD()) {
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
        List<Double> measures = (summaryType == SummaryType.SINGLE_SUBJECT ? getStandardMeasures() : getCompoundMeasure());

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

        String subj = " of " + subjects.get(0).getName();
        String secondSubj = (summaryType != SummaryType.SINGLE_SUBJECT ? subjects.get(1).getName() : "");
        String connector = (summaryType == SummaryType.SINGLE_SUBJECT ? "" : (summaryType == SummaryType.FOURTH_FORM ? " than " : ", comparing to "));
        String summary = "";

        summary = quant + subj + (summaryType == SummaryType.SINGLE_SUBJECT || summaryType == SummaryType.THIRD_FORM ? " " + qual : connector + secondSubj) + ( summaryType == SummaryType.SECOND_FORM ? " " + qual : (summaryType == SummaryType.THIRD_FORM ? connector + secondSubj : "")) + " " + summ;

        /* 
        switch(summaryType) {

            case SINGLE_SUBJECT:
                summary = quant + subj + " " + qual + 
                " " + summ;
                break;

            case FIRST_FORM:
                summary = quant + subj + connector + secondSubj + 
                " " + summ;
                break;

            case SECOND_FORM:
                summary = quant + subj + connector + secondSubj + 
                " " + qual + " " + summ;
                break;

            case THIRD_FORM:

                summary = quant + subj + " " + qual + 
                connector + secondSubj + " " + summ;
                break;

            case FOURTH_FORM:
                
                summary = quant + subj + connector + secondSubj + 
                " " + summ;
                break;

            default:
                break;
            
            
        }*/
        
        return summary + measures.toString();

    }
}
