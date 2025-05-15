/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import datos.ClienteDAO;
import datos.EmpleadoDAO;
import modelo.Persona;
import datos.PersonaDAO;
import java.util.List;
import modelo.Cliente;
import modelo.Empleado;
/*
import java.sql.SQLException;
*/

/**
 *
 * @author Jheyson Gaona
 */
public class PersonaServicio {
    
    private final PersonaDAO personaDAO;
    private final ClienteDAO clienteDAO;
    private final EmpleadoDAO empleadoDAO;
    
    
    public PersonaServicio(){
        this.personaDAO = new PersonaDAO();
        this.clienteDAO = new ClienteDAO();
        this.empleadoDAO = new EmpleadoDAO();
    }
    
    
    // [0] ya existe la persona  [1] registro de persona exitoso
    // [2] Error interno [3] la persona es menor de edad
    public int AgregarPersona(Persona nuevaPersona){
        nuevaPersona.CalcularEdad();
        // se verifica que la persona sea mayor de edad para registrar
        if(nuevaPersona.getEdad() >= 18){
            // Ajusto el nombre y apellido para almacenarlo en formato de MAY.
            String nombre = nuevaPersona.getNombre().toUpperCase(); // Mayusculas
            nuevaPersona.setNombre(nombre);

            String apellido = nuevaPersona.getApellido().toUpperCase(); // Minusculas
            nuevaPersona.setApellido(apellido);
            
            // El objeto es un cliente
            if(nuevaPersona instanceof Cliente cliente){
                return this.clienteDAO.RegistrarClienteDB(cliente);
                
            // El objeto es un empleado
            } else if(nuevaPersona instanceof Empleado empleado){
                return this.empleadoDAO.RegistrarEmpleadoDB(empleado);
            }else{
                return 2;
            }
        } else {
            return 3;
        }
    }
    
    
    // Este metodo permite devolver todas las personas registradas en el sistema
    public List<Persona> ListarPersonas(){
        return personaDAO.ListarPersonasRegistradas();
    }
    
    
    public boolean EliminarPersonaPorId(int numId){
        return personaDAO.EliminarPersona(numId);
    }
    
    
    public boolean ActualizarPersona(int id, Persona persona) {
        return personaDAO.ActualizarPersona(id, persona);
    }
}
