package ksr.grupa3;

import java.util.*;

public class ConfusionMatrix {

    private int[][] confmat = new int[6][6];

    ConfusionMatrix() {
    }

    public int[][] getConfmat() {
        return confmat;
    }

    public void incrementConfmat(Places predicted, Places accual) {
        confmat[predicted.label][accual.label] += 1;
    }

    public void print() {
        for (int i = 0; i < 6; i++) {

            for (int j = 0; j < 6; j++) {
                System.out.print(confmat[i][j]);
            }
            System.out.println();
        }
    }

    public double calculateAccuracy() {
        double allTPs = Places.stream().mapToDouble(place -> confmat[place.label][place.label]).sum();
        
        double sumOfAllItems = 0.0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                sumOfAllItems += confmat[i][j];
            }
        }
        if (sumOfAllItems == 0.0)
            return 0;
        else
            return allTPs / sumOfAllItems;
    }

    private int getTP(Places place) {
        return confmat[place.label][place.label];
    }

    private int getFP(Places place) {
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            sum += confmat[place.label][i];
        }
        sum -= confmat[place.label][place.label];

        return sum;
    }

    private int getFN(Places place) {
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            sum += confmat[i][place.label];
        }
        sum -= confmat[place.label][place.label];

        return sum;
    }

    private int getTN(Places place) {
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++)
                sum += confmat[i][j];
        }
        sum -= (getTP(place) + getFN(place) + getFP(place));
        return sum;
    }

    public double calculatePrecisionForCountry(Places place) {
        double denominator = getTP(place) + getFP(place);
        if (denominator == 0)
            return 0.0;
        return getTP(place) / denominator;
    }

    public double calculateRecallForCountry(Places place) {
        double denominator = getTP(place) + getFN(place);
        if (denominator == 0)
            return 0.0;
        return getTP(place) / denominator;
    }

    public double calculateF1ScoreForCountry(Places place) {
        double precision = calculatePrecisionForCountry(place);
        double recall = calculateRecallForCountry(place);
        if (precision + recall == 0.0)
            return 0.0;

        return 2 * (precision * recall) / (precision + recall);
    }

    private int numberOfAllArticlesClassified() {
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                sum += confmat[i][j];
            }
        }
        return sum;
    }

    public double calculatePrecisionForClassification() {

        double nominator = 0.0;
        for (Places place : Places.values()) {
            nominator += (calculatePrecisionForCountry(place)
                    * ((getFN(place) + getTP(place)) / (double) numberOfAllArticlesClassified()));
        }
        return nominator / 6;
    }

    public double calculateRecallForClassification() {

        double nominator = 0.0;
        for (Places place : Places.values()) {
            nominator += (calculateRecallForCountry(place)
                    * ((getFN(place) + getTP(place)) / (double) numberOfAllArticlesClassified()));
        }
        return nominator / 6;
    }

    public double calculateF1scoreForClassification() {

        double nominator = 0.0;
        for (Places place : Places.values()) {
            nominator += (calculateF1ScoreForCountry(place)
                    * ((getFN(place) + getTP(place)) / (double) numberOfAllArticlesClassified()));
        }
        return nominator / 6;
    }

    public List<Double> generateResults() {
        List<Double> results = new ArrayList<Double>();
        results.add(calculateAccuracy());
        results.add(calculatePrecisionForClassification());
        results.add(calculateRecallForClassification());
        results.add(calculateF1scoreForClassification());
        for (Places place : Places.values()) {
            results.add(calculatePrecisionForCountry(place));
            results.add(calculateRecallForCountry(place));
            results.add(calculateF1ScoreForCountry(place));
        }
        return results;
    }
}
