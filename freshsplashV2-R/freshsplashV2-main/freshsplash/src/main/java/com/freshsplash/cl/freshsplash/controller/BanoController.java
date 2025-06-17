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

import com.freshsplash.cl.freshsplash.model.Bano;
import com.freshsplash.cl.freshsplash.service.BanoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/baños")
@Tag(name = "Api que administra la informacion de los baños")

public class BanoController {

    @Autowired
    private BanoService banoService;

    @GetMapping
    @Operation(summary="Esta api listara los baño", description="Esta permitira api listar todos los baños")

    public ResponseEntity<List<Bano>> listar() {
        List<Bano> banos = banoService.findAll();
        if (banos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(banos);
    }

    @GetMapping("/{id}")
    @Operation(summary="Esta api buscara un baño", description="Esta permitira api buscar un baño especifico")

    public ResponseEntity<Bano> buscar(@PathVariable Long id) {
        try {
            Bano bano = banoService.findById(id);
            return ResponseEntity.ok(bano);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary="Esta api guardara los baño", description="Esta permitira api guardar todos los baños")

    public ResponseEntity<Bano> guardar(@RequestBody Bano bano) {
        Bano banoNuevo = banoService.save(bano);
        return ResponseEntity.status(HttpStatus.CREATED).body(banoNuevo);
    }

    @PutMapping("/{id}")
    @Operation(summary="Esta api actualizara un baño", description="Esta permitira api actualizar un baño especifico")

    public ResponseEntity<Bano> actualizar(@PathVariable Long id, @RequestBody Bano bano) {
        try {
            banoService.save(bano);
            return ResponseEntity.ok(bano);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    @Operation(summary="Esta api modificara un baño", description="Esta permitira api modificar un baño especifico")

    public ResponseEntity<Bano> patchBano(@PathVariable Long id, @RequestBody Bano partialBano) {
        try {
            Bano updatedBano = banoService.patchBano(id, partialBano);
            return ResponseEntity.ok(updatedBano);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Esta api actualizara un baño", description="Esta permitira api actualizar un baño especifico")

    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            banoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
