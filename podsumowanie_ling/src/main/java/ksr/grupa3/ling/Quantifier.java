package ksr.grupa3.ling;

import java.io.Serializable;

import ksr.grupa3.fuzzy.MemberFunc;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Quantifier implements Serializable {

    private String name;
    private MemberFunc memberFunc;
    private Boolean isAbsolute;
    private Boolean isForthForm = false;

    public double getValue(double x) {
        return (memberFunc != null ? memberFunc.getValue(x) : -1);
    }

}
