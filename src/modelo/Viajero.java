/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Jeffer Cardenas <jecgdevp@gmail.com>
 */

public class Viajero {
    
    private int id;
    private String nombre;
    private String especie;
    private String genero;
    
    public Viajero(int id, String nombre, String especie, String genero){
        
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.genero = genero;
    }
    
    public Viajero(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    
    
}
