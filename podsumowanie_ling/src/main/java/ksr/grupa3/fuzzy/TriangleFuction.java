package ksr.grupa3.fuzzy;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class TriangleFuction implements MembershipFuction {

    private double a;
    private double b;
    private double c;
    private double upperBound;

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
    public String asFunction() {
        return "if(x<=" + a + ", 0, if(x<" + b + ", (1/(" + b + "- " + a + "))*(x - " + a + "), if(x<" + c + ", 1+(-1/(" + c + "-" + b + "))*(x - " + b + "), 0)))";
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
        double integral = 0;
        String newExp;
        Expression newFunc;
        Argument arg = new Argument("x");

        if (a != b) {
            newExp = "int((1/(" + Double.toString(b) + " - " + Double.toString(a) + "))*(x - " + Double.toString(a) + "), x, " + Double.toString(a) + ", " + Double.toString(b) + ")";
            newFunc = new Expression(newExp, arg);
            integral += newFunc.calculate();
        }

        if (b != c) {
            newExp = "int(1+(-1/(" + Double.toString(c) + " - " + Double.toString(b) + "))*(x - " + Double.toString(b) + "), x, " + Double.toString(b) + ", " + Double.toString(c) + ")";
            newFunc = new Expression(newExp, arg);
            integral += newFunc.calculate();
        }

        return integral;
    }

}