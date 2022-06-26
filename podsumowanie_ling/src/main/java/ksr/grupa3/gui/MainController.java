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

public class MainController implements Initializable {

    public ChoiceBox QualifierAddOr;
    public Button LoadResults;
    public Button ResetTableButton;
    public TextField W10_textField_eos;
    public Button Advanced_panel_generation_button;
    public Button save_all_to_file_button;
    public Label counter;
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
    private Button qualifier_add_button;

    @FXML
    private ChoiceBox qualifier_label_dropdown;

    @FXML
    private ListView<String> qualifier_listview;

    @FXML
    private Button qualifier_reset_button;

    @FXML
    private ChoiceBox qualifier_variable_dropdown;














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

//    public List<Quantifier> getQuantifiers(){
//
//    }

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

        qualifier_variable_dropdown.getItems().setAll(getVariables());
        
        qualifier_listview.setItems(qualifierList);


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






    private Variable getVariable(String variable_name) {
        for(int i=0;i<dataHolder.variables.size();i++){
            if(variable_name.equals(dataHolder.variables.get(i).getName())){
                return dataHolder.variables.get(i);
            }
        }
        return null;

    }



    ObservableList<String> qualifierList=FXCollections.observableArrayList();


    public void qualifierAdd(ActionEvent actionEvent) {
        String variable= String.valueOf(qualifier_variable_dropdown.getValue());
        String label=String.valueOf(qualifier_label_dropdown.getValue());
        qualifierList.add(variable+' '+label);
        Variable tmp=  getVariable(String.valueOf(qualifier_variable_dropdown.getValue()));
        qualifierLabels.add(new LabelLing(tmp.getName(),tmp.getFoodProperty(),String.valueOf(qualifier_label_dropdown.getValue())));
        qualifiers.add(new FuzzySet(dataHolder.foodItems,tmp, String.valueOf(qualifier_label_dropdown.getValue())));
        qualifiersAnd.add(true);
    }

    public void qualifierReset(ActionEvent actionEvent) {
        qualifierList.clear();
        qualifiers.clear();
        qualifierLabels.clear();
        qualifiersAnd.clear();
//        QualifierAddOr.setDisable(true);
    }



    public Subject getSubject(String name){
        for (int i=0;i<dataHolder.subjects.size();i++){
            if(dataHolder.subjects.get(i).getName().equals(name)){
                return dataHolder.subjects.get(i);
            }
        }
        return null;
    }


    List<FuzzySet> qualifiers=new ArrayList<>();
    List<LabelLing> qualifierLabels=new ArrayList<LabelLing>();
    List<Boolean> qualifiersAnd=new ArrayList<>();

    public void Apply(ActionEvent actionEvent) {
    	long startTime = System.currentTimeMillis();
    	
        counter.setText("In progress");
        List<List<Boolean>> ands = new ArrayList<>();
        for (int i = 0; i < qualifiers.size(); i++) {
            List<Boolean> t = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                t.add(true);
            }
            ands.add(t);
        }

        // wszystkie kombinacje setÃ³w/zmiennych
        ArrayList<ArrayList<FuzzySet>> allsubsetsS =new ArrayList<ArrayList<FuzzySet>>();
        ArrayList<ArrayList<LabelLing>> allsubsetsL =new ArrayList<ArrayList<LabelLing>>();
        int max = 1 << qualifiers.size();

