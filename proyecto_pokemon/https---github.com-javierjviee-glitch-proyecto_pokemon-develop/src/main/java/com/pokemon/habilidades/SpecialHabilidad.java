package com.pokemon.habilidades;

import com.pokemon.modelo.Pokemon;
import com.pokemon.modelo.CalcularDanio;

/**
 * @author Franz
 */
public class SpecialHabilidad extends Habilidad {

    public SpecialHabilidad(String nombre, int poder, String tipo) {
        super(nombre, poder, tipo);
    }

    @Override
    public void usar(Pokemon atacante, Pokemon defensor) {
        int danio = CalcularDanio.calcular(
                atacante.getNivel(),
                atacante.getAtaqueEspecial(),
                this.poder,
                defensor.getDefensaEspecial(),
                1.0, 1.0
        );

        defensor.setHp(defensor.getHp() - danio);
        System.out.println(atacante.getNombre() + " lanzó " + this.nombre + " causando " + danio + " de daño especial.");
    }
}