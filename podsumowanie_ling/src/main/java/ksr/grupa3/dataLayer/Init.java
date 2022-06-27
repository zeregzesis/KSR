package ksr.grupa3.dataLayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ksr.grupa3.fuzzy.*;
import ksr.grupa3.ling.Quantifier;
import ksr.grupa3.ling.Subject;
import ksr.grupa3.ling.Variable;

public class Init {

    public static void initialize() throws NoSuchFieldException, SecurityException, IOException {

        List<Subject> subjects = new ArrayList<>();
        List<Quantifier> quantifiers = new ArrayList<>();
        List<Variable> variables = new ArrayList<>();


        Subject subject = new Subject("all products");
        subjects.add(subject);

        Subject subject2 = new Subject("products that have \"Meat\" in their name");
        subjects.add(subject2);

        Subject subject3 = new Subject("products that don't have \"Meat\" in their name");
        subjects.add(subject3);


        Quantifier almostNone = new Quantifier();
        almostNone.setName("Almost none");
        // SubFunc zero = new SubFunc("0", 0, 0);
        // SubFunc value = new SubFunc("exp(-(12.5 * x)^2)", 0, 1);
        // SubFunc zero2 = new SubFunc("0", 0.25, 1);
        // MemberFunc memberFunc = new MemberFunc(List.of(zero, value, zero2));
        MembershipFuction memberFunc = new GaussFuction(1, 0.0, 0.056, 1);
        almostNone.setMemberFunc(memberFunc);
        almostNone.setIsAbsolute(false);
        quantifiers.add(almostNone);

        Quantifier some = new Quantifier();
        some.setName("Some");
        // zero = new SubFunc("0", 0, 0);
        // value = new SubFunc("exp(-(12.5 * x - 3.125)^2)", 0, 0.5);
        // zero2 = new SubFunc("0", 0.5, 1);
        // memberFunc = new MemberFunc(List.of(zero, value, zero2));
        memberFunc = new GaussFuction(1, 0.25, 0.056, 1);
        some.setMemberFunc(memberFunc);
        some.setIsAbsolute(false);
        quantifiers.add(some);

        Quantifier aboutHalf = new Quantifier();
        aboutHalf.setName("About half");
        // zero = new SubFunc("0", 0, 0.25);
        // value = new SubFunc("exp(-(12.5 * x - 6.25)^2)", 0.25, 0.75);
        // zero2 = new SubFunc("0", 0.75, 1);
        // memberFunc = new MemberFunc(List.of(zero, value, zero2));
        memberFunc = new GaussFuction(1, 0.5, 0.056, 1);
        aboutHalf.setMemberFunc(memberFunc);
        aboutHalf.setIsAbsolute(false);
        quantifiers.add(aboutHalf);

        Quantifier most = new Quantifier();
        most.setName("Most");
        // zero = new SubFunc("0", 0, 0.5);
        // value = new SubFunc("exp(-(12.5 * x - 9.375)^2)", 0.5, 1);
        // zero2 = new SubFunc("0", 1, 1);
        // memberFunc = new MemberFunc(List.of(zero, value, zero2));
        memberFunc = new GaussFuction(1, 0.75, 0.056, 1);
        most.setMemberFunc(memberFunc);
        most.setIsAbsolute(false);
        quantifiers.add(most);

        Quantifier almostAll = new Quantifier();
        almostAll.setName("Almost all");
        // zero = new SubFunc("0", 0, 0.75);
        // value = new SubFunc("exp(-(12.5 * x - 12.5)^2)", 0.75, 1.001);
        // zero2 = new SubFunc("0", 1.001, 1.001);
        // memberFunc = new MemberFunc(List.of(zero, value, zero2));
        memberFunc = new GaussFuction(1, 1.0, 0.056, 1);
        almostAll.setMemberFunc(memberFunc);
        almostAll.setIsAbsolute(false);
        quantifiers.add(almostAll);

        Quantifier muchMoreThan500 = new Quantifier();
        muchMoreThan500.setName("Much more than 500");
        // zero = new SubFunc("0", 0, 500);
        // value = new SubFunc("(2.0/3000.0) * x - (1.0/3.0)", 500, 2000);
        // SubFunc one = new SubFunc("1", 2000, 13544);
        // zero2 = new SubFunc("0", 13544, 13544);
        // memberFunc = new MemberFunc(List.of(zero, value, one, zero2));
        memberFunc = new TrapezoidFuction(500, 2000, 13544, 13544, 13544);
        muchMoreThan500.setMemberFunc(memberFunc);
        muchMoreThan500.setIsAbsolute(true);
        quantifiers.add(muchMoreThan500);

        Quantifier muchLessThan8000 = new Quantifier();
        muchLessThan8000.setName("Much less than 8000");
        // zero = new SubFunc("0", -1, 0);
        // one = new SubFunc("1", 0, 6000);
        // value = new SubFunc("-0.0005 * x + 4", 6000, 8000);
        // zero2 = new SubFunc("0", 8000, 13544);
        // memberFunc = new MemberFunc(List.of(zero, value, one, zero2));
        memberFunc = new TrapezoidFuction(1, 0, 6000, 8000, 13544);
        muchLessThan8000.setMemberFunc(memberFunc);
        muchLessThan8000.setIsAbsolute(true);
        quantifiers.add(muchLessThan8000);

        Quantifier aboutFewThousand = new Quantifier();
        aboutFewThousand.setName("About few thousand");
        // zero = new SubFunc("0", -1, 0);
        // value = new SubFunc("exp(-(0.001 * x - 4)^2)", 0, 4000);
        // one = new SubFunc("1", 4000, 6000);
        // SubFunc value2 = new SubFunc("exp(-(0.001 * x - 4)^2)", 6000, 13544);
        // zero2 = new SubFunc("0", 13544, 13544);
        // memberFunc = new MemberFunc(List.of(zero, value, one, value2, zero2));
        memberFunc = new GaussFuction(2, 5000, 850, 13544);
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
        
        List<String> values =new ArrayList<>();
        values.add("low");
        values.add("medium");
        values.add("high");


        // low
        memberFuncs.add(new TrapezoidFuction(0,0,85,200,1000));



//        SubFunc temp = new SubFunc("0", -1, 0);
//        subFuncs.add(temp);
//        temp = new SubFunc("1", 0, 85);
//        subFuncs.add(temp);
//        temp = new SubFunc("-0.009 * x + 1.739", 85, 200);
//        subFuncs.add(temp);
//        temp = new SubFunc("0", 200, 903);
//        subFuncs.add(temp);
//
//        memberFunc = new MemberFunc(List.copyOf(subFuncs));
//        memberFuncs.add(memberFunc);
//        subFuncs.clear();

        // medium
        memberFuncs.add(new GaussFuction(1,300,35.5,1000));

//        temp = new SubFunc("0", 0, 150);
//        subFuncs.add(temp);
//        temp = new SubFunc("exp(-(x - 300)^2 / 50^2)", 150, 450);
//        subFuncs.add(temp);
//        temp = new SubFunc("0", 450, 903);
//        subFuncs.add(temp);
//
//        memberFunc = new MemberFunc(List.copyOf(subFuncs));
//        memberFuncs.add(memberFunc);
//        subFuncs.clear();

        // high
        memberFuncs.add(new TrapezoidFuction(350,500,902,902,1000));

//        temp = new SubFunc("0", 0, 350);
//        subFuncs.add(temp);
//        temp = new SubFunc("exp(-(x - 300)^2 / 50^2)", 350, 500);
//        subFuncs.add(temp);
//        temp = new SubFunc("1", 500, 903);
//        subFuncs.add(temp);
//        temp = new SubFunc("0", 903, 903);
//        subFuncs.add(temp);
//
//        memberFunc = new MemberFunc(List.copyOf(subFuncs));
//        memberFuncs.add(memberFunc);
//        subFuncs.clear();


        Variable caloricValue = new Variable("caloric value", foodItem.getClass().getDeclaredField("calories"), List.copyOf(values), List.copyOf(memberFuncs),1000);

        variables.add(caloricValue);

        memberFuncs.clear();

// fat
        values.clear();
//        values = List.of("close to none", "low", "medium", "high");
        values.add("close to none");
        values.add("low");
        values.add("medium");
        values.add("high");


        // close to none
        memberFuncs.add(new TrapezoidFuction(0,0,1,2,100));
//        temp = new SubFunc("0", -1, 0);
//        subFuncs.add(temp);
//        temp = new SubFunc("1", 0, 1);
//        subFuncs.add(temp);
//        temp = new SubFunc("-x + 2", 1, 2);
//        subFuncs.add(temp);
//        temp = new SubFunc("0", 2, 101);
//        subFuncs.add(temp);
//
//        memberFunc = new MemberFunc(List.copyOf(subFuncs));
//        memberFuncs.add(memberFunc);
//        subFuncs.clear();

        // low
        memberFuncs.add(new TrapezoidFuction(1,5,8,12,100));
//        temp = new SubFunc("0", 0, 1);
//        subFuncs.add(temp);
//        temp = new SubFunc("0.25 * x - 0.25", 1, 5);
//        subFuncs.add(temp);
//        temp = new SubFunc("1", 5, 8);
//        subFuncs.add(temp);
//        temp = new SubFunc("-0.25 * x + 3", 8, 12);
//        subFuncs.add(temp);
//        temp = new SubFunc("0", 12, 101);
//        subFuncs.add(temp);
//
//        memberFunc = new MemberFunc(List.copyOf(subFuncs));
//        memberFuncs.add(memberFunc);
//        subFuncs.clear();

        // medium
        memberFuncs.add(new TriangleFuction(7,11,50,100));
//        temp = new SubFunc("0", 0, 7);
//        subFuncs.add(temp);
//        temp = new SubFunc("0.25 * x - 7/3", 7, 11);
//        subFuncs.add(temp);
//        temp = new SubFunc("-0.026 * x + 1.282", 11, 50);
//        subFuncs.add(temp);
//        temp = new SubFunc("0", 50, 101);
//        subFuncs.add(temp);
//
//        memberFunc = new MemberFunc(List.copyOf(subFuncs));
//        memberFuncs.add(memberFunc);
//        subFuncs.clear();

        // high
        memberFuncs.add(new TrapezoidFuction(20,40,100,100,100));
//        temp = new SubFunc("0", 0, 20);
//        subFuncs.add(temp);
//        temp = new SubFunc("0.05 * x - 1", 20, 40);
//        subFuncs.add(temp);
//        temp = new SubFunc("1", 40, 101);
//        subFuncs.add(temp);
//        temp = new SubFunc("0", 101, 101);
//        subFuncs.add(temp);
//
//        memberFunc = new MemberFunc(List.copyOf(subFuncs));
//        memberFuncs.add(memberFunc);
//        subFuncs.clear();


        Variable fatContent = new Variable("Fat content", foodItem.getClass().getDeclaredField("fat"), List.copyOf(values), List.copyOf(memberFuncs),100);

        variables.add(fatContent);
        memberFuncs.clear();

// protein
        values.clear();
//    values = List.of("low", "medium", "high");
        values.add("low");
        values.add("medium");
        values.add("high");

    // low
        memberFuncs.add(new TriangleFuction(0,6,10,100));
//    temp = new SubFunc("0", -1, 0);
//    subFuncs.add(temp);
//    temp = new SubFunc("1/6 * x", 0, 6);
//    subFuncs.add(temp);
//    temp = new SubFunc("-1/4 * x + 2.5", 6, 10);
//    subFuncs.add(temp);
//    temp = new SubFunc("0", 10, 89);
//    subFuncs.add(temp);
//
//    memberFunc = new MemberFunc(List.copyOf(subFuncs));
//    memberFuncs.add(memberFunc);
//    subFuncs.clear();

    // medium
        memberFuncs.add(new GaussFuction(1,10,2.5,100));
//    temp = new SubFunc("0", -1, 0);
//    subFuncs.add(temp);
//    temp = new SubFunc("exp(-(x-10)^2 / 16)", 0, 20);
//    subFuncs.add(temp);
//    temp = new SubFunc("0", 20, 89);
//    subFuncs.add(temp);
//
//    memberFunc = new MemberFunc(List.copyOf(subFuncs));
//    memberFuncs.add(memberFunc);
//    subFuncs.clear();

    // high
        memberFuncs.add(new TrapezoidFuction(10,20,100,100,100));
//    temp = new SubFunc("0", 0, 10);
//    subFuncs.add(temp);
//    temp = new SubFunc("0.1 * x - 1", 10, 20);
//    subFuncs.add(temp);
//    temp = new SubFunc("1", 20, 101);
//    subFuncs.add(temp);
//    temp = new SubFunc("0", 101, 101);
//    subFuncs.add(temp);
//
//    memberFunc = new MemberFunc(List.copyOf(subFuncs));
//    memberFuncs.add(memberFunc);
//    subFuncs.clear();



    Variable proteinContent = new Variable("Protein content", foodItem.getClass().getDeclaredField("protein"), List.copyOf(values), List.copyOf(memberFuncs),100);

    variables.add(proteinContent);
    memberFuncs.clear();

// carbohydrate
        values.clear();
//        values = List.of("close to none", "low", "medium", "high");
        values.add("close to none");
        values.add("low");
        values.add("medium");
        values.add("high");


    // close to none
        memberFuncs.add(new TriangleFuction(0,0,1,100));
//    temp = new SubFunc("0", -1, 0);
//    subFuncs.add(temp);
//    temp = new SubFunc("-x + 1", 0, 1);
//    subFuncs.add(temp);
//    temp = new SubFunc("0", 1, 101);
//    subFuncs.add(temp);
//
//    memberFunc = new MemberFunc(List.copyOf(subFuncs));
//    memberFuncs.add(memberFunc);
//    subFuncs.clear();

    // low
        memberFuncs.add(new TrapezoidFuction(0,5,10,15,100));
//    temp = new SubFunc("0", -1, 0);
//    subFuncs.add(temp);
//    temp = new SubFunc("0.2 * x", 0, 5);
//    subFuncs.add(temp);
//    temp = new SubFunc("1", 5, 10);
//    subFuncs.add(temp);
//    temp = new SubFunc("-0.2 * x + 3", 10, 15);
//    subFuncs.add(temp);
//    temp = new SubFunc("0", 15, 101);
//    subFuncs.add(temp);
//
//    memberFunc = new MemberFunc(List.copyOf(subFuncs));
//    memberFuncs.add(memberFunc);
//    subFuncs.clear();

    // medium
        memberFuncs.add(new TriangleFuction(15,40,60,100));
//    temp = new SubFunc("0", 0, 15);
//    subFuncs.add(temp);
//    temp = new SubFunc("0.04 * x - 0.6", 15, 40);
//    subFuncs.add(temp);
//    temp = new SubFunc("-0.05 * x + 3", 40, 60);
//    subFuncs.add(temp);
//    temp = new SubFunc("0", 60, 101);
//    subFuncs.add(temp);
//
//    memberFunc = new MemberFunc(List.copyOf(subFuncs));
//    memberFuncs.add(memberFunc);
//    subFuncs.clear();

    // high
        memberFuncs.add(new TrapezoidFuction(50,85,100,100,100));
//    temp = new SubFunc("0", 0, 50);
//    subFuncs.add(temp);
//    temp = new SubFunc("0.029 * x - 1.429", 50, 85);
//    subFuncs.add(temp);
//    temp = new SubFunc("1", 85, 101);
//    subFuncs.add(temp);
//    temp = new SubFunc("0", 101, 101);
//    subFuncs.add(temp);
//
//    memberFunc = new MemberFunc(List.copyOf(subFuncs));
//    memberFuncs.add(memberFunc);
//    subFuncs.clear();


    Variable carbohydrateContent = new Variable("Carbohydrate content", foodItem.getClass().getDeclaredField("carbohydrates"), List.copyOf(values), List.copyOf(memberFuncs),100);

    variables.add(carbohydrateContent);
    memberFuncs.clear();

// saturated fats
        

        values.clear();
//        values = List.of("close to none", "low", "medium", "high");
        values.add("close to none");
        values.add("low");
        values.add("medium");
        values.add("high");
    // close to none
        memberFuncs.add(new TriangleFuction(0,0,1,100));
//    temp = new SubFunc("0", -1, 0);
//    subFuncs.add(temp);
//    temp = new SubFunc("-x + 1", 0, 1);
//    subFuncs.add(temp);
//    temp = new SubFunc("0", 1, 101);
//    subFuncs.add(temp);
//
//    memberFunc = new MemberFunc(List.copyOf(subFuncs));
//    memberFuncs.add(memberFunc);
//    subFuncs.clear();

    // low
        memberFuncs.add(new TriangleFuction(0,4,7,100));
//    temp = new SubFunc("0", -1, 0);
//    subFuncs.add(temp);
//    temp = new SubFunc("0.25 * x", 0, 4);
//    subFuncs.add(temp);
//    temp = new SubFunc("-1/3 * x + 7/3", 4, 7);
//    subFuncs.add(temp);
//    temp = new SubFunc("0", 7, 101);
//    subFuncs.add(temp);
//
//    memberFunc = new MemberFunc(List.copyOf(subFuncs));
//    memberFuncs.add(memberFunc);
//    subFuncs.clear();

    // regular
        memberFuncs.add(new TrapezoidFuction(5,7,12,15,100));
//    temp = new SubFunc("0", 0, 5);
//    subFuncs.add(temp);
//    temp = new SubFunc("0.5 * x - 2.5", 5, 7);
//    subFuncs.add(temp);
//    temp = new SubFunc("1", 7, 12);
//    subFuncs.add(temp);
//    temp = new SubFunc("-1/3 * x + 5", 12, 15);
//    subFuncs.add(temp);
//    temp = new SubFunc("0", 15, 101);
//    subFuncs.add(temp);
//
//    memberFunc = new MemberFunc(List.copyOf(subFuncs));
//    memberFuncs.add(memberFunc);
//    subFuncs.clear();

    // high
        memberFuncs.add(new TrapezoidFuction(12,18,96,96,100));
//    temp = new SubFunc("0", 0, 12);
//    subFuncs.add(temp);
//    temp = new SubFunc("32/30 * x - 2", 12, 18);
//    subFuncs.add(temp);
//    temp = new SubFunc("1", 18, 101);
//    subFuncs.add(temp);
//    temp = new SubFunc("0", 101, 101);
//    subFuncs.add(temp);
//
//    memberFunc = new MemberFunc(List.copyOf(subFuncs));
//    memberFuncs.add(memberFunc);
//    subFuncs.clear();


    Variable satFatContent = new Variable("Saturated fats content", foodItem.getClass().getDeclaredField("saturatedFats"), List.copyOf(values), List.copyOf(memberFuncs),100);

    variables.add(satFatContent);
    memberFuncs.clear();

// sodium

        memberFuncs = new ArrayList<>();
//        subFuncs = new ArrayList<>();

//         values = List.of("low", "medium", "high");
        values.clear();
//        values = List.of("close to none", "low", "medium", "high");

        values.add("low");
        values.add("medium");
        values.add("high");


        //low
        memberFuncs.add(new TrapezoidFuction(0,0,0.200,0.315,100));
//        temp = new SubFunc("0", -1, 0);
//        subFuncs.add(temp);
//        temp = new SubFunc("1", 0, 200);
//        subFuncs.add(temp);
//        temp = new SubFunc("-0.009 * x + 2.739", 200, 315);
//        subFuncs.add(temp);
//        temp = new SubFunc("0", 315, 38759);
//        subFuncs.add(temp);
//
//        memberFunc = new MemberFunc(List.copyOf(subFuncs));
//        memberFuncs.add(memberFunc);
//        subFuncs.clear();

        //medium
        memberFuncs.add(new GaussFuction(1,0.315,0.04625,100));
//        temp = new SubFunc("0", -1, 130);
//        subFuncs.add(temp);
//        temp = new SubFunc("exp(-1*((x-315)^2)/75^2)", 130, 500);
//        subFuncs.add(temp);
//        temp = new SubFunc("0", 500, 38759);
//        subFuncs.add(temp);
//
//        memberFunc = new MemberFunc(List.copyOf(subFuncs));
//        memberFuncs.add(memberFunc);
//        subFuncs.clear();

        //high
        memberFuncs.add(new TrapezoidFuction(0.400,0.700,100,100,100));
//        temp = new SubFunc("0", -1, 400);
//        subFuncs.add(temp);
//        temp = new SubFunc("1.0/300*x-1.25", 400, 700);
//        subFuncs.add(temp);
//        temp = new SubFunc("1", 700, 38759);
//        subFuncs.add(temp);
//        temp = new SubFunc("0", 38759, 3214124);
//        subFuncs.add(temp);
//
//        memberFunc = new MemberFunc(List.copyOf(subFuncs));
//        memberFuncs.add(memberFunc);
//        subFuncs.clear();

        Variable SodiumContent = new Variable("Sodium Content", foodItem.getClass().getDeclaredField("sodium"), List.copyOf(values), List.copyOf(memberFuncs),100);

        variables.add(SodiumContent);
        memberFuncs.clear();
/////////////


// Phosphorus

        memberFuncs = new ArrayList<>();
//        subFuncs = new ArrayList<>();

//         values = List.of("low", "medium", "high");
        values.clear();


        values.add("low");
        values.add("medium");
        values.add("high");
        //low
        memberFuncs.add(new TrapezoidFuction(0,0,0.040,0.100,100));
//        temp = new SubFunc("0", -1, 0);
//        subFuncs.add(temp);
//        temp = new SubFunc("1", 0, 50);
//        subFuncs.add(temp);
//        temp = new SubFunc("-1.0/50.0 * x + 2.0", 50, 100);
//        subFuncs.add(temp);
//        temp = new SubFunc("0", 100, 9919);
//        subFuncs.add(temp);
//
//        memberFunc = new MemberFunc(List.copyOf(subFuncs));
//        memberFuncs.add(memberFunc);
//        subFuncs.clear();

        //medium
        memberFuncs.add(new GaussFuction(1,0.140,0.015,100));
//        temp = new SubFunc("0", -1, 80);
//        subFuncs.add(temp);
//        temp = new SubFunc("exp(-1*((x-140)^2)/20^2)", 80, 200);
//        subFuncs.add(temp);
//        temp = new SubFunc("0", 200, 9919);
//        subFuncs.add(temp);
//
//        memberFunc = new MemberFunc(List.copyOf(subFuncs));
//        memberFuncs.add(memberFunc);
//        subFuncs.clear();

        //high
        memberFuncs.add(new TrapezoidFuction(0.160,0.200,100,100,100));
//        temp = new SubFunc("0", -1, 160);
//        subFuncs.add(temp);
//        temp = new SubFunc("0.025*x-4", 160, 200);
//        subFuncs.add(temp);
//        temp = new SubFunc("1",200, 9919);
//        subFuncs.add(temp);
//        temp = new SubFunc("0", 9919, 3214124);
//        subFuncs.add(temp);
//
//        memberFunc = new MemberFunc(List.copyOf(subFuncs));
//        memberFuncs.add(memberFunc);
//        subFuncs.clear();



        Variable PhosphorusContent = new Variable("Phosphorus Content", foodItem.getClass().getDeclaredField("phosphorus"), List.copyOf(values), List.copyOf(memberFuncs),100);

        variables.add(PhosphorusContent);
        memberFuncs.clear();
/////////////
// Water Content

        memberFuncs = new ArrayList<>();
//        subFuncs = new ArrayList<>();

//         values = List.of("low", "medium", "high");

        values.clear();


        values.add("low");
        values.add("medium");
        values.add("high");

        //low
        memberFuncs.add(new TrapezoidFuction(0,5,25,35,100));
//        temp = new SubFunc("0", -1, 0);
//        subFuncs.add(temp);
//        temp = new SubFunc("0.2*x", 0, 5);
//        subFuncs.add(temp);
//        temp = new SubFunc("1", 5, 25);
//        subFuncs.add(temp);
//        temp = new SubFunc("-0.1*x+3.5", 25, 35);
//        subFuncs.add(temp);
//        temp = new SubFunc("0", 35, 101);
//        subFuncs.add(temp);
//
//        memberFunc = new MemberFunc(List.copyOf(subFuncs));
//        memberFuncs.add(memberFunc);
//        subFuncs.clear();

        //medium
        memberFuncs.add(new TrapezoidFuction(30,45,55,75,100));
//        temp = new SubFunc("0", -1, 30);
//        subFuncs.add(temp);
//        temp = new SubFunc("0.06667*x-2", 30, 45);
//        subFuncs.add(temp);
//        temp = new SubFunc("1", 45, 55);
//        subFuncs.add(temp);
//        temp = new SubFunc("-0.05*x+3.75", 55, 75);
//        subFuncs.add(temp);
//        temp = new SubFunc("0", 75, 101);
//        subFuncs.add(temp);
//
//        memberFunc = new MemberFunc(List.copyOf(subFuncs));
//        memberFuncs.add(memberFunc);
//        subFuncs.clear();

        //high
        memberFuncs.add(new TrapezoidFuction(65, 85,100,100,100));
//        temp = new SubFunc("0", -1, 65);
//        subFuncs.add(temp);
//        temp = new SubFunc("0.05*x-3.25", 65, 85);
//        subFuncs.add(temp);
//        temp = new SubFunc("1",85, 101);
//        subFuncs.add(temp);
//        temp = new SubFunc("0", 101, 3214124);
//        subFuncs.add(temp);
//
//        memberFunc = new MemberFunc(List.copyOf(subFuncs));
//        memberFuncs.add(memberFunc);
//        subFuncs.clear();



        Variable WaterContent = new Variable("Water Content", foodItem.getClass().getDeclaredField("water"), List.copyOf(values), List.copyOf(memberFuncs),100);

        variables.add(WaterContent);
        memberFuncs.clear();
/////////////
// Richness of Iron

        memberFuncs = new ArrayList<>();
//        subFuncs = new ArrayList<>();

//         values = List.of("poor", "medium", "rich");

        values.clear();

        values.add("close to none");
        values.add("poor");
        values.add("medium");
        values.add("rich");


        //poor
        memberFuncs.add(new TriangleFuction(0,0,0.0015,100));
//        temp = new SubFunc("0", -1, 0);
//        subFuncs.add(temp);
//        temp = new SubFunc("-0.6667*x+1", 0, 1.5);
//        subFuncs.add(temp);
//        temp = new SubFunc("0", 1.5, 125);
//        subFuncs.add(temp);
//
//        memberFunc = new MemberFunc(List.copyOf(subFuncs));
//        memberFuncs.add(memberFunc);
//        subFuncs.clear();

        //medium
        memberFuncs.add(new TrapezoidFuction(0.00075,0.002,0.005,0.007,100));
//        temp = new SubFunc("0", -1, 0.75);
//        subFuncs.add(temp);
//        temp = new SubFunc("0.8*x-0.6", 0.75, 2);
//        subFuncs.add(temp);
//        temp = new SubFunc("1", 2, 5);
//        subFuncs.add(temp);
//        temp = new SubFunc("-0.5*x+3.5", 5, 7);
//        subFuncs.add(temp);
//        temp = new SubFunc("0", 7, 125);
//        subFuncs.add(temp);
//
//        memberFunc = new MemberFunc(List.copyOf(subFuncs));
//        memberFuncs.add(memberFunc);
//        subFuncs.clear();

        //rich
        memberFuncs.add(new TrapezoidFuction(0.006,0.009,100,100,100));
//        temp = new SubFunc("0", -1, 6);
//        subFuncs.add(temp);
//        temp = new SubFunc("0.3333*x-2", 6, 9);
//        subFuncs.add(temp);
//        temp = new SubFunc("1",9, 125);
//        subFuncs.add(temp);
//        temp = new SubFunc("0", 125, 3214124);
//        subFuncs.add(temp);
//
//        memberFunc = new MemberFunc(List.copyOf(subFuncs));
//        memberFuncs.add(memberFunc);
//        subFuncs.clear();



        Variable IronRichness = new Variable("RichnessOfIron", foodItem.getClass().getDeclaredField("iron"), List.copyOf(values), List.copyOf(memberFuncs),100);

        variables.add(IronRichness);
        memberFuncs.clear();
/////////////

// Calcium Content

        memberFuncs = new ArrayList<>();
//        subFuncs = new ArrayList<>();

//         values = List.of("low", "low-mid", "mid", "high-mid", "high");

        values.clear();

        values.add("low");
        values.add("low-mid");
        values.add("mid");
        values.add("high-mid");
        values.add("high");


        //low
        memberFuncs.add(new TrapezoidFuction(0,0.005,0.010,0.015,100));
//        temp = new SubFunc("0", -1, 0);
//        subFuncs.add(temp);
//        temp = new SubFunc("0.2 * x", 0, 5);
//        subFuncs.add(temp);
//        temp = new SubFunc("1", 5, 10);
//        subFuncs.add(temp);
//        temp = new SubFunc("-0.2 * x", 10, 15);
//        subFuncs.add(temp);
//        temp = new SubFunc("0", 15, 7365);
//        subFuncs.add(temp);
//
//        memberFunc = new MemberFunc(List.copyOf(subFuncs));
//        memberFuncs.add(memberFunc);
//        subFuncs.clear();

        //low-mid
        memberFuncs.add(new TrapezoidFuction(0.012,0.035,0.040,0.055,100));
//        temp = new SubFunc("0", -1, 12);
//        subFuncs.add(temp);
//        temp = new SubFunc("0.043*x-0.522", 12 , 35);
//        subFuncs.add(temp);
//        temp = new SubFunc("1", 35, 40);
//        subFuncs.add(temp);
//        temp = new SubFunc("-0.06667*x+3.6667", 40, 55);
//        subFuncs.add(temp);
//        temp = new SubFunc("0", 55, 7365);
//        subFuncs.add(temp);
//
//        memberFunc = new MemberFunc(List.copyOf(subFuncs));
//        memberFuncs.add(memberFunc);
//        subFuncs.clear();

        //mid
        memberFuncs.add(new TriangleFuction(0.050,0.070,0.090,100));
//        temp = new SubFunc("0", 0, 50);
//        subFuncs.add(temp);
//        temp = new SubFunc("0.05 * x - 2.5", 50, 70);
//        subFuncs.add(temp);
//        temp = new SubFunc("-0.05 * x + 5", 70, 90);
//        subFuncs.add(temp);
//        temp = new SubFunc("0", 90, 7365);
//        subFuncs.add(temp);
//
//        memberFunc = new MemberFunc(List.copyOf(subFuncs));
//        memberFuncs.add(memberFunc);
//        subFuncs.clear();

        //high-mid
        memberFuncs.add(new TriangleFuction(0.085,0.12,0.150,100));
//        temp = new SubFunc("0", 0, 85);
//        subFuncs.add(temp);
//        temp = new SubFunc("0.029*x -2.429", 85, 120);
//        subFuncs.add(temp);
//        temp = new SubFunc("-0.03333 * x + 5", 120, 150);
//        subFuncs.add(temp);
//        temp = new SubFunc("0",150, 7365);
//        subFuncs.add(temp);
//
//        memberFunc = new MemberFunc(List.copyOf(subFuncs));
//        memberFuncs.add(memberFunc);
//        subFuncs.clear();

        
        //high
        memberFuncs.add(new TrapezoidFuction(0.14,0.16,100,100,100));
//        temp = new SubFunc("0", 0, 140);
//        subFuncs.add(temp);
//        temp = new SubFunc("0.05 * x - 7", 140, 160);
//        subFuncs.add(temp);
//        temp = new SubFunc("1", 160, 7365);
//        subFuncs.add(temp);
//        temp = new SubFunc("0", 7365, 7365);
//        subFuncs.add(temp);
//
//        memberFunc = new MemberFunc(List.copyOf(subFuncs));
//        memberFuncs.add(memberFunc);
//        subFuncs.clear();



        Variable CalciumContent = new Variable("Calcium Content", foodItem.getClass().getDeclaredField("calcium"), List.copyOf(values), List.copyOf(memberFuncs),100);

        variables.add(CalciumContent);
        memberFuncs.clear();
/////////////

        Serializer.serialize(subjects, "subjects.ser");
        Serializer.serialize(variables, "variables.ser");
        Serializer.serialize(quantifiers, "quantifiers.ser");

    }
    
}
