/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import modelo.Factura;
import util.PersistenceUtil;

/**
 *
 * @author Jheyson Gaona
 */
public class FacturaDAO {
    
    
    // [0] registro exitoso [1] ocurrio un error 
    public int RegistrarFactura(Factura facturaAgregar){
        // Inicia la sesion de trabajo con la base de datos
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            // Se inicia la transicion
            em.getTransaction().begin();
            
            // Se inserta la persona
            em.persist(facturaAgregar);
            
            // Confirmar y guardar los cambios
            em.getTransaction().commit();
            return 0;
        } catch(Exception ex){
            // Revertir todo, no guardar nada
            em.getTransaction().rollback();
            System.err.println("Error de sesion de trabajo: " + ex.getMessage());
            return 1;
        }finally{
            em.close();
        }
    }
    
    
    // Se emplea este metodo para obtener la factura por el ID
    public Factura ObtenerFacturaCompletaPorId(int idFactura) {
        // Inicia la sesion de trabajo con la base de datos
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            // Se genera el query para buscar toda la informacion de la factura
            return em.createQuery("""
                    SELECT f FROM Factura f
                    JOIN FETCH f.persona
                    LEFT JOIN FETCH f.detalles d
                    LEFT JOIN FETCH d.producto
                    WHERE f.id = :idFactura
                    """, Factura.class)
                    .setParameter("idFactura", idFactura)
                    .getSingleResult();
//            return em.createQuery("SELECT p FROM Factura p WHERE p.id = :idS", Factura.class)
//                    .setParameter("idS", idFactura)
//                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}