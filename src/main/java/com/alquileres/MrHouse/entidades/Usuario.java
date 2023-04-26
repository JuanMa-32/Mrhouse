
package com.alquileres.MrHouse.entidades;

import com.alquileres.MrHouse.enumeradores.Roles;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid",strategy="uuid2")
    private String id;
    
    @NotBlank(message=("Nombre vacio"))
    private String nombre;
    
    @Email
    private String email;
    
    @NotBlank(message=("Contraseña vacio"))
    private String password;
    
    @NotBlank(message=("DNI vacio"))
    private String dni;
    
    @Enumerated(EnumType.STRING)
    private Roles rol;
    
    @NotBlank(message=("pais vacio"))
    private String pais;
    
    @NotBlank(message=("Telefono vacio"))
    private String telefono;
    
    
    @OneToMany
    private List<Turno> turno;

    public Usuario() {
    }

    public Usuario(String id, String nombre, String email, String password, String dni, Roles rol, String pais, String telefono, List<Turno> turno) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.dni = dni;
        this.rol = rol;
        this.pais = pais;
        this.telefono = telefono;
        this.turno = turno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Turno> getTurno() {
        return turno;
    }

    public void setTurno(List<Turno> turno) {
        this.turno = turno;
    }
    
    
}
