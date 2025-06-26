package com.practica01.controller;

import com.practica01.domain.Arbol;
import com.practica01.service.ArbolService;
import com.practica01.service.FirebaseStorageService;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categoria")
public class ArbolController {

    @Autowired
    private ArbolService categoriaService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = categoriaService.getCategoria(false);

        model.addAttribute("totalCategorias", lista.size());
        model.addAttribute("categorias", lista);

        return "/categoria/listado";
    }

    @Autowired
    private FirebaseStorageService FirebaseStorageService;
    @Autowired
    private MessageSource MessageSource;

    @PostMapping("/guardar")
    public String guardar(Arbol categoria,
            @RequestParam MultipartFile imagenFile) {

        if (!imagenFile.isEmpty()) {//! sirve para decir por ejemplo(si no)
            categoriaService.save(categoria);
            String rutaImagen = FirebaseStorageService.cargaImagen(imagenFile, "categoria", categoria.getIdCategoria());
            categoria.setRutaImagen(rutaImagen);

        }
        categoriaService.save(categoria);
        return "redirect:/categoria/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(Arbol categoria,
            RedirectAttributes redirectAttributes) {
        categoria = categoriaService.getCategoria(categoria);
        if (categoria == null) {
            redirectAttributes.addFlashAttribute("error", MessageSource.getMessage("categoria.error01", null, Locale.getDefault()));

        }
        else if (false){ 
            redirectAttributes.addFlashAttribute("error", MessageSource.getMessage("categoria.error02", null, Locale.getDefault()));
        
        }
        else if (categoriaService.delete(categoria)){ 
            redirectAttributes.addFlashAttribute("error", MessageSource.getMessage("todoOk", null, Locale.getDefault()));
        
        }
        else{ 
            redirectAttributes.addFlashAttribute("error", MessageSource.getMessage("categoria.error03", null, Locale.getDefault()));
        
        }
        categoriaService.delete(categoria);

        return "redirect:/categoria/listado";
    }

    @PostMapping("/modificar")
    public String modificarPost(@RequestParam("idCategoria") Long idCategoria, Model model) {
        Arbol categoria = new Arbol();
        categoria.setIdCategoria(idCategoria);

        categoria = categoriaService.getCategoria(categoria);
        model.addAttribute("categoria", categoria);

        return "/categoria/modifica";
    }

}
