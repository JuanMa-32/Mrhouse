
package com.alquileres.MrHouse.controladores;

import com.alquileres.MrHouse.entidades.Inmueble;
import com.alquileres.MrHouse.entidades.Usuario;
import com.alquileres.MrHouse.servicios.InmuebleServicio;
import com.alquileres.MrHouse.servicios.UsuarioServicio;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class InicioControlador {
    
    @Autowired
    private UsuarioServicio usuarioServ;
    
    @Autowired
    private InmuebleServicio inmuebleServ;
    
    @GetMapping("/")
    public String index(ModelMap model){
        
        List<Inmueble> inmuebles = inmuebleServ.listar();
        model.put("inmuebles", inmuebles);
        return "index.html";
    }
    
   
     
    @GetMapping("/login")
    public String login(){
        return "login.html";
    }
    
    @GetMapping("/inicio")
    public String inicio(ModelMap model,HttpSession session){
        
        List<Inmueble> inmuebles = inmuebleServ.listar();
        model.put("inmuebles", inmuebles);
         Usuario logueado = (Usuario) session.getAttribute("usuariosession");

        if (logueado.getRol().toString().equals("ADMIN")) {
            return "redirect:/admin/dashboard";
        }
        return "inicio.html";
    }
    
  
    
     //vista para cuando el usuario busque CASAS EN VENTA
    @GetMapping("/casaVende")
    public String casaVende(ModelMap model){
        
        List<Inmueble> inmuebles = inmuebleServ.buscarPorViviendaYCondicion("casa", "vende");
        model.put("inmuebles", inmuebles);
        
        
            return  "lista_inmuebles.html";
    }
    
     //vista para cuando el usuario busque CASAS EN ALQUILER
    @GetMapping("/casaAlquila")
    public String casaAlquila(ModelMap model ){
        
        List<Inmueble> inmuebles = inmuebleServ.buscarPorViviendaYCondicion("casa", "alquila");
        model.put("inmuebles", inmuebles);
        
        
            return  "lista_inmuebles.html";
    }
    
     //vista para cuando el usuario busque DEPTOS EN VENTA
    @GetMapping("/deptoVende")
    public String deptoVende(ModelMap model ){
        
        List<Inmueble> inmuebles = inmuebleServ.buscarPorViviendaYCondicion("departamento", "vende");
        model.put("inmuebles", inmuebles);
       
        
            return  "lista_inmuebles.html";
    }
    
     //vista para cuando el usuario busque DEPTOS EN ALQUILER
    @GetMapping("/deptoAlquila")
    public String deptoAlquila(ModelMap model ){
        
        List<Inmueble> inmuebles = inmuebleServ.buscarPorViviendaYCondicion("departamento", "alquila");
        model.put("inmuebles", inmuebles);
        
        return  "lista_inmuebles.html";
          
    }
    
    
    //ver publicaciones
    @GetMapping("/ver/{id}")
    public String ver(@PathVariable String id,ModelMap model){
        
        Inmueble inmueble = inmuebleServ.findByid(id);
        model.put("inmueble", inmueble);
        
        return "ver_publicacion.html";
    }
    
    
}
