package com.freshsplash.cl.freshsplash.repository;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freshsplash.cl.freshsplash.model.Bano;
import com.freshsplash.cl.freshsplash.model.Etiqueta;

@Repository
public interface BanoRepository extends JpaRepository<Bano, Long> {

    List<Bano> findByetiqueta(Etiqueta etiqueta);

    List<Bano> findByHorarioIn(List<LocalTime> horarioApertura);

    Bano findById(Integer id);

}
