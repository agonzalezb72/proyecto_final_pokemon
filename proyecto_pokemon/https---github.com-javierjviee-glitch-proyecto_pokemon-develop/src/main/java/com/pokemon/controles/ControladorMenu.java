package com.pokemon.controles;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import com.pokemon.modelo.Pokemon;
import com.pokemon.datos.DataLoader; // Tu clase para Gson
import java.util.List;

/**
 * @author Angel
 */
public class ControladorMenu {

    @FXML
    private ListView<String> listaPokemon; // Para mostrar los nombres

    @FXML
    private ImageView vistaPrevia;

    @FXML
    private Button botonIniciar;

    private List<Pokemon> pokemonDisponibles;
    private Pokemon pokemonSeleccionado;

    /**
     * Se ejecuta automáticamente al cargar el FXML.
     * Aquí es donde debes "alimentar" la vista con los datos del modelo [1].
     */
    @FXML
    public void initialize() {
        // EXIGENCIA: Cargar datos desde JSON externo usando Gson [3]
        this.pokemonDisponibles = DataLoader.cargarPokemons(); 
        
        if (pokemonDisponibles == null || pokemonDisponibles.isEmpty()) {
            System.err.println("Error crítico: No hay criaturas cargadas.");
            return;
        }

        // Poblar la lista en la interfaz
        for (Pokemon p : pokemonDisponibles) {
            listaPokemon.getItems().add(p.getNombre());
        }

        // Listener para la selección (UT3/UT5)
        listaPokemon.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            actualizarSeleccion(newVal);
        });
    }

    private void actualizarSeleccion(String nombre) {
        // Buscar el objeto Pokemon que coincide con el nombre seleccionado (UT6)
        this.pokemonSeleccionado = pokemonDisponibles.stream()
            .filter(p -> p.getNombre().equals(nombre))
            .findFirst()
            .orElse(null);
            
        // Mostrar imagen (Requisito: La vista debe mostrar información relevante) [4]
        // vistaPrevia.setImage(new Image(getClass().getResourceAsStream(pokemonSeleccionado.getRutaImagen())));
    }

    @FXML
    private void manejarIniciarCombate() {
        if (pokemonSeleccionado == null) {
            System.out.println("Debes seleccionar una criatura primero.");
            return;
        }
        
        // Lógica de navegación: De menú a combate [4, 5]
        System.out.println("Iniciando combate con: " + pokemonSeleccionado.getNombre());
        // Aquí llamarás a la clase App o un Router para cambiar la Scene
    }
}
