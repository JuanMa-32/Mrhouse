
package com.alquileres.MrHouse.controladores;

import com.alquileres.MrHouse.entidades.Inmueble;
import com.alquileres.MrHouse.entidades.Turno;
import com.alquileres.MrHouse.entidades.Usuario;
import com.alquileres.MrHouse.servicios.InmuebleServicio;
import com.alquileres.MrHouse.servicios.TurnoServicio;
import com.alquileres.MrHouse.servicios.UsuarioServicio;
import java.util.Collection;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/turno")
public class TurnoControlador {
    
    @Autowired
    private TurnoServicio turnoServ;
    
    @Autowired
    private InmuebleServicio inmuebleServ;
    
    @Autowired
    private UsuarioServicio usuarioServ;
    
    @GetMapping("/sacarTurno/{id}")
    public String sacarTurno(@PathVariable("id") String id,ModelMap model){
        
       
        
        Inmueble e = inmuebleServ.findByid(id);
        model.put("inmueble", e);
        model.put("turno",  new Turno());
      

        return "turno.html";
    }
    
    @PostMapping("/turnoPedido")
    public String turnoPedido(@Valid Turno turno,BindingResult result ,@RequestParam String idInmueble,ModelMap model,RedirectAttributes redirectAttributes,HttpSession session){
         
        Usuario cliente = (Usuario) session.getAttribute("usuariosession");//traigo la session para saber que usuario esta solicitando el turno
      
        try {
            if (result.hasErrors()) {
               //si hay errores de validacion redirecciono los errores a la pagina con el formulario
                 redirectAttributes.addFlashAttribute("errorFecha", "fecha vacia ");
                 return "redirect:/turno/sacarTurno/"+idInmueble;
            }
            
            if(turnoServ.disponibilidadFecha(idInmueble, turno.getFecha())==false){
                //si la fecha no esta disponible redirecciono con los errores hacia la apgina del form
                 redirectAttributes.addFlashAttribute("ocupado", "Esta fecha no esta disponible");
                 return "redirect:/turno/sacarTurno/"+idInmueble;
            }
 
            turno.setInmueble(inmuebleServ.findByid(idInmueble));//seteo el inmueble para el que sacaron turno
            turno.setUsuario(usuarioServ.findByid(cliente.getId()));//seteo el usuario que saco el turno
              
            turnoServ.sacarTurno(turno);//Guardo el turno con fecha,Inmueble y el que solicita el turno.

             
              //--------------------------------
             usuarioServ.guardarTurno(cliente.getId(), turno.getId());//seteo al usuario el turni
             
             inmuebleServ.guardarTurno(idInmueble,  turno.getId());//seteo al inmueble el turno

           

            model.addAttribute("turnoAsignado", "Turno Asignado!");
            List<Inmueble> inmuebles = inmuebleServ.listar();
            model.put("inmuebles", inmuebles);
             
            return "inicio.html";
        } catch (Exception e) {
           
            return "index.html";
        }
       
    }
}
