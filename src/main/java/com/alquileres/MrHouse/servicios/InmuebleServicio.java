
package com.alquileres.MrHouse.servicios;

import com.alquileres.MrHouse.entidades.Inmueble;
import com.alquileres.MrHouse.entidades.Turno;
import com.alquileres.MrHouse.entidades.Usuario;
import com.alquileres.MrHouse.repositorios.InmuebleRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InmuebleServicio {
    
    @Autowired
    private InmuebleRepositorio inmuebleRepo;
    
    @Autowired
    private TurnoServicio turnoServ;
    
    private List<Turno> turnos = new ArrayList();
    
    @Transactional
    public void crear(Inmueble inmueble)throws Exception{
        inmuebleRepo.save(inmueble);
    }
    
    @Transactional
    public void edit(Inmueble inmueble, String id){
        
        Optional<Inmueble> resp = inmuebleRepo.findById(id);
        
        if(resp!=null){
            
            Inmueble inm = resp.get();
            
            inmuebleRepo.save(inmueble);
            
        }
    }
    
    @Transactional
    public void delete(String id){
        
        Optional<Inmueble> resp = inmuebleRepo.findById(id);
        if(resp!=null){
            Inmueble inm = resp.get();
            inmuebleRepo.delete(inm);
        }
    }
     
    public void guardarTurno(String id,String idTurno){
        
         Optional<Inmueble> resp = inmuebleRepo.findById(id);
        if(resp!=null){
              
            Inmueble inm = resp.get();
            Turno t = turnoServ.findByid(idTurno);
            
            turnos.add(t);
            inm.setTurno(turnos);
            inmuebleRepo.save(inm);
        }
    }
    
    @Transactional
    public void DarDeBaja(String id){
        Optional<Inmueble> resp=inmuebleRepo.findById(id);
        
        if(resp.isPresent()){
            Inmueble inm = resp.get();
            
            inm.setAlta(Boolean.FALSE);
            inmuebleRepo.save(inm);
        }
    }
       @Transactional
    public void DarDeAlta(String id){
        Optional<Inmueble> resp=inmuebleRepo.findById(id);
        
        if(resp.isPresent()){
            Inmueble inm = resp.get();
            
            inm.setAlta(Boolean.TRUE);
            inmuebleRepo.save(inm);
        }
    }
   
    @Transactional
    public void eliminar(String id){
        
        Optional <Inmueble> resp = inmuebleRepo.findById(id);
        
        if(resp.isPresent()){
            Inmueble inm = resp.get();
            
            inmuebleRepo.delete(inm);
        }
    }
    
    public List<Inmueble> listar(){
        return inmuebleRepo.findAll();
    }
    
    public Inmueble findByid(String id){
        Optional<Inmueble> resp = inmuebleRepo.findById(id);
        if(resp.isPresent()){
            Inmueble inm = resp.get();
            return inm;
        }
        return null;
    }
    
    public List<Inmueble> buscarPorViviendaYCondicion(String tipoDeVivienda,String condicion){
        return inmuebleRepo.findByTipoDeViviendaAndCondicion(tipoDeVivienda, condicion);
    }
    
     public List<Inmueble> misPublicaciones(Usuario Duenio){
        return inmuebleRepo.findDistinctByDuenio(Duenio);
    }
}
