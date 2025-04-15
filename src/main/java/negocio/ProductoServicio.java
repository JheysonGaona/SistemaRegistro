/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import datos.ProductoDAO;
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
    
    
    public void AgregarNuevoProducto(Producto nuevoProducto){
        productoDAO.AgregarProducto(nuevoProducto);
    }
}