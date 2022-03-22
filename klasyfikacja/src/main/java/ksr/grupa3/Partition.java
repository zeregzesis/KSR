package ksr.grupa3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Partition {
    private String filepath;
    String content;
    private int startIndex = 0;
    private int endIndex = 0;
    

    public Partition(String path) {
        this.filepath = path;
        try{
            this.getFileContents();
        }
        catch (FileNotFoundException nofile) {
            System.out.println("Failed to open file");
        }
    }

    public void setFilepath(String newPath) {
        this.filepath = newPath;
    }

    public void getFileContents() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(this.filepath));
        this.content = scanner.useDelimiter("\\Z").next();
        scanner.close();
    }

    public Article getNextArticle() {

        this.startIndex = this.content.indexOf("<REUTERS ", this.endIndex);
        
        //System.out.println(this.startIndex);

        if (this.startIndex == -1) return null;

        this.endIndex = content.indexOf("</REUTERS>", this.startIndex);

        int placeStartIndex = content.indexOf("<D>", this.startIndex) + 3;
        int placeEndIndex = content.indexOf("</D>", placeStartIndex);

        Places label = Places.valueOf(this.content.substring(placeStartIndex, placeEndIndex));

        // ekstrakcja cech

        // Properties props = new Properties( tutaj wyciągnięte cechy i label );

        // Article art = new Article(this.content.substring(this.startIndex, this.endIndex), props);

        return null;    // tutaj będzie zwracany obiekt Article z cechami, póki co jest null żeby java się nie czepiała że funkcja nic nie zwraca
    }

}