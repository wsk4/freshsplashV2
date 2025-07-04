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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/ubicaciones")
@Tag(name="Api que administra las ubicaciones de los baños")
public class UbicacionController {

    @Autowired
    private UbicacionService ubicacionService;

    @GetMapping
    @Operation(summary="Esta api listara las ubicaiones de los baño", description="Esta api permitira listar las ubicaciones de los baño")

    public ResponseEntity<List<Ubicacion>> listar() {
        List<Ubicacion> ubicaciones = ubicacionService.findAll();
        if (ubicaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ubicaciones);
    }

    @GetMapping("/{id}")
    @Operation(summary="Esta api buscara la ubicacion de un baño", description="Esta api permitira buscar la ubicacion de un baño especifico")

    public ResponseEntity<Ubicacion> buscar(@PathVariable Long id) {
        try {
            Ubicacion ubicacion = ubicacionService.findById(id);
            return ResponseEntity.ok(ubicacion);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary="Esta api guardara la imagen de un baño", description="Esta api permitira gaurdar las ubicacion de los baño")

    public ResponseEntity<Ubicacion> guardar(@RequestBody Ubicacion ubicacion) {
        Ubicacion ubicacionNueva = ubicacionService.save(ubicacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(ubicacionNueva);
    }

    @PutMapping("/{id}")
    @Operation(summary="Esta api actualizara la ubicacion de un baño", description="Esta api permitira actualizar la ubicacion de un baño especifico")

    public ResponseEntity<Ubicacion> actualizar(@PathVariable Long id, @RequestBody Ubicacion ubicacion) {
        try {
            ubicacionService.save(ubicacion);
            return ResponseEntity.ok(ubicacion);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    @Operation(summary="Esta api modificara la ubicacion de un baño", description="Esta api permitira modificar la ubicacion de un baño especifico")

    public ResponseEntity<Ubicacion> patchUbicacion(@PathVariable Long id, @RequestBody Ubicacion partialUbicacion) {
        try {
            Ubicacion updatedUbicacion = ubicacionService.patchUbicacion(id, partialUbicacion);
            return ResponseEntity.ok(updatedUbicacion);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Esta api eliminara la ubicacion de un baño", description="Esta api permitira eliminar la ubicaicon de un baño especifico")

    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            ubicacionService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
