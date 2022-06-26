package ksr.grupa3.fuzzy;

import java.lang.reflect.Field;

public record FoodItem
    (
        String name,
        int calories,
        double fat,
        double protein,
        double carbohydrates,
        double saturatedFats,
        double calcium,
        double iron,
        double water,
        double phosphorus,
        double sodium
    )
    {
        public double getProperty(Field field) {
            try {
                return field.getDouble(this);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return -1;
        }

        @Override
        public boolean equals(Object other) {
            if (other == null) {
                return false;
            }
            if (other.getClass() != this.getClass()) {
                return false;
            }
            FoodItem otherFoodItem = (FoodItem) other;
            return this.name.equals(otherFoodItem.name);
        }
        
        //if equals returns true then hashCode should return the same value. Opposite implication need not be true. 
        public int hashCode() {
        	return (name == null) ? 0 : name.hashCode();
        }
        
    }

