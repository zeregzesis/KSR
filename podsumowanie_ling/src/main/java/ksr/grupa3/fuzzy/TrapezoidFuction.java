package ksr.grupa3.fuzzy;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class TrapezoidFuction implements MembershipFuction {

    private double a;
    private double b;
    private double c;
    private double d;
    private double upperBound;
    private String functionString;


    public TrapezoidFuction(double a, double b, double c, double d, double upperBound) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.upperBound = upperBound;
        this.functionString = asFunction();
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
    public String asFunction() {
        return "if(x<=" + a + ", 0, if(x<" + b + ", (1/(" + b + "- " + a + "))*(x - " + a + "), if(x<" + c + ", 1, if(x<" + d + ", 1+(-1/(" + d + "-" + c + "))*(x - " + c + "), 0))))";
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
        double integral = 0;
        String newExp;
        Expression newFunc;
        Argument arg = new Argument("x");

        if (a != b) {
            newExp = "int((1/(" + Double.toString(b) + " - " + Double.toString(a) + "))*(x - " + Double.toString(a) + "), x, " + Double.toString(a) + ", " + Double.toString(b) + ")";
            newFunc = new Expression(newExp, arg);
            integral += newFunc.calculate();
        }

        if (c != d) {
            newExp = "int(1+(-1/(" + Double.toString(d) + " - " + Double.toString(c) + "))*(x - " + Double.toString(c) + "), x, " + Double.toString(c) + ", " + Double.toString(d) + ")";
            newFunc = new Expression(newExp, arg);
            integral += newFunc.calculate();
        }
        

        return integral + (c - b);
    }
    

}