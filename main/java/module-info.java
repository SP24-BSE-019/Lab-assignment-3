module com.example.ooplabtask3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.ooplabtask3 to javafx.fxml;
    exports com.example.ooplabtask3;
}