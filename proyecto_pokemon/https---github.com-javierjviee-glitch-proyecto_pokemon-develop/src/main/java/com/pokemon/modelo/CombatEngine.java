package com.pokemon.modelo;

import com.pokemon.habilidades.Habilidad;
import com.pokemon.habilidades.HealHabilidad;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class CombatEngine {

    private Pokemon jugador;
    private Pokemon rival;
    private boolean turnoJugador;
    private List<String> logCombate;
    private Random random;

    public CombatEngine(Pokemon jugador, Pokemon rival) {
        this.jugador = jugador;
        this.rival = rival;
        this.logCombate = new ArrayList<>();
        this.random = new Random();
        determinarPrimerTurno();
    }

    private void determinarPrimerTurno() {
        if (jugador.getVelocidad() > rival.getVelocidad()) {
            turnoJugador = true;
            logCombate.add("¡" + jugador.getNombre() + " es más rápido!");
        } else if (rival.getVelocidad() > jugador.getVelocidad()) {
            turnoJugador = false;
            logCombate.add("¡" + rival.getNombre() + " es más rápido!");
        } else {
            turnoJugador = random.nextBoolean();
            logCombate.add("¡Empate en velocidad! Azar decidido.");
        }
    }

    public void turnoJugador(Habilidad habilidad) {
        if (turnoJugador && !combateTerminado()) {
            habilidad.ejecutar(jugador, rival);
            logCombate.add(jugador.getNombre() + " usó " + habilidad.getNombre());
            
            if (rival.estaDebilitado()) {
                logCombate.add("¡" + rival.getNombre() + " debilitado!");
            } else {
                turnoJugador = false;
            }
        }
    }

    public void turnoIA() {
        if (turnoJugador || combateTerminado()) return;

        List<Habilidad> habilidades = rival.getHabilidades();
        Habilidad elegida = null;
        Habilidad movimientoCura = null;

        for (Habilidad h : habilidades) {
            if (h instanceof HealHabilidad) {
                movimientoCura = h;
                break;
            }
        }

        double porcentajeVida = (double) rival.getHp() / rival.getHpMax();

        if (movimientoCura != null && porcentajeVida < 0.15) {
            elegida = movimientoCura;
        } 
        else if (movimientoCura != null && porcentajeVida < 0.35) {
            elegida = (random.nextInt(100) < 75) ? movimientoCura : habilidades.get(random.nextInt(habilidades.size()));
        } 
        else {
            elegida = habilidades.get(random.nextInt(habilidades.size()));
        }

        elegida.ejecutar(rival, jugador);
        logCombate.add("Rival usó " + elegida.getNombre());

        if (jugador.estaDebilitado()) {
            logCombate.add("¡" + jugador.getNombre() + " ha caído!");
        } else {
            turnoJugador = true;
        }
    }

    public boolean combateTerminado() {
        return jugador.estaDebilitado() || rival.estaDebilitado();
    }

    public List<String> getLogCombate() { return logCombate; }
    public boolean isTurnoJugador() { return turnoJugador; }
}
