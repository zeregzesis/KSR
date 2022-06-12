package ksr.grupa3.fuzzy;

import java.io.Serializable;

public interface MembershipFuction extends Serializable {

    public double getValue(double x);
    public double getBegin();
    public double getEnd();
    public double getIntegral();
    
}
