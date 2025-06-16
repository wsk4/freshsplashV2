package com.freshsplash.cl.freshsplash.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freshsplash.cl.freshsplash.model.Imagen;
import com.freshsplash.cl.freshsplash.service.ImagenService;

@RestController
@RequestMapping("/api/v1/imagenes")
public class ImagenController {

    @Autowired
    private ImagenService imagenService;

    public ResponseEntity<List<Imagen>> listar() {
        List<Imagen> imagenes = imagenService.findAll();
        if (imagenes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(imagenes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Imagen> buscar(@PathVariable Long id) {
        try {
            Imagen imagen = imagenService.findById(id);
            return ResponseEntity.ok(imagen);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Imagen> guardar(@RequestBody Imagen imagen) {
        Imagen imagenNueva = imagenService.save(imagen);
        return ResponseEntity.status(HttpStatus.CREATED).body(imagenNueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Imagen> actualizar(@PathVariable Long id, @RequestBody Imagen imagen) {
        try {
            imagenService.save(imagen);
            return ResponseEntity.ok(imagen);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Imagen> patchImagen(@PathVariable Long id, @RequestBody Imagen partialImagen) {
        try {
            Imagen updatedImagen = imagenService.patchImagen(id, partialImagen);
            return ResponseEntity.ok(updatedImagen);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            imagenService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
