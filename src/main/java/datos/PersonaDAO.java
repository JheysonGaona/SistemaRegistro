/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import modelo.Cliente;
import modelo.Persona;
import util.PersistenceUtil;

/**
 *
 * @author Jheyson Gaona
 */
public class PersonaDAO {
    
    
    public PersonaDAO(){
        
    }
    
    
    // Se emplea este metodo para poder agregar la persona
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
    // [2] Hubo un error inesperado
    // Se emplea este petodo para verificar si existe primero la persona
    // y posterior a ello intentar registrarla
    public int VerificarAgregarPersona(Persona personaAgregar){
        int result = 0;
        
         // Inicia la sesion de trabajo con la base de datos
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            Persona personaExiste = em.createQuery(
                "SELECT p FROM Persona p WHERE p.numIdentificacion = :numId", Persona.class)
            .setParameter("numId", personaAgregar.getNumIdentificacion())
            .getSingleResult();
            
            // Se valida si la persona ya existe
            if(personaExiste != null){
                System.out.println("YA EXISTE LA PERSONA");
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


    // [0] ya existe la persona  [1] registro de persona exitoso
    // [2] Error interno
    // Se emplea este metodo para verificar si existe la persona por su cedula
    // y si no existe se procede a registrarla
    public int RegistrarPersona(Cliente personaAgregar){
        // Inicia la sesion de trabajo con la base de datos
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            Long count = em.createQuery(
                "SELECT COUNT(p) FROM Persona p WHERE p.numIdentificacion = :numId", Long.class)
            .setParameter("numId", personaAgregar.getNumIdentificacion())
            .getSingleResult();
            
            // Existe la persona, porque el contador dio un resultado
            if(count > 0){
                return 0;
            }
            
            // Se inicia la transicion
            em.getTransaction().begin();
            // Se inserta la persona
            em.persist(personaAgregar);
            // Confirmar y guardar los cambios
            em.getTransaction().commit();
            return 1;
        }catch(Exception ex){
            // Revertir todo, no guardar nada
            if(em.getTransaction().isActive()){
               em.getTransaction().rollback(); 
            }
            return 2;
            
        }finally{
            em.close();
        }
    }
    
    
    // Metodo que permite actulizar la persona
    public boolean ActualizarPersona(int id, Persona personaActualizar) {
        // Inicia la sesion de trabajo con la base de datos
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            // Se verifica si la persona que se intenta actualizar existe
            Persona existente = em.find(Persona.class, id);
            if (existente == null) return false;

            // Se inicia la transicion
            em.getTransaction().begin();
            
            // Se actualiza cada parametro de la persona
            existente.setNombre(personaActualizar.getNombre());
            existente.setApellido(personaActualizar.getApellido());
            existente.setNumIdentificacion(personaActualizar.getNumIdentificacion());
            existente.setCorreo(personaActualizar.getCorreo());
            existente.setFechaNacimiento(personaActualizar.getFechaNacimiento());
            
            // em.merge(personaActualizar);
            // Confirmar y guardar los cambios
            em.getTransaction().commit();
            return true;

        } catch (Exception ex) {
            // Revertir todo, no guardar nada
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }
    
    
    // Eliminar persona
    // Si retorna true se elimino el registro, false no se pudo eliminar
    public boolean EliminarPersona(int numId) {
        // Inicia la sesion de trabajo con la base de datos
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            // Se verifica si la persona que se intenta eliminar existe
            Persona persona = em.find(Persona.class, numId);
            
            // Existe la persona a eliminar
            if (persona == null) {
                return false;
            }
            // Se inicia la transicion
            em.getTransaction().begin();
            // Se elimina la persona de la DB
            em.remove(persona);
            // Confirmar y guardar los cambios
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            // Revertir todo, no guardar nada
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }
    
    
    // Devuelve en una lista todas las personas
    public List<Persona> ListarPersonasRegistradas() {
        // Inicia la sesion de trabajo con la base de datos
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            // Devuelve el listado de la busqueda
            return em.createQuery("SELECT p FROM Persona p", Persona.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    
    // Se emplea este metodo para devolver una unica persona a buscar por su cedula
    public Persona BuscarPersonaPorCedula(String cedula){
        // Inicia la sesion de trabajo con la base de datos
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            // Devuelve un objeto de la persona a buscar
            return em.createQuery("SELECT p FROM Persona p WHERE p.numIdentificacion = :cedula", Persona.class)
                    .setParameter("cedula", cedula)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }
}