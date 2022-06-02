package ksr.grupa3;

//import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;
//import java.util.Random;

//import ksr.grupa3.dataLayer.CSVFileReader;
import ksr.grupa3.dataLayer.Init;
import ksr.grupa3.dataLayer.Serializer;

//import ksr.grupa3.fuzzy.FoodItem;
//import ksr.grupa3.fuzzy.FuzzySet;
import ksr.grupa3.fuzzy.LingVariable;

import ksr.grupa3.ling.Quantifier;
//import ksr.grupa3.ling.Qualifier;
//import ksr.grupa3.ling.Summarizer;
//import ksr.grupa3.ling.Summary;
import ksr.grupa3.ling.Subject;


//TODO: Przerobić UML żeby reprezentował stan faktyczny

public class App {
    
    public static void main(String[] args) throws IOException, NoSuchFieldException, SecurityException, ClassNotFoundException {

        Init.initialize();

        //Random rand = new Random();
        
        List<Object> listOfObjects = Serializer.deserialize("quantifiers.ser");
        List<Quantifier> quantifiers = new ArrayList<>();
        for (Object object : listOfObjects) {
            quantifiers.add((Quantifier) object);
        }

        listOfObjects = Serializer.deserialize("variables.ser");
        List<LingVariable> variables = new ArrayList<>();
        for (Object object : listOfObjects) {
            variables.add((LingVariable) object);
        }

        listOfObjects = Serializer.deserialize("subjects.ser");
        List<Subject> subjects = new ArrayList<>();
        for (Object object : listOfObjects) {
            subjects.add((Subject) object);
        }

        /*  REWORK!!!
        List<FoodItem> foodItems = CSVFileReader.readCsv();

        LingVariable lingVariable;
        LingVariable secondLingVariable;
        String value;
        FuzzySet set;
        Summarizer summarizer;
        Summary summary;

        List<String> firstFormSummaries = new ArrayList<>();
        List<String> secondFormSummaries = new ArrayList<>();

        
        for (Quantifier quantifier : quantifiers) {

            //first form
            lingVariable = variables.get(rand.nextInt(variables.size()));
            value = lingVariable.getValues().get(rand.nextInt(lingVariable.getValues().size()));
            set = new FuzzySet(lingVariable, value);
            summarizer = new Summarizer(new ArrayList<>(Arrays.asList(set)), new ArrayList<>());
            //secondLingVariable = variables.get(rand.nextInt(variables.size()));
            secondLingVariable = variables.get(6);
            summarizer.addSet(new FuzzySet(secondLingVariable, secondLingVariable.getValues().get(0)), true);
            summary = new Summary(quantifier, List.of(subjects.get(0)), new Qualifier(), summarizer);
            firstFormSummaries.add(summary.constructSummary(List.copyOf(foodItems)));
            

            //second form
            lingVariable = variables.get(rand.nextInt(variables.size()));
            value = lingVariable.getValues().get(rand.nextInt(lingVariable.getValues().size()));
            Qualifier qualifier = new Qualifier(new ArrayList<>(Arrays.asList(new FuzzySet(lingVariable, value))), new ArrayList<>());
            summary = new Summary(quantifier, List.of(subjects.get(0)), qualifier, summarizer);
            secondFormSummaries.add(summary.constructSummary(List.copyOf(foodItems)));
        }

        FileWriter firstform = new FileWriter("first.txt");
        for (String s : firstFormSummaries) {
            firstform.write(s + "\n");
        }
        firstform.close();
        
         FileWriter secondform = new FileWriter("second.txt");
        for (String s : secondFormSummaries) {
           secondform.write(s + "\n");
        }
        secondform.close();
    }*/
    }
}