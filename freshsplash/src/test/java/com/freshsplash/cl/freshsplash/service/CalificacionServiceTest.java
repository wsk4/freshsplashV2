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

import com.freshsplash.cl.freshsplash.model.Calificacion;
import com.freshsplash.cl.freshsplash.repository.CalificacionRepository;

@SpringBootTest
public class CalificacionServiceTest {

    @Autowired
    private CalificacionService calificacionService;

    @MockBean
    private CalificacionRepository calificacionRepository;

    private Calificacion createCalificacion() {
        return new Calificacion(1, "esta bien limpio", 4);
    }

    @Test
    public void testFindAll() {
        when(calificacionRepository.findAll()).thenReturn(List.of(createCalificacion()));
        List<Calificacion> calificaciones = calificacionService.findAll();
        assertNotNull(calificaciones);
        assertEquals(1, calificaciones.size());
    }

    @Test
    public void testFindById() {
        when(calificacionRepository.findById(1L)).thenReturn(java.util.Optional.of(createCalificacion()));
        Calificacion calificacion = calificacionService.findById(1L);
        assertNotNull(calificacion);
        assertEquals("esta bien limpio", calificacion.getComentario());
    }

    @Test
    public void testSave() {
        Calificacion calificacion = createCalificacion();
        when(calificacionRepository.save(calificacion)).thenReturn(calificacion);
        Calificacion savedCalificacion = calificacionService.save(calificacion);
        assertNotNull(savedCalificacion);
        assertEquals("esta bien limpio", savedCalificacion.getComentario());
    }

    @Test
    public void testPatchCalificacion() {
        Calificacion existingCalificacion = createCalificacion();
        Calificacion patchData = new Calificacion();
        patchData.setComentario("buen servicio");

        when(calificacionRepository.findById(1L)).thenReturn(java.util.Optional.of(existingCalificacion));
        when(calificacionRepository.save(any(Calificacion.class))).thenReturn(existingCalificacion);

        Calificacion patchedCalificacion = calificacionService.patchCalificacion(1L, patchData);
        assertNotNull(patchedCalificacion);
        assertEquals("buen servicio", patchedCalificacion.getComentario());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(calificacionRepository).deleteById(1L);
        calificacionService.delete(1L);
        verify(calificacionRepository, times(1)).deleteById(1L);
    }

}
