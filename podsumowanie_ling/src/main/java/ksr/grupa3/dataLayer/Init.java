package ksr.grupa3.dataLayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ksr.grupa3.fuzzy.FoodItem;
import ksr.grupa3.fuzzy.GaussFuction;
import ksr.grupa3.fuzzy.oldVariable;
import ksr.grupa3.fuzzy.MembershipFuction;
import ksr.grupa3.fuzzy.TrapezoidFuction;
import ksr.grupa3.ling.Quantifier;
import ksr.grupa3.ling.Subject;

public class Init {

    public static void initialize() throws NoSuchFieldException, SecurityException, IOException {

        List<Subject> subjects = new ArrayList<>();
        List<Quantifier> quantifiers = new ArrayList<>();
        List<oldVariable> variables = new ArrayList<>();


        Subject subject = new Subject("all products");
        subjects.add(subject);

        Subject subject2 = new Subject("products that have \"Meat\" in their name");
        subjects.add(subject2);

        Subject subject3 = new Subject("products that don't have \"Meat\" in their name");
        subjects.add(subject3);


        Quantifier almostNone = new Quantifier();
        // almostNone.setName("Almost none");
        // SubFunc zero = new SubFunc("0", 0, 0);
        // SubFunc value = new SubFunc("exp(-(12.5 * x)^2)", 0, 1);
        // SubFunc zero2 = new SubFunc("0", 0.25, 1);
        // MemberFunc memberFunc = new MemberFunc(List.of(zero, value, zero2));
        MembershipFuction memberFunc = new GaussFuction(1, 0.0, 0.056);
        almostNone.setMemberFunc(memberFunc);
        almostNone.setIsAbsolute(false);
        quantifiers.add(almostNone);

        Quantifier some = new Quantifier();
        // some.setName("Some");
        // zero = new SubFunc("0", 0, 0);
        // value = new SubFunc("exp(-(12.5 * x - 3.125)^2)", 0, 0.5);
        // zero2 = new SubFunc("0", 0.5, 1);
        // memberFunc = new MemberFunc(List.of(zero, value, zero2));
        memberFunc = new GaussFuction(1, 0.25, 0.056);
        some.setMemberFunc(memberFunc);
        some.setIsAbsolute(false);
        quantifiers.add(some);

        Quantifier aboutHalf = new Quantifier();
        // aboutHalf.setName("About half");
        // zero = new SubFunc("0", 0, 0.25);
        // value = new SubFunc("exp(-(12.5 * x - 6.25)^2)", 0.25, 0.75);
        // zero2 = new SubFunc("0", 0.75, 1);
        // memberFunc = new MemberFunc(List.of(zero, value, zero2));
        memberFunc = new GaussFuction(1, 0.5, 0.056);
        aboutHalf.setMemberFunc(memberFunc);
        aboutHalf.setIsAbsolute(false);
        quantifiers.add(aboutHalf);

        Quantifier most = new Quantifier();
        // most.setName("Most");
        // zero = new SubFunc("0", 0, 0.5);
        // value = new SubFunc("exp(-(12.5 * x - 9.375)^2)", 0.5, 1);
        // zero2 = new SubFunc("0", 1, 1);
        // memberFunc = new MemberFunc(List.of(zero, value, zero2));
        memberFunc = new GaussFuction(1, 0.75, 0.056);
        most.setMemberFunc(memberFunc);
        most.setIsAbsolute(false);
        quantifiers.add(most);

        Quantifier almostAll = new Quantifier();
        // almostAll.setName("Almost all");
        // zero = new SubFunc("0", 0, 0.75);
        // value = new SubFunc("exp(-(12.5 * x - 12.5)^2)", 0.75, 1.001);
        // zero2 = new SubFunc("0", 1.001, 1.001);
        // memberFunc = new MemberFunc(List.of(zero, value, zero2));
        memberFunc = new GaussFuction(1, 1.0, 0.056);
        almostAll.setMemberFunc(memberFunc);
        almostAll.setIsAbsolute(false);
        quantifiers.add(almostAll);

        Quantifier muchMoreThan500 = new Quantifier();
        // muchMoreThan500.setName("Much more than 500");
        // zero = new SubFunc("0", 0, 500);
        // value = new SubFunc("(2.0/3000.0) * x - (1.0/3.0)", 500, 2000);
        // SubFunc one = new SubFunc("1", 2000, 13544);
        // zero2 = new SubFunc("0", 13544, 13544);
        // memberFunc = new MemberFunc(List.of(zero, value, one, zero2));
        memberFunc = new TrapezoidFuction(500, 2000, 13544, 13544);
        muchMoreThan500.setMemberFunc(memberFunc);
        muchMoreThan500.setIsAbsolute(true);
        quantifiers.add(muchMoreThan500);

        Quantifier muchLessThan8000 = new Quantifier();
        // muchLessThan8000.setName("Much less than 8000");
        // zero = new SubFunc("0", -1, 0);
        // one = new SubFunc("1", 0, 6000);
        // value = new SubFunc("-0.0005 * x + 4", 6000, 8000);
        // zero2 = new SubFunc("0", 8000, 13544);
        // memberFunc = new MemberFunc(List.of(zero, value, one, zero2));
        memberFunc = new TrapezoidFuction(0, 0, 6000, 8000);
        muchLessThan8000.setMemberFunc(memberFunc);
        muchLessThan8000.setIsAbsolute(true);
        quantifiers.add(muchLessThan8000);

        Quantifier aboutFewThousand = new Quantifier();
        // aboutFewThousand.setName("Much less than 8000");
        // zero = new SubFunc("0", -1, 0);
        // value = new SubFunc("exp(-(0.001 * x - 4)^2)", 0, 4000);
        // one = new SubFunc("1", 4000, 6000);
        // SubFunc value2 = new SubFunc("exp(-(0.001 * x - 4)^2)", 6000, 13544);
        // zero2 = new SubFunc("0", 13544, 13544);
        // memberFunc = new MemberFunc(List.of(zero, value, one, value2, zero2));
        memberFunc = new GaussFuction(2, 5000, 850);
        aboutFewThousand.setMemberFunc(memberFunc);
        aboutFewThousand.setIsAbsolute(true);
        quantifiers.add(aboutFewThousand);

        Quantifier moreOf = new Quantifier();
        moreOf.setName("More of");
        moreOf.setIsForthForm(true);
        quantifiers.add(moreOf);

        /////////////////////////////////////////////////////////////////////

        FoodItem foodItem = new FoodItem("mock", 0,0,0,0,0,0,0,0,0,0);

        List<MembershipFuction> memberFuncs = new ArrayList<>();
        //List<SubFunc> subFuncs = new ArrayList<>();

// calories
        
        List<String> values = List.of("low", "medium", "high");

        // low
        SubFunc temp = new SubFunc("0", -1, 0);
        subFuncs.add(temp);
        temp = new SubFunc("1", 0, 85);
        subFuncs.add(temp);
        temp = new SubFunc("-0.009 * x + 1.739", 85, 200);
        subFuncs.add(temp);
        temp = new SubFunc("0", 200, 903);
        subFuncs.add(temp);

        memberFunc = new MemberFunc(List.copyOf(subFuncs));
        memberFuncs.add(memberFunc);
        subFuncs.clear();

        // medium
        temp = new SubFunc("0", 0, 150);
        subFuncs.add(temp);
        temp = new SubFunc("exp(-(x - 300)^2 / 50^2)", 150, 450);
        subFuncs.add(temp);
        temp = new SubFunc("0", 450, 903);
        subFuncs.add(temp);

        memberFunc = new MemberFunc(List.copyOf(subFuncs));
        memberFuncs.add(memberFunc);
        subFuncs.clear();

        // high
        temp = new SubFunc("0", 0, 350);
        subFuncs.add(temp);
        temp = new SubFunc("exp(-(x - 300)^2 / 50^2)", 350, 500);
        subFuncs.add(temp);
        temp = new SubFunc("1", 500, 903);
        subFuncs.add(temp);
        temp = new SubFunc("0", 903, 903);
        subFuncs.add(temp);

        memberFunc = new MemberFunc(List.copyOf(subFuncs));
        memberFuncs.add(memberFunc);
        subFuncs.clear();


        oldVariable caloricValue = new oldVariable("caloric value", foodItem.getClass().getDeclaredField("calories"), values, List.copyOf(memberFuncs));

        variables.add(caloricValue);

        memberFuncs.clear();

// fat
        
        values = List.of("close to none", "low", "medium", "high");

        // close to none
        temp = new SubFunc("0", -1, 0);
        subFuncs.add(temp);
        temp = new SubFunc("1", 0, 1);
        subFuncs.add(temp);
        temp = new SubFunc("-x + 2", 1, 2);
        subFuncs.add(temp);
        temp = new SubFunc("0", 2, 101);
        subFuncs.add(temp);

        memberFunc = new MemberFunc(List.copyOf(subFuncs));
        memberFuncs.add(memberFunc);
        subFuncs.clear();

        // low
        temp = new SubFunc("0", 0, 1);
        subFuncs.add(temp);
        temp = new SubFunc("0.25 * x - 0.25", 1, 5);
        subFuncs.add(temp);
        temp = new SubFunc("1", 5, 8);
        subFuncs.add(temp);
        temp = new SubFunc("-0.25 * x + 3", 8, 12);
        subFuncs.add(temp);
        temp = new SubFunc("0", 12, 101);
        subFuncs.add(temp);

        memberFunc = new MemberFunc(List.copyOf(subFuncs));
        memberFuncs.add(memberFunc);
        subFuncs.clear();

        // medium
        temp = new SubFunc("0", 0, 7);
        subFuncs.add(temp);
        temp = new SubFunc("0.25 * x - 7/3", 7, 11);
        subFuncs.add(temp);
        temp = new SubFunc("-0.026 * x + 1.282", 11, 50);
        subFuncs.add(temp);
        temp = new SubFunc("0", 50, 101);
        subFuncs.add(temp);

        memberFunc = new MemberFunc(List.copyOf(subFuncs));
        memberFuncs.add(memberFunc);
        subFuncs.clear();

        // high
        temp = new SubFunc("0", 0, 20);
        subFuncs.add(temp);
        temp = new SubFunc("0.05 * x - 1", 20, 40);
        subFuncs.add(temp);
        temp = new SubFunc("1", 40, 101);
        subFuncs.add(temp);
        temp = new SubFunc("0", 101, 101);
        subFuncs.add(temp);

        memberFunc = new MemberFunc(List.copyOf(subFuncs));
        memberFuncs.add(memberFunc);
        subFuncs.clear();


        oldVariable fatContent = new oldVariable("Fat content", foodItem.getClass().getDeclaredField("fat"), values, List.copyOf(memberFuncs));

        variables.add(fatContent);
        memberFuncs.clear();

// protein
        
    values = List.of("low", "medium", "high");

    // low
    temp = new SubFunc("0", -1, 0);
    subFuncs.add(temp);
    temp = new SubFunc("1/6 * x", 0, 6);
    subFuncs.add(temp);
    temp = new SubFunc("-1/4 * x + 2.5", 6, 10);
    subFuncs.add(temp);
    temp = new SubFunc("0", 10, 89);
    subFuncs.add(temp);

    memberFunc = new MemberFunc(List.copyOf(subFuncs));
    memberFuncs.add(memberFunc);
    subFuncs.clear();

    // medium
    temp = new SubFunc("0", -1, 0);
    subFuncs.add(temp);
    temp = new SubFunc("exp(-(x-10)^2 / 16)", 0, 20);
    subFuncs.add(temp);
    temp = new SubFunc("0", 20, 89);
    subFuncs.add(temp);

    memberFunc = new MemberFunc(List.copyOf(subFuncs));
    memberFuncs.add(memberFunc);
    subFuncs.clear();

    // high
    temp = new SubFunc("0", 0, 10);
    subFuncs.add(temp);
    temp = new SubFunc("0.1 * x - 1", 10, 20);
    subFuncs.add(temp);
    temp = new SubFunc("1", 20, 101);
    subFuncs.add(temp);
    temp = new SubFunc("0", 101, 101);
    subFuncs.add(temp);

    memberFunc = new MemberFunc(List.copyOf(subFuncs));
    memberFuncs.add(memberFunc);
    subFuncs.clear();


    oldVariable proteinContent = new oldVariable("Protein content", foodItem.getClass().getDeclaredField("protein"), values, List.copyOf(memberFuncs));

    variables.add(proteinContent);
    memberFuncs.clear();

// carbohydrate
        
    values = List.of("close to none", "low", "medium", "high");

    // close to none
    temp = new SubFunc("0", -1, 0);
    subFuncs.add(temp);
    temp = new SubFunc("-x + 1", 0, 1);
    subFuncs.add(temp);
    temp = new SubFunc("0", 1, 101);
    subFuncs.add(temp);

    memberFunc = new MemberFunc(List.copyOf(subFuncs));
    memberFuncs.add(memberFunc);
    subFuncs.clear();

    // low
    temp = new SubFunc("0", -1, 0);
    subFuncs.add(temp);
    temp = new SubFunc("0.2 * x", 0, 5);
    subFuncs.add(temp);
    temp = new SubFunc("1", 5, 10);
    subFuncs.add(temp);
    temp = new SubFunc("-0.2 * x + 3", 10, 15);
    subFuncs.add(temp);
    temp = new SubFunc("0", 15, 101);
    subFuncs.add(temp);

    memberFunc = new MemberFunc(List.copyOf(subFuncs));
    memberFuncs.add(memberFunc);
    subFuncs.clear();

    // medium
    temp = new SubFunc("0", 0, 15);
    subFuncs.add(temp);
    temp = new SubFunc("0.04 * x - 0.6", 15, 40);
    subFuncs.add(temp);
    temp = new SubFunc("-0.05 * x + 3", 40, 60);
    subFuncs.add(temp);
    temp = new SubFunc("0", 60, 101);
    subFuncs.add(temp);

    memberFunc = new MemberFunc(List.copyOf(subFuncs));
    memberFuncs.add(memberFunc);
    subFuncs.clear();

    // high
    temp = new SubFunc("0", 0, 50);
    subFuncs.add(temp);
    temp = new SubFunc("0.029 * x - 1.429", 50, 85);
    subFuncs.add(temp);
    temp = new SubFunc("1", 85, 101);
    subFuncs.add(temp);
    temp = new SubFunc("0", 101, 101);
    subFuncs.add(temp);

    memberFunc = new MemberFunc(List.copyOf(subFuncs));
    memberFuncs.add(memberFunc);
    subFuncs.clear();


    oldVariable carbohydrateContent = new oldVariable("Carbohydrate content", foodItem.getClass().getDeclaredField("carbohydrates"), values, List.copyOf(memberFuncs));

    variables.add(carbohydrateContent);
    memberFuncs.clear();

// saturated fats
        
    values = List.of("close to none", "low", "regular", "high");

    // close to none
    temp = new SubFunc("0", -1, 0);
    subFuncs.add(temp);
    temp = new SubFunc("-x + 1", 0, 1);
    subFuncs.add(temp);
    temp = new SubFunc("0", 1, 101);
    subFuncs.add(temp);

    memberFunc = new MemberFunc(List.copyOf(subFuncs));
    memberFuncs.add(memberFunc);
    subFuncs.clear();

    // low
    temp = new SubFunc("0", -1, 0);
    subFuncs.add(temp);
    temp = new SubFunc("0.25 * x", 0, 4);
    subFuncs.add(temp);
    temp = new SubFunc("-1/3 * x + 7/3", 4, 7);
    subFuncs.add(temp);
    temp = new SubFunc("0", 7, 101);
    subFuncs.add(temp);

    memberFunc = new MemberFunc(List.copyOf(subFuncs));
    memberFuncs.add(memberFunc);
    subFuncs.clear();

    // regular
    temp = new SubFunc("0", 0, 5);
    subFuncs.add(temp);
    temp = new SubFunc("0.5 * x - 2.5", 5, 7);
    subFuncs.add(temp);
    temp = new SubFunc("1", 7, 12);
    subFuncs.add(temp);
    temp = new SubFunc("-1/3 * x + 5", 12, 15);
    subFuncs.add(temp);
    temp = new SubFunc("0", 15, 101);
    subFuncs.add(temp);

    memberFunc = new MemberFunc(List.copyOf(subFuncs));
    memberFuncs.add(memberFunc);
    subFuncs.clear();

    // high
    temp = new SubFunc("0", 0, 12);
    subFuncs.add(temp);
    temp = new SubFunc("32/30 * x - 2", 12, 18);
    subFuncs.add(temp);
    temp = new SubFunc("1", 18, 101);
    subFuncs.add(temp);
    temp = new SubFunc("0", 101, 101);
    subFuncs.add(temp);

    memberFunc = new MemberFunc(List.copyOf(subFuncs));
    memberFuncs.add(memberFunc);
    subFuncs.clear();


    oldVariable satFatContent = new oldVariable("Saturated fats content", foodItem.getClass().getDeclaredField("saturatedFats"), values, List.copyOf(memberFuncs));

    variables.add(satFatContent);
    memberFuncs.clear();

// sodium

        memberFuncs = new ArrayList<>();
        subFuncs = new ArrayList<>();

         values = List.of("low", "medium", "high");

        //low
        temp = new SubFunc("0", -1, 0);
        subFuncs.add(temp);
        temp = new SubFunc("1", 0, 200);
        subFuncs.add(temp);
        temp = new SubFunc("-0.009 * x + 2.739", 200, 315);
        subFuncs.add(temp);
        temp = new SubFunc("0", 315, 38759);
        subFuncs.add(temp);

        memberFunc = new MemberFunc(List.copyOf(subFuncs));
        memberFuncs.add(memberFunc);
        subFuncs.clear();

        //medium
        temp = new SubFunc("0", -1, 130);
        subFuncs.add(temp);
        temp = new SubFunc("exp(-1*((x-315)^2)/75^2)", 130, 500);
        subFuncs.add(temp);
        temp = new SubFunc("0", 500, 38759);
        subFuncs.add(temp);
        
        memberFunc = new MemberFunc(List.copyOf(subFuncs));
        memberFuncs.add(memberFunc);
        subFuncs.clear();

        //high
        temp = new SubFunc("0", -1, 400);
        subFuncs.add(temp);
        temp = new SubFunc("1.0/300*x-1.25", 400, 700);
        subFuncs.add(temp);
        temp = new SubFunc("1", 700, 38759);
        subFuncs.add(temp);
        temp = new SubFunc("0", 38759, 3214124);
        subFuncs.add(temp);
        
        memberFunc = new MemberFunc(List.copyOf(subFuncs));
        memberFuncs.add(memberFunc);
        subFuncs.clear();

        oldVariable SodiumContent = new oldVariable("Sodium Content", foodItem.getClass().getDeclaredField("sodium"), values, List.copyOf(memberFuncs));

        variables.add(SodiumContent);
        memberFuncs.clear();
/////////////


// Phosphorus

        memberFuncs = new ArrayList<>();
        subFuncs = new ArrayList<>();

         values = List.of("low", "medium", "high");

        //low
        temp = new SubFunc("0", -1, 0);
        subFuncs.add(temp);
        temp = new SubFunc("1", 0, 50);
        subFuncs.add(temp);
        temp = new SubFunc("-1.0/50.0 * x + 2.0", 50, 100);
        subFuncs.add(temp);
        temp = new SubFunc("0", 100, 9919);
        subFuncs.add(temp);

        memberFunc = new MemberFunc(List.copyOf(subFuncs));
        memberFuncs.add(memberFunc);
        subFuncs.clear();

        //medium
        temp = new SubFunc("0", -1, 80);
        subFuncs.add(temp);
        temp = new SubFunc("exp(-1*((x-140)^2)/20^2)", 80, 200);
        subFuncs.add(temp);
        temp = new SubFunc("0", 200, 9919);
        subFuncs.add(temp);
        
        memberFunc = new MemberFunc(List.copyOf(subFuncs));
        memberFuncs.add(memberFunc);
        subFuncs.clear();

        //high
        temp = new SubFunc("0", -1, 160);
        subFuncs.add(temp);
        temp = new SubFunc("0.025*x-4", 160, 200);
        subFuncs.add(temp);
        temp = new SubFunc("1",200, 9919);
        subFuncs.add(temp);
        temp = new SubFunc("0", 9919, 3214124);
        subFuncs.add(temp);
        
        memberFunc = new MemberFunc(List.copyOf(subFuncs));
        memberFuncs.add(memberFunc);
        subFuncs.clear();



        oldVariable PhosphorusContent = new oldVariable("Phosphorus Content", foodItem.getClass().getDeclaredField("phosphorus"), values, List.copyOf(memberFuncs));

        variables.add(PhosphorusContent);
        memberFuncs.clear();
/////////////
// Water Content

        memberFuncs = new ArrayList<>();
        subFuncs = new ArrayList<>();

         values = List.of("low", "medium", "high");

        //low
        temp = new SubFunc("0", -1, 0);
        subFuncs.add(temp);
        temp = new SubFunc("0.2*x", 0, 5);
        subFuncs.add(temp);
        temp = new SubFunc("1", 5, 25);
        subFuncs.add(temp);
        temp = new SubFunc("-0.1*x+3.5", 25, 35);
        subFuncs.add(temp);
        temp = new SubFunc("0", 35, 101);
        subFuncs.add(temp);

        memberFunc = new MemberFunc(List.copyOf(subFuncs));
        memberFuncs.add(memberFunc);
        subFuncs.clear();

        //medium
        temp = new SubFunc("0", -1, 30);
        subFuncs.add(temp);
        temp = new SubFunc("0.06667*x-2", 30, 45);
        subFuncs.add(temp);
        temp = new SubFunc("1", 45, 55);
        subFuncs.add(temp);
        temp = new SubFunc("-0.05*x+3.75", 55, 75);
        subFuncs.add(temp);
        temp = new SubFunc("0", 75, 101);
        subFuncs.add(temp);

        memberFunc = new MemberFunc(List.copyOf(subFuncs));
        memberFuncs.add(memberFunc);
        subFuncs.clear();

        //high
        temp = new SubFunc("0", -1, 65);
        subFuncs.add(temp);
        temp = new SubFunc("0.05*x-3.25", 65, 85);
        subFuncs.add(temp);
        temp = new SubFunc("1",85, 101);
        subFuncs.add(temp);
        temp = new SubFunc("0", 101, 3214124);
        subFuncs.add(temp);
        
        memberFunc = new MemberFunc(List.copyOf(subFuncs));
        memberFuncs.add(memberFunc);
        subFuncs.clear();



        oldVariable WaterContent = new oldVariable("Water Content", foodItem.getClass().getDeclaredField("water"), values, List.copyOf(memberFuncs));

        variables.add(WaterContent);
        memberFuncs.clear();
/////////////
// Richness of Iron

        memberFuncs = new ArrayList<>();
        subFuncs = new ArrayList<>();

         values = List.of("poor", "medium", "rich");

        //poor
        temp = new SubFunc("0", -1, 0);
        subFuncs.add(temp);
        temp = new SubFunc("-0.6667*x+1", 0, 1.5);
        subFuncs.add(temp);
        temp = new SubFunc("0", 1.5, 125);
        subFuncs.add(temp);

        memberFunc = new MemberFunc(List.copyOf(subFuncs));
        memberFuncs.add(memberFunc);
        subFuncs.clear();

        //medium
        temp = new SubFunc("0", -1, 0.75);
        subFuncs.add(temp);
        temp = new SubFunc("0.8*x-0.6", 0.75, 2);
        subFuncs.add(temp);
        temp = new SubFunc("1", 2, 5);
        subFuncs.add(temp);
        temp = new SubFunc("-0.5*x+3.5", 5, 7);
        subFuncs.add(temp);
        temp = new SubFunc("0", 7, 125);
        subFuncs.add(temp);

        memberFunc = new MemberFunc(List.copyOf(subFuncs));
        memberFuncs.add(memberFunc);
        subFuncs.clear();

        //rich
        temp = new SubFunc("0", -1, 6);
        subFuncs.add(temp);
        temp = new SubFunc("0.3333*x-2", 6, 9);
        subFuncs.add(temp);
        temp = new SubFunc("1",9, 125);
        subFuncs.add(temp);
        temp = new SubFunc("0", 125, 3214124);
        subFuncs.add(temp);
        
        memberFunc = new MemberFunc(List.copyOf(subFuncs));
        memberFuncs.add(memberFunc);
        subFuncs.clear();



        oldVariable IronRichness = new oldVariable("RichnessOfIron", foodItem.getClass().getDeclaredField("iron"), values, List.copyOf(memberFuncs));

        variables.add(IronRichness);
        memberFuncs.clear();
/////////////

// Calcium Content

        memberFuncs = new ArrayList<>();
        subFuncs = new ArrayList<>();

         values = List.of("low", "low-mid", "mid", "high-mid", "high");

        //low
        temp = new SubFunc("0", -1, 0);
        subFuncs.add(temp);
        temp = new SubFunc("0.2 * x", 0, 5);
        subFuncs.add(temp);
        temp = new SubFunc("1", 5, 10);
        subFuncs.add(temp);
        temp = new SubFunc("-0.2 * x", 10, 15);
        subFuncs.add(temp);
        temp = new SubFunc("0", 15, 7365);
        subFuncs.add(temp);

        memberFunc = new MemberFunc(List.copyOf(subFuncs));
        memberFuncs.add(memberFunc);
        subFuncs.clear();

        //low-mid
        temp = new SubFunc("0", -1, 12);
        subFuncs.add(temp); 
        temp = new SubFunc("0.043*x-0.522", 12 , 35);
        subFuncs.add(temp);
        temp = new SubFunc("1", 35, 40);
        subFuncs.add(temp);
        temp = new SubFunc("-0.06667*x+3.6667", 40, 55);
        subFuncs.add(temp);
        temp = new SubFunc("0", 55, 7365);
        subFuncs.add(temp);

        memberFunc = new MemberFunc(List.copyOf(subFuncs));
        memberFuncs.add(memberFunc);
        subFuncs.clear();

        //mid
        temp = new SubFunc("0", 0, 50);
        subFuncs.add(temp);
        temp = new SubFunc("0.05 * x - 2.5", 50, 70);
        subFuncs.add(temp);
        temp = new SubFunc("-0.05 * x + 5", 70, 90);
        subFuncs.add(temp);
        temp = new SubFunc("0", 90, 7365);
        subFuncs.add(temp);

        memberFunc = new MemberFunc(List.copyOf(subFuncs));
        memberFuncs.add(memberFunc);
        subFuncs.clear();

        //high-mid
        temp = new SubFunc("0", 0, 85);
        subFuncs.add(temp);
        temp = new SubFunc("0.029*x -2.429", 85, 120);
        subFuncs.add(temp);
        temp = new SubFunc("-0.03333 * x + 5", 120, 150);
        subFuncs.add(temp);
        temp = new SubFunc("0",150, 7365);
        subFuncs.add(temp);

        memberFunc = new MemberFunc(List.copyOf(subFuncs));
        memberFuncs.add(memberFunc);
        subFuncs.clear();

        
        //high
        temp = new SubFunc("0", 0, 140);
        subFuncs.add(temp);
        temp = new SubFunc("0.05 * x - 7", 140, 160);
        subFuncs.add(temp);
        temp = new SubFunc("1", 160, 7365);
        subFuncs.add(temp);
        temp = new SubFunc("0", 7365, 7365);
        subFuncs.add(temp);
        
        memberFunc = new MemberFunc(List.copyOf(subFuncs));
        memberFuncs.add(memberFunc);
        subFuncs.clear();



        oldVariable CalciumContent = new oldVariable("Calcium Content", foodItem.getClass().getDeclaredField("calcium"), values, List.copyOf(memberFuncs));

        variables.add(CalciumContent);
        memberFuncs.clear();
/////////////

        Serializer.serialize(subjects, "subjects.ser");
        Serializer.serialize(variables, "variables.ser");
        Serializer.serialize(quantifiers, "quantifiers.ser");

    }
    
}
