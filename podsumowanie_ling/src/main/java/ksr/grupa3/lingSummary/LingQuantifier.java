package ksr.grupa3.lingSummary;

import java.io.Serializable;

import ksr.grupa3.fuzzy.MemberFunc;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LingQuantifier implements Serializable {

    private String name;
    private MemberFunc memberFunc;
    private Boolean isAbsolute;

    // public double getValue(double x) {
    //     return memberFunc.getValue(x);
    // }

}
