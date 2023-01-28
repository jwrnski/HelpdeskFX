package GUI;

import Main.IDValidator;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.Serializable;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainWindow extends Application implements Initializable, Serializable {

    @FXML Label helloLabel;
    @FXML Label timeLabel;



    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainWindow.fxml")));
            Scene scene = new Scene(root);
            stage.setTitle("Helpdesk");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            Platform.runLater(root::requestFocus);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void mainController(ActionEvent actionEvent){

    }

    @FXML
    public void logoutController(ActionEvent actionEvent){
        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");
        Alert logoutAlert = new Alert(Alert.AlertType.NONE,"Logout.", yesButton, noButton);
        logoutAlert.setTitle("Logout.");
        logoutAlert.setContentText("Are you sure?");
        //logoutAlert.show();
        logoutAlert.showAndWait().ifPresent(response -> {
            if (response == yesButton)
                changeWindow(actionEvent);
            else if (response == noButton)
                logoutAlert.close();
        });
    }

    @FXML
    public void newReport(ActionEvent actionEvent){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(RegisterWindow.class.getResource("reportWindow.fxml")));
            Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("New Report");
            stage.setScene(scene);
            Platform.runLater(root::requestFocus);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void changeWindow(ActionEvent actionEvent){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(RegisterWindow.class.getResource("loginWindow.fxml")));
            Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Heldesk - Login");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String name = IDValidator.name;
        helloLabel.setText("Hello " + name + "!");
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE HH:mm:ss", Locale.getDefault());
        String dateTimeString = now.format(formatter);
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            timeLabel.setText(LocalDateTime.now().format(formatter));
        }),
                new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

    }
}
