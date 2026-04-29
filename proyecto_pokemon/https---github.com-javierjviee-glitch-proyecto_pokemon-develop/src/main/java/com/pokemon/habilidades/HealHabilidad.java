package com.pokemon.habilidades;

import com.pokemon.modelo.Pokemon;

public class HealHabilidad extends Habilidad {

    public HealHabilidad(String nombre, int poder, String tipo) {
        super(nombre, poder, tipo);
    }

    @Override
    public void usar(Pokemon atacante, Pokemon defensor) {
        double porcentaje = this.getPoder() / 100.0;
        int cantidadCuracion = (int) (atacante.getHpMax() * porcentaje);
        
        if (cantidadCuracion <= 0) cantidadCuracion = 10; 

        int hpAntes = atacante.getHp();
        
        atacante.setHp(hpAntes + cantidadCuracion);
        
        int curacionReal = atacante.getHp() - hpAntes;

        System.out.println("---------------------------------------");
        System.out.println(atacante.getNombre() + " usó " + this.getNombre().toUpperCase());
        
        if (curacionReal > 0) {
            System.out.println("¡Recuperó " + curacionReal + " PS!");
        } else {
            System.out.println("¡Pero su salud ya estaba al máximo!");
        }
        System.out.println("Salud actual: " + atacante.getHp() + "/" + atacante.getHpMax());
        System.out.println("---------------------------------------");
    }
}