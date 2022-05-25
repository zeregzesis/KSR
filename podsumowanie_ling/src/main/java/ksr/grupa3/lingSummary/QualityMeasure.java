package ksr.grupa3.lingSummary;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class QualityMeasure {

    private static double T1() {
        return 0;
    }

    private static double T2() {
        return 0;
    }

    private static double T3() {
        return 0;
    }

    private static double T4() {
        return 0;
    }

    private static double T5() {
        return 0;
    }

    private static double T6() {
        return 0;
    }

    private static double T7() {
        return 0;
    }

    private static double T8() {
        return 0;
    }

    private static double T9() {
        return 0;
    }

    private static double T10() {
        return 0;
    }

    private static double T11() {
        return 0;
    }

    public static List<Double> getMeasures() {
        List<Double> measures = new ArrayList<>();
        for (Method method : QualityMeasure.class.getDeclaredMethods()) {
            double temp = 0;
            try {
                temp = (double) method.invoke(null);
            } catch (IllegalAccessException | InvocationTargetException e) {
                // TODO zmienić to jakoś
                e.printStackTrace();
            }
            measures.add(temp);
        }
        return measures;
    }
    
}
