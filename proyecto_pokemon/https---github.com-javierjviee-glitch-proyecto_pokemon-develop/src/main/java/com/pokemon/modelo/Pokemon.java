package com.pokemon.modelo;

import java.util.List;

import com.pokemon.habilidades.Habilidad;

/**
 * @author Angel
 */


public class Pokemon {

    private String nombre;
    private int hp;
    private int hpMax;
    private int ataque;
    private int defensa;
    private int ataqueEspecial;
    private int defensaEspecial;
    private int velocidad;
    private String rutaImg;
    private List<Habilidad> habilidades;



    public Pokemon(String nombre, int puntosVidaMax, int ataque, int defensa, int ataqueEspecial, int defensaEspecial, int velocidad, String rutaImagen, List<Habilidad> habilidades) {

    this.nombre = nombre;
    this.hpMax = puntosVidaMax;
    this.hp = puntosVidaMax;
    this.ataque = ataque;
    this.defensa = defensa;
    this.ataqueEspecial = ataqueEspecial;
    this.defensaEspecial = defensaEspecial;
    this.velocidad = velocidad;
    this.rutaImg = rutaImagen;
    this.habilidades = habilidades;

    }

    public String getNombre() {return nombre;}

    public int getHp() { return hp; }

    public void setHp(int hp) {

        if (hp < 0) {

            this.hp = 0;

        } else if (hp > hpMax) {

            this.hp = hpMax;

        } else {

            this.hp = hp;
            
        }
    }

    public List<Habilidad> getHabilidades() { return habilidades; }
    
    public String getRutaImagen() { return rutaImg; }

    public int getAtaque() { return ataque; }

    public int getDefensa() { return defensa; }

    public int getAtaqueEspecial() { return ataqueEspecial; }

    public int getDefensaEspecial() { return defensaEspecial; }

    public int getVelocidad() { return velocidad; }

    public boolean estaDebilitado() {

        return this.hp <= 0;

    }

    public void setAtaque(int nuevoPoder) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAtaque'");
    }

    public void setDefensa(int max) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDefensa'");
    }

    public void setVida(int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setVida'");
    }

    public int getVida() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getVida'");
    }

    public int getNivel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNivel'");
    }

    public int getHpMax() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHpMax'");
    }
}