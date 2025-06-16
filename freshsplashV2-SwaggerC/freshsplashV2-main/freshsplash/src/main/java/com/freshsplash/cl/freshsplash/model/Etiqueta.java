package com.freshsplash.cl.freshsplash.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "etiqueta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Etiqueta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private boolean accesoDiscapacitado;

    @Column(nullable = false)
    private boolean gratuito;

    @Column(nullable = false)
    private Integer precio;

    @ManyToOne
    @JoinColumn(name = "tipoSitio_id")
    private TipoSitio tipoSitio;

}
