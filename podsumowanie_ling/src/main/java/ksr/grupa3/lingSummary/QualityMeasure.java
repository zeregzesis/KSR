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

    public static double tNorm(Double a, Double b) {

        return Math.min(a, b);

    }

    public static double T1(Summarizer summarizer, List<FoodItem> foodItems) {

        double card = summarizer.cardinality(foodItems);

        if (summarizer.getDivisors().size() == 0) 
            return card / (summarizer.getLingQuantifier().getIsAbsolute() ? 1 :  foodItems.size());

        return card / summarizer.divisorCardinality(foodItems);

    }

    public static double T2(Summarizer summarizer, List<FoodItem> foodItems) {

        double ret = 1.0;

        for (FuzzySet fuzzySet : summarizer.getFuzzySets()) {
            ret *= (fuzzySet.supportCount(foodItems) / foodItems.size());
        }

        ret = 1 - Math.pow(ret, 1/summarizer.getFuzzySets().size());

        return ret;

    }
    
    // nie mam pojęcia czy to wgl dobrze jest
    public static double T3(Summarizer summarizer, List<FoodItem> foodItems) {
        
        int topSum = 0;
        int bottomSum = 0;
        
        for (FoodItem foodItem : foodItems) {

            double us = 0;
            double uw = 0;

            List<Double> funcValues = new ArrayList<>();
            for (FuzzySet fuzzySet : summarizer.getFuzzySets()) {
                funcValues.add(fuzzySet.getVariable().getFuncValue(fuzzySet.getValue(), foodItem.getProperty(fuzzySet.getVariable().getFoodProperty())));
            }

            for (int i = 0; i < summarizer.getSetAnd().size(); i++) {

                if (summarizer.getSetAnd().get(i)) {
                    try {
                        us += (double) QualityMeasure.class.getMethod("tNorm").invoke(null, funcValues.get(i), funcValues.get(i+1));
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException
                            | SecurityException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        us += 1 - (double) QualityMeasure.class.getMethod("tNorm").invoke(null, 1 - funcValues.get(i), 1 - funcValues.get(i+1));
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException
                            | SecurityException e) {
                        e.printStackTrace();
                    }
                }

            }

            if (summarizer.getDivisors().size() > 0) {

                List<Double> divisorValues = new ArrayList<>();
                for (FuzzySet divisor : summarizer.getDivisors()) {
                    divisorValues.add(divisor.getVariable().getFuncValue(divisor.getValue(), foodItem.getProperty(divisor.getVariable().getFoodProperty())));
                }

                for (int i = 0; i < summarizer.getDivisorAnd().size(); i++) {

                    if (summarizer.getDivisorAnd().get(i)) {
                        try {
                            uw += (double) QualityMeasure.class.getMethod("tNorm").invoke(null, divisorValues.get(i), divisorValues.get(i+1));
                        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException
                                | SecurityException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        try {
                            uw += 1 - (double) QualityMeasure.class.getMethod("tNorm").invoke(null, 1 - divisorValues.get(i), 1 - divisorValues.get(i+1));
                        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException
                                | SecurityException e) {
                            e.printStackTrace();
                        }
                    }

                }

                topSum += ((us > 0 && uw > 0) ? 1 : 0);
                bottomSum += ((uw > 0) ? 1 : 0);

            }
            else {
                topSum += ((us > 0) ? 1 : 0);
                bottomSum += 1;
            }

        }
        
        return topSum / bottomSum;

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
        try {
            return agregate - (double) QualityMeasure.class.getMethod("T3").invoke(null, summarizer, foodItems);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }

        return -1;

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

        return (end - start) / (summarizer.getLingQuantifier().getIsAbsolute() ? foodItems.size() : 1);

    }

    public static double T7(Summarizer summarizer, List<FoodItem> foodItems) {

        double intSum = 0;
        for(SubFunc subFunc : summarizer.getLingQuantifier().getMemberFunc().getSubFuncs()) {
            intSum += subFunc.getIntegral();
        }

        return 1 - (intSum / (summarizer.getLingQuantifier().getIsAbsolute() ? foodItems.size() : 1));

    }

    public static double T8(Summarizer summarizer, List<FoodItem> foodItems) {

        double agregate = 1;
        for (FuzzySet fuzzySet : summarizer.getFuzzySets()) {
            agregate *= fuzzySet.cardinality(foodItems) / foodItems.size();
        }

        return 1 - Math.pow(agregate, 1.0 / summarizer.getFuzzySets().size());

    }

    public static double T9(Summarizer summarizer, List<FoodItem> foodItems) {

        double agregate = 1;
        for (FuzzySet divisor : summarizer.getDivisors()) {
            agregate *= divisor.supportCount(foodItems) / foodItems.size();
        }

        return 1 - Math.pow(agregate, 1.0 / summarizer.getDivisors().size());

    }

    public static double T10(Summarizer summarizer, List<FoodItem> foodItems) {

        double agregate = 1;
        for (FuzzySet divisor : summarizer.getDivisors()) {
            agregate *= divisor.cardinality(foodItems) / foodItems.size();
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
            } catch (IllegalAccessException | InvocationTargetException e) {}

        }

        return measures;

    }
    
}