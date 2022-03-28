package ksr.grupa3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lombok.Data;

@Data
public class Dicts {
    
    private List<String> c1_dict = new ArrayList<String>();
    private List<String> c2_dict = new ArrayList<String>();
    private List<String> c3_dict = new ArrayList<String>();
    private List<String> c4_dict = new ArrayList<String>();
    private List<String> c5_dict = new ArrayList<String>();
    private List<String> c6_dict = new ArrayList<String>();
    private List<String> c7_dict = new ArrayList<String>();
    private List<String> c8_dict = new ArrayList<String>();
    private List<String> c9_dict = new ArrayList<String>();
    
    public Dicts(String dictPath) throws FileNotFoundException {
        Scanner c1 = new Scanner(new File(dictPath + "\\c1.txt"));
        while (c1.hasNextLine()) {
            this.c1_dict.add(c1.nextLine().toLowerCase());
        }
        c1.close();
        
        Scanner c2 = new Scanner(new File(dictPath + "\\c2.txt"));
        while (c2.hasNextLine()) {
            this.c2_dict.add(c2.nextLine().toLowerCase());
        }
        c2.close();
        
        Scanner c3 = new Scanner(new File(dictPath + "\\c3.txt"));
        while (c3.hasNextLine()) {
            this.c3_dict.add(c3.nextLine().toLowerCase());
        }
        c3.close();
        
        Scanner c4 = new Scanner(new File(dictPath + "\\c4.txt"));
        while (c4.hasNextLine()) {
            this.c4_dict.add(c4.nextLine().toLowerCase());
        }
        c4.close();
        
        Scanner c5 = new Scanner(new File(dictPath + "\\c5.txt"));
        while (c5.hasNextLine()) {
            this.c5_dict.add(c5.nextLine().toLowerCase());
        }
        c5.close();

        Scanner c6 = new Scanner(new File(dictPath + "\\c6.txt"));
        while (c6.hasNextLine()) {
            this.c6_dict.add(c6.nextLine().toLowerCase());
        }
        c6.close();

        Scanner c7 = new Scanner(new File(dictPath + "\\c7.txt"));
        while (c7.hasNextLine()) {
            this.c7_dict.add(c7.nextLine().toLowerCase());
        }
        c7.close();

        Scanner c8 = new Scanner(new File(dictPath + "\\c8.txt"));
        while (c8.hasNextLine()) {
            this.c8_dict.add(c8.nextLine().toLowerCase());
        }
        c8.close();

        Scanner c9 = new Scanner(new File(dictPath + "\\c9.txt"));
        while (c9.hasNextLine()) {
            this.c9_dict.add(c9.nextLine().toLowerCase());
        }
        c9.close();
    }
}