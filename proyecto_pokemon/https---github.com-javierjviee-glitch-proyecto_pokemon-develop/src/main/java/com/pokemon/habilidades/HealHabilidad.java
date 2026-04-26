package com.pokemon.habilidades;

import com.pokemon.modelo.Pokemon;

/**
 * @author Javier
 */
public class HealHabilidad extends Habilidad {

	public HealHabilidad(String nombre, int poder, String tipo) {
		super(nombre, poder, tipo);
	}

	@Override
	public void usar(Pokemon atacante, Pokemon defensor) {
		int hpActual = atacante.getHp();
		int nuevaHp = hpActual + this.poder;
		
		if (nuevaHp > atacante.getHpMax()) {
			nuevaHp = atacante.getHpMax();
		}

		atacante.setHp(nuevaHp);
		System.out.println(atacante.getNombre() + " recuperó vida con " + this.nombre);
	}
}