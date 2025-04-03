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
    
    
    public int VerificarAgregarProducto(Producto producto) {
        int resultado = 0;

        // Inicia una sesión de trabajo con la base de datos
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        
        try {
            Producto productoExiste = em.createQuery(
                "SELECT p FROM Producto p WHERE p.codigo = :numCodigo", Producto.class)
                .setParameter("numCodigo", producto.getCodigo())
                .getSingleResult();
            
            if(productoExiste != null){
                System.out.println("YA EXISTE EL Producto");
                return resultado;
            }

        } catch (NoResultException nre) {
            // Se inicia la transicion
            em.getTransaction().begin();
            // Se inserta la persona
            em.persist(producto);
            // Confirma y guarda los cambios
            em.getTransaction().commit();
            resultado = 1;
            System.out.println("AGREGANDO NUEVo PRODUCTO");
        } catch (Exception ex){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            System.out.println("Error: " + ex.getMessage());
            resultado = 2;
        } finally {
            em.close();
        }
        return resultado;
    }
}
