module com.example.managementapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires org.xerial.sqlitejdbc; // Correct module name for SQLite JDBC
     // Correct module name for FontAwesomeFX
    requires com.google.gson;
    requires org.json;

    requires fontawesomefx;

    requires java.net.http;


    opens com.example.managementapp to javafx.fxml;
    exports com.example.managementapp;
    //export all packages


}