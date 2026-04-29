package com.pokemon.modelo;

import com.pokemon.habilidades.*;
import java.util.Random;
import java.util.List;

public class CombatEngine {
    private static final Random rand = new Random();

    public static Habilidad elegirAtaqueIA(Pokemon rival, Pokemon pj) {
        List<Habilidad> habilidades = rival.getHabilidades();
        Habilidad mejorCura = null;
        Habilidad golpeMortal = null;
        Habilidad mejorDebuff = null;

        for (Habilidad h : habilidades) {
            if (h instanceof HealHabilidad) {
                mejorCura = h;
            }
            
            if (h instanceof DmgHabilidad) {
                int danioPresunto = CalcularDanio.calcular(rival.getNivel(), rival.getAtaque(), h.getPoder(), pj.getDefensa(), 1.0, 1.0);
                if (danioPresunto >= pj.getHp()) {
                    golpeMortal = h;
                }
            }

            if (h instanceof DebuffHabilidad) {
                mejorDebuff = h;
            }
        }


        if (golpeMortal != null) return golpeMortal;

        double ratioVida = (double) rival.getHp() / rival.getHpMax();
        if (mejorCura != null) {
            if (ratioVida < 0.20) return mejorCura;
            if (ratioVida < 0.40 && rand.nextDouble() < 0.60) return mejorCura;
        }

        if (mejorDebuff != null && pj.getHp() > (pj.getHpMax() * 0.8) && rand.nextDouble() < 0.40) {
            return mejorDebuff;
        }

        return habilidades.get(rand.nextInt(habilidades.size()));
    }
}