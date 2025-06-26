package com.practica01.service;

import com.practica01.domain.Arbol;
import com.practica01.repository.ArbolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

@Service
public class ArbolService {

    private static final Logger logger = LoggerFactory.getLogger(ArbolService.class); 

    private final ArbolRepository arbolRepository;

    @Autowired
    public ArbolService(ArbolRepository arbolRepository) {
        this.arbolRepository = arbolRepository;
    }

    @Transactional(readOnly = true)
    public List<Arbol> getArboles() {
        return arbolRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Arbol getArbol(Arbol arbol) {
        return arbolRepository.findById(arbol.getIdArbol())
                .orElse(null);
    }
    
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
            logger.error("Error al eliminar el árbol con ID {}: {}", arbol.getIdArbol(), e.getMessage(), e); 
            return false;
        }
    }
}