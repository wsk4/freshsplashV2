package com.freshsplash.cl.freshsplash.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.freshsplash.cl.freshsplash.model.Ubicacion;

@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion, Long> {

    @Query("SELECT u FROM Ubicacion u WHERE u.pais = :pais")
    List<Ubicacion> findByPais(String pais);

    @Query("SELECT u FROM Ubicacion u WHERE u.region = :region")
    List<Ubicacion> findByRegion(String region);

    @Query("SELECT u FROM Ubicacion u WHERE u.ciudad = :ciudad")
    List<Ubicacion> findByCiudad(String ciudad);

    @Query("SELECT u FROM Ubicacion u WHERE u.comuna = :comuna")
    List<Ubicacion> findByComuna(String comuna);

    @Query("SELECT u FROM Ubicacion u WHERE u.direccion = :direccion")
    List<Ubicacion> findByDireccion(String direccion);

    @Query("SELECT u FROM Ubicacion u WHERE u.id = :id")
    Ubicacion findById(Integer id);
}

