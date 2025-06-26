package com.practica01.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@Data
@Entity
@Table(name="arbol")
public class Arbol implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long idArbol;
    private String nombre;
    private String tipoFlor;
    private Integer durezaMadera;
    private BigDecimal tiempoVida;
    private String rutaImagen;

    // Constructor vacío requerido por JPA
    public Arbol() {
    }

    // Constructor para pruebas o inicialización (opcional)
    public Arbol(String nombre, String tipoFlor, Integer durezaMadera, BigDecimal tiempoVida, String rutaImagen) {
        this.nombre = nombre;
        this.tipoFlor = tipoFlor;
        this.durezaMadera = durezaMadera;
        this.tiempoVida = tiempoVida;
        this.rutaImagen = rutaImagen;
    }
}