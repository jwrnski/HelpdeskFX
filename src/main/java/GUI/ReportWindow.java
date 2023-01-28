package GUI;

import Main.IDValidator;
import Main.JsonFileAppender;
import Main.Report;
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
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ReportWindow extends Application implements Serializable, Initializable {

    @FXML private Button submitButton;
    @FXML private TextField subjectTextField;
    @FXML private TextArea descriptionTextArea;
    @FXML private ChoiceBox<String> priorityBox;
    @FXML private Label errorLabel;

    private final String[] priorities = {"Low", "Medium", "High"};
    private static int id;

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("reportWindow.fxml")));
            Scene scene = new Scene(root);
            stage.setTitle("New report.");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            Platform.runLater(root::requestFocus);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        priorityBox.getItems().addAll(priorities);
    }

    @FXML
    public void mainController(ActionEvent actionEvent) throws IOException {
        id = IDValidator.ID;
        String subject = subjectTextField.getText();
        String description = descriptionTextArea.getText();
        String priority = priorityBox.getValue();
        String status = "New";
        if(!(subject == null && description == null && priority == null)){
            Main.Report report = new Report(id, subject, description, priority, status);
            saveToJSON(report);
            errorLabel.setText("New report created!");
            changeToMainWindow(actionEvent);
        }
        else
            errorLabel.setText("No empty fields!");
    }
    static JsonFileAppender json = new JsonFileAppender();
    public static void saveToJSON(Object report) throws IOException {
        String fileName = id + "_Reports.json";
        String filePath = "C:\\Users\\Kuba\\Desktop\\HelpdeskFX\\src\\main\\java\\UserReports\\" + fileName;
        File file = new File(filePath);
        json.appendToArray(file, report);
    }
    public void changeToMainWindow(ActionEvent actionEvent){
        PauseTransition pause = new PauseTransition();
        pause.setDuration(Duration.seconds(0.5));
        pause.setOnFinished(event -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(RegisterWindow.class.getResource("mainWindow.fxml")));
                Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setTitle("Helpdesk");
                stage.setScene(scene);
                Platform.runLater(root::requestFocus);
                stage.show();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });
        pause.play();
    }
}
