/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenimiento;

import Persistencia.Cronogramas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author eliseo.garciausam
 */
public class MantenimientoCronogramas {
    
    public int guardar(Cronogramas cronogramas) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        try {
            em.persist(cronogramas);
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
    
    public Cronogramas consultarid(int idCronograma) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Cronogramas cronogramas = null;
        em.getTransaction().begin();
        try {
            cronogramas = em.find(Cronogramas.class, idCronograma);
            em.getTransaction().commit();
            System.out.println("exito consultarid");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("error consultarid");
        } finally {
            em.close();
        }
        return cronogramas;
    }
    
     public List<Cronogramas> consultar() {
        List<Cronogramas> listaCR = null;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try{
            Query query=em.createQuery("SELECT c FROM Cronogramas c");
            em.getTransaction().commit();
            listaCR=query.getResultList();
            System.out.println(listaCR+"si");
            return listaCR;
        }catch(Exception e){
            em.getTransaction().rollback();
            return null;
        }finally{
            em.close();
        }
    }
     
        public int Actualizar(Cronogramas cronogramas){
        EntityManager em=JpaUtil.getEntityManagerFactory().createEntityManager();
        Cronogramas crono=null;
        em.getTransaction().begin();
        int flag=0;
        try{
            crono=em.find(Cronogramas.class,cronogramas.getIdCronograma());
            crono.setIdCronograma(cronogramas.getIdCronograma());
            crono.setNombre(cronogramas.getNombre());
            crono.setDescripcion(cronogramas.getDescripcion());
            crono.setIdUsuario(cronogramas.getIdUsuario());
            
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
        
         public int eliminar(Cronogramas cronogramas){
      EntityManager em=JpaUtil.getEntityManagerFactory().createEntityManager();
      Cronogramas cro=null;
      em.getTransaction().begin();
      int flag=0;
      try{
          cro=em.find(Cronogramas.class,cronogramas.getIdCronograma());
          em.remove(cro);
          em.getTransaction().commit();
          flag=1;
          System.out.println("exito al eliminar");
      }catch(Exception e){
          em.getTransaction().rollback();
          flag=0;
      }finally{
          em.close();
      }
      return flag;
    }
}
    
