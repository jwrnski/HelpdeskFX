package GUI;

import Main.EmployeeCreation;
import Main.IDValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;




public class LoginWindow extends Application {


    @FXML private Button loginButton;
    @FXML private Button registerButton;
    @FXML private Label errorLabel;
    @FXML private TextField idLoginField;

    public int id;

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("loginWindow.fxml")));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Helpdesk - Login");
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();
            Platform.runLater(root::requestFocus);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch();
    }

    public void loginButtonClick(ActionEvent actionEvent){

        try{
            id = Integer.parseInt(idLoginField.getText());
            if(id == 0){
                errorLabel.setText("Enter your ID!");
                idLoginField.clear();
            }
            if(!(id > 10000 && id < 30999)){
                errorLabel.setText("Incorrect ID!");
                idLoginField.clear();
            }
            else {
                boolean contains = IDValidator.validateFromJSON(id);
                if(contains){
                    errorLabel.setText("Login successful!");

                    changeToMainWindow(actionEvent);
                }
                else {
                    errorLabel.setText("No user with whis ID.");
                    idLoginField.clear();
                }
            }
        }
        catch (NumberFormatException e){
            errorLabel.setText("Incorrect ID!");
            idLoginField.clear();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeToMainWindow(ActionEvent actionEvent){
        PauseTransition pause = new PauseTransition();
        pause.setDuration(Duration.seconds(1));
        pause.setOnFinished(event -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(RegisterWindow.class.getResource("mainWindow.fxml")));
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

    public void registerButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("registerWindow.fxml")));
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
