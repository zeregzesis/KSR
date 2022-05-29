package ksr.grupa3.fuzzy;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FuzzySet {

    private LingVariable variable;
    private String value;

    public double DoM(FoodItem foodItem) {

        return variable.getMemberFuncList().get(variable.getValues().indexOf(value)).getValue(foodItem.getProperty(variable.getFoodProperty()));

    }

    public double cardinality(List<FoodItem> foodItems) {

        double ret = 0.0;
        for (FoodItem foodItem : foodItems) {
            ret += DoM(foodItem);
        }

        return ret;

    }

    public List<FoodItem> support(List<FoodItem> foodItems) {

        List<FoodItem> ret = new ArrayList<>();
        for (FoodItem foodItem : foodItems) {
            if (DoM(foodItem) > 0) {
                ret.add(foodItem);
            }
        }

        return ret;

    }

    public int supportCount(List<FoodItem> foodItems) {

        return support(foodItems).size();

    }

    public List<FoodItem> alphaCut(List<FoodItem> foodItems, double alpha){

        List<FoodItem> ret = new ArrayList<>();
        for (FoodItem foodItem : foodItems) {
            if (DoM(foodItem) >= alpha) {
                ret.add(foodItem);
            }
        }

        return ret;

    }

    public int alphaCutCount(List<FoodItem> foodItems, double alpha){
        return alphaCut(foodItems, alpha).size();
    }

    
    public FuzzySet setSum(FuzzySet other) {

        String newVariableName = this.getVariable().getName() + " or " + other.getVariable().getName();
        String newValue = this.getValue() + " or " + other.getValue();

        List<SubFunc> temp = this.getVariable().getMemberFunc(this.getValue()).getSubFuncs();
        List<SubFunc> temp2 = this.getVariable().getMemberFunc(other.getValue()).getSubFuncs();
            
        double start = Math.min(
                    temp.get(1).getStart(),
                    temp2.get(1).getStart()
                    );
        double end = Math.max(
                temp.get(temp.size()-2).getEnd(),
                temp2.get(temp2.size()-2).getEnd()
                );

        int firtsIndex = 0, secondIndex = 0;
        double rangeStart = start, rangeEnd = start;

        SubFunc first = temp.get(firtsIndex);
        SubFunc second = temp2.get(secondIndex);

        List<SubFunc> subFuncs = new ArrayList<>();

        for (int i = (int) start; i <= end; i++) {
            if (first.getEnd() < i) {
                firtsIndex++;
                first = temp.get(firtsIndex);
            }
            if (second.getEnd() < i) {
                secondIndex++;
                second = temp2.get(secondIndex);
            }
            if (first.getValue((double)i) >= second.getValue((double)i)) {
                rangeEnd++;
            } else {
                subFuncs.add(new SubFunc(first.getFunc(), rangeStart, rangeEnd));
                rangeStart = rangeEnd;
                SubFunc tempFunc = first;
                first = second;
                second = tempFunc;
            }
        }
        if (rangeStart != rangeEnd) {
            subFuncs.add(new SubFunc(first.getFunc(), rangeStart, rangeEnd));
        }

        List<MemberFunc> newMemberFuncs = new ArrayList<>(this.getVariable().getMemberFuncList());
        newMemberFuncs.add(new MemberFunc(subFuncs));

        List<String> newValues = new ArrayList<>(this.getVariable().getValues());
        newValues.add(newValue);
        
        LingVariable newVariable = new LingVariable(newVariableName, this.getVariable().getFoodProperty(), newValues, newMemberFuncs);
        
        return new FuzzySet(newVariable, newValue);
    }

    public FuzzySet setIntersect(FuzzySet other) {

        String newVariableName = this.getVariable().getName() + " and " + other.getVariable().getName();
        String newValue = this.getValue() + " and " + other.getValue();

        List<SubFunc> temp = this.getVariable().getMemberFunc(this.getValue()).getSubFuncs();
        List<SubFunc> temp2 = this.getVariable().getMemberFunc(other.getValue()).getSubFuncs();

            double start = Math.min(
                    temp.get(1).getStart(),
                    temp2.get(1).getStart()
                    );

            double end = Math.max(
                temp.get(temp.size()-2).getEnd(),
                temp2.get(temp2.size()-2).getEnd()
                );

            int firtsIndex = 0, secondIndex = 0;
            double rangeStart = 0, rangeEnd = 0;
            double funcStart = 0, funcEnd = 0;
    
            SubFunc first = temp.get(firtsIndex);
            SubFunc second = temp2.get(secondIndex);
    
            List<SubFunc> subFuncs = new ArrayList<>();
            
            for (int i = (int) start; i <= end; i++) {

                if (first.getEnd() < i) {
                    firtsIndex++;
                    first = temp.get(firtsIndex);
                }
                if (second.getEnd() < i) {
                    secondIndex++;
                    second = temp2.get(secondIndex);
                }

                if (first.getValue((double)i) > 0 && second.getValue((double)i) > 0) {
                    if (rangeStart == 0) {
                        rangeStart = i;
                        rangeEnd = i;
                        funcStart = i;
                        funcEnd = i;
                    }
                    else {
                        rangeEnd = i;
                        if (first.getValue((double)i) <= second.getValue((double)i)) {
                            funcEnd = i;
                        } else {
                            subFuncs.add(new SubFunc(first.getFunc(), funcStart, funcEnd));
                            funcStart = i;
                            SubFunc tempFunc = first;
                            first = second;
                            second = tempFunc;
                        }
                    }
                } else if (rangeStart != 0) {
                    subFuncs.add(new SubFunc(first.getFunc(), rangeStart, rangeEnd));
                    rangeStart = rangeEnd;
                    SubFunc tempFunc = first;
                    first = second;
                    second = tempFunc;
                }
            }

            if (rangeStart != rangeEnd) {
                subFuncs.add(new SubFunc(first.getFunc(), rangeStart, rangeEnd));
            }

            List<MemberFunc> newMemberFuncs = new ArrayList<>(this.getVariable().getMemberFuncList());
            newMemberFuncs.add(new MemberFunc(subFuncs));

            List<String> newValues = new ArrayList<>(this.getVariable().getValues());
            newValues.add(newValue);

            LingVariable newVariable = new LingVariable(newVariableName, this.getVariable().getFoodProperty(), newValues, newMemberFuncs);
            
            return new FuzzySet(newVariable, newValue);
    }

    

}
