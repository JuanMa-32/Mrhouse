
package com.alquileres.MrHouse.controladores;

import com.alquileres.MrHouse.entidades.Inmueble;
import com.alquileres.MrHouse.entidades.Turno;
import com.alquileres.MrHouse.entidades.Usuario;
import com.alquileres.MrHouse.servicios.InmuebleServicio;
import com.alquileres.MrHouse.servicios.TurnoServicio;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/inmueble")
public class InmuebleControlador {
    
    @Autowired
    private InmuebleServicio inmuebleServ;
    
     @Autowired
    private TurnoServicio turnoServ;
    
    
    @GetMapping("/publicar/{id}")
    public String registrar(ModelMap mode,@PathVariable String id){
        
        if(id.equals("0")){
            Inmueble inmueble = new Inmueble();
            
            mode.put("inmueble", inmueble);
           
        }else{
            mode.put("inmueble", inmuebleServ.findByid(id));
           
        }
        return "publicar_inmu.html";
    }
    
    @PostMapping("/publicado")
    public String publicado(@Valid Inmueble inmueble, BindingResult resul,HttpSession session,ModelMap model, MultipartFile file,@RequestParam String id){
        
       
        try {
            if(resul.hasErrors()){
                 
                 return "publicar_inmu.html";
            }
            Usuario us = (Usuario) session.getAttribute("usuariosession"); //traigo al usuario de la session que esta publicando este inmueble
                                                                                //y le seteo a inmueble su dueño
            inmueble.setDuenio(us);
            inmueble.setAlta(true);
             //cargar foto
        
        String folder = "imagen//";
        if(!file.isEmpty()){
            byte [] bytes = file.getBytes();
            Path path = Paths.get(folder+file.getOriginalFilename());
            Files.write(path, bytes);
           inmueble.setFoto(file.getOriginalFilename());
        }
        
            if(id.isEmpty()){
               
                inmuebleServ.crear(inmueble);
                model.addAttribute("exito", "publicado con exito");
            }else{
               
                inmuebleServ.edit(inmueble, inmueble.getId());
                model.addAttribute("exito", "editado con exito");
            }
            
        
        
                
            List<Inmueble> inmuebles = inmuebleServ.listar();
            model.put("inmuebles", inmuebles);
          
             return "inicio.html";
        } catch (Exception ex) {
          
             return "inicio.html";
        }
       
        
    }
    
     @GetMapping("/turnosEnte")
    public String turnosClient(HttpSession session,ModelMap model){
        
       
         Usuario us = (Usuario) session.getAttribute("usuariosession");
         List<Turno> turnosInmueble = turnoServ.turnoInmueble(us.getId());
        
        model.put("turnosInmueble", turnosInmueble);
        
        return "turnos_inmueble.html";
    }
    
    @GetMapping("/misPublicaciones")
    public String misPublicaiones(ModelMap model,HttpSession session){
        
        Usuario us = (Usuario) session.getAttribute("usuariosession");
        
        List<Inmueble> publicaciones= inmuebleServ.misPublicaciones(us);
        
        model.put("inmuebles", publicaciones);
        
        return"mis_publicaciones.html";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id,HttpSession session,ModelMap model){
        
        inmuebleServ.delete(id);
           Usuario us = (Usuario) session.getAttribute("usuariosession");
        
        List<Inmueble> publicaciones= inmuebleServ.misPublicaciones(us);
        
        model.put("inmuebles", publicaciones);
        return "mis_publicaciones.html";
    }
}
