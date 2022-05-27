package ksr.grupa3.fuzzy;

import java.lang.reflect.Field;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LingVariable {
    
    private final String name;
    private final Field foodProperty;
    private final List<String> values;
    private List<MemberFunc> MemberFuncList;

    public double getFuncValue(String value, double x){
        return MemberFuncList.get(values.indexOf(value)).getValue(x);
    }

    public MemberFunc getMemberFunc(String value){
        return MemberFuncList.get(values.indexOf(value));
    }

    public SubFunc getSubFunc(String value, double x){
        List<SubFunc> temp = MemberFuncList.get(values.indexOf(value)).getSubFuncs();
        for (SubFunc subFunc : temp) {
            if (subFunc.getStart() <= x && x <= subFunc.getEnd()) {
                return subFunc;
            }
        }
        return null;
    }
}