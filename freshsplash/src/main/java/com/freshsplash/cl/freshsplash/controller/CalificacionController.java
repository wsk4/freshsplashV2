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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/calificaciones")
@Tag(name = "Api que controla las comentarios y puntuaciones de los baños")
public class CalificacionController {

    @Autowired
    private CalificacionService calificacionService;

    @GetMapping
    @Operation(summary = "Esta api llama a todas las calificaciones de los baños", description = "Esta api llama a todos los comentarios y las puntuaciones que coloca la gente de los baños")
    public ResponseEntity<List<Calificacion>> listar() {
        List<Calificacion> calificaciones = calificacionService.findAll();
        if (calificaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(calificaciones);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Esta api llama a una calificacion especifica de un baño", description = "Esta api llama al comentario y calificacion especifica que se le ha colocado a un baño")
    public ResponseEntity<Calificacion> buscar(@PathVariable Long id) {
        try {
            Calificacion calificacion = calificacionService.findById(id);
            return ResponseEntity.ok(calificacion);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Esta api guarda una calificacion de los baño", description = "Esta api guarda un comentario nuevo y calificacion nueva que se le ha colocado a un baño")
    public ResponseEntity<Calificacion> guardar(@RequestBody Calificacion calificacion) {
        Calificacion calificacionNueva = calificacionService.save(calificacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(calificacionNueva);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Esta api actualizara una calificacion de un baño", description = "Esta api actualizara un comentario y calificacion ya creados que se le ha colocado a un baño")
    public ResponseEntity<Calificacion> actualizar(@PathVariable Long id, @RequestBody Calificacion calificacion) {
        try {
            calificacionService.save(calificacion);
            return ResponseEntity.ok(calificacion);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Esta api modificara una calificacion de un baño", description = "Esta api permitira modificar un comentario o calificacion ya creados que se le haya colocado a un baño")
    public ResponseEntity<Calificacion> patchCalificacion(@PathVariable Long id, @RequestBody Calificacion partialCalificacion) {
        try {
            Calificacion updatedCalificacion = calificacionService.patchCalificacion(id, partialCalificacion);
            return ResponseEntity.ok(updatedCalificacion);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Esta api borrara la calificacion de un baño", description = "Esta api borrara un comentario y calificacion de un baño")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            calificacionService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
