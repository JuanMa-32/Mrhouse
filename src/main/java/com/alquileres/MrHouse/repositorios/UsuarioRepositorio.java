
package com.alquileres.MrHouse.repositorios;

import com.alquileres.MrHouse.entidades.Usuario;
import com.alquileres.MrHouse.enumeradores.Roles;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,String>{
    
    
     Usuario findByEmail(String email);
     
     List<Usuario> findByRol(Roles rol);
}
