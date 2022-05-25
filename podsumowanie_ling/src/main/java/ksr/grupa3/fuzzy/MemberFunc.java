package ksr.grupa3.fuzzy;

import java.util.List;

public class MemberFunc {
    private List<SubFunc> subFuncs;

    public double getValue(double x) {
        for (SubFunc subFunc : subFuncs) {
            if (subFunc.getStart() <= x && x <= subFunc.getEnd()) {
                return subFunc.getValue(x);
            }
        }
        return -1;
    }
}
