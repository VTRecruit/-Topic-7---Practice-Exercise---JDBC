module com.example {
    requires transitive java.sql;
    requires transitive javafx.graphics;
    requires transitive javafx.base;

    requires javafx.controls;
    requires javafx.fxml;

    opens com.example to javafx.fxml;
    exports com.example;
}