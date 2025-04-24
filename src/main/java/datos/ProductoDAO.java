/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import modelo.Producto;
import util.PersistenceUtil;

/**
 *
 * @author Jheyson Gaona
 */
public class ProductoDAO {
    
    // [0] ya existe el producto  [1] registro de producto exitoso
    // [2] Error interno
    public int RegistrarProducto(Producto productoAgregar){
        // Inicia la sesion de trabajo con la base de datos
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            Long count = em.createQuery(
                "SELECT COUNT(p) FROM Producto p WHERE p.codigo = :numCod", Long.class)
            .setParameter("numCod", productoAgregar.getCodigo())
            .getSingleResult();
            
            // Existe el Producto, porque el contador dio un resultado
            if(count > 0){
                return 0;
            }
            
            // Se inicia la transicion
            em.getTransaction().begin();
            // Se inserta el producto
            em.persist(productoAgregar);
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
    
    
    // Metodo que permite actulizar el producto
    public boolean ActualizarProducto(int id, Producto productoActualizar) {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            Producto existente = em.find(Producto.class, id);
            if (existente == null) return false;

            em.getTransaction().begin();
            existente.setNombre(productoActualizar.getNombre());
            existente.setCodigo(productoActualizar.getCodigo());
            existente.setPrecio(productoActualizar.getPrecio());
            
            // em.merge(productoActualizar);
            em.getTransaction().commit();
            return true;

        } catch (Exception ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }
    
    
    // Eliminar producto
    // Si retorna true se elimino el registro, false no se pudo eliminar
    public boolean EliminarProducto(int numId) {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            Producto producto = em.find(Producto.class, numId);
            
            if (producto == null) return false;

            em.getTransaction().begin();
            em.remove(producto);
            em.getTransaction().commit();
            return true;

        } catch (Exception ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }
    
    
    // Devuelve en una lista todas los productos
    public List<Producto> ListarProductosRegistrados() {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    
    public Producto BuscarProductoPorCodigo(String codigo){
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Producto p WHERE p.codigo = :cod", Producto.class)
                    .setParameter("cod", codigo)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }
}
