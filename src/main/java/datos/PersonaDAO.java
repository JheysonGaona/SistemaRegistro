/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import modelo.Persona;

/**
 *
 * @author XRLab
 */
public class PersonaDAO {
    
    // private final ConexionDB conexionDB;
    
    
    public PersonaDAO(){
        // this.conexionDB = new ConexionDB();
    }
    
    
    public void AgregarPersona(Persona persona) throws SQLException{
        String sql = "INSERT INTO usuario (nombre, correo) VALUES (?, ?)";
        Connection conn = ConexionDB.AbrirConexion();
        try (PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getCorreo());
            
            int fliasAfectadas = stmt.executeUpdate();
            if(fliasAfectadas > 0){
                try(ResultSet generateKeys = stmt.getGeneratedKeys()) {
                    if(generateKeys.next()){
                        int idGenerado = generateKeys.getInt(1);
                        System.out.println("Registro Exitoso con ID: " + idGenerado);
                    }else{
                        System.out.println("No se generó ningún ID.");
                    }
                }
            }else{
                System.out.println("No se pudo insertar el registro");
            }
        }catch(SQLException ex){
            System.out.println("Error al agregar persona: " + ex.getMessage());
        } finally {
            ConexionDB.CerrarConexion(conn);
        }
    }
    
}
