package com.pokemon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import com.pokemon.modelo.Pokemon;
import com.pokemon.controles.ControladorCombate;
import java.io.IOException;
import java.net.URL;

public class App extends Application {

    private static Scene scene;
    private static MediaPlayer musicPlayer;

    @Override
    public void start(Stage stage) throws IOException {
        reproducirMusica("/com/pokemon/music/batalla.mp3");

        Parent root = loadFXML("/com/pokemon/fxml/menu.fxml");
        scene = new Scene(root, 800, 600);
        
        stage.setTitle("Pokémon Battle Simulator");
        stage.setScene(scene);
        stage.show();
    }

    public static void abrirCombate(Pokemon jugador, Pokemon rival) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/pokemon/fxml/combate.fxml"));
            Parent root = loader.load();

            ControladorCombate controlador = loader.getController();
            if (controlador != null) {
                controlador.inicializar(jugador, rival);
                scene.setRoot(root);
                System.out.println("✅ Combate iniciado: " + jugador.getNombre() + " vs " + rival.getNombre());
            } else {
                System.err.println("❌ Error: No se pudo obtener el controlador de combate.");
            }
            
        } catch (IOException e) {
            System.err.println("❌ ERROR CRÍTICO al cargar combate.fxml:");
            e.printStackTrace();
        }
    }

    public static void reproducirMusica(String ruta) {
        try {
            URL recurso = App.class.getResource(ruta);
            if (recurso != null) {
                Media media = new Media(recurso.toExternalForm());
                musicPlayer = new MediaPlayer(media);
                musicPlayer.setCycleCount(MediaPlayer.INDEFINITE); 
                musicPlayer.setVolume(0.4);
                musicPlayer.play();
            }
        } catch (Exception e) {
            System.err.println("⚠️ Error audio: " + e.getMessage());
        }
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}