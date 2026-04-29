package com.pokemon.controles;

import com.pokemon.modelo.Pokemon;
import com.pokemon.modelo.CombatEngine;
import com.pokemon.habilidades.Habilidad;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ControladorCombate {

    @FXML private Label lblNombreJugador, lblNombreRival, lblHpJugador, lblHpRival;
    @FXML private ProgressBar pbHpJugador, pbHpRival;
    @FXML private ImageView imgJugador, imgRival;
    @FXML private TextArea txtLog;
    @FXML private GridPane gridHabilidades;
    @FXML private Button btnVolver;

    private Pokemon pj;
    private Pokemon rival;

    public void inicializar(Pokemon jugador, Pokemon oponente) {
        this.pj = jugador;
        this.rival = oponente;
        
        cargarImagen(imgJugador, pj.getRutaImagen());
        cargarImagen(imgRival, rival.getRutaImagen());
        
        actualizarUI();
        vincularHabilidades();
        escribirLog("¡Un " + rival.getNombre() + " salvaje apareció!");
    }

    private void cargarImagen(ImageView iv, String ruta) {
        try {
            String path = ruta.startsWith("/") ? ruta : "/" + ruta;
            InputStream is = getClass().getResourceAsStream(path);
            if (is != null) {
                iv.setImage(new Image(is));
            } else {
                System.err.println("❌ No se encontró imagen: " + path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void vincularHabilidades() {
        gridHabilidades.getChildren().clear();
        List<Habilidad> lista = pj.getHabilidades();
        for (int i = 0; i < lista.size(); i++) {
            Habilidad hab = lista.get(i);
            Button btn = new Button(hab.getNombre().toUpperCase());
            btn.setPrefSize(170, 60);
            btn.setStyle("-fx-background-color: #34495e; -fx-text-fill: white; -fx-font-weight: bold;");
            btn.setOnAction(e -> procesarTurno(hab));
            gridHabilidades.add(btn, i % 2, i / 2);
        }
    }

    private void actualizarUI() {
        lblNombreJugador.setText(pj.getNombre());
        lblNombreRival.setText(rival.getNombre());
        lblHpJugador.setText(pj.getHp() + " / " + pj.getHpMax());
        lblHpRival.setText(rival.getHp() + " / " + rival.getHpMax());
        
        double proPj = (double) pj.getHp() / pj.getHpMax();
        double proRiv = (double) rival.getHp() / rival.getHpMax();
        
        pbHpJugador.setProgress(proPj);
        pbHpRival.setProgress(proRiv);
        
        actualizarColorBarra(pbHpJugador, proPj);
        actualizarColorBarra(pbHpRival, proRiv);
    }

    private void actualizarColorBarra(ProgressBar pb, double p) {
        if (p < 0.2) pb.setStyle("-fx-accent: #e74c3c;");
        else if (p < 0.5) pb.setStyle("-fx-accent: #f1c40f;");
        else pb.setStyle("-fx-accent: #2ecc71;");
    }

    private void escribirLog(String t) {
        txtLog.appendText("> " + t + "\n");
    }

    private void procesarTurno(Habilidad habPj) {
        gridHabilidades.setDisable(true);

        habPj.usar(pj, rival);
        escribirLog(pj.getNombre() + " usó " + habPj.getNombre());
        actualizarUI();

        if (rival.getHp() <= 0) {
            escribirLog("¡" + rival.getNombre() + " debilitado! VICTORIA.");
            terminarCombate();
            return;
        }

        PauseTransition pausa = new PauseTransition(Duration.seconds(1.2));
        pausa.setOnFinished(e -> {
            Habilidad habRiv = CombatEngine.elegirAtaqueIA(rival, pj);
            habRiv.usar(rival, pj);
            escribirLog(rival.getNombre() + " respondió con " + habRiv.getNombre());
            actualizarUI();

            if (pj.getHp() <= 0) {
                escribirLog("Has sido derrotado...");
                terminarCombate();
            } else {
                gridHabilidades.setDisable(false);
            }
        });
        pausa.play();
    }

    private void terminarCombate() {
        gridHabilidades.setDisable(true);
        btnVolver.setVisible(true);
        escribirLog("Pulsa 'Volver al Menú' para salir.");
    }

    @FXML 
    private void handleVolverMenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/pokemon/fxml/menu.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("❌ Error al volver al menú:");
            e.printStackTrace();
        }
    }
}