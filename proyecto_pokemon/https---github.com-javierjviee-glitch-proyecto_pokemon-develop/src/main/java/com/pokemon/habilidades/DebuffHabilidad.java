package com.pokemon.habilidades;

import com.pokemon.modelo.Pokemon;

public class DebuffHabilidad extends Habilidad {

    public DebuffHabilidad(String nombre, int poder, String tipo) {
        super(nombre, poder, tipo);
    }

    @Override
    public void usar(Pokemon atacante, Pokemon defensor) {
        String estadisticaReducida = "";
        double factorReduccion = 0.8;

        if (this.nombre.equalsIgnoreCase("Onda Trueno")) {
            int nuevoAtk = (int) (defensor.getAtaque() * factorReduccion);
            defensor.setAtaque(Math.max(5, nuevoAtk));
            estadisticaReducida = "ATAQUE";
        } 
        
        else if (this.nombre.equalsIgnoreCase("Rugido") || 
                 this.nombre.equalsIgnoreCase("Moflete Estatico") || 
                 this.nombre.equalsIgnoreCase("Intimidar") || 
                 this.nombre.equalsIgnoreCase("Deslumbrar")) {
            
            int nuevaDef = (int) (defensor.getDefensa() * factorReduccion);
            defensor.setDefensa(Math.max(5, nuevaDef));
            estadisticaReducida = "DEFENSA";
        }

        System.out.println("---------------------------------------");
        System.out.println(atacante.getNombre() + " usó " + this.nombre.toUpperCase());
        if (!estadisticaReducida.isEmpty()) {
            System.out.println("¡El " + estadisticaReducida + " de " + defensor.getNombre() + " bajó un 20%!");
        }
        System.out.println("---------------------------------------");
    }
}