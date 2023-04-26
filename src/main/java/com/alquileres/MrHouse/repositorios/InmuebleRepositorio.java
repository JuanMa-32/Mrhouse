
package com.alquileres.MrHouse.repositorios;

import com.alquileres.MrHouse.entidades.Inmueble;
import com.alquileres.MrHouse.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InmuebleRepositorio extends JpaRepository<Inmueble,String> {
    
    List<Inmueble> findByTipoDeViviendaAndCondicion(String tipoDeVivienda, String condicion);
    
    List<Inmueble> findDistinctByDuenio(Usuario duenio);
    
    
}
