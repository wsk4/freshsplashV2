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

import com.freshsplash.cl.freshsplash.model.Ubicacion;
import com.freshsplash.cl.freshsplash.service.UbicacionService;

@RestController
@RequestMapping("/api/v1/ubicaciones")
public class UbicacionController {

    @Autowired
    private UbicacionService ubicacionService;

    @GetMapping
    public ResponseEntity<List<Ubicacion>> listar() {
        List<Ubicacion> ubicaciones = ubicacionService.findAll();
        if (ubicaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ubicaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ubicacion> buscar(@PathVariable Long id) {
        try {
            Ubicacion ubicacion = ubicacionService.findById(id);
            return ResponseEntity.ok(ubicacion);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Ubicacion> guardar(@RequestBody Ubicacion ubicacion) {
        Ubicacion ubicacionNueva = ubicacionService.save(ubicacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(ubicacionNueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ubicacion> actualizar(@PathVariable Long id, @RequestBody Ubicacion ubicacion) {
        try {
            ubicacionService.save(ubicacion);
            return ResponseEntity.ok(ubicacion);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Ubicacion> patchUbicacion(@PathVariable Long id, @RequestBody Ubicacion partialUbicacion) {
        try {
            Ubicacion updatedUbicacion = ubicacionService.patchUbicacion(id, partialUbicacion);
            return ResponseEntity.ok(updatedUbicacion);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            ubicacionService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
