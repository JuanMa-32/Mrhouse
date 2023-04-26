
package com.alquileres.MrHouse.entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Inmueble {
    
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid",strategy="uuid2")
    private String id;
    
    @NotBlank(message="Nombre Vacio")//con esta anotacion valido que el campo no este ni VACIO ni NULL
    private String nombre;
    
    @NotBlank(message="Ubicacion Vacia")
    private String ubicacion;
    
    @Size(max=200,min=25,message=("Cantidad de caracteres incorrecto"))//con esta anotacion valido que el tamaño de caracteres este entre 25 Y 200
    private String descripcion;
    
    @NotNull(message="Campo obligatorio")//con esta anotacion valido que este campo no sea null
    private Boolean tv;
    
    @NotNull(message=("Campo obligatorio"))
    private Boolean wifi;
    
    @NotNull(message=("Campo obligatorio"))
    private Integer cantPersonas;
    
    @NotNull(message="Campo obligatorio")
    private Boolean desayuno;
    
    @NotNull(message="Campo obligatorio")
    private Integer cantHabitacion;
    
    @NotNull(message="Campo obligatorio")
    private Integer cantBanios;
    
    @NotNull(message="Campo obligatorio")
    private Double precio;
    
    @NotBlank(message="Tipo de vivienda Vacio")
    private String tipoDeVivienda;
    
    @NotBlank(message=("Campo obligatorio"))
    private String condicion;//este atributo indica si esta en venta o alquiler.
    
    private String foto;
    
    private Boolean alta;
    
    @OneToMany
    private List<Turno> turno;
    
    
    @ManyToOne
    private Usuario duenio;

    public Inmueble() {
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getTv() {
        return tv;
    }

    public void setTv(Boolean tv) {
        this.tv = tv;
    }

    public Boolean getWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public Integer getCantPersonas() {
        return cantPersonas;
    }

    public void setCantPersonas(Integer cantPersonas) {
        this.cantPersonas = cantPersonas;
    }

    public Boolean getDesayuno() {
        return desayuno;
    }

    public void setDesayuno(Boolean desayuno) {
        this.desayuno = desayuno;
    }

    public Integer getCantHabitacion() {
        return cantHabitacion;
    }

    public void setCantHabitacion(Integer cantHabitacion) {
        this.cantHabitacion = cantHabitacion;
    }

    public Integer getCantBanios() {
        return cantBanios;
    }

    public void setCantBanios(Integer cantBanios) {
        this.cantBanios = cantBanios;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getTipoDeVivienda() {
        return tipoDeVivienda;
    }

    public void setTipoDeVivienda(String tipoDeVivienda) {
        this.tipoDeVivienda = tipoDeVivienda;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    
    public List<Turno> getTurno() {
        return turno;
    }

    public void setTurno(List<Turno> turno) {
        this.turno = turno;
    }

    public Usuario getDuenio() {
        return duenio;
    }

    public void setDuenio(Usuario duenio) {
        this.duenio = duenio;
    }
    
    
    
}
