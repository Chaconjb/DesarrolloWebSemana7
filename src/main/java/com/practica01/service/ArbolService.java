package com.practica01.service;

import com.practica01.domain.Arbol;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scripting.support.StandardScriptUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.practica01.repository.ArbolRepository;

@Service
public class ArbolService {
    
    @Autowired
    private ArbolRepository categoriaRepository;
    
    @Transactional(readOnly=true)
    public List<Arbol> getCategoria(boolean activos){
        var lista = categoriaRepository.findAll();
        
        if (activos) {
            lista.removeIf(c -> !c.isActivo());
        }
        return lista;
    }
    @Transactional(readOnly=true)
    public Arbol getCategoria(Arbol categoria){
        return categoriaRepository.findById(categoria.getIdCategoria())
                .orElse(null);
        
    }
    
    @Transactional
    public void save (Arbol categoria){
        categoriaRepository.save(categoria);
    }
    @Transactional
    public boolean delete(Arbol categoria){
        try {
            categoriaRepository.delete(categoria);
            categoriaRepository.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
  
    }
    
}
