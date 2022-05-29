package ksr.grupa3.fuzzy;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubFunc {
    private Argument arg;
    private Expression func;
    private double start;
    private double end;
    

    public SubFunc(String expression, double start, double end) {

        this.arg = new Argument("x");
        this.func = new Expression(expression, arg);
        this.start = start;
        this.end = end;
        
    }
    public SubFunc(Expression expression, double start, double end) {

        this.arg = new Argument("x");
        this.func = expression;
        this.start = start;
        this.end = end;
        
    }

    public double getValue(double x) {

        arg.setArgumentValue(x);
        return func.calculate();

    }

    public double getIntegral() {

        String newExp = "int(" + this.getFunc().getExpressionString() + ", x, " + this.getStart() + ", " + this.getEnd() + ")";
        Expression newFunc = new Expression(newExp, arg);

        return newFunc.calculate();

    }
    
}
