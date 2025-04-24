/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import datos.FacturaDAO;
import datos.PersonaDAO;
import datos.ProductoDAO;
import modelo.DetalleFactura;
import modelo.Factura;
import modelo.Persona;
import modelo.Producto;

/**
 *
 * @author XRLab
 */
public class FacturaServicio {
    
    private final FacturaDAO facturaDAO;
    private final ProductoDAO productoDAO;
    private final PersonaDAO personaDAO;
    
    
    public FacturaServicio(){
        this.facturaDAO = new FacturaDAO();
        this.productoDAO = new ProductoDAO();
        this.personaDAO = new PersonaDAO();
    }
    
    
    public Persona BuscarPersonaPorCedula(String cedula) {
        Persona personaEncontrada = this.personaDAO.BuscarPersonaPorCedula(cedula);
        return personaEncontrada;
    }
    
    
    public Producto BuscarProductoPorCodigo(String codigo){
        Producto productoEncontrado = this.productoDAO.BuscarProductoPorCodigo(codigo);
        return productoEncontrado;
    }
    
    
    public Factura obtenerFacturaCompletaPorId(int idFactura){
        return this.facturaDAO.obtenerFacturaCompletaPorId(idFactura);
    }
    
    
    public int RegistrarNuevaFactura(Factura nuevaFactura){
        return this.facturaDAO.RegistrarFactura(nuevaFactura);
//        for(DetalleFactura d: nuevaFactura.getDetalles()){
//            Producto p = d.getProducto();
//            
//            if(p == null || p.getId() == 0) return false;
//            
//            Producto
//        }
    }
}
