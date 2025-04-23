/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import datos.ProductoDAO;
import java.util.List;
import modelo.Producto;

/**
 *
 * @author Jheyson Gaona
 */
public class ProductoServicio {
    
    private final ProductoDAO productoDAO;
    
    
    public ProductoServicio(){
        this.productoDAO = new ProductoDAO();
    }
    
    
    public int AgregarNuevoProducto(Producto nuevoProducto){
        return this.productoDAO.RegistrarProducto(nuevoProducto);
    }
    
    
    // Este metodo permite devolver todas las personas registradas en el sistema
    public List<Producto> ListarProductos(){
        return this.productoDAO.ListarProductosRegistrados();
    }
    
    
    public boolean EliminarProductoPorId(int numId){
        return this.productoDAO.EliminarProducto(numId);
    }
    
    
    public boolean ActualizarProducto(int id, Producto producto) {
        return this.productoDAO.ActualizarProducto(id, producto);
    }
}