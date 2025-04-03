/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion;

import java.time.LocalDate;
import negocio.PersonaServicio;
import modelo.Persona;
import modelo.Producto;

/**
 *
 * @author Jheyson Gaona
 */
public class PersonaMain {
    public static void main(String[] args){
        // Instanciar una fecha de nacimiento (26 de septiembre de 1994)
        LocalDate fechaNacimiento = LocalDate.of(1994, 9, 26);
        
        PersonaServicio servicio = new PersonaServicio();
        Persona p1 = new Persona("Jheyson", "Gaona", "1104856495",
                "jsgaona@ucacue.edu.ec", fechaNacimiento);
        
        // Se valida si el nombre, apellido y correo no sean vacios
        System.out.println("Capa de presentacion");
        servicio.AgregarNuevaPersona(p1);
        
        
        /*
        ProductoServicio proServicio = new ProductoServicio();
        Producto prod1 = new Producto("010101", "Yogurt", 0.75f);
        
        // Se valida si el nombre, apellido y correo no sean vacios
        System.out.println("Capa de presentacion");
        proServicio.AgregarNuevoProducto(prod1);
        */
    }
}