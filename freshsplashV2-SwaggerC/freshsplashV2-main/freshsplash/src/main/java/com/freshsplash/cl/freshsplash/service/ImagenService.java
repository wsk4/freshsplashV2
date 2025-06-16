package com.freshsplash.cl.freshsplash.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freshsplash.cl.freshsplash.model.Imagen;
import com.freshsplash.cl.freshsplash.repository.ImagenRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ImagenService {

    @Autowired
    private ImagenRepository imagenRepository;

    public List<Imagen> findAll() {
        return imagenRepository.findAll();
    }

    public Imagen findById(Long id) {
        return imagenRepository.findById(id).orElse(null);
    }

    public Imagen save(Imagen imagen) {
        return imagenRepository.save(imagen);
    }

    public void delete(long id) {
        imagenRepository.deleteById(id);
    }

    public Imagen patchImagen(Long id, Imagen parcialImagen) {
        Optional<Imagen> imagenOptional = imagenRepository.findById(id);
        if (imagenOptional.isPresent()) {

            Imagen imagenToUpdate = imagenOptional.get();

            if (parcialImagen.getRuta() != null) {
                imagenToUpdate.setRuta(parcialImagen.getRuta());
            }

            return imagenRepository.save(imagenToUpdate);
        } else {
            return null;
        }
    }

}
