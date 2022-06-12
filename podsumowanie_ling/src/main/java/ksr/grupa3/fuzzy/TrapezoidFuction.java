package ksr.grupa3.fuzzy;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;

public class TrapezoidFuction implements MembershipFuction {

    private double a;
    private double b;
    private double c;
    private double d;

    public TrapezoidFuction(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public double getValue(double x) {
        if (x <= a) {
            return 0;
        } else if (x > a && x < b) {
            return (1/(b - a))*(x - a);
        } else if (x >= b && x <= c) {
            return 1;
        } else if (x > c && x < d) {
            return 1+(-1/(d - c))*(x - c);
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
        return d;
    }

    @Override
    public double getIntegral() {
        String newExp = "int(if (x <= " + a + ", 0, if (x > " + a + " && x < " + b + ", (x - " + a + "), if (x >= " + b + " && x < " + c + ", 1, if (x > " + c + " && x < " + d + ", 1 + (-1/(x - " + c + "))*(x - " + c + "), 0))))";
        Expression newFunc = new Expression(newExp, new Argument("x"));

        return newFunc.calculate();
    }
    

}