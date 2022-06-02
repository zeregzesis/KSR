package ksr.grupa3.lingSummary;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ksr.grupa3.fuzzy.FoodItem;
import ksr.grupa3.fuzzy.FuzzySet;
import ksr.grupa3.fuzzy.SubFunc;

public class QualityMeasure {

    

    public static double T1(Summarizer summarizer, List<FoodItem> foodItems) {

        double card = summarizer.cardinality(foodItems);

        if (summarizer.getDivisors().size() == 0) 
            return summarizer.getLingQuantifier().getValue(card / (summarizer.getLingQuantifier().getIsAbsolute() ? 1 :  foodItems.size()));

        return summarizer.getLingQuantifier().getValue(card / summarizer.divisorCardinality(foodItems));

    }

    public static double T2(Summarizer summarizer, List<FoodItem> foodItems) {

        double ret = 1.0;

        for (FuzzySet fuzzySet : summarizer.getFuzzySets()) {
            ret *= ( (double) fuzzySet.supportCount(foodItems) / foodItems.size());
        }

        ret = 1 - Math.pow(ret, 1/summarizer.getFuzzySets().size());

        return ret;

    }
    
    public static double T3(Summarizer summarizer, List<FoodItem> foodItems) {
        
        int topSum = 0;
        int bottomSum = 0;
        
        for (FoodItem foodItem : foodItems) {

            int temp = (summarizer.getDivisors().size() > 0 || summarizer.DoM(foodItem, true) > 0) ? 1 : 0;

            topSum += (summarizer.DoM(foodItem, false) > 0 && temp == 1) ? 1 : 0;
            bottomSum += temp;

        }

        return (double) topSum / bottomSum;
        
    }


    public static double T4(Summarizer summarizer, List<FoodItem> foodItems){

        double agregate = 1;
        for (FuzzySet fuzzySet : summarizer.getFuzzySets()) {

            double r = 0;
            for (FoodItem foodItem : foodItems) {
                r += (fuzzySet.DoM(foodItem) > 0 ? 1 : 0);
            }

            r /= foodItems.size();
            agregate *= r;

        }

        return Math.abs(agregate - QualityMeasure.T3(summarizer, foodItems));

    }

    public static double T5(Summarizer summarizer, List<FoodItem> foodItems){

        return 2 * Math.pow(0.5, summarizer.getFuzzySets().size());

    }

    public static double optimalSummaryMetric(Summarizer summarizer, List<FoodItem> foodItems, List<Double> weights) {

        if (Math.abs(1.0 - weights.stream().collect(Collectors.summingDouble(Double::doubleValue))) < 0.0001d) {
            return T1(summarizer, foodItems) * weights.get(0) +
                T2(summarizer, foodItems) * weights.get(1) +
                T3(summarizer, foodItems) * weights.get(2) +
                T4(summarizer, foodItems) * weights.get(3) +
                T5(summarizer, foodItems) * weights.get(4);
        }

        return -1;

    }

    public static double T6(Summarizer summarizer, List<FoodItem> foodItems) {

        double start = summarizer.getLingQuantifier().getMemberFunc().getSubFuncs().get(0).getEnd();
        double end = summarizer.getLingQuantifier().getMemberFunc().getSubFuncs().get(summarizer.getLingQuantifier().getMemberFunc().getSubFuncs().size() - 1).getStart();

        return 1 - Math.abs(end - start) / (summarizer.getLingQuantifier().getIsAbsolute() ? foodItems.size() : 1);

    }

    public static double T7(Summarizer summarizer, List<FoodItem> foodItems) {

        double intSum = 0;

        List<SubFunc> temp = summarizer.getLingQuantifier().getMemberFunc().getSubFuncs();

        for (int i = 1; i < temp.size() - 1; i++) {
            intSum += temp.get(i).getIntegral();
        }

        return 1 - (double) (Math.abs(intSum) / (double) (summarizer.getLingQuantifier().getIsAbsolute() ? foodItems.size() : 1));

    }

    public static double T8(Summarizer summarizer, List<FoodItem> foodItems) {

        double agregate = 1;
        for (FuzzySet fuzzySet : summarizer.getFuzzySets()) {
            agregate *= ((double) (fuzzySet.cardinality(foodItems) / foodItems.size()));
        }

        return 1 - Math.pow(agregate, 1.0 / summarizer.getFuzzySets().size());

    }

    public static double T9(Summarizer summarizer, List<FoodItem> foodItems) {

        double agregate = 1;
        for (FuzzySet divisor : summarizer.getDivisors()) {
            agregate *= ((double) divisor.supportCount(foodItems) / foodItems.size());
        }

        return 1 - Math.pow(agregate, 1.0 / summarizer.getDivisors().size());

    }

    public static double T10(Summarizer summarizer, List<FoodItem> foodItems) {

        double agregate = 1;
        for (FuzzySet divisor : summarizer.getDivisors()) {
            agregate *= ((double) divisor.cardinality(foodItems) / foodItems.size());
        }

        return 1 - Math.pow(agregate, 1.0 / summarizer.getDivisors().size());

    }

    public static double ExtendedOptimalSummaryMetric(Summarizer summarizer, List<FoodItem> foodItems, List<Double> weights) {

        if (Math.abs(1.0 - weights.stream().collect(Collectors.summingDouble(Double::doubleValue))) < 0.0001d) {
            return T1(summarizer, foodItems) * weights.get(0) +
                T2(summarizer, foodItems) * weights.get(1) +
                T3(summarizer, foodItems) * weights.get(2) +
                T4(summarizer, foodItems) * weights.get(3) +
                T5(summarizer, foodItems) * weights.get(4) +
                T6(summarizer, foodItems) * weights.get(5) +
                T7(summarizer, foodItems) * weights.get(6) +
                T8(summarizer, foodItems) * weights.get(7) +
                T9(summarizer, foodItems) * weights.get(8) +
                T10(summarizer, foodItems) * weights.get(9);
        }

        return -1;

    }

    // to będzie do wywalenia/kompletnego przerobienia bo każda funkcja będzie miała inne parametry(chyba)
    public static List<Double> getMeasures(Summarizer summarizer, List<FoodItem> foodItems) {

        List<Double> measures = new ArrayList<>();
        for (Method method : QualityMeasure.class.getDeclaredMethods()) {

            double temp = -1;
            try {
                temp = (double) method.invoke(null, summarizer, foodItems);
                measures.add(temp);
            } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {}

        }

        return measures;

    }
    
}