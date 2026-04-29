package com.pokemon.modelo;

import java.util.Random;

public class CalcularDanio {
    private static final Random rand = new Random();

    public static int calcular(int nivel, int atq, int poder, int def, double stab, double efec) {
        double var = 0.85 + (1.0 - 0.85) * rand.nextDouble();
        double n = (2.0 * nivel / 5.0) + 2.0;
        double base = ((n * poder * ((double) atq / def)) / 50.0) + 2.0;
        int total = (int) (base * stab * efec * var);
        return Math.max(1, total);
    }
}