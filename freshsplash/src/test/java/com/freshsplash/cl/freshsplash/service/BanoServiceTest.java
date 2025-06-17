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

import com.freshsplash.cl.freshsplash.model.Bano;
import com.freshsplash.cl.freshsplash.model.Calificacion;
import com.freshsplash.cl.freshsplash.model.Etiqueta;
import com.freshsplash.cl.freshsplash.model.Horario;
import com.freshsplash.cl.freshsplash.model.Imagen;
import com.freshsplash.cl.freshsplash.model.Ubicacion;
import com.freshsplash.cl.freshsplash.repository.BanoRepository;

public class BanoServiceTest {

    @Autowired
    private BanoService banoService;

    @MockBean
    private BanoRepository banoRepository;

    private Bano createBano() {
        return new Bano(1, 1, new Etiqueta(), new Horario(), new Ubicacion(), new Calificacion(), new Imagen());
    }

    @Test
    public void testFindAll() {
        when(banoRepository.findAll()).thenReturn(List.of(createBano()));
        List<Bano> banos = banoService.findAll();
        assertNotNull(banos);
        assertEquals(1, banos.size());
    }

    @Test
    public void testFindById() {
        when(banoRepository.findById(1L)).thenReturn(java.util.Optional.of(createBano()));
        Bano bano = banoService.findById(1L);
        assertNotNull(bano);
        assertEquals(1, bano.getId());
    }

    @Test
    public void testSave() {
        Bano bano = createBano();
        when(banoRepository.save(bano)).thenReturn(bano);
        Bano savedBano = banoService.save(bano);
        assertNotNull(savedBano);
        assertEquals(1, savedBano.getId());
    }

    @Test
    public void testPatchBano() {
        Bano existingBano = createBano();
        Bano patchData = new Bano();
        patchData.setCapacidad(3);

        when(banoRepository.findById(1L)).thenReturn(java.util.Optional.of(existingBano));
        when(banoRepository.save(any(Bano.class))).thenReturn(existingBano);

        Bano patchedBano = banoService.patchBano(1L, patchData);
        assertNotNull(patchedBano);
        assertEquals(3, patchedBano.getCapacidad());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(banoRepository).deleteById(1L);
        banoService.delete(1L);
        verify(banoRepository, times(1)).deleteById(1L);
    }

}
