package ksr.grupa3.fuzzy;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GaussFuction implements MembershipFuction {

    private double a = 1;
    private double b;
    private double c = 0.056;
    private double upperBound;


    @Override
    public double getValue(double x) {
        return a * Math.exp(-Math.pow(x - b, 2) / (2 * Math.pow(c, 2)));
    }
    @Override
    public String asFunction() {
        return "" + a + " * exp(-(" + b + " - x)^2 / (2 * " + c + "^2))";
    }

    @Override
    public double getBegin() {
        return Math.max(0,b-4*c);
    }

    @Override
    public double getEnd() {
        return b == upperBound ? upperBound : b+4*c;
    }

    @Override
    public double getIntegral() {
        String newExp = "int(" + a + "*exp(-((x-" + b + ")^2)/(2*" + c + "^2)),x," + getBegin() + "," + getEnd() + ")";
        Expression newFunc = new Expression(newExp, new Argument("x"));

        return newFunc.calculate();
    }

}
