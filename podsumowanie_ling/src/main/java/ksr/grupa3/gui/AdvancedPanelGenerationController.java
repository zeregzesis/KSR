package ksr.grupa3.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ksr.grupa3.DataHolder;
import ksr.grupa3.fuzzy.FuzzySet;

import ksr.grupa3.ling.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdvancedPanelGenerationController implements Initializable {
    public ChoiceBox SummarizerAddOr;
    public ChoiceBox QualifierAddOr;
    public Button LoadResults;
    public Button ResetTableButton;
    public TextField W10_textField_eos;
    public Button Basic_panel_button;
    @FXML
    private TextField W11_textField_eos;

    @FXML
    private TextField W1_textField_eos;

    @FXML
    private TextField W1_textField_os;

    @FXML
    private TextField W2_textField_eos;

    @FXML
    private TextField W2_textField_os;

    @FXML
    private TextField W3_textField_eos;

    @FXML
    private TextField W3_textField_os;

    @FXML
    private TextField W4_textField_eos;

    @FXML
    private TextField W4_textField_os;

    @FXML
    private TextField W5_textField_eos;

    @FXML
    private TextField W5_textField_os;

    @FXML
    private TextField W6_textField_eos;

    @FXML
    private TextField W7_textField_eos;

    @FXML
    private TextField W8_textField_eos;

    @FXML
    private TextField W9_textField_eos;
    public Button save_to_file;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableColumn<SummaryObs, Object> EOSColumn;

    @FXML
    private TableColumn<SummaryObs, Object> OSColumn;

    @FXML
    private TableColumn<Summary, String> SummaryColumn;


    @FXML
    private TableColumn<SummaryObs, Object> T10Column;

    @FXML
    private TableColumn<SummaryObs, Object> T11Column;

    @FXML
    private TableColumn<SummaryObs, Object> T1Column;

    @FXML
    private TableColumn<SummaryObs, Object> T2Column;

    @FXML
    private TableColumn<SummaryObs, Object> T3Column;

    @FXML
    private TableColumn<SummaryObs, Object> T4Column;

    @FXML
    private TableColumn<SummaryObs, Object> T5Column;

    @FXML
    private TableColumn<SummaryObs, Object> T6Column;

    @FXML
    private TableColumn<SummaryObs, Object> T7Column;

    @FXML
    private TableColumn<SummaryObs, Object> T8Column;

    @FXML
    private TableColumn<SummaryObs, Object> T9Column;

    @FXML
    private TableView<SummaryObs> Table;


    @FXML
    private Button apply_button;


    @FXML
    private Button buttonSwitchToAdvancedUserPanel;

    @FXML
    private ChoiceBox<String> first_subject_dropdown;

    @FXML
    private Button qualifier_add_button;

    @FXML
    private ChoiceBox qualifier_label_dropdown;

    @FXML
    private ListView<String> qualifier_listview;

    @FXML
    private Button qualifier_reset_button;

    @FXML
    private ChoiceBox qualifier_variable_dropdown;

    @FXML
    private ChoiceBox quantifier_dropdown;

    @FXML
    private ChoiceBox second_subject_dropdown;

    @FXML
    private Button summarizer_add_button;

    @FXML
    private ChoiceBox summarizer_label_dropdown;

    @FXML
    private ListView<String> summarizer_listview;

    @FXML
    private Button summarizer_reset_button;

    @FXML
    private ChoiceBox summarizer_variable_dropdown;


    private DataHolder dataHolder;

    public void setDataHolder(DataHolder dataHolder) {
        this.dataHolder = dataHolder;
//        System.out.println(this.dataHolder.quantifiers.size());
    }

    public Quantifier getQuantifier(String name){
        for(int i=0;i<dataHolder.quantifiers.size();i++){
            if(name.equals(dataHolder.quantifiers.get(i).getName())){
                return dataHolder.quantifiers.get(i);
            }
        }
        return null;
    }

    public List<String> getQuantifiers(){
        List<String> tmp= new ArrayList<>();
        for(int i=0;i<dataHolder.quantifiers.size();i++){
            tmp.add(dataHolder.quantifiers.get(i).getName());
        }
        return tmp;
    }

    public Quantifier getFourthFormQuantifier(){
        return dataHolder.quantifiers.get(dataHolder.quantifiers.size()-1);
    }

    public List<String> getVariables(){
        List<String> tmp= new ArrayList<>();
        for(int i=0;i<dataHolder.variables.size();i++){
            tmp.add(dataHolder.variables.get(i).getName());
        }
        return tmp;
    }

    ObservableList<SummaryObs> summariesObs = FXCollections.observableArrayList();
//    public ObservableList<Summary> getSummaries() {
//
//        double i = 0.0;
//        for(int j=0;j<10;j++){
//        while (i < 1.0) {
//            summaries.add(new Summary("aaa", i, 1-i, i, i, i, i, i, i, i, i, i));
//            i+=0.1;
//        }
//        i=0;}
////        System.out.println("dupa");
//        return summaries;
//    }




    @FXML
    public void switchToAdvancedPanel(ActionEvent event) throws IOException {
//        Parent root= FXMLLoader.load(getClass().getResource("AdvancedPanel.fxml"));
        FXMLLoader loader=new FXMLLoader(getClass().getResource("AdvancedPanel.fxml"));
        root=loader.load();
        AdvancedPanelController advancedPanelController=loader.getController();
        advancedPanelController.setDataHolder(dataHolder);

        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    public void SetSortable(){
        Table.getSortOrder().add(T1Column);
        Table.getSortOrder().add(T2Column);
        Table.getSortOrder().add(T3Column);
        Table.getSortOrder().add(T4Column);
        Table.getSortOrder().add(T5Column);
        Table.getSortOrder().add(T6Column);
        Table.getSortOrder().add(T7Column);
        Table.getSortOrder().add(T8Column);
        Table.getSortOrder().add(T9Column);
        Table.getSortOrder().add(T10Column);
        Table.getSortOrder().add(T11Column);
        Table.getSortOrder().add(EOSColumn);
        Table.getSortOrder().add(OSColumn);
    }


    private List<String> getSubjects() {
        List<String> tmp= new ArrayList<>();
        for(int i=0;i<dataHolder.subjects.size();i++){
            tmp.add(dataHolder.subjects.get(i).getName());
        }
        return tmp;
    }
    public void loadResults(ActionEvent actionEvent) {
        quantifier_dropdown.getItems().setAll(getQuantifiers());
        summarizer_variable_dropdown.getItems().setAll(getVariables());
        qualifier_variable_dropdown.getItems().setAll(getVariables());
        summarizer_listview.setItems(summarizerList);
        qualifier_listview.setItems(qualifierList);
        first_subject_dropdown.getItems().setAll(getSubjects());
        second_subject_dropdown.getItems().setAll(getSubjects());

    }



    public void getSummarizerLabels(Event actionEvent){
        String value= String.valueOf(summarizer_variable_dropdown.getValue());
//        System.out.println(value);
        for(int i=0;i<dataHolder.variables.size();i++){
            if(value.equals(dataHolder.variables.get(i).getName())){
                summarizer_label_dropdown.getItems().setAll(dataHolder.variables.get(i).getValues());
                break;
            }

        }
    }

    public void getQualifierLabels(Event actionEvent){
        String value= String.valueOf(qualifier_variable_dropdown.getValue());
//        System.out.println(value);
        for(int i=0;i<dataHolder.variables.size();i++){
            if(value.equals(dataHolder.variables.get(i).getName())){
                qualifier_label_dropdown.getItems().setAll(dataHolder.variables.get(i).getValues());
            }

        }
    }

    ObservableList<String> summarizerList=FXCollections.observableArrayList();


    List<FuzzySet> summarizers=new ArrayList<>();
    List<Boolean> summarizersAnd=new ArrayList<>();

    List<LabelLing> summarizerLabels=new ArrayList<LabelLing>();

    public void summarizerAdd(ActionEvent actionEvent) {
        if(SummarizerAddOr.isDisabled()){
            String variable= String.valueOf(summarizer_variable_dropdown.getValue());
            String label=String.valueOf(summarizer_label_dropdown.getValue());
            summarizerList.add(variable+' '+label);


            Variable tmp=  getVariable(String.valueOf(summarizer_variable_dropdown.getValue()));
            summarizerLabels.add(new LabelLing(tmp.getName(),tmp.getFoodProperty(),String.valueOf(summarizer_label_dropdown.getValue())));
            summarizers.add(new FuzzySet(dataHolder.foodItems,tmp, String.valueOf(summarizer_label_dropdown.getValue())));





        }

        else{
            String variable= String.valueOf(summarizer_variable_dropdown.getValue());
            String label=String.valueOf(summarizer_label_dropdown.getValue());
            String operation=String.valueOf(SummarizerAddOr.getValue());
            summarizerList.add(operation+' '+variable+' '+label);
            Variable tmp=  getVariable(String.valueOf(summarizer_variable_dropdown.getValue()));
            summarizerLabels.add(new LabelLing(tmp.getName(),tmp.getFoodProperty(),String.valueOf(summarizer_label_dropdown.getValue())));
            summarizers.add(new FuzzySet(dataHolder.foodItems,tmp, String.valueOf(summarizer_label_dropdown.getValue())));
            summarizersAnd.add(String.valueOf(SummarizerAddOr.getValue()).equals("And"));

//            System.out.println(String.valueOf(SummarizerAddOr.getValue()).equals("And"));

        }

        if(!summarizerList.isEmpty()){
            SummarizerAddOr.setDisable(false);
        }
    }

    private Variable getVariable(String variable_name) {
        for(int i=0;i<dataHolder.variables.size();i++){
            if(variable_name.equals(dataHolder.variables.get(i).getName())){
                return dataHolder.variables.get(i);
            }
        }
        return null;

    }

    public void summarizerReset(ActionEvent actionEvent) {
        summarizerList.clear();
        SummarizerAddOr.setDisable(true);
    }

    ObservableList<String> qualifierList=FXCollections.observableArrayList();

    List<FuzzySet> qualifiers=new ArrayList<>();
    List<LabelLing> qualifierLabels=new ArrayList<LabelLing>();
    List<Boolean> qualifiersAnd=new ArrayList<>();
    public void qualifierAdd(ActionEvent actionEvent) {
        if(QualifierAddOr.isDisabled()){
            String variable= String.valueOf(qualifier_variable_dropdown.getValue());
            String label=String.valueOf(qualifier_label_dropdown.getValue());
            qualifierList.add(variable+' '+label);
            Variable tmp=  getVariable(String.valueOf(qualifier_variable_dropdown.getValue()));
            qualifierLabels.add(new LabelLing(tmp.getName(),tmp.getFoodProperty(),String.valueOf(qualifier_label_dropdown.getValue())));
            qualifiers.add(new FuzzySet(dataHolder.foodItems,tmp, String.valueOf(qualifier_label_dropdown.getValue())));

        }

        else{
            String variable= String.valueOf(qualifier_variable_dropdown.getValue());
            String label=String.valueOf(qualifier_label_dropdown.getValue());
            String operation=String.valueOf(QualifierAddOr.getValue());
            qualifierList.add(operation+' '+variable+' '+label);
            Variable tmp=  getVariable(String.valueOf(qualifier_variable_dropdown.getValue()));
            qualifierLabels.add(new LabelLing(tmp.getName(),tmp.getFoodProperty(),String.valueOf(qualifier_label_dropdown.getValue())));
            qualifiers.add(new FuzzySet(dataHolder.foodItems,tmp, String.valueOf(qualifier_label_dropdown.getValue())));
            qualifiersAnd.add(String.valueOf(QualifierAddOr.getValue()).equals("And"));}

        if(!qualifierList.isEmpty()){
            QualifierAddOr.setDisable(false);
        }
    }

    public void qualifierReset(ActionEvent actionEvent) {
        qualifierList.clear();
        QualifierAddOr.setDisable(true);
    }



    public Subject getSubject(String name){
        for (int i=0;i<dataHolder.subjects.size();i++){
            if(dataHolder.subjects.get(i).getName().equals(name)){
                return dataHolder.subjects.get(i);
            }
        }
        return null;
    }

    public void Apply(ActionEvent actionEvent) {
        Quantifier quantifier=getQuantifier(String.valueOf(quantifier_dropdown.getValue()));


        //summarizer
        List<Agregator> summ = new ArrayList<>();

        for (int i = 0; i < summarizers.size(); i++) {
            summ.add(new Agregator(summarizers.get(i), summarizerLabels.get(i)));
        }
        summ.add(new Agregator(summarizers, summarizersAnd, summarizerLabels));

        //qualifier
        List<Agregator> qual = new ArrayList<>();

        for (int i = 0; i < qualifiers.size(); i++) {
            summ.add(new Agregator(qualifiers.get(i), qualifierLabels.get(i)));
        }
        qual.add(new Agregator(qualifiers, qualifiersAnd, qualifierLabels));

        Quantifier fourthFormQuantifier=getFourthFormQuantifier();


        List<Summary> summaries = new ArrayList<>();
        Subject subject=getSubject(String.valueOf(first_subject_dropdown.getValue()));
        Subject secondSubject=getSubject(String.valueOf(second_subject_dropdown.getValue()));
        for (Agregator ag : summ) {
            summaries.add(SummaryBuilder.buildSummary(quantifier, subject, null, new Agregator(), ag, SummaryType.SINGLE_SUBJECT)); // jednopodmiotowe pierwszej formy
            if (secondSubject != null) {
                summaries.add(SummaryBuilder.buildSummary(quantifier, subject, secondSubject, new Agregator(), ag, SummaryType.FIRST_FORM)); // wielopodmiotowe pierwszej formy
                summaries.add(SummaryBuilder.buildSummary(quantifier, secondSubject, subject, new Agregator(), ag, SummaryType.FIRST_FORM)); // wielopodmiotowe pierwszej formy uzupełniające
                summaries.add(SummaryBuilder.buildSummary(fourthFormQuantifier, subject, secondSubject, new Agregator(), ag, SummaryType.FOURTH_FORM)) ;// wielopodmiotowe czwartej formy
                summaries.add(SummaryBuilder.buildSummary(fourthFormQuantifier, subject, secondSubject, new Agregator(), ag, SummaryType.FOURTH_FORM)) ;// wielopodmiotowe czwartej formy uzupełniające
            }

            for (Agregator agg : qual) {
                summaries.add(SummaryBuilder.buildSummary(quantifier, subject, null, agg, ag, SummaryType.SINGLE_SUBJECT) ); // jednopodmiotowe drugiej formy
                summaries.add(SummaryBuilder.buildSummary(quantifier, subject, secondSubject, agg, ag, SummaryType.SECOND_FORM)) ;// wielopodmiotowe drugiej formy
                summaries.add(SummaryBuilder.buildSummary(quantifier, secondSubject, subject, agg, ag, SummaryType.SECOND_FORM)) ;// wielopodmiotowe drugiej formy uzupełniające
                summaries.add(SummaryBuilder.buildSummary(quantifier, subject, secondSubject, agg, ag, SummaryType.THIRD_FORM)) ;// wielopodmiotowe trzeciej formy
                summaries.add(SummaryBuilder.buildSummary(quantifier, secondSubject, subject, agg, ag, SummaryType.THIRD_FORM)) ;// wielopodmiotowe trzeciej formy uzupełniające
            }

        }
        List<Double> weights=new ArrayList<>();
        List<Double> extweights=new ArrayList<>();
        weights.add(Double.parseDouble(String.valueOf(W1_textField_os.getText())));
        weights.add(Double.parseDouble(String.valueOf(W2_textField_os.getText())));
        weights.add(Double.parseDouble(String.valueOf(W3_textField_os.getText())));
        weights.add(Double.parseDouble(String.valueOf(W4_textField_os.getText())));
        weights.add(Double.parseDouble(String.valueOf(W5_textField_os.getText())));


        extweights.add(Double.parseDouble(String.valueOf(W1_textField_eos.getText())));
        extweights.add(Double.parseDouble(String.valueOf(W2_textField_eos.getText())));
        extweights.add(Double.parseDouble(String.valueOf(W3_textField_eos.getText())));
        extweights.add(Double.parseDouble(String.valueOf(W4_textField_eos.getText())));
        extweights.add(Double.parseDouble(String.valueOf(W5_textField_eos.getText())));
        extweights.add(Double.parseDouble(String.valueOf(W6_textField_eos.getText())));
        extweights.add(Double.parseDouble(String.valueOf(W7_textField_eos.getText())));
        extweights.add(Double.parseDouble(String.valueOf(W8_textField_eos.getText())));
        extweights.add(Double.parseDouble(String.valueOf(W9_textField_eos.getText())));
        extweights.add(Double.parseDouble(String.valueOf(W10_textField_eos.getText())));
        extweights.add(Double.parseDouble(String.valueOf(W11_textField_eos.getText())));
        Table.setItems(summariesObs);
        for(int i=0;i<summaries.size();i++){
            System.out.println(i);
            summariesObs.add(new SummaryObs(summaries.get(i),weights,extweights));
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SetSortable();
        SummaryColumn.setCellValueFactory(new PropertyValueFactory<>("sentence"));
        T1Column.setCellValueFactory(new PropertyValueFactory<>("T1"));
        T2Column.setCellValueFactory(new PropertyValueFactory<>("T2"));
        T3Column.setCellValueFactory(new PropertyValueFactory<>("T3"));
        T4Column.setCellValueFactory(new PropertyValueFactory<>("T4"));
        T5Column.setCellValueFactory(new PropertyValueFactory<>("T5"));
        T6Column.setCellValueFactory(new PropertyValueFactory<>("T6"));
        T7Column.setCellValueFactory(new PropertyValueFactory<>("T7"));
        T8Column.setCellValueFactory(new PropertyValueFactory<>("T8"));
        T9Column.setCellValueFactory(new PropertyValueFactory<>("T9"));
        T10Column.setCellValueFactory(new PropertyValueFactory<>("T10"));
        T11Column.setCellValueFactory(new PropertyValueFactory<>("T11"));
        OSColumn.setCellValueFactory(new PropertyValueFactory<>("OS"));
        EOSColumn.setCellValueFactory(new PropertyValueFactory<>("EOS"));
        summarizer_variable_dropdown.setOnAction(this::getSummarizerLabels);
        qualifier_variable_dropdown.setOnAction(this::getQualifierLabels);
        SummarizerAddOr.getItems().add("And");
        SummarizerAddOr.getItems().add("Or");
        QualifierAddOr.getItems().add("And");
        QualifierAddOr.getItems().add("Or");
        SummarizerAddOr.setDisable(true);
        QualifierAddOr.setDisable(true);


    }


    public void resetTable(ActionEvent actionEvent) {
        summariesObs.clear();

    }

    public void SaveToFile(ActionEvent actionEvent) throws IOException {
        SummaryObs rows= Table.getSelectionModel().getSelectedItem();
        System.out.println(rows.toString());
        BufferedWriter writer = new BufferedWriter(new FileWriter("saved.txt", true));

        writer.append(rows.toString());
        writer.append("\n");

        writer.close();
    }

    public void switchToBasicPanel(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("..\\main.fxml"));
        root=loader.load();
        MainController mainController=loader.getController();
        mainController.setDataHolder(dataHolder);

        stage=(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
