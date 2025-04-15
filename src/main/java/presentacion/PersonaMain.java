/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion;

import java.time.LocalDate;
//import modelo.DetalleFactura;
//import modelo.Factura;
import modelo.Persona;
//import negocio.FacturaServicio;
import negocio.PersonaServicio;

/**
 *
 * @author Jheyson Gaona
 */
public class PersonaMain {
    public static void main(String[] args){
        // Instanciar una fecha de nacimiento (26 de septiembre de 1994)
        LocalDate fechaNacimiento = LocalDate.of(1994, 9, 26);
        
//        String fecha = "03-04-2000";
//        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
//        Date fechaNacimiento = null;
//        try {
//            fechaNacimiento = formato.parse(fecha);
//        } catch (Exception ex){
//            System.err.println("Error de formato fecha: " + ex.getMessage());
//        }
        
        
        PersonaServicio servicio = new PersonaServicio();
        Persona p1 = new Persona("Maria", "Lojan", "1234567899",
                "maria@ucacue.edu.ec", fechaNacimiento);
        
        // Se valida si el nombre, apellido y correo no sean vacios
        servicio.AgregarNuevaPersona(p1);
        
        
//        ProductoServicio servicioPro = new ProductoServicio();
//        Producto prod1 = new Producto(0.50f, "001", "esfero");
//        Producto prod2 = new Producto(0.75f, "002", "cuaderno");
//        Producto prod3 = new Producto(1.50f, "003", "tijeras");
//        
//        // Se valida si el nombre, apellido y correo no sean vacios
//        servicioPro.AgregarNuevoProducto(prod1);
//        servicioPro.AgregarNuevoProducto(prod2);
//        servicioPro.AgregarNuevoProducto(prod3);
        
        
//        DetalleFactura detalle1 = new DetalleFactura(3, prod1.getPrecio(), prod1);
//        DetalleFactura detalle2 = new DetalleFactura(20, prod2.getPrecio(), prod2);
//        
//        List<DetalleFactura> detalles = new ArrayList<DetalleFactura>();
//        detalles.add(detalle1);
//        detalles.add(detalle2);
//        
//        
//        Factura factura = new Factura(p1, detalles);
//        FacturaServicio facturaServicio = new FacturaServicio();
//        
//        facturaServicio.RegistrarNuevaFactura(factura);

        

        // Ejecutar UI Sistema Usuario
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new SistemaUsuario().setVisible(true);
//            }
//        });
    }
}