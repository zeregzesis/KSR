package ksr.grupa3.fuzzy;

import java.io.IOException;
import java.io.Serializable;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubFunc implements Serializable {
    private transient Argument arg;
    private transient Expression func;
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

    private void writeObject(java.io.ObjectOutputStream stream)
            throws IOException {
        stream.writeObject(start);
        stream.writeObject(end);
        stream.writeObject(arg.getArgumentName());
        stream.writeObject(func.getExpressionString());
    }

    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        start = (double) stream.readObject();
        end = (double) stream.readObject();
        arg = new Argument((String) stream.readObject());
        func = new Expression((String) stream.readObject(), arg);
    }
    
}
