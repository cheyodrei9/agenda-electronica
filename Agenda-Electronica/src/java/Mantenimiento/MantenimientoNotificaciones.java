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
    
    public Notificaciones consultarId(Integer idNotificacion){
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Notificaciones notificacion = null;
        em.getTransaction().begin();
        try {
            notificacion = em.find(Notificaciones.class, idNotificacion);
            em.getTransaction().commit();
            System.out.println("Su consulta por ID fue exitosa");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error al realizar la consulta por ID" +e);
        }finally{
            em.close();
        }
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
    
    public int actualizar(Notificaciones notificacion){
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Notificaciones not = null;
        em.getTransaction().begin();
        int flag = 0;
        try {
            not = em.find(Notificaciones.class, notificacion.getIdnotificacion());
            not.setIdusuario(notificacion.getIdusuario());
            not.setRango(notificacion.getRango());
            
            
            em.getTransaction().commit();
            flag = 1;
            System.out.println("Actualizacion exitosa");
        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;
            System.out.println("Error al acualizar"+e);
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
