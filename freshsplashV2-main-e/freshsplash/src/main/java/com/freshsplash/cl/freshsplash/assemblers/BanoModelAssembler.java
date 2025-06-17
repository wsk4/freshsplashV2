package com.freshsplash.cl.freshsplash.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.freshsplash.cl.freshsplash.controller.BanoControllerV2;
import com.freshsplash.cl.freshsplash.model.Bano;



@Component
public class BanoModelAssembler implements RepresentationModelAssembler<Bano, EntityModel<Bano>> {

    @SuppressWarnings("null")
    @Override
    public EntityModel<Bano> toModel(Bano bano) {
        return EntityModel.of(bano,
            linkTo(methodOn(BanoControllerV2.class).getBanoById(bano.getId())).withSelfRel(),
            linkTo(methodOn(BanoControllerV2.class).getAllBanos()).withRel("todos"),
            linkTo(methodOn(BanoControllerV2.class).updateBano(bano.getId(), bano)).withRel("actualizar"),
            linkTo(methodOn(BanoControllerV2.class).deleteBano(bano.getId())).withRel("eliminar"),
            linkTo(methodOn(BanoControllerV2.class).patchBano(bano.getId(), bano)).withRel("actualizar-parcial")
        );
    }
}
