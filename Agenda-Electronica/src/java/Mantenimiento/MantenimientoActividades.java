/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenimiento;

import Persistencia.Actividades;
import Persistencia.Cronogramas;
import Persistencia.Fases;
import Persistencia.TiposActividades;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author eliseo.garciausam
 */
public class MantenimientoActividades {

    public int guardar(Actividades actividades) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        try {
            em.persist(actividades);
            em.getTransaction().commit();
            flag = 1;
            System.out.println("exito al guardar actividades,mantenimientoActividades");
        } catch (Exception ex) {
            //em.getTransaction().rollback();
            flag = 0;
            System.out.println("error al guardaractividades,ManteminientoActividades"+ex);
        } finally {
            em.close();
        }
        return flag;
    }
    
    public Actividades consultarid(int idActividad) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Actividades actividades = null;
        em.getTransaction().begin();
        try {
            actividades = em.find(Actividades.class, idActividad);
            em.getTransaction().commit();
            System.out.println("exito consultarid,mantenimientoActividad");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("error consultarid mantenimientoActividad");
        } finally {
            em.close();
        }
        return actividades;
    }

    public List<Actividades> consultar() {
        List<Actividades> listaA = null;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try{
            Query query=em.createQuery("SELECT a FROM Actividades a");
            em.getTransaction().commit();
            listaA=query.getResultList();
            System.out.println(listaA+"si");
            return listaA;
        }catch(Exception e){
            em.getTransaction().rollback();
            return null;
        }finally{
            em.close();
        }
    }
    
    public int Actualizar(Actividades actividades){
        EntityManager em=JpaUtil.getEntityManagerFactory().createEntityManager();
        Actividades acti=null;
        em.getTransaction().begin();
        int flag=0;
        try{
            acti=em.find(Actividades.class,actividades.getIdActividad());
            acti.setIdActividad(actividades.getIdActividad());
            acti.setNombreaActividad(actividades.getNombreaActividad());
            acti.setIdCronograma(actividades.getIdCronograma());
            acti.setIdTipoActividad(actividades.getIdTipoActividad());
            acti.setFechaActividad(actividades.getFechaActividad());
            acti.setIdFases(actividades.getIdFases());
            
            
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
    
    public int eliminar(Actividades actividades){
      EntityManager em=JpaUtil.getEntityManagerFactory().createEntityManager();
      Actividades act=null;
      em.getTransaction().begin();
      int flag=0;
      try{
          act=em.find(Actividades.class,actividades.getIdActividad());
          em.remove(act);
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
