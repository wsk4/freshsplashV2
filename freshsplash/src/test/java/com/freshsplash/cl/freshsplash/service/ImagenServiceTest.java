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

import com.freshsplash.cl.freshsplash.model.Imagen;
import com.freshsplash.cl.freshsplash.repository.ImagenRepository;

@SpringBootTest
public class ImagenServiceTest {

    @Autowired
    private ImagenService imagenService;

    @MockBean
    private ImagenRepository imagenRepository;

    private Imagen createImagen() {
        return new Imagen(1, "https://ejemplo.com/imagen.jpg");
    }

    @Test
    public void testFindAll() {
        when(imagenRepository.findAll()).thenReturn(List.of(createImagen()));
        List<Imagen> imagenes = imagenService.findAll();
        assertNotNull(imagenes);
        assertEquals(1, imagenes.size());
    }

    @Test
    public void testFindById() {
        when(imagenRepository.findById(1L)).thenReturn(java.util.Optional.of(createImagen()));
        Imagen imagen = imagenService.findById(1L);
        assertNotNull(imagen);
        assertEquals("https://ejemplo.com/imagen.jpg", imagen.getRuta());
    }

    @Test
    public void testSave() {
        Imagen imagen = createImagen();
        when(imagenRepository.save(imagen)).thenReturn(imagen);
        Imagen savedImagen = imagenService.save(imagen);
        assertNotNull(savedImagen);
        assertEquals("https://ejemplo.com/imagen.jpg", savedImagen.getRuta());
    }

    @Test
    public void testPatchImagen() {
        Imagen existingImagen = createImagen();
        Imagen patchData = new Imagen();
        patchData.setRuta("https://ejemplo.com/cr7.jpg");

        when(imagenRepository.findById(1L)).thenReturn(java.util.Optional.of(existingImagen));
        when(imagenRepository.save(any(Imagen.class))).thenReturn(existingImagen);

        Imagen patchedImagen = imagenService.patchImagen(1L, patchData);
        assertNotNull(patchedImagen);
        assertEquals("Imagen Actualizada", patchedImagen.getRuta());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(imagenRepository).deleteById(1L);
        imagenService.delete(1L);
        verify(imagenRepository, times(1)).deleteById(1L);
    }

}
