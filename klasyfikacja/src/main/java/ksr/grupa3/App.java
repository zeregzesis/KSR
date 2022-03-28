package ksr.grupa3;

import java.io.FileNotFoundException;
import java.util.ArrayList;

// argumenty do wywołania: yarn <nazwa> <inne yern'owe rzeczy> [absolutePath pliku z artykułami : String] [odstetek artykułów do setu uczącego (0;1) : Double] [metryka : String] [parametr k : int] {cecha do pominięcia} 

public class App 
{
    public static void main( String[] args ) throws FileNotFoundException
    {

        // CLI zaczyna się tutaj?

        // zamiast tego dać podaną ścieżkę pliku z artykułami
        String absolutePath = "C:/Users/maste/Documents/GitHub/KSR/klasyfikacja/output.txt";

        // ścieżka do słowników
        String dictPath = "C:/Users/maste/Documents/GitHub/KSR/klasyfikacja/dicts";

        ArrayList<Article> articles = new ArrayList<Article>();

        Partition part = new Partition(absolutePath);

        Dicts dicts = new Dicts(dictPath);

        //System.out.print(dicts.getC7_dict());

        FeatureExtractor fe = new FeatureExtractor(dictPath);

        Article art = part.getNextArticle();

        //System.out.print(art.getContents());

        Properties p1 = fe.extract(art);

        System.out.println(p1.getNumericFeatures());
        System.out.println(p1.getStringFeatures());

        // while (art != null) {

        //     //coś

        //     articles.add(art);
        //     art = part.getNextArticle();
        // }

        // tutaj odpalić ekstrakcję

        //tutaj podzielić na set uczący i testowy

        // tutaj odpalić k-NN dla podanego 'k', metryki i odpowiednich cech

        // tutaj liczenie i wypisanie miar klasyfikacji(dokładność itd.) i zapis do .csv
        
    }
}