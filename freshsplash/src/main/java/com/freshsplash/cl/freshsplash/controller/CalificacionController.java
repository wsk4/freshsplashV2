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

import com.freshsplash.cl.freshsplash.model.Calificacion;
import com.freshsplash.cl.freshsplash.service.CalificacionService;

@RestController
@RequestMapping("/api/v1/calificaciones")
public class CalificacionController {

    @Autowired
    private CalificacionService calificacionService;

    @GetMapping
    public ResponseEntity<List<Calificacion>> listar() {
        List<Calificacion> calificaciones = calificacionService.findAll();
        if (calificaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(calificaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Calificacion> buscar(@PathVariable Long id) {
        try {
            Calificacion calificacion = calificacionService.findById(id);
            return ResponseEntity.ok(calificacion);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Calificacion> guardar(@RequestBody Calificacion calificacion) {
        Calificacion calificacionNueva = calificacionService.save(calificacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(calificacionNueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Calificacion> actualizar(@PathVariable Long id, @RequestBody Calificacion calificacion) {
        try {
            calificacionService.save(calificacion);
            return ResponseEntity.ok(calificacion);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Calificacion> patchCalificacion(@PathVariable Long id, @RequestBody Calificacion partialCalificacion) {
        try {
            Calificacion updatedCalificacion = calificacionService.patchCalificacion(id, partialCalificacion);
            return ResponseEntity.ok(updatedCalificacion);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            calificacionService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
