
package com.alquileres.MrHouse.repositorios;

import com.alquileres.MrHouse.entidades.Turno;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepositorio extends JpaRepository<Turno,String>{
    
    List<Turno> findByUsuarioId(String usuarioId);
    
    List<Turno> findByInmuebleDuenioId(String duenioId);
    
    Optional<Turno> findByInmuebleIdAndFecha(String inmuebleId,Date fecha);
}
