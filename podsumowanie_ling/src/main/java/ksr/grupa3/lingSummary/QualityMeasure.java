package ksr.grupa3.lingSummary;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import ksr.grupa3.fuzzy.FoodItem;
import ksr.grupa3.fuzzy.FuzzySet;
import ksr.grupa3.fuzzy.MemberFunc;

public class QualityMeasure {


    //degree of truth
    public static double T1(double sCount, double M, MemberFunc func) {
        return func.getValue(sCount / M);
    }

    public static double T2(List<FuzzySet> fuzzySets, List<FoodItem> foodItems) {
        double res = 1;
        for (FuzzySet fuzzySet : fuzzySets) {
            res *= fuzzySet.supportCount(foodItems);
        }
        res = Math.pow(res, 1.0 / fuzzySets.size());

        return 1 - res;
    }

    public static double T3() {
        return 0;
    }

    public static double T4() {
        return 0;
    }

    public static double T5(int numberOfSummarizers) {
        return 2*Math.pow(0.5, numberOfSummarizers);
    }

    public static double OptimalSummary(List<Double> weights){
        double res=0;
        
        
        return res;

    }


    public static double T6() {
        return 0;
    }

    public static double T7() {
        return 0;
    }

    public static double T8() {
        return 0;
    }

    public static double T9() {
        return 0;
    }

    public static double T10() {
        return 0;
    }

    public static double T11() {
        return 0;
    }

    // to będzie do wywalenia/kompletnego przerobienia bo każda funkcja będzie miała inne parametry(chyba)
    public static List<Double> getMeasures() {
        List<Double> measures = new ArrayList<>();
        for (Method method : QualityMeasure.class.getDeclaredMethods()) {
            double temp = -1;
            try {
                temp = (double) method.invoke(null);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            measures.add(temp);
        }
        return measures;
    }

    private boolean isQuantifierAbsolute(){
        
        
        
        return true;

    }
    
}
