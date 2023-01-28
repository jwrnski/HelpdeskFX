package GUI;

import Main.IDValidator;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.Serializable;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainWindow extends Application implements Initializable, Serializable {

    @FXML Label helloLabel;



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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String name = IDValidator.name;
        helloLabel.setText("Hello " + name + "!");

    }
}
