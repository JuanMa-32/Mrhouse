
package com.alquileres.MrHouse.controladores;

import com.alquileres.MrHouse.entidades.Inmueble;
import com.alquileres.MrHouse.entidades.Usuario;
import com.alquileres.MrHouse.enumeradores.Roles;
import com.alquileres.MrHouse.servicios.InmuebleServicio;
import com.alquileres.MrHouse.servicios.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminControlador {
    
    @Autowired
    private InmuebleServicio inmuebleServ;
    
     @Autowired
    private UsuarioServicio usuarioServ;
    
    @GetMapping("/dashboard")
    public String dashbord(ModelMap model){
         List<Inmueble> inmuebles = inmuebleServ.listar();
        model.put("inmuebles", inmuebles);
        return "panel.html";
    }
    
    @GetMapping("/listarPublicaciones")
    public String listarPublicaciones(ModelMap model){
        List<Inmueble> publicaciones = inmuebleServ.listar();
        model.put("publicaciones", publicaciones);
        
        return "listar_publicaciones.html";
    }
    
      @GetMapping("/listarEntes")
    public String listarEntes(ModelMap model){
        List<Usuario> entes = usuarioServ.lstarXRol(Roles.ENTE);
        model.put("us", entes);
        
        return "listar_usuario.html";
    }
    
    
      @GetMapping("/listarClient")
    public String listarClient(ModelMap model){
          List<Usuario> entes = usuarioServ.lstarXRol(Roles.CLIENT);
          model.put("us", entes);
        
        return "listar_usuario.html";
    }
    
    @GetMapping("/darDeBaja/{id}")
    public String darDeBaja(@PathVariable String id,ModelMap model){
        
        inmuebleServ.DarDeBaja(id);
         List<Inmueble> publicaciones = inmuebleServ.listar();
        model.put("publicaciones", publicaciones);
        
        return "listar_publicaciones.html";
        
    }
    
     @GetMapping("/darDeAlta/{id}")
    public String darDeAlta(@PathVariable String id,ModelMap model){
        
        inmuebleServ.DarDeAlta(id);
         List<Inmueble> publicaciones = inmuebleServ.listar();
        model.put("publicaciones", publicaciones);
        
        return "listar_publicaciones.html";
        
    }
}
