/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import modelo.Persona;
import datos.PersonaDAO;

/**
 *
 * @author Jheyson Gaona
 */

public class PersonaServicio {
    
    private final PersonaDAO personaDAO;
    
    
    public PersonaServicio(){
        this.personaDAO = new PersonaDAO();
    }

    
    public void AgregarNuevaPersona(Persona persona){
        System.out.println("Capa de negocio");
        int resultado = personaDAO.VerificarAgregarPersona(persona);
    }
}
