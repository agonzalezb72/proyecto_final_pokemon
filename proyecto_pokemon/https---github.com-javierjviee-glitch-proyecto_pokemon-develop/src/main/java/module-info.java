module com.pokemon {

    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires com.google.gson;
    requires java.sql;
    requires javafx.media;

    opens com.pokemon.controles to javafx.fxml;
    opens com.pokemon to javafx.fxml;
    opens com.pokemon.modelo to com.google.gson, javafx.base;
    opens com.pokemon.habilidades to com.google.gson;

    exports com.pokemon;
    exports com.pokemon.modelo;
    exports com.pokemon.controles;
    exports com.pokemon.habilidades;
    
}