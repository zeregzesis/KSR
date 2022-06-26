package ksr.grupa3;

import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ksr.grupa3.dataLayer.CSVFileReader;
import ksr.grupa3.dataLayer.Init;
import ksr.grupa3.dataLayer.Serializer;
import ksr.grupa3.fuzzy.FoodItem;
import ksr.grupa3.gui.MainController;
import ksr.grupa3.ling.Quantifier;
import ksr.grupa3.ling.Subject;
import ksr.grupa3.ling.Variable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App2 extends Application {
    public DataHolder dataHolder;

//    public App2(DataHolder dataHolder){
//        this.dataHolder=dataHolder;
//    }

    @Override
    public void start(Stage stage) throws IOException, NoSuchFieldException, ClassNotFoundException {

        Init.initialize();

        Random rand = new Random();

        List<Object> listOfObjects = Serializer.deserialize("quantifiers.ser");
        List<Quantifier> quantifiers = new ArrayList<>();
        for (Object object : listOfObjects) {
            quantifiers.add((Quantifier) object);
        }

        listOfObjects = Serializer.deserialize("variables.ser");
        List<Variable> variables = new ArrayList<>();
        for (Object object : listOfObjects) {
            variables.add((Variable) object);
        }

        listOfObjects = Serializer.deserialize("subjects.ser");
        List<Subject> subjects = new ArrayList<>();
        for (Object object : listOfObjects) {
            subjects.add((Subject) object);
        }

        List<FoodItem> foodItems = CSVFileReader.readCsv();

        DataHolder dataHolder=new DataHolder(quantifiers,variables,subjects,foodItems);
        System.out.println(dataHolder.quantifiers.size());



        FXMLLoader fxmlLoader = new FXMLLoader(App2.class.getResource("main.fxml"));
        Parent mainloader=fxmlLoader.load();
        MainController mainController=fxmlLoader.getController();
        mainController.setDataHolder(dataHolder);

        Scene scene = new Scene(mainloader, 1320, 740);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main() {

        launch();
    }
}