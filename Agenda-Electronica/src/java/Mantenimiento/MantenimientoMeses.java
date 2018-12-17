/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenimiento;

import Persistencia.Meses;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author manuel.suarezusam
 */
public class MantenimientoMeses {
    
    public int Guardar(Meses mes) {

        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        try {
            em.persist(mes);
            em.getTransaction().commit();
            flag = 1;
            System.out.println("El mes fue Guardado Exitosamente");
        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;
            System.out.println("Error Al Guardar El mes" + e);
        } finally {
            em.close();
        }
        return flag;
    }
    
    public Meses consultarid(Integer idMes) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Meses mess = null;
        em.getTransaction().begin();
        try {
            mess = em.find(Meses.class, idMes);
            em.getTransaction().commit();
            System.out.println("Su consulta por ID Fue exitosa");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error al consultar por ID " + e);
        } finally {
            em.close();
        }
        return mess;
    }
    
    public List<Meses> consultar() {
        List<Meses> listaU = null;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT e FROM Meses e");

            em.getTransaction().commit();
            listaU = query.getResultList();
            return listaU;

        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        } finally {
            em.close();
        }
    }
    
    public int Actualizar(Meses mes){
        EntityManager em  = JpaUtil.getEntityManagerFactory().createEntityManager();
        Meses mess = null;
        em.getTransaction().begin();
        int flag = 0;
        try {
            mess = em.find(Meses.class, mes.getIdmes());
            mess.setIdmes(mes.getIdmes());
            mess.setIdcalendario(mes.getIdcalendario());
            mess.setMes(mes.getMes());
            
            em.getTransaction().commit();
            flag = 1;
            System.out.println("La actualizacion fue exitosa");
        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;
            System.out.println("Error al reaclizar la actualizacion de mantenimiento Mes" + e);
        }finally{
            em.close();
        }
        return flag;
    }
    
    public int eliminar (Meses mes){
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Meses mess = null;
        em.getTransaction().begin();
        int flag = 0;
        try {
            mess = em.find(Meses.class, mes.getIdmes());
            em.remove(mess);
            
            em.getTransaction().commit();
            flag = 1;
            System.out.println("Exito al eliminar el mes");
        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;
            System.out.println("Error al eliminar el mes" + e);
        }finally{
            em.close();
        }
        return flag; 
    }
}
