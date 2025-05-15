/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;
import javax.persistence.*;

/**
 *
 * @author Jheyson Gaona
 */
@Entity
@Table(name = "empleado")
public class Empleado extends Persona{
        
    @Column(nullable = false)
    private String rol;

    @Column(nullable = false)
    private LocalDate fechaIngreso;

    @Column
    private boolean activo;
    
    @Column
    private String clave;
    
    
    // Constructor vacio
    public Empleado() {
        
    }
    
    
    // Constructor con parametros
    public Empleado(String nombre, String apellido, String numIdentificacion,
            String correo, LocalDate fechaNacimiento, String rol,
            LocalDate fechaIngreso, boolean activo) {
        super(nombre, apellido, numIdentificacion, correo, fechaNacimiento);
        this.rol = rol;
        this.fechaIngreso = fechaIngreso;
        this.activo = activo;
        this.clave = numIdentificacion;
    }
    
    
        // Constructor con parametros
    public Empleado(int id, String nombre, String apellido, String numIdentificacion,
            String correo, LocalDate fechaNacimiento, String rol,
            LocalDate fechaIngreso, boolean activo, String clave) {
        super(nombre, apellido, numIdentificacion, correo, fechaNacimiento);
        this.rol = rol;
        this.fechaIngreso = fechaIngreso;
        this.activo = activo;
        this.clave = clave;
    }

    
    // Getters and Setters
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}