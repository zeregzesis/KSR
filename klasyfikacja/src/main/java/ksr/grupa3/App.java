package ksr.grupa3;

import java.util.ArrayList;

public class App 
{
    public static void main( String[] args )
    {
        ArrayList<Article> articles = new ArrayList<Article>();

        String path = "klasyfikacja\\output.txt";

        Partition part = new Partition(path);

        Article art = part.getNextArticle();

        while (art != null) {
            articles.add(art);
            art = part.getNextArticle();
        }

        System.out.println(articles);

        // tutaj dorzucić k-NN
    }
}