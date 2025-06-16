package com.freshsplash.cl.freshsplash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.freshsplash.cl.freshsplash.model.Etiqueta;
import com.freshsplash.cl.freshsplash.service.EtiquetaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/etiquetas")
public class EtiquetaController {

    @Autowired
    private EtiquetaService etiquetaService;

    @GetMapping
    public ResponseEntity<List<Etiqueta>> listar() {
        List<Etiqueta> etiquetas = etiquetaService.obtenerEtiquetas();
        if (etiquetas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(etiquetas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Etiqueta> buscarEtiquetaPorId(@PathVariable Long id) {
        Etiqueta etiqueta = etiquetaService.obtenerEtiquetaPorId(id);
        if (etiqueta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(etiqueta);
    }

    @PostMapping
    public ResponseEntity<Etiqueta> guardar(@RequestBody Etiqueta etiqueta) {
        Etiqueta nuevaEtiqueta = etiquetaService.guardarEtiqueta(etiqueta);
        return ResponseEntity.status(201).body(nuevaEtiqueta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Etiqueta> actualizar(@PathVariable Long id, @RequestBody Etiqueta etiqueta) {
        Etiqueta etiquetaActualizada = etiquetaService.actualizarEtiqueta(id, etiqueta);
        if (etiquetaActualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(etiquetaActualizada);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Etiqueta> editar(@PathVariable Long id, @RequestBody Etiqueta etiqueta) {
        Etiqueta etiquetaActualizada = etiquetaService.actualizarEtiqueta(id, etiqueta);
        if (etiquetaActualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(etiquetaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        etiquetaService.eliminarEtiqueta(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/discapacidad")
    public ResponseEntity<List<Etiqueta>> obtenerAccesibles() {
    List<Etiqueta> etiquetas = etiquetaService.obtenerEtiquetasAccesoDiscapacitado();
    if (etiquetas.isEmpty()) {
        return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(etiquetas);
}

    @GetMapping("/gratuitas")
    public ResponseEntity<List<Etiqueta>> obtenerGratuitas() {
    List<Etiqueta> etiquetas = etiquetaService.obtenerEtiquetasGratuitas();
    if (etiquetas.isEmpty()) {
        return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(etiquetas);
}
}