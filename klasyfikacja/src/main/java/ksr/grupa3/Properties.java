package ksr.grupa3;
import java.util.Map;
import java.util.*;

import lombok.Data;

@Data
public class Properties{
    private Map<String, Integer> numericFeatures = new java.util.HashMap<String, Integer>();
    private Map<String, String> stringFeatures = new java.util.HashMap<String, String>();
    private Places label;

    public Properties() {
    }

    public void addToNumericFeatures(String key, Integer value) {
        this.numericFeatures.put(key, value);
    }

    public void addToStringFeatures(String key, String value) {
        this.stringFeatures.put(key, value);
    }

    public void setLabel(Places label) {
        this.label = label;
    }

    public List<Integer> getNumericValuesAsList(){
        return new ArrayList<Integer>(numericFeatures.values());
    }

    public List<String> getStringValuesAsList(){
        return new ArrayList<String>(stringFeatures.values());
    }

}