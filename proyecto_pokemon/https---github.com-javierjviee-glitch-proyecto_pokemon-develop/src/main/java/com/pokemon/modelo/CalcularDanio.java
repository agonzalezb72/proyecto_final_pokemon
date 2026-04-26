package com.pokemon.modelo;

import java.util.Random;

/**
 * Clase encargada de procesar la lógica matemática de los enfrentamientos.
 * @author Franz
 */

public class CalcularDanio {
    /**

     * Calcula el daño final siguiendo la fórmula :
     * (0,01 * B * E * V) * ((((0,2*N + 1) * A * P) / (25*D)) + 2)
     * * @param nivel (N) Nivel del atacante.
     * @param ataque (A) Estadística de ataque.
     * @param poder (P) Potencia del movimiento.
     * @param defensa (D) Estadística de defensa del rival.
     * @param bonificacion (B) Bonus por mismo tipo (STAB).
     * @param efectividad (E) Multiplicador de tipos.
     * @return El daño total redondeado.
     */

    public static int calcular(int nivel, int ataque, int poder, int defensa, double bonificacion, double efectividad) {

        Random random = new Random();
        int variacionInt = random.nextInt(16) + 85;
        double variacion = variacionInt / 100.0;

        double divisionBase = ((0.2 * nivel + 1) * ataque * poder) / (25.0 * defensa);
        double parteDerecha = divisionBase + 2;
        double parteIzquierda = 0.01 * bonificacion * efectividad * variacionInt;

        double danioFinal = parteIzquierda * parteDerecha;

        return (int) Math.round(danioFinal);

    }
}