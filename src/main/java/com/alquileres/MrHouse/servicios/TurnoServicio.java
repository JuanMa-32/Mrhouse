
package com.alquileres.MrHouse.servicios;

import com.alquileres.MrHouse.entidades.Turno;
import com.alquileres.MrHouse.repositorios.TurnoRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurnoServicio {
    
    @Autowired
    private TurnoRepositorio turnoRepo;
    
    
    
    public void sacarTurno(Turno turno){
        turnoRepo.save(turno);
    }
  
    public Turno findByid(String id ){
        Optional<Turno> resp = turnoRepo.findById(id);
        
        if(resp!=null){
           
            Turno turno = resp.get();
            return turno;
        }
        return null;
    }
    
    public List<Turno> turnoUsuario (String usuarioId){
        return turnoRepo.findByUsuarioId(usuarioId);
    }
    
      public List<Turno> turnoInmueble (String duenioId){
        return turnoRepo.findByInmuebleDuenioId(duenioId);
    }
      
      public boolean disponibilidadFecha(String inmuebleId,Date fecha){
          
          Optional<Turno> resp = turnoRepo.findByInmuebleIdAndFecha(inmuebleId, fecha);
          
          if(resp.isEmpty()){
              return true;
          }
          return false;
      }
}
