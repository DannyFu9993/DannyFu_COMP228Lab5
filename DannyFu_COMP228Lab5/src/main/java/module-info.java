module com.example.dannyfu_comp228lab5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.dannyfu_comp228lab5 to javafx.fxml;
    exports com.example.dannyfu_comp228lab5;
}