package com.freshsplash.cl.freshsplash.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freshsplash.cl.freshsplash.model.Calificacion;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {

    Calificacion findById(Integer id);

    List<Calificacion> findByPuntuacion(Integer puntuacion);

}
