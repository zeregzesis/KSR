package ksr.grupa3.lingSummary;

import java.util.function.Function;

import ksr.grupa3.fuzzy.MemberFunc;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuantifierType {

    private String name;
    private MemberFunc memberFunc;
    private Boolean isAbsolute;
    
}
