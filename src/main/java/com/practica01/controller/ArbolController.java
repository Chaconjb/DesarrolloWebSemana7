package com.practica01.controller;

import com.practica01.domain.Arbol;
import com.practica01.service.ArbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes; 
import java.util.List;

@Controller
@RequestMapping("/arboles")
public class ArbolController {

    private final ArbolService arbolService;

    @Autowired
    public ArbolController(ArbolService arbolService) {
        this.arbolService = arbolService;
    }

    @GetMapping({"/", ""})
    public String listarArboles(Model model) {
        List<Arbol> arboles = arbolService.getArboles();
        model.addAttribute("arboles", arboles);
        return "practica01/listado";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioArbol(Model model) {
        model.addAttribute("arbol", new Arbol());
        return "practica01/modifica";
    }

    @PostMapping("/guardar")
    public String guardarArbol(@ModelAttribute Arbol arbol,
                               RedirectAttributes redirectAttributes) { 
        arbolService.save(arbol); 
        redirectAttributes.addFlashAttribute("successMessage", "Árbol guardado exitosamente!");
        return "redirect:/arboles";
    }

    @GetMapping("/editar/{idArbol}")
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

    @GetMapping("/eliminar/{idArbol}")
    public String eliminarArbol(@PathVariable("idArbol") Long idArbol, RedirectAttributes redirectAttributes) {
        Arbol arbol = new Arbol();
        arbol.setIdArbol(idArbol); 
        boolean eliminado = arbolService.delete(arbol);
        if (eliminado) {
            redirectAttributes.addFlashAttribute("successMessage", "Árbol eliminado exitosamente!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "No se pudo eliminar el árbol.");
        }
        return "redirect:/arboles";
    }
}