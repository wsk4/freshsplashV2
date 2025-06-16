package com.freshsplash.cl.freshsplash.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.freshsplash.cl.freshsplash.model.Etiqueta;

@Repository
public interface EtiquetaRepository extends JpaRepository<Etiqueta, Long> {

    @Query("SELECT e FROM Etiqueta e WHERE e.accesoDiscapacitado = true")
    List<Etiqueta> findEtiquetasAccesoDiscapacitado();

    @Query("SELECT e FROM Etiqueta e WHERE e.gratuito = true")
    List<Etiqueta> findEtiquetasGratuitas();
}
