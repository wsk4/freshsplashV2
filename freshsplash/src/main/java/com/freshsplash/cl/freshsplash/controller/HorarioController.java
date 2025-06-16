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

import com.freshsplash.cl.freshsplash.model.Horario;
import com.freshsplash.cl.freshsplash.service.HorarioService;

@RestController
@RequestMapping("/api/v1/horarios")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    @GetMapping
    public ResponseEntity<List<Horario>> listar() {
        List<Horario> horarios = horarioService.findAll();
        if (horarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(horarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Horario> buscar(@PathVariable Long id) {
        try {
            Horario horario = horarioService.findById(id);
            return ResponseEntity.ok(horario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Horario> guardar(@RequestBody Horario horario) {
        Horario horarioNuevo = horarioService.save(horario);
        return ResponseEntity.status(HttpStatus.CREATED).body(horarioNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Horario> actualizar(@PathVariable Long id, @RequestBody Horario horario) {
        try {
            horarioService.save(horario);
            return ResponseEntity.ok(horario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Horario> patchHorario(@PathVariable Long id, @RequestBody Horario partialHorario) {
        try {
            Horario updatedHorario = horarioService.patchHorario(id, partialHorario);
            return ResponseEntity.ok(updatedHorario);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            horarioService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
