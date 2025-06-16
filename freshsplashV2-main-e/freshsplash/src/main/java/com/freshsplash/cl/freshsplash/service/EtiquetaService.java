package com.freshsplash.cl.freshsplash.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freshsplash.cl.freshsplash.model.Etiqueta;
import com.freshsplash.cl.freshsplash.repository.EtiquetaRepository;

@Service
public class EtiquetaService {

    @Autowired
    private EtiquetaRepository etiquetaRepository;

    private static final int cargo = 500;

    public List<Etiqueta> obtenerEtiquetas() {
        return etiquetaRepository.findAll();
    }

    public Etiqueta obtenerEtiquetaPorId(Long id) {
        return etiquetaRepository.findById(id).orElse(null);
    }

    public Etiqueta guardarEtiqueta(Etiqueta etiqueta) {
        if (!etiqueta.isGratuito() && (etiqueta.getPrecio() == null || etiqueta.getPrecio() <= 0)) {
            etiqueta.setPrecio(cargo);
        }
        return etiquetaRepository.save(etiqueta);
    }

    public void eliminarEtiqueta(Long id) {
        etiquetaRepository.deleteById(id);
    }

    public Etiqueta actualizarEtiqueta(Long id, Etiqueta parcialEtiqueta) {
        Optional<Etiqueta> etiquetaOptional = etiquetaRepository.findById(id);

        if (etiquetaOptional.isPresent()) {
            Etiqueta etiquetaToUpdate = etiquetaOptional.get();

            if (parcialEtiqueta.isAccesoDiscapacitado() != etiquetaToUpdate.isAccesoDiscapacitado()) {
                etiquetaToUpdate.setAccesoDiscapacitado(parcialEtiqueta.isAccesoDiscapacitado());
            }

            if (parcialEtiqueta.isGratuito() != etiquetaToUpdate.isGratuito()) {
                etiquetaToUpdate.setGratuito(parcialEtiqueta.isGratuito());
            }

            if (parcialEtiqueta.getPrecio() != null) {
                etiquetaToUpdate.setPrecio(parcialEtiqueta.getPrecio());
            }

            if (parcialEtiqueta.getTipoSitio() != null) {
                etiquetaToUpdate.setTipoSitio(parcialEtiqueta.getTipoSitio());
            }

            return etiquetaRepository.save(etiquetaToUpdate);
        } else {
            return null;
        }
    }

    public List<Etiqueta> obtenerEtiquetasAccesoDiscapacitado() {
        return etiquetaRepository.findEtiquetasAccesoDiscapacitado();
    }

    public List<Etiqueta> obtenerEtiquetasGratuitas() {
        return etiquetaRepository.findEtiquetasGratuitas();
    }
}
