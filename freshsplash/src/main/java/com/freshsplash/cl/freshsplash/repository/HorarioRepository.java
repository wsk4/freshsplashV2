package com.freshsplash.cl.freshsplash.repository;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.freshsplash.cl.freshsplash.model.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

    @Query("SELECT h FROM Horario h JOIN h.diasAbierto d WHERE d.diaSemana = :diaSemana")
    List<Horario> findByDiaSemana(String diaSemana);

    @Query("SELECT h FROM Horario h WHERE h.horaApertura IN :horaApertura")
    List<Horario> findByHoraAperturaIn(List<LocalTime> horaApertura);

    @Query("SELECT h FROM Horario h WHERE h.horaCierre IN :horaCierre")
    List<Horario> findByHoraCierreIn(List<LocalTime> horaCierre);

    @Query("SELECT h FROM Horario h WHERE h.id = :id")
    Horario findById(Integer id);
}
