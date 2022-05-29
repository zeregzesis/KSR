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

        public boolean equals(FoodItem other) {
            return this.name.equals(other.name);
        }
    }

