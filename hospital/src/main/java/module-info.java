module com.example.hospital {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.hospital to javafx.fxml;
    exports com.example.hospital;
}