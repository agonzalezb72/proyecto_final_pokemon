module com.pokemon {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.pokemon to javafx.fxml;
    exports com.pokemon;
}
