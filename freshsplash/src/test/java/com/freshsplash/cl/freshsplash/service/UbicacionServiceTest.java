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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.freshsplash.cl.freshsplash.model.Ubicacion;
import com.freshsplash.cl.freshsplash.repository.UbicacionRepository;

@SpringBootTest
public class UbicacionServiceTest {

    @Autowired
    private UbicacionService ubicacionService;

    @MockBean
    private UbicacionRepository ubicacionRepository;

    private Ubicacion createUbicacion() {
        return new Ubicacion(1, "chile", "metropolitana", "santiago", "maipu", "av.hola cabros 1234");
    }

    @Test
    public void testFindAll() {
        when(ubicacionRepository.findAll()).thenReturn(List.of(createUbicacion()));
        List<Ubicacion> ubicacion = ubicacionService.findAll();
        assertNotNull(ubicacion);
        assertEquals(1, ubicacion.size());
    }

    @Test
    public void testFindById() {
        when(ubicacionRepository.findById(1L)).thenReturn(java.util.Optional.of(createUbicacion()));
        Ubicacion ubicacion = ubicacionService.findById(1L);
        assertNotNull(ubicacion);
        assertEquals("av.hola cabros 1234", ubicacion.getDireccion());
    }

    @Test
    public void testSave() {
        Ubicacion ubicacion = createUbicacion();
        when(ubicacionRepository.save(ubicacion)).thenReturn(ubicacion);
        Ubicacion savedUbicacion = ubicacionService.save(ubicacion);
        assertNotNull(savedUbicacion);
        assertEquals("av.hola cabros 1234", savedUbicacion.getDireccion());
    }

    @Test
    public void testPatchUbicacion() {
        Ubicacion existingUbicacion = createUbicacion();
        Ubicacion patchData = new Ubicacion();
        patchData.setDireccion("av.springfield");

        when(ubicacionRepository.findById(1L)).thenReturn(java.util.Optional.of(existingUbicacion));
        when(ubicacionRepository.save(any(Ubicacion.class))).thenReturn(existingUbicacion);

        Ubicacion patchedUbicacion = ubicacionService.patchUbicacion(1L, patchData);
        assertNotNull(patchedUbicacion);
        assertEquals("av.springfield", patchedUbicacion.getDireccion());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(ubicacionRepository).deleteById(1L);
        ubicacionService.delete(1L);
        verify(ubicacionRepository, times(1)).deleteById(1L);
    }

}
