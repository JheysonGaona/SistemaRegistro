/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import modelo.Producto;
import util.PersistenceUtil;

/**
 *
 * @author Jheyson Gaona
 */
public class ProductoDAO {
    
    public void AgregarProducto(Producto productoAgregar){
        // Inicia la sesion de trabajo con la base de datos
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            // Se inicia la transicion
            em.getTransaction().begin();
            
            // Se inserta el producto
            em.persist(productoAgregar);
            
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
    
    
    // [0] ya existe el producto  [1] no existe el producto, registro exitoso
    // [2] Huno un error inesperado
    public int VerificarAgregarPersona(Producto productoAgregar){
        int result = 0;
         // Inicia la sesion de trabajo con la base de datos
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            Producto productoExiste = em.createQuery(
                "SELECT p FROM Producto p WHERE p.codigo = :numCod", Producto.class)
            .setParameter("numCod", productoAgregar.getCodigo())
            .getSingleResult();
            
            if(productoExiste != null){
                System.out.println("YA EXISTE EL PRODUCTO");
                em.close();
                return result;
            }
        } catch(NoResultException ex){
            // Se inicia la transicion
            em.getTransaction().begin();
            // Se inserta el producto
            em.persist(productoAgregar);
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
