module helpdesk.helpdeskfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    exports Main;
    opens Main;
    opens GUI to javafx.fxml;
    exports GUI;
}