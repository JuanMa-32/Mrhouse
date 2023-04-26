
package com.alquileres.MrHouse.servicios;

import com.alquileres.MrHouse.entidades.Turno;
import com.alquileres.MrHouse.entidades.Usuario;
import com.alquileres.MrHouse.enumeradores.Roles;
import com.alquileres.MrHouse.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UsuarioServicio implements UserDetailsService  {
    
    @Autowired
    private UsuarioRepositorio usuarioRepo;
    
    @Autowired
    private TurnoServicio turnoServ;
    
     private List<Turno> turnos  = new ArrayList();;
    
    //FORMA CON OBJETO
    public void registrar (Usuario us)throws Exception{
        usuarioRepo.save(us);
    }
    
    
    @Transactional
    public void edit(Usuario us,String id)throws Exception{
      
        Optional<Usuario> resp = usuarioRepo.findById(id);
        
        if(resp.isPresent()){
            Usuario usuario = resp.get();
            usuarioRepo.save(us);
        }
    }
    
    
    public Usuario findByid(String id)throws Exception{
       Optional<Usuario> resp= usuarioRepo.findById(id);
        
      
       if(resp.isPresent()){
           Usuario us = resp.get();
           return us;
       }
        return null;
    }
    
    public void guardarTurno (String id, String idturno){
           Optional<Usuario> resp = usuarioRepo.findById(id);
        
        if(resp.isPresent()){
            
            Usuario us = resp.get();
           Turno t = turnoServ.findByid(idturno);
            turnos.add(t);
            us.setTurno(turnos);
            usuarioRepo.save(us);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       
        Usuario us = usuarioRepo.findByEmail(email);
        
        if(us!=null){
            
            List<GrantedAuthority> permisos = new ArrayList();
            
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_"+us.getRol().toString());
            permisos.add(p);
            
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            
            HttpSession  session = attr.getRequest().getSession(true);
            
            session.setAttribute("usuariosession", us);
            
            return new User(us.getEmail(), us.getPassword(), permisos);
        }else{
            return null;
        }
    }
    
    public void cambiarRol(String id){
        Optional<Usuario> resp = usuarioRepo.findById(id);
        
        if(resp!=null){
            
            Usuario us = resp.get();
            
            if(us.getRol().equals(Roles.ENTE)){
                us.setRol(Roles.CLIENT);
            }else{
                us.setRol(Roles.ENTE);
            }
            usuarioRepo.save(us);
            
        }
    }
    
    public List<Usuario> lstarXRol(Roles rol){
        
        return usuarioRepo.findByRol(rol);
    }
}
