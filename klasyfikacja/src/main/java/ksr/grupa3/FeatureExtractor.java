package ksr.grupa3;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class FeatureExtractor {
    Dicts dictionaries;

    public FeatureExtractor(String dictPath) throws FileNotFoundException {
        dictionaries = new Dicts(dictPath);
    }

    public Properties extract(Article article, List<String> features) {
        Properties properties = new Properties();
        properties.setLabel(article.getLabel());
        String contents = article.getContents();
        properties.addToNumericFeatures("c10", 0);
        int count = 0, index = 0;

        Map<String, Integer> tempMap = new HashMap<String, Integer>();

        // cechy oparte o pierwsze wystąpienie
        if (!features.contains("c1")) {

            for (String key : dictionaries.getC1_dict()) {

                count = StringUtils.countMatches(contents, key);
                if (count > 0) {
                    index = StringUtils.indexOf(contents, key);
                    tempMap.put(key, index);
                }
                properties.addToNumericFeatures("c10", properties.getNumericFeatures().get("c10") + count);
            }
            properties.addToStringFeatures("c1", this.getMin(tempMap));
            tempMap.clear();
        }

        if (!features.contains("c2")) {

            for (String key : dictionaries.getC2_dict()) {

                count = StringUtils.countMatches(contents, key);
                if (count > 0) {
                    index = StringUtils.indexOf(contents, key);
                    tempMap.put(key, index);
                }
                properties.addToNumericFeatures("c10", properties.getNumericFeatures().get("c10") + count);
            }
            properties.addToStringFeatures("c2", this.getMin(tempMap));
            tempMap.clear();
        }
        if (!features.contains("c5")) {

            for (String key : dictionaries.getC5_dict()) {

                count = StringUtils.countMatches(contents, key);
                if (count > 0) {
                    index = StringUtils.indexOf(contents, key);
                    tempMap.put(key, index);
                }
                properties.addToNumericFeatures("c10", properties.getNumericFeatures().get("c10") + count);
            }
            properties.addToStringFeatures("c5", this.getMin(tempMap));
            tempMap.clear();
        }

        if (!features.contains("c6")) {
            for (String key : dictionaries.getC6_dict()) {

                count = StringUtils.countMatches(contents, key);
                if (count > 0) {
                    index = StringUtils.indexOf(contents, key);
                    tempMap.put(key, index);
                }
                properties.addToNumericFeatures("c10", properties.getNumericFeatures().get("c10") + count);
            }
            properties.addToStringFeatures("c6", this.getMin(tempMap));
            tempMap.clear();
        }

        if (!features.contains("c7")) {
            for (String key : dictionaries.getC7_dict()) {

                count = StringUtils.countMatches(contents, key);
                if (count > 0) {
                    index = StringUtils.indexOf(contents, key);
                    tempMap.put(key, index);
                }
                properties.addToNumericFeatures("c10", properties.getNumericFeatures().get("c10") + count);
            }
            properties.addToStringFeatures("c7", this.getMin(tempMap));
            tempMap.clear();
        }
        if (!features.contains("c8")) {
            for (String key : dictionaries.getC8_dict()) {

                count = StringUtils.countMatches(contents, key);
                if (count > 0) {
                    index = StringUtils.indexOf(contents, key);
                    tempMap.put(key, index);
                }
                properties.addToNumericFeatures("c10", properties.getNumericFeatures().get("c10") + count);
            }
            properties.addToStringFeatures("c8", this.getMin(tempMap));
            tempMap.clear();
        }

        // cechy oparte o najczęstrze występowanie
        if (!features.contains("c3")) {
            for (String key : dictionaries.getC3_dict()) {

                count = StringUtils.countMatches(contents, key);
                if (count > 0) {
                    tempMap.put(key, count);
                }
                properties.addToNumericFeatures("c10", properties.getNumericFeatures().get("c10") + count);
            }
            properties.addToStringFeatures("c3", this.getMax(tempMap));
            tempMap.clear();
        }

        if (!features.contains("c4")) {
            for (String key : dictionaries.getC4_dict()) {

                count = StringUtils.countMatches(contents, key);
                if (count > 0) {
                    tempMap.put(key, count);
                }
                properties.addToNumericFeatures("c10", properties.getNumericFeatures().get("c10") + count);
            }
            properties.addToStringFeatures("c4", this.getMax(tempMap));
            tempMap.clear();
        }

        if (!features.contains("c9")) {
            for (String key : dictionaries.getC9_dict()) {

                count = StringUtils.countMatches(contents, key);
                if (count > 0) {
                    tempMap.put(key, count);
                }
                properties.addToNumericFeatures("c10", properties.getNumericFeatures().get("c10") + count);
            }
            properties.addToStringFeatures("c9", this.getMax(tempMap));
            tempMap.clear();
        }

        return properties;
    }

    public String getMax(Map<String, Integer> map) {
        Map.Entry<String, Integer> maxEntry = null;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }
        if (maxEntry == null) {
            return "";
        }
        return maxEntry.getKey();
    }

    public String getMin(Map<String, Integer> map) {
        Map.Entry<String, Integer> minEntry = null;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (minEntry == null || entry.getValue() < minEntry.getValue()) {
                minEntry = entry;
            }
        }
        if (minEntry == null) {
            return "";
        }
        return minEntry.getKey();
    }
}
