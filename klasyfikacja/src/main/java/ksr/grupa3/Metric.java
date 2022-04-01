package ksr.grupa3;

public abstract class Metric {
   abstract double calculate_distance_between_properties(Properties v1, Properties v2, TextSimilarityMeasure fun);

}