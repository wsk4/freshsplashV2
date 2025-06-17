package com.freshsplash.cl.freshsplash.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freshsplash.cl.freshsplash.model.Ubicacion;

@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion, Long> {

    List<Ubicacion> findByPais(String pais);

    List<Ubicacion> findByRegion(String region);

    List<Ubicacion> findByCiudad(String ciudad);

    List<Ubicacion> findByComuna(String comuna);

    List<Ubicacion> findByDireccion(String direccion);

    Ubicacion findById(Integer id);

}
