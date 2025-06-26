package com.practica01.service;

import com.practica01.domain.Arbol;
import com.practica01.repository.ArbolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional; 

@Service
public class ArbolService {

    private final ArbolRepository arbolRepository; // Usar final y constructor para inyección es una buena práctica

    @Autowired
    public ArbolService(ArbolRepository arbolRepository) {
        this.arbolRepository = arbolRepository;
    }

    @Transactional(readOnly = true)
    public List<Arbol> getArboles() { // Eliminado el parámetro 'activos'
        return arbolRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Arbol getArbol(Arbol arbol) {
        // Busca por el ID que está en el objeto arbol
        return arbolRepository.findById(arbol.getIdArbol())
                .orElse(null);
    }
    
    // Sobrecargamos para buscar directamente por ID
    @Transactional(readOnly = true)
    public Optional<Arbol> getArbolById(Long id) {
        return arbolRepository.findById(id);
    }

    @Transactional
    public void save(Arbol arbol) {
        arbolRepository.save(arbol);
    }

    @Transactional
    public boolean delete(Arbol arbol) {
        try {
            arbolRepository.delete(arbol);
            arbolRepository.flush(); // Fuerza la sincronización con la BD
            return true;
        } catch (Exception e) {
            System.err.println("Error al eliminar el árbol: " + e.getMessage()); // Considera usar un logger
            return false;
        }
    }
}