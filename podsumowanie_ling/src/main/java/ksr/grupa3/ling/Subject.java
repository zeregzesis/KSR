package ksr.grupa3.ling;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import ksr.grupa3.fuzzy.FoodItem;
import lombok.Data;

@Data
public class Subject implements Serializable{
    
    private String toFind;
    private String name;
    private boolean neg;

    public Subject(String name) {
        this.name = name;
        int begin = name.indexOf("\"");
        int end = name.lastIndexOf("\"");
        this.toFind = (begin != -1 ? name.substring(begin + 1, end) : "");
        neg = (name.indexOf("don't") == -1 ? false : true);
    }


    public boolean isPartOf(FoodItem foodItem) {
        return (neg ? !foodItem.name().contains(toFind) : foodItem.name().contains(toFind));
    }

    public List<FoodItem> filter(List<FoodItem> foodItems) {
        return foodItems.stream().filter(this::isPartOf).collect(Collectors.toList());
    }
}
