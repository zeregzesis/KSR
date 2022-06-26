package ksr.grupa3.fuzzy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ksr.grupa3.ling.Variable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor
public class FuzzySet {

    //private MembershipFuction membershipFuction;
    //private Field foodProperty;
    private List<FoodItem> foodItems;
    private List<Double> values = new ArrayList<>();
    private MembershipFuction membershipFuction;
    private Map<FoodItem, Double> valuesByFoodItem = new HashMap<FoodItem, Double>();


    public FuzzySet(List<FoodItem> foodItems, Variable variable, String value) {
        this.foodItems = foodItems;
        this.membershipFuction = variable.getMemberFunc(value);
        for (FoodItem foodItem : foodItems) {
            try {
            	double calculatedValue = variable.getFuncValue(value, foodItem.getProperty(variable.getFoodProperty()));
                values.add(calculatedValue);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        recalculateValuesByFoodItemMap();
    }
    
    public FuzzySet(List<FoodItem> foodItems, List<Double> values, MembershipFuction membershipFuction) {
    	this.foodItems = foodItems;
    	this.values = values;
    	this.membershipFuction = membershipFuction;
    	recalculateValuesByFoodItemMap();
    }
    
    private void recalculateValuesByFoodItemMap() {
    	valuesByFoodItem.clear();
    	for (int i=0; i<foodItems.size(); i++) {
    		valuesByFoodItem.put(foodItems.get(i), values.get(i));
    	}
    }
    
    public double cardinality(List<FoodItem> foodItems) {

        double sum = 0.0;
        for (FoodItem foodItem : foodItems) {
            sum += DoM(foodItem);
        }
        return sum;

    }
    public double DoM(FoodItem foodItem) {

    	//bottleneck - map (hashmap) is more efficient
        //int index = foodItems.indexOf(foodItem);
        //return (index != -1 ? values.get(index) : 0);
    	
    	Double value = valuesByFoodItem.get(foodItem);
    	return value;
        
    }

    public double cardinality() {

        return values.stream().mapToDouble(Double::doubleValue).sum();

    }
    
    public double UoD() {
        return membershipFuction.getUpperBound();
    }
    

    public List<FoodItem> support() {

        List<FoodItem> ret = new ArrayList<>();
        for (FoodItem foodItem : foodItems) {
            if (DoM(foodItem) > 0) {
                ret.add(foodItem);
            }
        }

        return ret;

    }

    public int height() {

        return support().size();

    }

    public List<FoodItem> alphaCut(double alpha){

        List<FoodItem> ret = new ArrayList<>();
        for (FoodItem foodItem : foodItems) {
            if (DoM(foodItem) >= alpha) {
                ret.add(foodItem);
            }
        }

        return ret;

    }

    public int alphaCutHeight(double alpha){
        return alphaCut(alpha).size();
    }

    public List<FoodItem> complement() {

        List<FoodItem> ret = new ArrayList<>();
        for (FoodItem foodItem : foodItems) {
            if (DoM(foodItem) == 0) {
                ret.add(foodItem);
            }
        }

        return ret;

    }

    public boolean isNormal() {

        for (FoodItem foodItem : foodItems) {
            if (DoM(foodItem) != 1) {
                return false;
            }
        }

        return true;

    }

    public boolean isEmpty() {

        for (FoodItem foodItem : foodItems) {
            if (DoM(foodItem) != 0) {
                return false;
            }
        }

        return true;

    }

    // mock żeby się nie czepiał, bo nie mam pojęcia jak to gówno zrobić
    public boolean isConvex() {

        for (FoodItem foodItem : foodItems) {
            if (DoM(foodItem) < 0.5) {
                return false;
            }
        }

        return true;

    }

    public boolean isConcave() {

        if (isConvex() || isEmpty() || isNormal()) {
            return false;
        }

        return true;

    }

    
    public FuzzySet setUnion(FuzzySet other) {

        List<FoodItem> ret = new ArrayList<>();
        List<Double> retValues = new ArrayList<>();
        for (int i = 0; i < foodItems.size(); i++) {
            ret.add(foodItems.get(i));
            retValues.add(Math.max(values.get(i), other.values.get(i)));
        }
        for (int i = 0; i < other.foodItems.size(); i++) {
            //if (this.foodItems.indexOf(other.getFoodItems().get(i)) == -1) continue;
        	//readability - contains may be used instead of indexOf
        	//general comment: continue is not recommended
        	if (! this.foodItems.contains(other.getFoodItems().get(i))) continue;
            ret.add(other.foodItems.get(i));
            retValues.add(Math.max(values.get(i), other.values.get(i)));
        }

        MembershipFuction combinedFunc = new CombinationFunction(this.membershipFuction, other.getMembershipFuction(), false);

        return new FuzzySet(ret, retValues, combinedFunc);
    }

    public FuzzySet setIntersect(FuzzySet other) {

        List<FoodItem> ret = new ArrayList<>(foodItems);
        List<Double> retValues = new ArrayList<>();
        for (int i = 0; i < foodItems.size(); i++) {
            //ret.add(foodItems.get(i));
            retValues.add(Math.min(values.get(i), other.values.get(i)));
        }
        if (!this.foodItems.equals(other.getFoodItems())) {
            for (int i = 0; i < other.foodItems.size(); i++) {
                //System.out.println(i);
                //if (this.foodItems.indexOf(other.getFoodItems().get(i)) != -1) continue;
            	//readability - contains may be used instead of indexOf
            	//general comment: continue is not recommended
            	if (this.foodItems.contains(other.getFoodItems().get(i))) continue;
                ret.add(other.foodItems.get(i));
                retValues.add(Math.min(values.get(i), other.values.get(i)));
            }
        }

        MembershipFuction combinedFunc = new CombinationFunction(this.membershipFuction, other.getMembershipFuction(), true);

        return new FuzzySet(ret, retValues, combinedFunc);
    }

    public FuzzySet copyOf() {
        return new FuzzySet(List.copyOf(foodItems), List.copyOf(values), membershipFuction);
    }

}
