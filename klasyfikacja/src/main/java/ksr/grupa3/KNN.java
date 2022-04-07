package ksr.grupa3;

import java.util.Map;
import java.util.Map.Entry;
import java.util.*;
import java.util.stream.Collectors;

public class KNN {
    int k;
    Metric metric;
    TextSimilarityMeasure textSimilarityMeasure;
    ConfusionMatrix confusionMatrix;
    PropertiesList train;
    PropertiesList test;

    KNN(int k, Metric m, TextSimilarityMeasure t, ConfusionMatrix mat, List<PropertiesList> sets) {
        this.k = k;
        this.metric = m;
        this.textSimilarityMeasure = t;
        this.confusionMatrix = mat;
        this.train = sets.get(0);
        this.test = sets.get(1);

    }

    void performKNN() {

        for (int i = 0; i < test.size(); i++) {
            Map<Integer, Double> distances = new HashMap<>();
            for (int j = 0; j < train.size(); j++) {
                distances.put(j, this.metric.calculate_distance_between_properties(test.getPropertiesList().get(i),
                        train.getPropertiesList().get(j), this.textSimilarityMeasure));

            }
            Map<Integer, Double> tmp = distances.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .limit(k)
                    .collect(Collectors.toMap(
                            Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

            List<Places> labels = new ArrayList<>();

            for (int key : tmp.keySet()) {
                labels.add(train.getPropertiesList().get(key).getLabel());
            }

            Places predicted = evaluate(labels);
            Places accual = test.getPropertiesList().get(i).getLabel();
            confusionMatrix.incrementConfmat(predicted, accual);

        }

    }

    private Places evaluate(List<Places> places) {
        Map<Places, Integer> map = new HashMap<>();
        for (Places place : places) {
            Integer val = map.get(place);
            map.put(place, val == null ? 1 : val + 1);
        }

        Entry<Places, Integer> max = null;

        for (Entry<Places, Integer> e : map.entrySet()) {
            if (max == null || e.getValue() > max.getValue())
                max = e;
        }

        return max.getKey();

    }

}