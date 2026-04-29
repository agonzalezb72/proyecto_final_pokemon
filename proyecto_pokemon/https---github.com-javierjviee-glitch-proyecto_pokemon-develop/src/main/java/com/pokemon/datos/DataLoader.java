package com.pokemon.datos;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.pokemon.modelo.Pokemon;
import com.pokemon.habilidades.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    private static final String FILE_PATH = "/com/pokemon/json/Pokemon.json";

    public static List<Pokemon> cargarPokemons() {
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(Habilidad.class, (JsonDeserializer<Habilidad>) (json, typeOfT, context) -> {
                JsonObject obj = json.getAsJsonObject();
                int poder = obj.get("poder").getAsInt();
                String nombre = obj.get("nombre").getAsString();
                String tipo = obj.get("tipo").getAsString();

                if (nombre.equalsIgnoreCase("Pocion Cura")) {
                    return new HealHabilidad(nombre, poder, tipo);
                } 

                else if (nombre.equalsIgnoreCase("Aullido") || 
                         nombre.equalsIgnoreCase("Danza Espada") || 
                         nombre.equalsIgnoreCase("Carga") || 
                         nombre.equalsIgnoreCase("Sintesis") || 
                         nombre.equalsIgnoreCase("Síntesis") || 
                         nombre.equalsIgnoreCase("Danza Dragon")) {
                    return new BuffHabilidad(nombre, poder, tipo);
                } 

                else if (nombre.equalsIgnoreCase("Rugido") || 
                         nombre.equalsIgnoreCase("Moflete Estatico") ||
                         nombre.equalsIgnoreCase("Intimidar") || 
                         nombre.equalsIgnoreCase("Deslumbrar") || 
                         nombre.equalsIgnoreCase("Onda Trueno")) {
                    return new DebuffHabilidad(nombre, poder, tipo);
                } 

                else if (poder > 0) {
                    return new DmgHabilidad(nombre, poder, tipo);
                } 

                else {
                    return new SpecialHabilidad(nombre, poder, tipo);
                }
            })
            .create();

        InputStream is = DataLoader.class.getResourceAsStream(FILE_PATH);

        if (is == null) {
            System.err.println("❌ ERROR CRÍTICO: No se encontró el archivo JSON en la ruta: " + FILE_PATH);
            return new ArrayList<>();
        }

        try (InputStreamReader reader = new InputStreamReader(is)) {
            Type listType = new TypeToken<ArrayList<Pokemon>>(){}.getType();
            List<Pokemon> lista = gson.fromJson(reader, listType);

            if (lista != null) {
                for (Pokemon p : lista) {
                    p.setHp(p.getHpMax());
                }
                System.out.println("✅ Datos cargados correctamente: " + lista.size() + " Pokémon.");
                return lista;
            }
        } catch (Exception e) {
            System.err.println("❌ Error grave al procesar el archivo JSON:");
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}