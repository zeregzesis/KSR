package ksr.grupa3;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import ksr.grupa3.dataLayer.CSVFileReader;
import ksr.grupa3.dataLayer.Init;
import ksr.grupa3.dataLayer.Serializer;

import ksr.grupa3.fuzzy.FoodItem;
import ksr.grupa3.fuzzy.newSet;
import ksr.grupa3.fuzzy.newVariable;
import ksr.grupa3.fuzzy.oldVariable;

import ksr.grupa3.ling.Quantifier;
import ksr.grupa3.ling.Agregator;
import ksr.grupa3.ling.Summary;
import ksr.grupa3.ling.SummaryBuilder;
import ksr.grupa3.ling.SummaryType;
import ksr.grupa3.ling.Subject;

public class App {
    
    public static void main(String[] args) throws IOException, NoSuchFieldException, SecurityException, ClassNotFoundException {

        Init.initialize();

        Random rand = new Random();
        
        List<Object> listOfObjects = Serializer.deserialize("quantifiers.ser");
        List<Quantifier> quantifiers = new ArrayList<>();
        for (Object object : listOfObjects) {
            quantifiers.add((Quantifier) object);
        }

        listOfObjects = Serializer.deserialize("variables.ser");
        List<oldVariable> variables = new ArrayList<>();
        for (Object object : listOfObjects) {
            variables.add((oldVariable) object);
        }

        listOfObjects = Serializer.deserialize("subjects.ser");
        List<Subject> subjects = new ArrayList<>();
        for (Object object : listOfObjects) {
            subjects.add((Subject) object);
        }

        List<FoodItem> foodItems = CSVFileReader.readCsv();

        

        List<Summary> firstForm = new ArrayList<>();
        List<Summary> secondForm = new ArrayList<>();
        List<Summary> compFirstForm = new ArrayList<>();
        List<Summary> compSecondForm = new ArrayList<>();
        List<Summary> compThirdForm = new ArrayList<>();
        List<Summary> compForthForm = new ArrayList<>();

        Quantifier forthFormQuantifier = quantifiers.remove(quantifiers.size() - 1);
        
        for (oldVariable variable : variables) {

            for (String value : variable.getValues()) {

                newSet set = new newSet(foodItems, variable, value);
                newVariable var = new newVariable(variable.getName(), variable.getFoodProperty(), value);
                Agregator summarizer = new Agregator(new ArrayList<newSet>(Arrays.asList(set)), new ArrayList<>(), new ArrayList<>(Arrays.asList(var)));

                for (Quantifier quantifier : quantifiers) {

                    Summary summary = SummaryBuilder.buildSummary(quantifier, List.of(subjects.get(0)), new Agregator(), summarizer, SummaryType.SINGLE_SUBJECT);

                    firstForm.add(summary);

                    /////////////////////////////////////////////////////////////////////////////////////////////////

                    oldVariable qualVar = variables.get(rand.nextInt(variables.size()));
                    
                    while (qualVar == variable) {
                        qualVar = variables.get(rand.nextInt(variables.size()));
                    }
                    newVariable newQualVar = new newVariable(qualVar.getName(), qualVar.getFoodProperty(), qualVar.getValues().get(rand.nextInt(qualVar.getValues().size())));
                    newSet qualSet = new newSet(foodItems, qualVar, newQualVar.getValue());
                    Agregator qualifier = new Agregator(new ArrayList<newSet>(Arrays.asList(qualSet)), new ArrayList<>(), new ArrayList<>(Arrays.asList(newQualVar)));

                    summary = SummaryBuilder.buildSummary(quantifier, List.of(subjects.get(0)), qualifier, summarizer, SummaryType.SINGLE_SUBJECT);

                    secondForm.add(summary);
                    

                    /////////////////////////////////////////////////////////////////////////////////////////////////

                    summary = SummaryBuilder.buildSummary(quantifier, List.of(subjects.get(1), subjects.get(2)), new Agregator(), summarizer, SummaryType.FIRST_FORM);

                    compFirstForm.add(summary);

                    /////////////////////////////////////////////////////////////////////////////////////////////////

                    qualVar = variables.get(rand.nextInt(variables.size()));
                    while (qualVar == variable) {
                        qualVar = variables.get(rand.nextInt(variables.size()));
                    }
                    newQualVar = new newVariable(qualVar.getName(), qualVar.getFoodProperty(), qualVar.getValues().get(rand.nextInt(qualVar.getValues().size())));
                    qualSet = new newSet(foodItems, qualVar, newQualVar.getValue());
                    qualifier = new Agregator(new ArrayList<newSet>(Arrays.asList(qualSet)), new ArrayList<>(), new ArrayList<>(Arrays.asList(newQualVar)));

                    summary = SummaryBuilder.buildSummary(quantifier, List.of(subjects.get(1), subjects.get(2)), qualifier, summarizer, SummaryType.SECOND_FORM);

                    compSecondForm.add(summary);

                    /////////////////////////////////////////////////////////////////////////////////////////////////

                    qualVar = variables.get(rand.nextInt(variables.size()));
                    while (qualVar == variable) {
                        qualVar = variables.get(rand.nextInt(variables.size()));
                    }
                    newQualVar = new newVariable(qualVar.getName(), qualVar.getFoodProperty(), qualVar.getValues().get(rand.nextInt(qualVar.getValues().size())));
                    qualSet = new newSet(foodItems, qualVar, newQualVar.getValue());
                    qualifier = new Agregator(new ArrayList<newSet>(Arrays.asList(qualSet)), new ArrayList<>(), new ArrayList<>(Arrays.asList(newQualVar)));

                    summary = SummaryBuilder.buildSummary(quantifier, List.of(subjects.get(1), subjects.get(2)), qualifier, summarizer, SummaryType.THIRD_FORM);

                    compThirdForm.add(summary);
                }

                

                Summary summary = SummaryBuilder.buildSummary(forthFormQuantifier, List.of(subjects.get(1), subjects.get(2)), new Agregator(), summarizer, SummaryType.FOURTH_FORM);

                compForthForm.add(summary);

            }
        }

        System.out.println("All done");

        FileWriter first = new FileWriter("first.txt");
        FileWriter second = new FileWriter("second.txt");
        FileWriter compFirst = new FileWriter("compFirst.txt");
        FileWriter compSecond = new FileWriter("compSecond.txt");
        FileWriter compThird = new FileWriter("compThird.txt");
        FileWriter compForth = new FileWriter("compForth.txt");

        

        //firstForm.sort(Comparator.comparingDouble(s -> s.getStandardMeasures().get(0)));
        

        //secondForm.sort(Comparator.comparingDouble(s -> s.getStandardMeasures().get(0)));
        

        //compFirstForm.sort(Comparator.comparingDouble(s -> s.getCompoundMeasure().get(0)));
        

        //compSecondForm.sort(Comparator.comparingDouble(s -> s.getCompoundMeasure().get(0)));
        

        //compThirdForm.sort(Comparator.comparingDouble(s -> s.getCompoundMeasure().get(0)));
        

        //compForthForm.sort(Comparator.comparingDouble(s -> s.getCompoundMeasure().get(0)));
        

        
        for (Summary s : firstForm) {
            first.write(s.generateLinguisticSummary() + "\n");
        }
        System.out.println("first measures done");

        for (Summary s : secondForm) {
            second.write(s.generateLinguisticSummary() + "\n");
        }
        System.out.println("second measures done");

        for (Summary s : compFirstForm) {
            compFirst.write(s.generateLinguisticSummary() + "\n");
        }
        System.out.println("compound first measures done");

        for (Summary s : compSecondForm) {
            compSecond.write(s.generateLinguisticSummary() + "\n");
        }
        System.out.println("compound second measures done");

        for (Summary s : compThirdForm) {
            compThird.write(s.generateLinguisticSummary() + "\n");
        }
        System.out.println("compound third measures done");

        for (Summary s : compForthForm) {
            compForth.write(s.generateLinguisticSummary() + "\n");
        }
        System.out.println("compound forth measures done");

        first.close();
        second.close();
        compFirst.close();
        compSecond.close();
        compThird.close();
        compForth.close();

        System.out.println("Measures saved");
    }
}