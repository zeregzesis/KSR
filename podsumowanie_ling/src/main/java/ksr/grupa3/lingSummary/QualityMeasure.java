package ksr.grupa3.lingSummary;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import ksr.grupa3.fuzzy.FoodItem;
import ksr.grupa3.fuzzy.FuzzySet;
import ksr.grupa3.fuzzy.MemberFunc;

public class QualityMeasure {


    public static double T1(LingSummary_alt lingSummary){
        
        if(lingSummary.summarizers.size()==1){
            //policzyć sigmaCount dla sumaryzatora
            double sigmaCount=0.0;
            //policzyć M - jeśli kwantyfikator jest absolutny to wziąć 1, jak relatywny to przestrzeń rozważań.
            double M=1;
            // return lingSummary.quantifier.fuzztySet.DoM(sigmaCount/M); // jakoś tak, ale nie chcę ci zmieniać w kodzie w chuj
            //na razie:
            return 0.1;
        }
        else{
            //wyznaczyć intersecta dla sumaryzatorów
            //proponuję w klasie summarizer zrobić funkcję intersect, która by robiła intersecta na podstawie fuzzy setów
            Summarizer intersected=lingSummary.summarizers[0];
            for(int i=0; i<lingSummary.summarizers.size()-1;i++){
                intersected= Summarizer((intersected.intersect(lingSummary.summarizers[i+1])).fuzzySet); //coś takiego
            }
            //policzyć sigmaCount dla sumaryzatora intersected
            double sigmaCountIntersected=0.0;
            
            //policzyć sigmaCount dla Sumaryzatora S2
            // double sigmaCountS2=lingSummary.summarizers[1].fuzzySet.cardinality(); //coś takiego
            double sigmaCountS2=0.1;
            // return lingSummary.quantifier.funkcjaprzynależności(sigmaCountIntersected/sigmaCountS2);
            return 0;
        }
        

    }



    public static double T2(LingSummary_alt lingSummary){
        
        

        return 0;

    }


    public static double T3(LingSummary_alt lingSummary){
        
        

        return 0;

    }


    public static double T4(LingSummary_alt lingSummary){
        
        

        return 0;

    }

    public static double T5(LingSummary_alt lingSummary){
        
        

        return 0;

    }

    public static double T6(LingSummary_alt lingSummary){
        
        

        return 0;

    }

    public static double T7(LingSummary_alt lingSummary){
        
        

        return 0;

    }

    public static double T8(LingSummary_alt lingSummary){
        
        

        return 0;

    }

    public static double T9(LingSummary_alt lingSummary){
        
        

        return 0;

    }

    public static double T10(LingSummary_alt lingSummary){
        
        

        return 0;

    }
    
    
    
    
    
    
    
    
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
