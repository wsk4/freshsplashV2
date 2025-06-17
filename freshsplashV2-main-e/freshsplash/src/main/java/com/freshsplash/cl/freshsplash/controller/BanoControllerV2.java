package com.freshsplash.cl.freshsplash.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.freshsplash.cl.freshsplash.assemblers.BanoModelAssembler;
import com.freshsplash.cl.freshsplash.model.Bano;
import com.freshsplash.cl.freshsplash.service.BanoService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/banos")
public class BanoControllerV2 {

    @Autowired
    private BanoService banoService;

    @Autowired
    private BanoModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<Bano>>> getAllBanos() {
        List<EntityModel<Bano>> banos = banoService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (banos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(
                CollectionModel.of(banos, linkTo(methodOn(BanoControllerV2.class).getAllBanos()).withSelfRel())
        );
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Bano>> getBanoById(@PathVariable Integer id) {
        Bano bano = banoService.findById(id);
        if (bano == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(bano));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Bano>> createBano(@RequestBody Bano bano) {
        Bano newBano = banoService.save(bano);
        return ResponseEntity
                .created(linkTo(methodOn(BanoControllerV2.class).getBanoById(newBano.getId())).toUri())
                .body(assembler.toModel(newBano));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Bano>> updateBano(@PathVariable Integer id, @RequestBody Bano bano) {
        bano.setId(id);
        Bano updatedBano = banoService.save(bano);
        return ResponseEntity.ok(assembler.toModel(updatedBano));
    }

    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Bano>> patchBano(@PathVariable Integer id, @RequestBody Bano bano) {
        Bano updatedBano = banoService.patchBano(id, bano);
        if (updatedBano == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(updatedBano));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> deleteBano(@PathVariable Integer id) {
        Bano bano = banoService.findById(id);
        if (bano == null) {
            return ResponseEntity.notFound().build();
        }
        banoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
