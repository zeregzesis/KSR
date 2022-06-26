package ksr.grupa3.dataLayer;

import java.util.ArrayList;
import java.util.List;
import java.io. *;

import ksr.grupa3.fuzzy.FoodItem;
import ksr.grupa3.fuzzy.FuzzySet;
import ksr.grupa3.lingSummary.LingQuantifier;
import lombok.Getter;
import lombok.Setter;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class CSVFileReader {

// String name,
//         int calories,
//         double fat,
//         double protein,
//         double carbohydrates,
//         double saturatedFats,
//         double calcium,
//         double iron,
//         double water,
//         double phosphorus,
//         double sodium


    public static List<FoodItem> readCsv() {
        try (CSVReader reader = new CSVReader(new FileReader("food_clean.csv"))) {
            List<String[]> r = reader.readAll();
            List<FoodItem> foodItems = new ArrayList<>();
            for ( int i=1;i<r.size();i++){
                foodItems.add(new FoodItem(r.get(i)[0],(int)Double.parseDouble(r.get(i)[1]),Double.parseDouble(r.get(i)[2]),Double.parseDouble(r.get(i)[3]),
                                            Double.parseDouble(r.get(i)[4]),Double.parseDouble(r.get(i)[5]),Double.parseDouble(r.get(i)[6]) ,Double.parseDouble(r.get(i)[7]),Double.parseDouble(r.get(i)[8]),
                                            Double.parseDouble(r.get(i)[9]),Double.parseDouble(r.get(i)[10])));

            }
            return foodItems;
        }
        catch (CsvException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        } 
        return null;

    }




}