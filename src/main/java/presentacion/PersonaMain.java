/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion;

import negocio.PersonaServicio;
import modelo.Persona;

/**
 *
 * @author XRLab
 */
public class PersonaMain {
    public static void main(String[] args){
        PersonaServicio servicio = new PersonaServicio();
        Persona p1 = new Persona("Juan ROJaS", "jsgaona@edu.ec");
        servicio.AgregarNuevaPersona(p1);
    }
}