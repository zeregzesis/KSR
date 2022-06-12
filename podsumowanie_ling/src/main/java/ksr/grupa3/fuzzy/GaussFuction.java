package ksr.grupa3.fuzzy;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;

public class GaussFuction implements MembershipFuction {

    private double a = 1;
    private double b;
    private double c = 0.056;

    public GaussFuction(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double getValue(double x) {
        return (1/(c - a))*(Math.exp(-(Math.pow((x - b),2)/(2*Math.pow(c - a,2)))));
    }

    @Override
    public double getBegin() {
        return a;
    }

    @Override
    public double getEnd() {
        return c;
    }

    @Override
    public double getIntegral() {
        String newExp = "int((1/(" + c + " - " + a + "))*(exp(-((x - " + b + ")^2)/(2*(" + c + " - " + a + ")^2)))))";
        Expression newFunc = new Expression(newExp, new Argument("x"));

        return newFunc.calculate();
    }

}
