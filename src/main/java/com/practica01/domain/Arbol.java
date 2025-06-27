package com.practica01.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data 
@Entity 
@Table(name = "arbol") 
public class Arbol implements Serializable {

    private static final long serialVersionUID = 1L; 

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id_arbol") 
    private Long idArbol;
    private String nombre;
    @Column(name = "tipo_flor")
    private String tipoFlor;
    
    // *** CAMBIO CRÍTICO AQUÍ: int a Integer ***
    @Column(name = "dureza_madera")
    private Integer durezaMadera; 
    
    // *** CAMBIO CRÍTICO AQUÍ: double a Double ***
    @Column(name = "tiempo_vida")
    private Double tiempoVida; 

    public Arbol() {
    }

    // Constructor con todos los campos (excepto ID para creación)
    // También ajustado a Integer y Double
    public Arbol(String nombre, String tipoFlor, Integer durezaMadera, Double tiempoVida) {
        this.nombre = nombre;
        this.tipoFlor = tipoFlor;
        this.durezaMadera = durezaMadera;
        this.tiempoVida = tiempoVida;
    }
}