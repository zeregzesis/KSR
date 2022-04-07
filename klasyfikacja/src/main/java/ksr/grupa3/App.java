package ksr.grupa3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class App {
    public static void main(String[] args) throws IOException {

        // interpretacja argumentów
        String absolutePath = args[0];
        String dictPath = args[1];
        double setSize = Float.parseFloat(args[2]);
        Metric metric;
        if ("M1".equals(args[3])) {
            // System.out.print("Here");
            metric = new EuclidianMetric();
        } else if ("M2".equals(args[3])) {
            metric = new ManhattanMetric();
        } else if ("M3".equals(args[3])) {
            metric = new ChebyshevMetric();
        } else {
            System.out.println("Niepoprawny argument metryki");
            return;
        }
        int k = Integer.parseInt(args[4]);
        List<String> featuresToIgnore = Arrays.asList(args).subList(5, args.length);

        // deklaracja potrzebnych obiektów
        Partition part = new Partition(absolutePath);
        FeatureExtractor fe = new FeatureExtractor(dictPath);
        PropertiesList pList = new PropertiesList();
        ArrayList<Article> articles = new ArrayList<Article>();
        TextSimilarityMeasure measure = new Trigram();
        CSVappender appender = new CSVappender(".\\data.csv");
        ConfusionMatrix matrix = new ConfusionMatrix();

        // wczytanie artykułów
        Article art = part.getNextArticle();
        while (art != null) {
            articles.add(art);
            art = part.getNextArticle();
        }

        // ekstrakcja cech
        for (Article a : articles) {
            pList.add(fe.extract(a, featuresToIgnore));
        }

        // k-NN
        KNN knn = new KNN(k, metric, measure, matrix, pList.createSets(setSize / 10.0));
        knn.performKNN();

        // zapis wyniku do pliku
        appender.append(matrix, k, metric.getClass().getSimpleName(), measure.getClass().getSimpleName(),
                setSize / 10.0);
        appender.close();

        System.out.println("Program zakończył działanie");
    }
}