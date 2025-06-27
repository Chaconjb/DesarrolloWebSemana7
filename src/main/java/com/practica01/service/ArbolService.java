package com.practica01.service;

import com.practica01.domain.Arbol;
import com.practica01.repository.ArbolRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArbolService {
    
    @Autowired 
    private ArbolRepository arbolRepository;
    
    @Transactional(readOnly = true)
    public List<Arbol> getArboles() {
        return arbolRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Arbol getArbol(Arbol arbol) { 
        return arbolRepository.findById(arbol.getIdArbol())
                .orElse(null);
    }
    
    @Transactional
    public void save(Arbol arbol) {
        arbolRepository.save(arbol);
    }

    @Transactional
    public boolean delete(Arbol arbol) {
        try {
            arbolRepository.delete(arbol);
            arbolRepository.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}