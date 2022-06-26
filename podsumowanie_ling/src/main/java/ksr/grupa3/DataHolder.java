package ksr.grupa3;
//
import ksr.grupa3.fuzzy.FoodItem;
import ksr.grupa3.ling.Quantifier;
import ksr.grupa3.ling.Subject;
import ksr.grupa3.ling.Variable;
import ksr.grupa3.fuzzy.FuzzySet;
import java.util.List;

public class DataHolder {

//    public ObservableList<Quantifier> quantifiers;
    public List<Quantifier> quantifiers;
    public List<Variable> variables;
    public List<Subject> subjects;

    public List<FoodItem> foodItems;

    public DataHolder(List<Quantifier> quantifiers,List<Variable> variables, List<Subject> subjects,List<FoodItem> foodItems){
//        this.quantifiers= FXCollections.observableList(quantifiers);
        this.quantifiers=quantifiers;
        this.variables=variables;
        this.subjects=subjects;
        this.foodItems=foodItems;
    }

}
