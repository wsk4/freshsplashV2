package com.freshsplash.cl.freshsplash.service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.freshsplash.cl.freshsplash.model.Etiqueta;
import com.freshsplash.cl.freshsplash.model.TipoSitio;
import com.freshsplash.cl.freshsplash.repository.EtiquetaRepository;

public class EtiquetaServiceTest {

    @Autowired
    private EtiquetaService etiquetaService;

    @MockBean
    private EtiquetaRepository etiquetaRepository;

    private Etiqueta createEtiqueta() {
        return new Etiqueta(1, false, true, 0, new TipoSitio());
    }

    @Test
    public void testFindAll() {
        when(etiquetaRepository.findAll()).thenReturn(List.of(createEtiqueta()));
        List<Etiqueta> etiquetas = etiquetaService.obtenerEtiquetas();
        assertNotNull(etiquetas);
        assertEquals(1, etiquetas.size());
    }

    @Test
    public void testFindById() {
        when(etiquetaRepository.findById(1L)).thenReturn(java.util.Optional.of(createEtiqueta()));
        Etiqueta etiqueta = etiquetaService.obtenerEtiquetaPorId(1L);
        assertNotNull(etiqueta);
        assertEquals(0, etiqueta.getPrecio());
    }

    @Test
    public void testSave() {
        Etiqueta etiqueta = createEtiqueta();
        when(etiquetaRepository.save(etiqueta)).thenReturn(etiqueta);
        Etiqueta savedEtiqueta = etiquetaService.guardarEtiqueta(etiqueta);
        assertNotNull(savedEtiqueta);
        assertEquals(0, savedEtiqueta.getPrecio());
    }

    @Test
    public void testPatchEtiqueta() {
        Etiqueta existingEtiqueta = createEtiqueta();
        Etiqueta patchData = new Etiqueta();
        patchData.setPrecio(100);

        when(etiquetaRepository.findById(1L)).thenReturn(java.util.Optional.of(existingEtiqueta));
        when(etiquetaRepository.save(any(Etiqueta.class))).thenReturn(existingEtiqueta);

        Etiqueta patchedEtiqueta = etiquetaService.actualizarEtiqueta(1L, patchData);
        assertNotNull(patchedEtiqueta);
        assertEquals(100, patchedEtiqueta.getPrecio());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(etiquetaRepository).deleteById(1L);
        etiquetaService.eliminarEtiqueta(1L);
        verify(etiquetaRepository, times(1)).deleteById(1L);
    }

}
