package com.freshsplash.cl.freshsplash.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freshsplash.cl.freshsplash.model.Ubicacion;
import com.freshsplash.cl.freshsplash.repository.UbicacionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UbicacionService {

    @Autowired
    private UbicacionRepository ubicacionRepository;

    public List<Ubicacion> findAll() {
        return ubicacionRepository.findAll();
    }

    public Ubicacion findById(Long id) {
        return ubicacionRepository.findById(id).orElse(null);
    }

    public Ubicacion save(Ubicacion ubicacion) {
        return ubicacionRepository.save(ubicacion);
    }

    public void delete(Long id) {
        ubicacionRepository.deleteById(id);
    }

    public Ubicacion patchUbicacion(Long id, Ubicacion parcialUbicacion) {
        Optional<Ubicacion> ubicacionOptional = ubicacionRepository.findById(id);
        if (ubicacionOptional.isPresent()) {

            Ubicacion ubicacionToUpdate = ubicacionOptional.get();

            if (parcialUbicacion.getPais() != null) {
                ubicacionToUpdate.setPais(parcialUbicacion.getPais());
            }
            if (parcialUbicacion.getRegion() != null) {
                ubicacionToUpdate.setRegion(parcialUbicacion.getRegion());
            }
            if (parcialUbicacion.getCiudad() != null) {
                ubicacionToUpdate.setCiudad(parcialUbicacion.getCiudad());
            }
            if (parcialUbicacion.getComuna() != null) {
                ubicacionToUpdate.setComuna(parcialUbicacion.getComuna());
            }
            if (parcialUbicacion.getDireccion() != null) {
                ubicacionToUpdate.setDireccion(parcialUbicacion.getDireccion());
            }

            return ubicacionRepository.save(ubicacionToUpdate);
        } else {
            return null;
        }
    }

}
