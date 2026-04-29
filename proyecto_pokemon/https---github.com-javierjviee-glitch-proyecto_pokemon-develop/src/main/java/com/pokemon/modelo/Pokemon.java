package com.pokemon.modelo;

import java.util.List;
import com.pokemon.habilidades.Habilidad;


public class Pokemon {

    private String nombre;
    private String tipo;
    private int hp;
    private int hpMax;
    private int nivel; 
    private int ataque;
    private int defensa;
    private int ataqueEspecial;
    private int defensaEspecial;
    private int velocidad;
    private String rutaImg;
    private List<Habilidad> habilidades;

    public Pokemon(String nombre, String tipo, int hpMax, int ataque, int defensa, 
                   int ataqueEspecial, int defensaEspecial, int velocidad, 
                   String rutaImg, List<Habilidad> habilidades) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.hpMax = hpMax;
        this.hp = hpMax;
        this.nivel = 50; 
        this.ataque = ataque;
        this.defensa = defensa;
        this.ataqueEspecial = ataqueEspecial;
        this.defensaEspecial = defensaEspecial;
        this.velocidad = velocidad;
        this.rutaImg = rutaImg;
        this.habilidades = habilidades;
    }

    public Pokemon() {}

    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public int getHp() { return hp; }
    public int getHpMax() { return hpMax; }
    public int getNivel() { return nivel; }
    public int getAtaque() { return ataque; }
    public int getDefensa() { return defensa; }
    public int getAtaqueEspecial() { return ataqueEspecial; }
    public int getDefensaEspecial() { return defensaEspecial; }
    public int getVelocidad() { return velocidad; }
    public String getRutaImagen() { return rutaImg; }
    public List<Habilidad> getHabilidades() { return habilidades; }

    public void setHp(int nuevoHp) {
        if (nuevoHp < 0) {
            this.hp = 0;
        } else if (nuevoHp > hpMax) {
            this.hp = hpMax;
        } else {
            this.hp = nuevoHp;
        }
    }

    public void setAtaque(int ataque) { this.ataque = ataque; }
    public void setDefensa(int defensa) { this.defensa = defensa; }
    public void setVelocidad(int velocidad) { this.velocidad = velocidad; }

    public boolean estaDebilitado() {
        return this.hp <= 0;
    }
}