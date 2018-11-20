/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenimiento;

import Persistencia.Calendarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author eliseo.garciausam
 */
public class MantenimientoCalendarios {

    public int guardar(Calendarios calendarios) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        try {
            em.persist(calendarios);
            em.getTransaction().commit();
            flag = 1;
            System.out.println("exito al guardar");
        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;
            System.out.println("error al guardar");
        } finally {
            em.close();
        }
        return flag;
    }

     public Calendarios consultarid(int idCalendario) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Calendarios calendarios = null;
        em.getTransaction().begin();
        try {
            calendarios = em.find(Calendarios.class, idCalendario);
            em.getTransaction().commit();
            System.out.println("exito consultarid");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("error consultarid");
        } finally {
            em.close();
        }
        return calendarios;
     }
     
      public List<Calendarios> consultar() {
        List<Calendarios> listaC = null;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try{
            Query query=em.createQuery("SELECT c FROM Calendarios c");
            em.getTransaction().commit();
            listaC=query.getResultList();
            System.out.println(listaC+"si");
            return listaC;
        }catch(Exception e){
            em.getTransaction().rollback();
            return null;
        }finally{
            em.close();
        }
    }
      
      public int Actualizar(Calendarios calendarios){
        EntityManager em=JpaUtil.getEntityManagerFactory().createEntityManager();
        Calendarios cale=null;
        em.getTransaction().begin();
        int flag=0;
        try{
           cale=em.find(Calendarios.class, calendarios.getIdCalendario());
           cale.setIdCalendario(calendarios.getIdCalendario());
           cale.setIdCronograma(calendarios.getIdCronograma());
           cale.setAño(calendarios.getAño());
           
           em.getTransaction().commit();
           flag=1;
            System.out.println("exitoso");
        }catch(Exception e){
            em.getTransaction().rollback();
            flag=0;
            System.out.println("error"+e);
        }finally{
            em.close();
        }
        return flag;
      }
}
