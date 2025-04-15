///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package datos;
//
//import javax.persistence.EntityManager;
//import modelo.Factura;
//import util.PersistenceUtil;
//
///**
// *
// * @author XRLab
// */
//public class FacturaDAO {
//    
//    public void RegistrarFactura(Factura facturaAgregar){
//        // Inicia la sesion de trabajo con la base de datos
//        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
//        try {
//            // Se inicia la transicion
//            em.getTransaction().begin();
//            
//            // Se inserta la persona
//            em.persist(facturaAgregar);
//            
//            // Confirmar y guardar los cambios
//            em.getTransaction().commit();
//        } catch(Exception ex){
//            // Revertir todo, no guardar nada
//            em.getTransaction().rollback();
//            System.err.println("Error de sesion de trabajo: " + ex.getMessage());
//        }finally{
//            em.close();
//        }
//    }
//    
//}
