/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenimiento;

import Persistencia.Notificaciones;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author manuel.suarezusam
 */
public class MantenimientoNotificaciones {
    
    public int guardar(Notificaciones notificacion){
        
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        try {
            em.persist(notificacion);
            em.getTransaction().commit();
            flag = 1;
            System.out.println("La notifiacion fue guardada exitosamente");
        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;
            System.out.println("Error al guardar la notificacion" + e);
        }finally{
            em.close();
        }
        return flag;
    }
    
    public Notificaciones consultarId(int idnotificacion){
        System.out.println("Algo no se");
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Notificaciones notificacion = null;
        em.getTransaction().begin();
        
        System.out.println("Algo no se3");
        try {
        System.out.println("Algo no se22");
            notificacion = em.find(Notificaciones.class, idnotificacion);
            em.getTransaction().commit();
            System.out.println("Su consulta por ID fue exitosa");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error al realizar la consulta por ID" +e);
        }finally{
            em.close();
        }
        System.out.println("act24");
        return notificacion;
    }
    
    public List<Notificaciones> consultar(){
        List<Notificaciones> listaN = null;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT e FROM Notificaciones e");
            
            em.getTransaction().commit();
            listaN = query.getResultList();
            return listaN;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }finally{
            em.close();
        }
    }
    
    public int Actualizar(Notificaciones notificacion){
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Notificaciones not = null;
        em.getTransaction().begin();
        int flag = 0;
        try {
            not = em.find(Notificaciones.class, notificacion.getIdnotificacion());
            not.setIdnotificacion(notificacion.getIdnotificacion());
            not.setIdusuario(notificacion.getIdusuario()); 
            not.setIdactividad(notificacion.getIdactividad());
            not.setRango(notificacion.getRango());

            em.getTransaction().commit();
            flag = 1;
            System.out.println("Actualizacion exitosa");
        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;
            System.out.println("Error al acualizar Mantenimiento Notificaciones"+e);
        }finally{
            em.close();
        }
        return flag;
    }
    
    public int eliminiar(Notificaciones notificacion){
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Notificaciones not = null;
        em.getTransaction().begin();
        int flag = 0;
        try {
            not = em.find(Notificaciones.class, notificacion.getIdnotificacion());
            em.remove(not);
            
            em.getTransaction().commit();
            flag = 1;
            System.out.println("Exito al eliminar su registro");
        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;
            System.out.println("Error al elminar el registro"+e);
        }finally{
            em.close();
        }
        return flag; 
    }
}
