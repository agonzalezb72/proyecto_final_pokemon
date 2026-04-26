package com.pokemon.habilidades;

import com.pokemon.modelo.Pokemon;

/**
 * @author Javier
 */
public abstract class Habilidad {

    protected String nombre;
    protected int poder;
    protected String tipo;

    public Habilidad(String nombre, int poder, String tipo) {
        this.nombre = nombre;
        this.poder = poder;
        this.tipo = tipo;
    }

    public abstract void usar(Pokemon atacante, Pokemon defensor);

    public String getNombre() { return nombre; }
    public int getPoder() { return poder; }
    public String getTipo() { return tipo; }

    public void ejecutar(Pokemon jugador, Pokemon rival) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ejecutar'");
    }
}