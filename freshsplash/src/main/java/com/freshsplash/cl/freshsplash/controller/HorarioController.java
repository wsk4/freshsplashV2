package com.freshsplash.cl.freshsplash.controller;

import java.time.LocalTime;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/horarios")
@Tag(name = "Api que administra los horarios de los baños")

public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    @GetMapping
    @Operation(summary = "Esta api listara los horarios de los baños", description = "Esta api nos listara los horarios de los baños")

    public ResponseEntity<List<Horario>> listar() {
        List<Horario> horarios = horarioService.findAll();
        if (horarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(horarios);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Esta api buscar los baños segun su horario", description = "Esta api nos permitira buscar un baño especifico segun el horario")

    public ResponseEntity<Horario> buscar(@PathVariable Long id) {
        try {
            Horario horario = horarioService.findById(id);
            return ResponseEntity.ok(horario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Esta api guardara los horarios de los baños", description = "Esta apí nos permitira guardar el horario de un baño")

    public ResponseEntity<Horario> guardar(@RequestBody Horario horario) {
        Horario horarioNuevo = horarioService.save(horario);
        return ResponseEntity.status(HttpStatus.CREATED).body(horarioNuevo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Esta api actualizara el horario de un baño", description = "Esta api nos permitira actualizar el horario de un baño especifico")

    public ResponseEntity<Horario> actualizar(@PathVariable Long id, @RequestBody Horario horario) {
        try {
            horarioService.save(horario);
            return ResponseEntity.ok(horario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Esta api modificara el horario de un baño", description = "Esta api permitira modificar el horario de un baño")

    public ResponseEntity<Horario> patchHorario(@PathVariable Long id, @RequestBody Horario partialHorario) {
        try {
            Horario updatedHorario = horarioService.patchHorario(id, partialHorario);
            return ResponseEntity.ok(updatedHorario);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Esta api eliminara el horario de un baño", description = "Esta api permitira eliminar el horario de un baño especifico")

    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            horarioService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/dia/{diaSemana}")
    @Operation(summary = "Esta api buscara por los dias de la semana", description = "Esta api permitira buscra un baño segun el dia de semana especifico")
    public ResponseEntity<List<Horario>> buscarPorDiaSemana(@PathVariable String diaSemana) {
        List<Horario> horarios = horarioService.findByDiaSemana(diaSemana);
        return horarios.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(horarios);
    }

    @GetMapping("/apertura")
    @Operation(summary = "Esta api buscara por el horario de apertura", description = "Esta api permitira buscar un baño segun su horario de apertura")

    public ResponseEntity<List<Horario>> buscarPorHorasApertura(@RequestBody List<LocalTime> horas) {
        List<Horario> horarios = horarioService.findByHoraApertura(horas);
        return horarios.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(horarios);
    }

    @GetMapping("/cierre")
    @Operation(summary = "Esta api buscara por el horario de cierre", description = "Esta api permitira buscar un baño segun su horairo de cierre")

    public ResponseEntity<List<Horario>> buscarPorHorasCierre(@RequestBody List<LocalTime> horas) {
        List<Horario> horarios = horarioService.findByHoraCierre(horas);
        return horarios.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(horarios);
    }
}
