package com.pokemon.habilidades;

import com.pokemon.modelo.Pokemon;

/**
 * @author Franz
 */
public class DebuffHabilidad extends Habilidad {

    public DebuffHabilidad(String nombre, int poder, String tipo) {
        super(nombre, poder, tipo);
    }

    @Override
    public void usar(Pokemon atacante, Pokemon defensor) {
        // Reduce la defensa del rival en 10 puntos
        int nuevaDefensa = defensor.getDefensa() - 10;

        // Evitamos que la defensa sea menor a 1
        if (nuevaDefensa < 1) {
            nuevaDefensa = 1;
        }

        defensor.setDefensa(nuevaDefensa);

        System.out.println(this.nombre + " redujo la defensa de " + defensor.getNombre());
    }
}