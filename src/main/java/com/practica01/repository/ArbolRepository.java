package com.practica01.repository;

import com.practica01.domain.Arbol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indica que esta interfaz es un componente de repositorio de Spring
public interface ArbolRepository extends JpaRepository<Arbol, Long> {
    // JpaRepository ya proporciona métodos CRUD básicos (save, findById, findAll, delete, etc.)
}