module com.example.dentistry {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;


    opens com.example.dentistry to javafx.fxml;
    exports com.example.dentistry;
    exports com.example.dentistry.controller;
    opens com.example.dentistry.controller to javafx.fxml;
}