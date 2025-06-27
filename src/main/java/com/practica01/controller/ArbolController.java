package com.practica01.controller;

import com.practica01.domain.Arbol;
import com.practica01.service.ArbolService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArbolController {

    @Autowired
    private ArbolService arbolService;

    @GetMapping({"/arboles", "/"})
    public String listarArboles(Model model) {
        List<Arbol> arboles = arbolService.getArboles();
        model.addAttribute("arboles", arboles);
        model.addAttribute("tituloSeccionPrincipal", "Explora el Mundo de los Árboles");
        return "practica01/listado";
    }

    @GetMapping("/arboles/nuevo")
    public String mostrarFormularioArbol(Model model) {
        model.addAttribute("arbol", new Arbol());
        return "practica01/modifica";
    }

    @PostMapping("/arboles/guardar")
    public String guardarArbol(Arbol arbol) {
        arbolService.save(arbol);
        return "redirect:/arboles";
    }

    @GetMapping("/arboles/eliminar/{idArbol}")
    public String eliminarArbol(@PathVariable("idArbol") Long idArbol) {
        Arbol arbolAEliminar = new Arbol();
        arbolAEliminar.setIdArbol(idArbol);
        arbolService.delete(arbolAEliminar);
        return "redirect:/arboles";
    }

    @GetMapping("/arboles/editar/{idArbol}")
    public String editarArbol(@PathVariable("idArbol") Long idArbol, Model model) {
        Arbol arbolConsulta = new Arbol();
        arbolConsulta.setIdArbol(idArbol);
        Arbol arbolEncontrado = arbolService.getArbol(arbolConsulta); 
        
        if (arbolEncontrado != null) {
            model.addAttribute("arbol", arbolEncontrado);
        } else {
            System.out.println("Árbol con ID " + idArbol + " no encontrado para edición.");
        }
        return "practica01/modifica"; 
    }
}