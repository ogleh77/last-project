module com.example.lastproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires AnimateFX;
    requires com.jfoenix;
    opens com.example.lastproject to javafx.fxml;
    opens com.example.lastproject.controllers to javafx.fxml;
    exports com.example.lastproject;
}