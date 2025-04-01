/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import modelo.Persona;
import datos.PersonaDAO;
import java.sql.SQLException;

/**
 *
 * @author XRLab
 */
public class PersonaServicio {
    
    private final PersonaDAO personaDAO;
    
    
    public PersonaServicio(){
        this.personaDAO = new PersonaDAO();
    }
    
    
    public void AgregarNuevaPersona(Persona persona){
        try {
            personaDAO.AgregarPersona(persona);
        }catch(SQLException ex){
            System.out.println("Erro en capa negocio, no se puede agregar persona");
        }
    }
}
