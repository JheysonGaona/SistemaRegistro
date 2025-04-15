/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import modelo.Persona;
import util.PersistenceUtil;

/**
 *
 * @author JHeyson Gaona
 */
public class PersonaDAO {
    
    // private final ConexionDB conexionDB;
    
    
    public PersonaDAO(){
        // this.conexionDB = new ConexionDB();
    }
    
    
    /*
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
¨   */
    
    public void AgregarPersona(Persona personaAgregar){
        // Inicia la sesion de trabajo con la base de datos
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            // Se inicia la transicion
            em.getTransaction().begin();
            
            // Se inserta la persona
            em.persist(personaAgregar);
            
            // Confirmar y guardar los cambios
            em.getTransaction().commit();
        } catch(Exception ex){
            // Revertir todo, no guardar nada
            em.getTransaction().rollback();
            System.err.println("Error de sesion de trabajo: " + ex.getMessage());
        }finally{
            em.close();
        }
    }
    
    
    // [0] ya existe la persomna  [1] no existe la persona, registro exitoso
    // [2] Huno un error inesperado
    public int VerificarAgregarPersona(Persona personaAgregar){
        int result = 0;
         // Inicia la sesion de trabajo con la base de datos
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            Persona personaExiste = em.createQuery(
                "SELECT p FROM Persona p WHERE p.numIdentificacion = :numId", Persona.class)
            .setParameter("numId", personaAgregar.getNumIdentificacion())
            .getSingleResult();
            
            if(personaExiste != null){
                System.out.println("YA EXISTE LA PERSONA");
                em.close();
                return result;
            }
        } catch(NoResultException ex){
            // Se inicia la transicion
            em.getTransaction().begin();
            // Se inserta la persona
            em.persist(personaAgregar);
            // Confirmar y guardar los cambios
            em.getTransaction().commit();
            result = 1;
        }catch(Exception ex){
            // Revertir todo, no guardar nada
            em.getTransaction().rollback();
            System.err.println("Error de sesion de trabajo: " + ex.getMessage());
            result = 2;
        }finally{
            em.close();
        }
        return result;
    }
}
