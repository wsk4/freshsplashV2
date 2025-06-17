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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/v1/etiquetas")
@Tag(name = "Api que administra las etiquetas de los baños")

public class EtiquetaController {

    @Autowired
    private EtiquetaService etiquetaService;

    @GetMapping
    @Operation(summary= "Esta api listara una etiqueta de los baño", description= "Esta api permitira listar las especificaciones concretas de los baños, ya sea si es aptos para discapacitados, es gratuito, si no es gratuito indicara el valor, donde esta ubicado(bencinera, baño publico, tienda, restaurant, etc...)")

    public ResponseEntity<List<Etiqueta>> listar() {
        List<Etiqueta> etiquetas = etiquetaService.obtenerEtiquetas();
        if (etiquetas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(etiquetas);
    }

    @GetMapping("/{id}")
    @Operation(summary= "Esta api buscara la etiqueta de un baño especifico", description= "Esta api permitira buscara las etiquetas de un baño en especifico")
    public ResponseEntity<Etiqueta> buscarEtiquetaPorId(@PathVariable Long id) {
        Etiqueta etiqueta = etiquetaService.obtenerEtiquetaPorId(id);
        if (etiqueta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(etiqueta);
    }

    @PostMapping
    @Operation(summary="Esta api guardara las etiquetas de los baños", description="Esta api permitira guardara las etiquetas de los baños ")
    public ResponseEntity<Etiqueta> guardar(@RequestBody Etiqueta etiqueta) {
        Etiqueta nuevaEtiqueta = etiquetaService.guardarEtiqueta(etiqueta);
        return ResponseEntity.status(201).body(nuevaEtiqueta);
    }

    @PutMapping("/{id}")
    @Operation(summary="Esta api actualizara la etiqueta de un baño", description="Esta api permitira actualizara la etiqueta de un baño especifico")
    public ResponseEntity<Etiqueta> actualizar(@PathVariable Long id, @RequestBody Etiqueta etiqueta) {
        Etiqueta etiquetaActualizada = etiquetaService.actualizarEtiqueta(id, etiqueta);
        if (etiquetaActualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(etiquetaActualizada);
    }

    @PatchMapping("/{id}")
    @Operation(summary="Esta api ediatara la etiqueta de un baño",description="Esta api permitira editara la etiqueta de un baño en especifco")
    public ResponseEntity<Etiqueta> editar(@PathVariable Long id, @RequestBody Etiqueta etiqueta) {
        Etiqueta etiquetaActualizada = etiquetaService.actualizarEtiqueta(id, etiqueta);
        if (etiquetaActualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(etiquetaActualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Esta api eliminara una etiqueta de un baño", description="Esta api permitira eliminar la etiqueta de un baño en especifico")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        etiquetaService.eliminarEtiqueta(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/discapacidad")
    @Operation(summary="Esta api mostrara informacion sobre los baños", description="Esta api mostrara informacion sobre los baños que son aptos para discapacitados")
    public ResponseEntity<List<Etiqueta>> obtenerAccesibles() {
    List<Etiqueta> etiquetas = etiquetaService.obtenerEtiquetasAccesoDiscapacitado();
    if (etiquetas.isEmpty()) {
        return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(etiquetas);
}

    @GetMapping("/gratuitas")
    @Operation(summary="Esta api mostrara informacion sobre los baños", description="Esta api mostrara informacion sobre los baños que son gratuitos")
    public ResponseEntity<List<Etiqueta>> obtenerGratuitas() {
    List<Etiqueta> etiquetas = etiquetaService.obtenerEtiquetasGratuitas();
    if (etiquetas.isEmpty()) {
        return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(etiquetas);
}
}