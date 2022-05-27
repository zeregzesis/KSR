package ksr.grupa3.fuzzy;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberFunc {
    private List<SubFunc> subFuncs;

    public double getValue(double x) {
        for (SubFunc subFunc : subFuncs) {
            if (subFunc.getStart() <= x && x <= subFunc.getEnd()) {
                return subFunc.getValue(x);
            }
        }
        return 0;
    }
}
