package ksr.grupa3.fuzzy;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;

public class TriangleFuction implements MembershipFuction {

    private double a;
    private double b;
    private double c;

    public TriangleFuction(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double getValue(double x) {
        if (x <= a) {
            return 0;
        } else if (x > a && x < b) {
            return (1/(b - a))*(x - a);
        } else if (x >= b && x <= c) {
            return 1+(-1/(c - b))*(x - b);
        } else {
            return 0;
        }
    }

    @Override
    public double getBegin() {
        return a;
    }

    @Override
    public double getEnd() {
        return c;
    }

    public double getIntegral() {
        String newExp = "int(if (x <= " + a + ", 0, if (x > " + a + " && x < " + b + ", (x - " + a + "), if (x >= " + b + " && x < " + c + ", 1 + (-1/(x - " + b + "))*(x - " + b + "), 0))))";
        Expression newFunc = new Expression(newExp, new Argument("x"));

        return newFunc.calculate();
    }

}