        for (int i = 0; i < max; i++) {
            ArrayList<FuzzySet> subsetS = new ArrayList<>();
            ArrayList<LabelLing> subsetL = new ArrayList<>();
            for (int j = 0; j < qualifiers.size(); j++) {
                if (((i >> j) & 1) == 1) {
                    subsetS.add(qualifiers.get(j));
                    subsetL.add(qualifierLabels.get(j));
                }
            }
            allsubsetsS.add(subsetS);
            allsubsetsL.add(subsetL);
        }
        List<Quantifier> quantifiers=dataHolder.quantifiers;
        Quantifier forthFormQuantifier = quantifiers.remove(quantifiers.size() - 1);
        List<Subject> subjects=dataHolder.subjects;
        List<Summary> summaries=new ArrayList<>();
        for (Quantifier q : quantifiers) {
            // pierwsza forma
            for (int i = 1; i < allsubsetsS.size(); i++) {
                System.out.println(allsubsetsS.get(i).size());
                Agregator summ = new Agregator(allsubsetsS.get(i), ands.get(allsubsetsS.get(i).size()-1), allsubsetsL.get(i));
                // jednopodmiotowe
                Summary s = SummaryBuilder.buildSummary(q, subjects.get(0), null, new Agregator(), summ, SummaryType.SINGLE_SUBJECT);
                summaries.add(s);
                // wielopodmiotowe
                Summary s2 = SummaryBuilder.buildSummary(q, subjects.get(1), subjects.get(2), new Agregator(), summ, SummaryType.FIRST_FORM);
                summaries.add(s2);
                Summary s3 = SummaryBuilder.buildSummary(q, subjects.get(2), subjects.get(1), new Agregator(), summ, SummaryType.FIRST_FORM);
                summaries.add(s3);
            }
            //druga i trzecia forma
            for (int i = 1; i < allsubsetsS.size() - 1; i++) {
                for (int j = i+1; j < allsubsetsS.size(); j++) {
                    Agregator summ = new Agregator(allsubsetsS.get(i), ands.get(allsubsetsS.get(i).size()-1), allsubsetsL.get(i));
                    Agregator qual = new Agregator(allsubsetsS.get(j), ands.get(allsubsetsS.get(j).size()-1), allsubsetsL.get(j));
                    // jednopodmiotowe druga forma
                    Summary s = SummaryBuilder.buildSummary(q, subjects.get(0), null, qual, summ, SummaryType.SINGLE_SUBJECT);
                    summaries.add(s);
                    Summary s2 = SummaryBuilder.buildSummary(q, subjects.get(0), null, summ, qual, SummaryType.SINGLE_SUBJECT);
                    summaries.add(s2);
                    // wielopodmiotowe druga forma
                    Summary s3 = SummaryBuilder.buildSummary(q, subjects.get(1), subjects.get(2), qual, summ, SummaryType.SECOND_FORM);
                    summaries.add(s3);
                    Summary s4 = SummaryBuilder.buildSummary(q, subjects.get(1), subjects.get(2), summ, qual, SummaryType.SECOND_FORM);
                    summaries.add(s4);
                    Summary s5 = SummaryBuilder.buildSummary(q, subjects.get(2), subjects.get(1), qual, summ, SummaryType.SECOND_FORM);
                    summaries.add(s5);
                    Summary s6 = SummaryBuilder.buildSummary(q, subjects.get(2), subjects.get(1), summ, qual, SummaryType.SECOND_FORM);
                    summaries.add(s6);
                    // wielopodmiotowe trzecia forma
                    Summary s7 = SummaryBuilder.buildSummary(q, subjects.get(1), subjects.get(2), qual, summ, SummaryType.THIRD_FORM);
                    summaries.add(s7);
                    Summary s8 = SummaryBuilder.buildSummary(q, subjects.get(1), subjects.get(2), summ, qual, SummaryType.THIRD_FORM);
                    summaries.add(s8);
                    Summary s9 = SummaryBuilder.buildSummary(q, subjects.get(2), subjects.get(1), qual, summ, SummaryType.THIRD_FORM);
                    summaries.add(s9);
                    Summary s10 = SummaryBuilder.buildSummary(q, subjects.get(2), subjects.get(1), summ, qual, SummaryType.THIRD_FORM);
                    summaries.add(s10);
                }
            }
        }
        // wielopodmiotowe czwarta forma
        for (int i = 1; i < allsubsetsS.size() - 1; i++) {
            Agregator summ = new Agregator(allsubsetsS.get(i), ands.get(allsubsetsS.get(i).size()-1), allsubsetsL.get(i));

            Summary s = SummaryBuilder.buildSummary(forthFormQuantifier, subjects.get(1), subjects.get(2), new Agregator(), summ, SummaryType.FOURTH_FORM);
            summaries.add(s);
            Summary s2 = SummaryBuilder.buildSummary(forthFormQuantifier, subjects.get(2), subjects.get(1), new Agregator(), summ, SummaryType.FOURTH_FORM);
            summaries.add(s2);
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
            counter.setText(String.valueOf(i));
            summariesObs.add(new SummaryObs(summaries.get(i),weights,extweights));
        }
        counter.setText("Done");
        long execTime = System.currentTimeMillis() - startTime;
        System.out.println("Processing completed. Execution time: " + execTime + " [ms]");
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

        qualifier_variable_dropdown.setOnAction(this::getQualifierLabels);

    }


    public void resetTable(ActionEvent actionEvent) {
        summariesObs.clear();
//        counter.setText("Reseted");
//        counter.setText(String.valueOf(5));

    }

    public void SaveToFile(ActionEvent actionEvent) throws IOException {
        SummaryObs rows= Table.getSelectionModel().getSelectedItem();
        System.out.println(rows.toString());
        BufferedWriter writer = new BufferedWriter(new FileWriter("saved.txt", true));

            writer.append(rows.toString());
            writer.append("\n");

        writer.close();
    }

    public void switchToAdvancedPanelGeneration(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("AdvancedPanelGenerate.fxml"));
        root=loader.load();
        AdvancedPanelGenerationController advancedPanelController=loader.getController();
        advancedPanelController.setDataHolder(dataHolder);

        stage=(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void saveAllToFile(ActionEvent actionEvent) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("savedAll.txt", true));
        for (int i=0;i<summariesObs.size();i++){

            writer.append(summariesObs.get(i).toString());
            writer.append("\n");
        }
        writer.close();
    }
}
