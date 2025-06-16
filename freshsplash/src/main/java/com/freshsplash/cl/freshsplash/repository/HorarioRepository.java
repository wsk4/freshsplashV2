package com.freshsplash.cl.freshsplash.repository;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freshsplash.cl.freshsplash.model.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

    // Buscar por díasAbierto
    List<Horario> findByDiasAbierto_DiaSemana(String diaSemana);
    
    // Buscar por  hora de apertura
    List<Horario> findByHoraAperturaIn(List<LocalTime> horaApertura);

    // busca por hora de cierre
    List<Horario> findByHoraCierreIn(List<LocalTime> horaCierre);

    Horario findById(Integer id);
}
