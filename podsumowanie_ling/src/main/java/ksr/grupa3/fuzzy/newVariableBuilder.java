package ksr.grupa3.fuzzy;

public class newVariableBuilder {
    
    public static newVariable buildVariableInstance(oldVariable lingVariable, String value) {
        return new newVariable(lingVariable.getName(), lingVariable.getFoodProperty(), value);
    }

}
