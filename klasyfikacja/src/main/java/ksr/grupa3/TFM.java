package ksr.grupa3;

public class TFM extends TextSimilarityMeasure {
    double sim(String s1, String s2) {

        if (s1 == s2 && s1!="") {
            return 1.0;
        } else {
            return 0.0;
        }

    }
}