package com.pokemon.habilidades;

import com.pokemon.modelo.Pokemon;

public class BuffHabilidad extends Habilidad {

    public BuffHabilidad(String nombre, int poder, String tipo) {
        super(nombre, poder, tipo);
    }

    @Override
    public void usar(Pokemon atacante, Pokemon defensor) {
        String estadisticaAumentada = "";
        double factorMejora = 1.2; 

        if (this.nombre.equalsIgnoreCase("Danza Espada") || 
            this.nombre.equalsIgnoreCase("Carga")) {
            
            int nuevoAtk = (int) (atacante.getAtaque() * factorMejora);
            atacante.setAtaque(nuevoAtk);
            estadisticaAumentada = "ATAQUE";
        } 
        
        else if (this.nombre.equalsIgnoreCase("Aullido") || 
                 this.nombre.equalsIgnoreCase("Sintesis") || 
                 this.nombre.equalsIgnoreCase("Danza Dragon")) {
            
            int nuevaDef = (int) (atacante.getDefensa() * factorMejora);
            atacante.setDefensa(nuevaDef);
            estadisticaAumentada = "DEFENSA";
        }

        System.out.println("---------------------------------------");
        System.out.println(atacante.getNombre() + " usó " + this.nombre.toUpperCase());
        if (!estadisticaAumentada.isEmpty()) {
            System.out.println("¡El " + estadisticaAumentada + " de " + atacante.getNombre() + " subió un 20%!");
            int valorFinal = estadisticaAumentada.equals("ATAQUE") ? atacante.getAtaque() : atacante.getDefensa();
            System.out.println("Nuevo valor de " + estadisticaAumentada + ": " + valorFinal);
        }
        System.out.println("---------------------------------------");
    }
}