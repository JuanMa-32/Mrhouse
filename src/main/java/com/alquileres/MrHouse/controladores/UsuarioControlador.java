
package com.alquileres.MrHouse.controladores;

import com.alquileres.MrHouse.entidades.Inmueble;
import com.alquileres.MrHouse.entidades.Turno;
import com.alquileres.MrHouse.entidades.Usuario;
import com.alquileres.MrHouse.enumeradores.Roles;
import com.alquileres.MrHouse.servicios.InmuebleServicio;
import com.alquileres.MrHouse.servicios.TurnoServicio;
import com.alquileres.MrHouse.servicios.UsuarioServicio;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {
    
    @Autowired 
    private UsuarioServicio usuarioServ;
    
    @Autowired 
    private InmuebleServicio inmuebleServ;
    
    @Autowired
    private TurnoServicio turnoServ;
    
     @GetMapping("/registrar")
    public String registrar(ModelMap model){

             model.put("usuario",  new Usuario());

        return "registrar_usuario.html";
    }
    
    @PostMapping("/registro")
    public String registro(@Valid Usuario usuario,BindingResult result,ModelMap model,@RequestParam String id) throws IOException{
        
       
      
        try {
            if (result.hasErrors()) {
                return "registrar_usuario.html";
            }
       
            if (id.isEmpty()) {
                
                usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
                usuario.setRol(Roles.CLIENT);
                usuarioServ.registrar(usuario);
                model.addAttribute("exito", "Registrado Exitosamente!");
                List<Inmueble> inmuebles = inmuebleServ.listar();
                model.put("inmuebles", inmuebles);
                return "index.html";
            } else {

                usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));

                usuarioServ.edit(usuario, id);
                model.addAttribute("exito", "Editaste tu perfil Exitosamente!");
                List<Inmueble> inmuebles = inmuebleServ.listar();
                model.put("inmuebles", inmuebles);
                return "inicio.html";
            }
        } catch (Exception e) {
           return "inde.html";
        }
        
    }
    
      @GetMapping("/perfil/{id}")
    public String perfil(@PathVariable String id,ModelMap model){
        
        try {
            Usuario us = usuarioServ.findByid(id);
            model.put("usuario", us);
            return "perfil.html";
        } catch (Exception e) {
            return "inicio.html";
        }
         
    }
   
    @GetMapping("/turnosClient")
    public String turnosClient(HttpSession session,ModelMap model){
        
       
         Usuario us = (Usuario) session.getAttribute("usuariosession");
         List<Turno> turnosUsuario = turnoServ.turnoUsuario(us.getId());
        
        model.put("turnosUsuario", turnosUsuario);
        
        return "turnos_client.html";
    }
    
    
       @GetMapping("/login")
    public String login(){
        return "login.html";
    }
    
  
    
    
    
    @GetMapping("/cambiarRol/{id}")
    public String cambiarRol(@PathVariable String id,ModelMap model){
        usuarioServ.cambiarRol(id);
        
        model.addAttribute("cambioRol", "Cambiaste tu rol vuelve a ingresar para disfrutar los beneficios!");
        return "redirect:/logout";
    }
}
