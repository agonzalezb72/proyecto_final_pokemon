package com.pokemon.habilidades;

import com.pokemon.modelo.Pokemon;
import java.util.Random;

public class DmgHabilidad extends Habilidad {

    private static final Random random = new Random();

    public DmgHabilidad(String nombre, int poder, String tipo) {
        super(nombre, poder, tipo);
    }

    @Override
    public void usar(Pokemon atacante, Pokemon defensor) {
        boolean esEspecial = (this.tipo.equalsIgnoreCase("Fuego") || 
                              this.tipo.equalsIgnoreCase("Agua") || 
                              this.tipo.equalsIgnoreCase("Planta") || 
                              this.tipo.equalsIgnoreCase("Eléctrico") ||
                              this.tipo.equalsIgnoreCase("Hielo") ||
                              this.tipo.equalsIgnoreCase("Psíquico") ||
                              this.tipo.equalsIgnoreCase("Dragón"));

        int atkRelativo = esEspecial ? atacante.getAtaqueEspecial() : atacante.getAtaque();
        int defRelativa = esEspecial ? defensor.getDefensaEspecial() : defensor.getDefensa();

        double bonificacion = 1.0; 
        
        double efectividad = calcularEfectividad(this.tipo, defensor.getTipo());        
        double variacion = 0.85 + (1.0 - 0.85) * random.nextDouble(); 

        double nivelCalc = (2 * atacante.getNivel()) / 5.0;
        int danioBase = (int) ((((nivelCalc + 2) * this.poder * ((double) atkRelativo / defRelativa)) / 50) + 2);
        
        int danioFinal = (int) (danioBase * bonificacion * efectividad * variacion);

        defensor.setHp(defensor.getHp() - danioFinal);
        
        System.out.println("---------------------------------------");
        System.out.println(atacante.getNombre() + " usó " + this.nombre.toUpperCase());
        if (efectividad > 1.0) System.out.println("¡Es súper efectivo!");
        else if (efectividad < 1.0 && efectividad > 0) System.out.println("No es muy efectivo...");
        else if (efectividad == 0) System.out.println("No afecta a " + defensor.getNombre() + "...");
        
        System.out.println("Daño: " + danioFinal);
        System.out.println("---------------------------------------");
    }

    private double calcularEfectividad(String tAtk, String tDef) {
        String a = tAtk.toLowerCase();
        String d = tDef.toLowerCase();

        switch (a) {
            case "fuego":
                if (d.equals("planta") || d.equals("hielo") || d.equals("bicho")) return 2.0;
                if (d.equals("agua") || d.equals("roca") || d.equals("fuego")) return 0.5;
                break;
            case "agua":
                if (d.equals("fuego") || d.equals("tierra") || d.equals("roca")) return 2.0;
                if (d.equals("agua") || d.equals("planta") || d.equals("dragon")) return 0.5;
                break;
            case "planta":
                if (d.equals("agua") || d.equals("tierra") || d.equals("roca")) return 2.0;
                if (d.equals("fuego") || d.equals("planta") || d.equals("volador") || d.equals("bicho") || d.equals("veneno") || d.equals("dragon")) return 0.5;
                break;
            case "eléctrico":
                if (d.equals("agua") || d.equals("volador")) return 2.0;
                if (d.equals("planta") || d.equals("eléctrico") || d.equals("dragon")) return 0.5;
                if (d.equals("tierra")) return 0.0;
                break;
            case "hielo":
                if (d.equals("planta") || d.equals("tierra") || d.equals("volador") || d.equals("dragon")) return 2.0;
                if (d.equals("fuego") || d.equals("agua") || d.equals("hielo")) return 0.5;
                break;
            case "lucha":
                if (d.equals("normal") || d.equals("hielo") || d.equals("roca")) return 2.0;
                if (d.equals("veneno") || d.equals("volador") || d.equals("psiquico") || d.equals("bicho")) return 0.5;
                if (d.equals("fantasma")) return 0.0;
                break;
            case "tierra":
                if (d.equals("fuego") || d.equals("eléctrico") || d.equals("veneno") || d.equals("roca")) return 2.0;
                if (d.equals("planta") || d.equals("bicho")) return 0.5;
                if (d.equals("volador")) return 0.0;
                break;
        }
        return 1.0;
    }
}