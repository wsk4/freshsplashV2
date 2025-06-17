package com.freshsplash.cl.freshsplash.service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freshsplash.cl.freshsplash.model.Horario;
import com.freshsplash.cl.freshsplash.repository.HorarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    public List<Horario> findAll() {
        return horarioRepository.findAll();
    }

    public Horario findById(Long id) {
        return horarioRepository.findById(id).orElse(null);
    }

    public Horario save(Horario horario) {
        return horarioRepository.save(horario);
    }

    public void delete(Long id) {
        horarioRepository.deleteById(id);
    }

    public Horario patchHorario(Long id, Horario parcialHorario) {
        Optional<Horario> horarioOptional = horarioRepository.findById(id);
        if (horarioOptional.isPresent()) {

            Horario horarioToUpdate = horarioOptional.get();

            if (parcialHorario.getHoraApertura() != null) {
                horarioToUpdate.setHoraApertura(parcialHorario.getHoraApertura());
            }

            if (parcialHorario.getHoraCierre() != null) {
                horarioToUpdate.setHoraCierre(parcialHorario.getHoraCierre());
            }
            if (parcialHorario.getDiasAbierto() != null) {
                horarioToUpdate.setDiasAbierto(parcialHorario.getDiasAbierto());
            }

            return horarioRepository.save(horarioToUpdate);
        } else {
            return null;
        }
    }

    public List<Horario> findByDiaSemana(String diaSemana) {
        return horarioRepository.findByDiaSemana(diaSemana);
    }

    public List<Horario> findByHoraApertura(List<LocalTime> horaApertura) {
        return horarioRepository.findByHoraAperturaIn(horaApertura);
    }

    public List<Horario> findByHoraCierre(List<LocalTime> horaCierre) {
        return horarioRepository.findByHoraCierreIn(horaCierre);
    }

}
