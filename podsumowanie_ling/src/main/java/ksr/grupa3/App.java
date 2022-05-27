package ksr.grupa3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ksr.grupa3.fuzzy.FoodItem;
import ksr.grupa3.fuzzy.FuzzySet;
import ksr.grupa3.fuzzy.LingVariable;
import ksr.grupa3.lingSummary.CompoundSummary;
import ksr.grupa3.lingSummary.LingQuantifier;
import ksr.grupa3.lingSummary.LingSummary;
import ksr.grupa3.lingSummary.SingleSummary;
import ksr.grupa3.lingSummary.Summary;

//TODO: Przerobić UML żeby reprezentował stan faktyczny

public class App {
    public static void main(String[] args) throws IOException {

        // póki co zrobimy konsolowo, żeby móc to wgl testować
        // to co poniżej i tak trzeba będzie zakomentować żeby zrobić wszystkie domyślne kwantyfikatory i zmienne i je zserializować do pliku
        
        // tutaj trzeba będzie to zaczytać z pliku/bazy danych
        List<FoodItem> foodItems = new ArrayList<>();

        // tutaj trzeba będzie deserializować z pliku
        List<LingQuantifier> quantifiers = new ArrayList<>();
        List<LingVariable> lingVariables = new ArrayList<>();

        // wybór kwantyfikatora, zmiennej i jej wartości
        LingQuantifier lingQuantifier = quantifiers.get(Integer.parseInt(args[0]));
        LingVariable lingVariable = lingVariables.get(Integer.parseInt(args[1]));
        String value = args[2];

        FuzzySet fuzzySet = new FuzzySet(lingVariable, value);

        // zostanie stworzony w switchu
        LingSummary lingSummary;

        // Single czy Compound?
        switch(Integer.parseInt(args[2])){
            case 1:
                lingSummary = new SingleSummary(fuzzySet, lingQuantifier);
                break;
            case 2:
                lingSummary = new CompoundSummary(fuzzySet, lingQuantifier);
                break;
            default:
                break;
        }

        // zostanie stworzony w switchu przez lingSummary, póki co dałem to żeby print nie marudził że niezainicjalizowane
        Summary summary = null;

        // Której formy posdumowanie?
        switch(Integer.parseInt(args[3])) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                break;
        }

        System.out.println(summary.toString());
    }
}

// wrzuciłem to tutaj bo łatwiej będzie zrobić te wstępne kwantyfikatory na bazie tych case'ów

/*
    public double compatibility(double value){
        switch (quantifierType){
            case ALMOST_NONE:
                return Math.exp(-Math.pow(12.5 * value, 2));
            case SOME:
                return Math.exp(-Math.pow(12.5 * value - 3.125, 2));
            case ABOUT_HALF:
                return Math.exp(-Math.pow(12.5 * value - 6.25, 2));
            case MOST:
                return Math.exp(-Math.pow(12.5 * value - 9.375, 2));
            case ALMOST_ALL:
                return Math.exp(-Math.pow(12.5 * value - 12.5, 2));
            case MUCH_MORE_THAN_500:
                if (value < 500)
                    return 0;
                else if (value > 2000)
                    return 1;
                else
                    return (2.0/3000.0) * value - (1.0/3.0);
            case ABOUT_FEW_THOUSAND:
                if (value < 4000)
                    return Math.exp(-Math.pow(0.001 * value - 4, 2));
                else if (value > 6000)
                    return Math.exp(-Math.pow(0.001 * value - 6, 2));
                else
                    return 1;
            case MUCH_LESS_THAN_8000:
                if (value > 8000)
                    return 0;
                else if (value < 6000)
                    return 1;
                else
                    return 0.0005 * value + 4;
            default:
                return 0;
        }
    }*/