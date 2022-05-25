package ksr.grupa3.fuzzy;

import java.util.List;

import lombok.Data;

@Data
public class LingVariable {
    
    private final String name;
    private final List<String> values;
    private List<MemberFunc> MemberFuncList;
}
