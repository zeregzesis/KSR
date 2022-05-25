package ksr.grupa3.fuzzy;

import java.lang.reflect.Field;
import java.util.List;

import lombok.Data;

@Data
public class LingVariable {
    
    private final String name;
    private final Field foodProperty;
    private final List<String> values;
    private List<MemberFunc> MemberFuncList;
}
