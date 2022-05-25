package ksr.grupa3.fuzzy;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubFunc {
    private double start;
    private double end;
    private Expression func;
    private Argument arg;

    public SubFunc(String expression, double start, double end) {
        this.start = start;
        this.end = end;
        this.arg = new Argument("x");
        this.func = new Expression(expression, arg);
    }

    public double getValue(double x) {
        arg.setArgumentValue(x);
        return func.calculate();
    }
}
