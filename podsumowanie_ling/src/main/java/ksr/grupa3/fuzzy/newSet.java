package ksr.grupa3.fuzzy;

import java.util.ArrayList;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class newSet {

    //private MembershipFuction membershipFuction;
    //private Field foodProperty;
    private List<FoodItem> foodItems;
    private List<Double> values = new ArrayList<>();

    public newSet(List<FoodItem> foodItems, oldVariable variable, String value) {
        this.foodItems = foodItems;
        for (FoodItem foodItem : foodItems) {
            try {
                values.add(variable.getFuncValue(value, foodItem.getProperty(variable.getFoodProperty())));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    public double DoM(FoodItem foodItem) {

        int index = foodItems.indexOf(foodItem);

        return (index != -1 ? values.get(index) : 0);
        
    }

    public double cardinality() {

        double ret = 0.0;
        for (FoodItem foodItem : foodItems) {
            ret += DoM(foodItem);
        }

        return ret;

    }
    /*
    public List<Double> UoD() {
        List<Double> ret = new ArrayList<>();
        ret.add(membershipFuction.getBegin());
        ret.add(membershipFuction.getEnd());
        return ret;
    }
    */

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

    
    public newSet setUnion(newSet other) {

        List<FoodItem> ret = new ArrayList<>();
        List<Double> retValues = new ArrayList<>();
        for (int i = 0; i < foodItems.size(); i++) {
            ret.add(foodItems.get(i));
            retValues.add(Math.max(values.get(i), other.values.get(i)));
        }
        for (int i = 0; i < other.foodItems.size(); i++) {
            if (this.foodItems.indexOf(other.getFoodItems().get(i)) == -1) continue;
            ret.add(other.foodItems.get(i));
            retValues.add(Math.max(values.get(i), other.values.get(i)));
        }        

        return new newSet(ret, retValues);
    }

    public newSet setIntersect(newSet other) {

        List<FoodItem> ret = new ArrayList<>();
        List<Double> retValues = new ArrayList<>();
        for (int i = 0; i < foodItems.size(); i++) {
            ret.add(foodItems.get(i));
            retValues.add(Math.min(values.get(i), other.values.get(i)));
        }
        for (int i = 0; i < other.foodItems.size(); i++) {
            if (this.foodItems.indexOf(other.getFoodItems().get(i)) == -1) continue;
            ret.add(other.foodItems.get(i));
            retValues.add(Math.min(values.get(i), other.values.get(i)));
        }        

        return new newSet(ret, retValues);
    }

    

}
