package com.practica01.controller;

import com.practica01.domain.Arbol;
import com.practica01.service.ArbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes; 
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        return "practica01/listado"; // <<<--- CORRECCIÓN AQUÍ: Apunta a templates/practica01/listado.html
    }
    
    // ... el resto de tu controlador (mostrarFormularioArbol, guardarArbol, editarArbol, eliminarArbol)
    // que ya deberían estar regresando "practica01/modifica"
    
    @GetMapping("/nuevo")
    public String mostrarFormularioArbol(Model model) {
        model.addAttribute("arbol", new Arbol());
        return "practica01/modifica"; 
    }

    @PostMapping("/guardar")
    public String guardarArbol(@ModelAttribute Arbol arbol,
                               @RequestParam("imagenFile") MultipartFile imagenFile,
                               RedirectAttributes redirectAttributes) { 

        arbolService.save(arbol);

        if (!imagenFile.isEmpty()) {
            try {
                String uploadDir = "src/main/resources/static/imagenes/";
                Path uploadPath = Paths.get(uploadDir);
                
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                String fileName = arbol.getIdArbol() + "_" + imagenFile.getOriginalFilename();
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(imagenFile.getInputStream(), filePath);

                arbol.setRutaImagen("/imagenes/" + fileName);

                arbolService.save(arbol);
                
                redirectAttributes.addFlashAttribute("successMessage", "Árbol y imagen guardados exitosamente!");

            } catch (IOException e) {
                System.err.println("Error al guardar la imagen: " + e.getMessage());
                redirectAttributes.addFlashAttribute("errorMessage", "Error al guardar la imagen: " + e.getMessage());
                if (arbol.getIdArbol() != null) {
                    return "redirect:/arboles/editar/" + arbol.getIdArbol();
                } else {
                    return "redirect:/arboles/nuevo";
                }
            }
        } else {
            redirectAttributes.addFlashAttribute("successMessage", "Árbol guardado exitosamente (sin cambios en la imagen).");
        }

        return "redirect:/arboles"; 
    }

    @GetMapping("/editar/{idArbol}")
    public String editarArbol(@PathVariable("idArbol") Long idArbol, Model model) {
        arbolService.getArbolById(idArbol).ifPresentOrElse(
            arbol -> model.addAttribute("arbol", arbol),
            () -> {
                System.out.println("Árbol con ID " + idArbol + " no encontrado para edición.");
            }
        );
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