package ksr.grupa3;

public class Trigram extends TextSimilarityMeasure {

    public double sim(String s1, String s2) {
        if (s1.equals(s2)) {
            return 1.0;
        }
        if (s1 == "" || s2 == "") {
            return 0.0;
        }
        if (s1.length() < s2.length()) {
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }
        double result = 0;
        double total = 0;
        for (int i = 0; i < s1.length() - 2; i++) {
            total++;
            for (int j = 0; j < s2.length() - 2; j++) {
                if (s1.substring(i, i + 3).equals(s2.substring(j, j + 3))) {
                    result++;
                }
            }
        }
        return result / total;
    }
}
