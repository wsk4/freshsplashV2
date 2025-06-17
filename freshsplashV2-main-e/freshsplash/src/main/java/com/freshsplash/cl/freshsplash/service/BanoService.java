package com.freshsplash.cl.freshsplash.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freshsplash.cl.freshsplash.model.Bano;
import com.freshsplash.cl.freshsplash.repository.BanoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BanoService {

    @Autowired
    private BanoRepository banoRepository;

    public List<Bano> findAll() {
        return banoRepository.findAll();
    }

    public Bano findById(Integer id) {
        return banoRepository.findById(id).orElse(null);
    }

    public Bano save(Bano bano) {
        return banoRepository.save(bano);
    }

    public void delete(Integer id) {
        banoRepository.deleteById(id);
    }

    public Bano patchBano(Integer id, Bano parcialBano) {
        Optional<Bano> banoOptional = banoRepository.findById(id);
        if (banoOptional.isPresent()) {

            Bano banoToUpdate = banoOptional.get();

            if (parcialBano.getEtiqueta() != null) {
                banoToUpdate.setEtiqueta(parcialBano.getEtiqueta());
            }

            if (parcialBano.getHorario() != null) {
                banoToUpdate.setHorario(parcialBano.getHorario());
            }
            if (parcialBano.getUbicacion() != null) {
                banoToUpdate.setUbicacion(parcialBano.getUbicacion());
            }
            if (parcialBano.getCalificacion() != null) {
                banoToUpdate.setCalificacion(parcialBano.getCalificacion());
            }
            if (parcialBano.getImagen() != null) {
                banoToUpdate.setImagen(parcialBano.getImagen());
            }

            return banoRepository.save(banoToUpdate);
        } else {
            return null;
        }
    }

}
