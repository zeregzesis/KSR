package ksr.grupa3.fuzzy;

import org.mariuszgromada.math.mxparser.Expression;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CombinationFunction implements MembershipFuction {

    public MembershipFuction firstFunction;
    public MembershipFuction secondFunction;
    boolean and;
    private String functionString;

    public CombinationFunction(MembershipFuction firstFunction, MembershipFuction secondFunction, boolean and) {
        this.firstFunction = firstFunction;
        this.secondFunction = secondFunction;
        this.and = and;
        this.functionString = asFunction();
    }

    @Override
    public double getValue(double x) {
        return(and ? Math.min(firstFunction.getValue(x), secondFunction.getValue(x)) : Math.max(firstFunction.getValue(x), secondFunction.getValue(x)));
    }

    @Override
    public String asFunction() {
        return (and ? "min(" : "max(") + firstFunction.getFunctionString() + ", " + secondFunction.getFunctionString() + ")";
    }

    @Override
    public double getBegin() {
        return Math.min(firstFunction.getBegin(), secondFunction.getBegin());
    }

    @Override
    public double getEnd() {
        return Math.max(firstFunction.getEnd(), secondFunction.getEnd());
    }

    @Override
    public double getUpperBound() {
        return Math.max(firstFunction.getUpperBound(), secondFunction.getUpperBound());
    }

    @Override
    public double getIntegral() {
        Expression newExp = new Expression("int(" + this.functionString + ", x, " + getBegin() + ", " + getEnd() + ")");
        return newExp.calculate();
    }
    
}
