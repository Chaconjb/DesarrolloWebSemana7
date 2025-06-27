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
    
    @Column(name = "nombre_comun")
    private String nombreComun; 
    
    @Column(name = "tipo_flor") 
    private String tipoFlor;
    
    @Column(name = "dureza_madera")
    private String durezaMadera; 
    
    @Column(name = "altura_promedio")
    private Double alturaPromedio; 

    @Column(name = "color_Hoja")
    private String colorHoja;


    public Arbol() {
    }

    public Arbol(String nombreComun, String tipoFlor, String durezaMadera, Double alturaPromedio, String colorHoja) {
        this.nombreComun = nombreComun;
        this.tipoFlor = tipoFlor;
        this.durezaMadera = durezaMadera;
        this.alturaPromedio = alturaPromedio;
        this.colorHoja = colorHoja;
    }
}