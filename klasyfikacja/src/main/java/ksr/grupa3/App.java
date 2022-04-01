package ksr.grupa3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// argumenty do wywołania: yarn <nazwa> <inne yern'owe rzeczy> [absolutePath pliku z artykułami : String] [odstetek artykułów do setu uczącego (0;1) : Double] [metryka : String] [parametr k : int] {cecha do pominięcia} 

public class App {
    public static void main(String[] args) throws IOException {

        // CLI zaczyna się tutaj?

        // zamiast tego dać podaną ścieżkę pliku z artykułami
        String absolutePath = "C:/Users/maste/Documents/GitHub/KSR/klasyfikacja/output.txt";

        // ścieżka do słowników
        String dictPath = "C:/Users/maste/Documents/GitHub/KSR/klasyfikacja/dicts";

        Partition part = new Partition(absolutePath);
        FeatureExtractor fe = new FeatureExtractor(dictPath);
        //Metric metric = new ChebyshevMetric();
        ConfusionMatrix matrix = new ConfusionMatrix();
        TextSimilarityMeasure measure = new TFM();
        
        
        ArrayList<Article> articles = new ArrayList<Article>();

        PropertiesList pList = new PropertiesList();

        Article art = part.getNextArticle();

        int tempLimit = 2000;

        int counter = 0;
        //while (art != null) {
        while (counter < tempLimit) {
            articles.add(art);
            art = part.getNextArticle();
            counter++;
        }

        for (Article a : articles) {
            pList.add(fe.extract(a));
        }

        KNN knn = new KNN(15, new EuclidianMetric(), measure, matrix, pList.createSets(0.7));

        knn.performKNN();

        CSVappender appender = new CSVappender(".\\data.csv");
        appender.append(matrix);

    }
}