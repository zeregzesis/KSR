package ksr.grupa3.lingSummary;

import ksr.grupa3.fuzzy.MemberFunc;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LingQuantifier {
    private String name;
    private MemberFunc memberFunc;
    private Boolean isAbsolute;
}
