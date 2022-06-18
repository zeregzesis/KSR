package ksr.grupa3.fuzzy;

import java.io.Serializable;

public interface MembershipFuction extends Serializable {

    public double getValue(double x);
    public String asFunction();
    public String getFunctionString();
    public double getBegin();
    public double getEnd();
    public double getUpperBound();
    public double getIntegral();
    
}
