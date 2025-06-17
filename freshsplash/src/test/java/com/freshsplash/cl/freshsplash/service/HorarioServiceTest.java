package com.freshsplash.cl.freshsplash.service;

import java.time.LocalTime;
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

import com.freshsplash.cl.freshsplash.model.DiasAbierto;
import com.freshsplash.cl.freshsplash.model.Horario;
import com.freshsplash.cl.freshsplash.repository.HorarioRepository;

@SpringBootTest
public class HorarioServiceTest {

    @Autowired
    private HorarioService horarioService;

    @MockBean
    private HorarioRepository horarioRepository;

    private Horario createHorario() {
        return new Horario(1, LocalTime.of(8, 0), LocalTime.of(20, 0), new DiasAbierto());
    }

    @Test
    public void testFindAll() {
        when(horarioRepository.findAll()).thenReturn(List.of(createHorario()));
        List<Horario> horarios = horarioService.findAll();
        assertNotNull(horarios);
        assertEquals(1, horarios.size());
    }

    @Test
    public void testFindById() {
        when(horarioRepository.findById(1L)).thenReturn(java.util.Optional.of(createHorario()));
        Horario horario = horarioService.findById(1L);
        assertNotNull(horario);
        assertEquals(LocalTime.of(8, 0), horario.getHoraApertura());
    }

    @Test
    public void testSave() {
        Horario horario = createHorario();
        when(horarioRepository.save(horario)).thenReturn(horario);
        Horario savedHorario = horarioService.save(horario);
        assertNotNull(savedHorario);
        assertEquals(LocalTime.of(8, 0), savedHorario.getHoraApertura());
    }

    @Test
    public void testPatchHorario() {
        Horario existingHorario = createHorario();
        Horario patchData = new Horario();
        patchData.setHoraApertura(LocalTime.of(8, 0));

        when(horarioRepository.findById(1L)).thenReturn(java.util.Optional.of(existingHorario));
        when(horarioRepository.save(any(Horario.class))).thenReturn(existingHorario);

        Horario patchedHorario = horarioService.patchHorario(1L, patchData);
        assertNotNull(patchedHorario);
        assertEquals(8, patchedHorario.getHoraApertura());

    }

    @Test
    public void testDeleteById() {
        doNothing().when(horarioRepository).deleteById(1L);
        horarioService.delete(1L);
        verify(horarioRepository, times(1)).deleteById(1L);
    }
}
