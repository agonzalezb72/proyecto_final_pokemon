package com.pokemon.habilidades;

import com.pokemon.modelo.Pokemon;
import com.pokemon.modelo.CalcularDanio;

/**
 * @author Franz
 */
public class DmgHabilidad extends Habilidad {

    public DmgHabilidad(String nombre, int poder, String tipo) {
        super(nombre, poder, tipo);
    }

    @Override
    public void usar(Pokemon atacante, Pokemon defensor) {
        int danio = CalcularDanio.calcular(
                atacante.getNivel(),
                atacante.getAtaque(),
                this.poder,
                defensor.getDefensa(),
                1.0, 1.0
        );

        defensor.setHp(defensor.getHp() - danio);
        System.out.println(atacante.getNombre() + " usó " + this.nombre + " y causó " + danio + " de daño.");
    }
}