module com.ja.optimgui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ja.optimgui to javafx.fxml;
    exports com.ja.optimgui;

}