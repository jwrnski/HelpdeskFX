package GUI;

import Main.IDValidator;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class RegisterWindow extends Application implements Initializable, Serializable {

    @FXML private Button registerButton;
    @FXML private TextField nameTextBox;
    @FXML private TextField idTextBox;
    @FXML private Label errorLabel;
    @FXML private ChoiceBox<String> departmentChoiceBox;
    @FXML private ChoiceBox<String> divisionChoiceBox;
    @FXML private Button idHelpButton;


    public String name = "", department = "", division = "";
    public String [] departments = {"Production", "Office", "IT"};
    public int id, x, test;
    public Exception nameError = new Exception();

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("registerWindow.fxml")));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Helpdesk - Register");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            Platform.runLater(root::requestFocus);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        departmentChoiceBox.getItems().addAll(departments);
        departmentChoiceBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String[] divisions = Main.Main.Divisions(newValue);
                divisionChoiceBox.getItems().addAll(divisions);
            }
            if(oldValue != null) {
                divisionChoiceBox.getItems().clear();
                assert newValue != null;
                String[] divisions = Main.Main.Divisions(newValue);
                divisionChoiceBox.getItems().addAll(divisions);
            }
        });
    }

    @FXML
    public void idHelpHandler(ActionEvent actionEvent){
        Alert idHelp = new Alert(Alert.AlertType.NONE,"ID instruction.",ButtonType.OK);
        idHelp.setTitle("How to create an ID.");
        idHelp.setContentText("""
                The first two numbers correspond with your department.
                
                10xxx - Production
                20xxx - Office
                30xxx - IT""");
        idHelp.show();
    }

    @FXML
    public void registerHandler(ActionEvent actionEvent) throws IOException, InterruptedException {

        boolean error = false;
        String nameCheck = nameTextBox.getText();
        if(!nameCheck.matches("[a-zA-Z]+")){
            errorLabel.setText("Incorrect name!");
            nameTextBox.clear();
            error = true;
        }
        else
            name = nameCheck;
        department = departmentChoiceBox.getValue();
        try{
            test = Integer.parseInt(idTextBox.getText());
            if(!(test > 10000 && test < 30999)){
                errorLabel.setText("Incorrect ID!");
                idTextBox.clear();
            }
            if(test == 0){
                errorLabel.setText("Enter your ID!");
                idTextBox.clear();
            }
            else{
                boolean contains = IDValidator.validateFromJSON(test);
                if(contains){
                    errorLabel.setText("User with this ID already exists!");
                    idTextBox.clear();
                }
                else id = test;
            }
        }
        catch (NumberFormatException e){
            errorLabel.setText("Incorrect ID!");
        }
        division = divisionChoiceBox.getValue();
        if(name != null && id != 0 && department != null && division != null && !error){
            Main.EmployeeCreation.createEmployee(name, id, department, division);
            errorLabel.setText("Register successful!");
            changeWindow(actionEvent);
        }
        else{
            errorLabel.setText("Incorrect values.");
        }
    }
    public static void changeWindow(ActionEvent actionEvent) throws InterruptedException {
        PauseTransition pause = new PauseTransition();
        pause.setDuration(Duration.seconds(1));
        pause.setOnFinished(event -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(RegisterWindow.class.getResource("loginWindow.fxml")));
                Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });
        pause.play();
    }
    public static void main(String[] args) {
        launch();
    }
}
