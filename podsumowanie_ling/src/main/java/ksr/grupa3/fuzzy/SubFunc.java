package ksr.grupa3.fuzzy;

import java.util.function.Function;

import lombok.Data;

@Data
public class SubFunc {
    private double start;
    private double end;
    private Function<Double, Double> func;

    public double getValue(double x) {
        return func.apply(x);
    }
}
