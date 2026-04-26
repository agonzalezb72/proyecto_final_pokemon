package com.pokemon.habilidades;

import com.pokemon.modelo.Pokemon;

/**
 * @author Franz
 */
public class BuffHabilidad extends Habilidad {

    public BuffHabilidad(String nombre, int poder, String tipo) {
        super(nombre, poder, tipo);
    }

    @Override
    public void usar(Pokemon atacante, Pokemon defensor) {
        // Aumenta el ataque del usuario
        int nuevoPoder = atacante.getAtaque() + 10;
        atacante.setAtaque(nuevoPoder);

        // Registro en consola (recuerda que luego deberás pasarlo a la interfaz) [cite: 20]
        System.out.println(atacante.getNombre() + " aumentó su ataque con " + this.nombre);
    }
}