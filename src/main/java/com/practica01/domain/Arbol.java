package com.practica01.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data // Anotación de Lombok para generar getters, setters, toString, equals, hashCode
@Entity // Indica que esta clase es una entidad JPA
@Table(name = "arbol") // Mapea esta entidad a la tabla "arbol" en la base de datos
public class Arbol implements Serializable {

    private static final long serialVersionUID = 1L; // Número de serie para la serialización

    @Id // Marca idArbol como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática de IDs (autoincremental)
    @Column(name = "id_arbol") // Mapea a la columna "id_arbol" en la BD
    private Long idArbol;
    
    @Column(name = "nombre_comun") // Mapeo a nombre_comun en la BD
    private String nombreComun; // Antes 'nombre', ahora 'nombreComun' para coincidir con la BD
    
    @Column(name = "tipo_flor") // Mapeo a tipo_flor en la BD
    private String tipoFlor;
    
    // *** CAMBIO: Integer para permitir NULLs desde la BD y mapeo de columna ***
    @Column(name = "dureza_madera")
    private Integer durezaMadera; 
    
    // *** CAMBIO: Double para permitir NULLs desde la BD y mapeo de columna (era tiempoVida) ***
    @Column(name = "altura_promedio")
    private Double alturaPromedio; 

    // *** NUEVO CAMPO: color_Hoja ***
    @Column(name = "color_Hoja")
    private String colorHoja;

    // Constructor vacío requerido por JPA
    public Arbol() {
    }

    // Constructor con todos los campos (excepto ID para creación)
    public Arbol(String nombreComun, String tipoFlor, Integer durezaMadera, Double alturaPromedio, String colorHoja) {
        this.nombreComun = nombreComun;
        this.tipoFlor = tipoFlor;
        this.durezaMadera = durezaMadera;
        this.alturaPromedio = alturaPromedio;
        this.colorHoja = colorHoja;
    }
}