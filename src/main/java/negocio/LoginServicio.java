/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import datos.AutenticacionDAO;
import modelo.Empleado;

/**
 *
 * @author XRLab
 */
public class LoginServicio {
    
    private final AutenticacionDAO autenticacionDAO;
    
    // Constructor vacio
    public LoginServicio(){
        this.autenticacionDAO = new AutenticacionDAO();
    }
    
    
    public boolean LoginUsuarioClave(String usuario, String clave){
        Empleado empleadoLogeado = this.autenticacionDAO.IniciarSesionUsuarioClave(usuario, clave);
        return empleadoLogeado != null;
    }
}
