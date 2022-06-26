package ksr.grupa3.gui;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ksr.grupa3.DataHolder;
import ksr.grupa3.fuzzy.*;
import ksr.grupa3.ling.Quantifier;
import ksr.grupa3.ling.Variable;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AdvancedPanelController implements Initializable {

    public Button reset_button;
    public TextField upperbound_textfield;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label A_label;

    @FXML
    private TextField A_textField;

    @FXML
    private Label B_label;

    @FXML
    private TextField B_textField;

    @FXML
    private Label C_label;

    @FXML
    private TextField C_textField;

    @FXML
    private Label D_label;

    @FXML
    private TextField D_textField;

    @FXML
    private Button Delete_button;

    @FXML
    private Label Label_label;

    @FXML
    private Button Load_button;

    @FXML
    private Button apply_button;

    @FXML
    private ChoiceBox<String> check_box_ling_variable_or_qual_type;

    @FXML
    private ChoiceBox<String> check_box_type;

    @FXML
    private ChoiceBox<String> function_type;

    @FXML
    private Button go_to_main;

    @FXML
    private ListView<String> labels_listview;

    @FXML
    private TextField name_placeholder;

    @FXML
    private ListView<String> quantifier_listview;

    @FXML
    private Label quantifiers_label;

    @FXML
    private Label variables_label;

    @FXML
    private ListView<String> variables_listview;

    @FXML
    private ComboBox<String> which_action;

    public DataHolder dataHolder;

    public void setDataHolder(DataHolder dataHolder){
        this.dataHolder=dataHolder;
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

    public List<String> getVariables(){
        List<String> tmp= new ArrayList<>();
        for(int i=0;i<dataHolder.variables.size();i++){
            tmp.add(dataHolder.variables.get(i).getName());
        }
        return tmp;
    }


    @FXML
    public void switch_to_main(ActionEvent event) throws IOException {
//        Parent root= FXMLLoader.load(getClass().getResource("main.fxml"));
//        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
//        scene=new Scene(root);
//        stage.setScene(scene);
//        stage.show();


        FXMLLoader loader=new FXMLLoader(getClass().getResource("..\\main.fxml"));
        root=loader.load();
        MainController mainController=loader.getController();
        mainController.setDataHolder(dataHolder);

        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void delete_selected(ActionEvent event) {

    }

    @FXML
    void add_new(ActionEvent event) {

    }

    @FXML
    void edit_existing(ActionEvent event) {

    }


    public MembershipFuction AddFunction(double upperBound){
        MembershipFuction function;
        if(String.valueOf(function_type.getValue()).equals(functions.get(0))){
            double a=Double.valueOf(A_textField.getText());
            double b=Double.valueOf(B_textField.getText());
            double c=Double.valueOf(C_textField.getText());


            function=new GaussFuction(a,b,c,upperBound);
            return function;
        }
        if(String.valueOf(function_type.getValue()).equals(functions.get(1))){
            double a=Double.valueOf(A_textField.getText());
            double b=Double.valueOf(B_textField.getText());
            double c=Double.valueOf(C_textField.getText());

            function=new TriangleFuction(a,b,c,upperBound);
            return function;
        }
        if(String.valueOf(function_type.getValue()).equals(functions.get(2))){
            double a=Double.valueOf(A_textField.getText());
            double b=Double.valueOf(B_textField.getText());
            double c=Double.valueOf(C_textField.getText());
            double d=Double.valueOf(D_textField.getText());
            function=new TrapezoidFuction(a,b,c,d,upperBound);
            return function;
        }
        return null;
    }

    @FXML
    void apply(ActionEvent event) {
        if(which_action.getValue().equals(options.get(0))){
            Quantifier quantifier=new Quantifier();
            quantifier.setName(String.valueOf(name_placeholder.getText()));
            double upperBound;
            if(String.valueOf(check_box_type.getValue()).equals("Absolute")){
            quantifier.setIsAbsolute(true);
            upperBound=13544;
            }
            else
            {
                quantifier.setIsAbsolute(false);
                upperBound=1;
            }
            quantifier.setMemberFunc(AddFunction(upperBound));
            dataHolder.quantifiers.add(quantifier);
            quantifier_listview.getItems().addAll(getQuantifiers());
            check_box_ling_variable_or_qual_type.setDisable(false);

        }

        if(which_action.getValue().equals(options.get(1))){
            try {

                Variable variable=new Variable(String.valueOf(name_placeholder.getText()), FoodItem.class.getDeclaredField(String.valueOf(check_box_type.getValue())),new ArrayList<>(),new ArrayList<>(),String.valueOf(check_box_type.getValue())=="calories"?1000:100);
                dataHolder.variables.add(variable);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }

        if(which_action.getValue().equals(options.get(2))){

            for(int i=0;i<dataHolder.variables.size();i++){
                if(String.valueOf(check_box_type.getValue()).equals(dataHolder.variables.get(i).getName())){
                    double upperbound=Double.parseDouble(String.valueOf(upperbound_textfield.getText()));
                    dataHolder.variables.get(i).AddMembershipFunctionAndValue(String.valueOf(name_placeholder.getText()),AddFunction(upperbound));
//                    dataHolder.variables.get(i).AddMembershipFunctionAndValue("b",AddFunction(1000));
                    break;
                }
            }
            check_box_ling_variable_or_qual_type.setDisable(false);

        }



        reset();


    }



        
    private List<String> options= Arrays.asList("add quantifier","add variable","add label");
    private List<String> functions= Arrays.asList("Gaussian","Triangular","Trapezoid");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        upperbound_textfield.setVisible(false);
        which_action.getItems().addAll(options);
        which_action.setOnAction(this::setCheckBoxType);
        function_type.getItems().addAll(functions);
        function_type.setOnAction(this::disableD);
    }

    private void disableD(ActionEvent actionEvent) {
        if(function_type.getValue().equals(functions.get(2))){
            D_textField.setDisable(false);
        }
        else {
            D_textField.setDisable(true);
        }
    }

    private void setCheckBoxType(ActionEvent actionEvent) {
        String value=String.valueOf(which_action.getValue());
        if(value.equals(options.get(0))){
            reset();
            upperbound_textfield.setVisible(false);
            check_box_type.getItems().add("Absolute");
            check_box_type.getItems().add("Relative");
            check_box_ling_variable_or_qual_type.setDisable(true);

        }

        if(value.equals(options.get(1))){
            reset();
            check_box_ling_variable_or_qual_type.setDisable(true);
            check_box_ling_variable_or_qual_type.setVisible(false);
            function_type.setDisable(true);
            A_label.setDisable(true);
            B_label.setDisable(true);
            C_label.setDisable(true);
            D_label.setDisable(true);
            upperbound_textfield.setVisible(true);
            List<Field> fields=new ArrayList<>(List.of(FoodItem.class.getDeclaredFields()));

            for(int i=1;i<fields.size();i++){
                check_box_type.getItems().add(fields.get(i).getName());
            }



        }

        if(value.equals(options.get(2))){
            reset();

            check_box_type.getItems().removeAll();
            check_box_type.getItems().addAll(getVariables());
            check_box_ling_variable_or_qual_type.setDisable(true);
            check_box_ling_variable_or_qual_type.setVisible(false);
            upperbound_textfield.setVisible(true);

        }

    }


    public void loadData(ActionEvent actionEvent) {
        quantifier_listview.getItems().addAll(getQuantifiers());
        variables_listview.getItems().addAll(getVariables());
    }

    @FXML  public void onclickvariable(MouseEvent mouseEvent) {
        labels_listview.getItems().clear();
        String value=variables_listview.getSelectionModel().getSelectedItem();
        System.out.println(value);
        for(int i=0;i<dataHolder.variables.size();i++){
            if(value.equals(dataHolder.variables.get(i).getName())){
                labels_listview.getItems().addAll(dataHolder.variables.get(i).getValues());
                break;
            }
        }
    }

    public void reset(ActionEvent actionEvent) {
        //reset
        upperbound_textfield.setVisible(false);
        check_box_type.getItems().clear();
        check_box_ling_variable_or_qual_type.getItems().clear();
        check_box_type.setDisable(false);
        check_box_ling_variable_or_qual_type.setDisable(false);
        check_box_ling_variable_or_qual_type.setVisible(true);
        function_type.setDisable(false);
        A_label.setDisable(false);
        B_label.setDisable(false);
        C_label.setDisable(false);
        D_label.setDisable(false);
        which_action.getItems().clear();
        which_action.getItems().addAll(options);
        name_placeholder.setText("");
    }

    public void reset() {
        //reset
        upperbound_textfield.setVisible(false);
        check_box_type.getItems().clear();
        check_box_ling_variable_or_qual_type.getItems().clear();
        check_box_type.setDisable(false);
        check_box_ling_variable_or_qual_type.setDisable(false);
        check_box_ling_variable_or_qual_type.setVisible(true);
        function_type.setDisable(false);
        A_label.setDisable(false);
        B_label.setDisable(false);
        C_label.setDisable(false);
        D_label.setDisable(false);
//        which_action.getItems().clear();
//        which_action.getItems().addAll(options);
        name_placeholder.setText("");


    }
}


