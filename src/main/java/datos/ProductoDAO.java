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
    // Se emplea este metodo para poder registrar el producto
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
    // Si retorna true se actualizo el producto, false no se pudo actualizar
    public boolean ActualizarProducto(int id, Producto productoActualizar) {
        // Inicia la sesion de trabajo con la base de datos
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            // Se verifica si el producto existe, para proceder a actualizar
            Producto existente = em.find(Producto.class, id);
            if (existente == null) return false;

            // Se inicia la transicion
            em.getTransaction().begin();
            // Se actualiza toda la informacion del producto
            existente.setNombre(productoActualizar.getNombre());
            existente.setCodigo(productoActualizar.getCodigo());
            existente.setPrecio(productoActualizar.getPrecio());
            
            // em.merge(productoActualizar);
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
    
    
    // Eliminar producto
    // Si retorna true se elimino el registro, false no se pudo eliminar
    public boolean EliminarProducto(int numId) {
        // Inicia la sesion de trabajo con la base de datos
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            // Se verifica si el producto existe, para proceder a eliminarlo
            Producto producto = em.find(Producto.class, numId);
            if (producto == null) return false;

            // Se inicia la transicion
            em.getTransaction().begin();
            // Se remueve el producto
            em.remove(producto);
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
    
    
    // Devuelve en una lista todas los productos
    public List<Producto> ListarProductosRegistrados() {
        // Inicia la sesion de trabajo con la base de datos
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            // Devuelve la lista de productos encontrados en la DB
            return em.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    
    // Se emplea este metodo para poder buscar el producto por codigo
    public Producto BuscarProductoPorCodigo(String codigo){
        // Inicia la sesion de trabajo con la base de datos
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            // Devuelve un unico producto encontrado en la DB
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
