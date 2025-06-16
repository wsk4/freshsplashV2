package com.freshsplash.cl.freshsplash.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freshsplash.cl.freshsplash.model.Calificacion;
import com.freshsplash.cl.freshsplash.repository.CalificacionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CalificacionService {

    @Autowired
    private CalificacionRepository calificacionRepository;

    public List<Calificacion> findAll() {
        return calificacionRepository.findAll();
    }

    public Calificacion findById(Long id) {
        return calificacionRepository.findById(id).orElse(null);
    }

    public Calificacion save(Calificacion calificacion) {
        return calificacionRepository.save(calificacion);
    }

    public void delete(Long id) {
        calificacionRepository.deleteById(id);
    }

    public Calificacion patchCalificacion(Long id, Calificacion parcialCalificacion) {
        Optional<Calificacion> calificacionOptional = calificacionRepository.findById(id);
        if (calificacionOptional.isPresent()) {

            Calificacion calificacionToUpdate = calificacionOptional.get();

            if (parcialCalificacion.getComentario() != null) {
                calificacionToUpdate.setComentario(parcialCalificacion.getComentario());
            }
            if (parcialCalificacion.getPuntuacion() != null) {
                calificacionToUpdate.setPuntuacion(parcialCalificacion.getPuntuacion());
            }

            return calificacionRepository.save(calificacionToUpdate);
        } else {
            return null;
        }
    }

}
