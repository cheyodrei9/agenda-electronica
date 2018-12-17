/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenimiento;

import BeansCalendario.BeanLogin;
import Persistencia.Actividades;
import Persistencia.Cronogramas;
import Persistencia.Fases;
import Persistencia.Tiposactividades;
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
            System.out.println("error al guardaractividades,ManteminientoActividades" + ex);
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
        Query query;
        try {
            if (BeanLogin.getNiveldemando() == 3) {
                query = em.createQuery("SELECT a FROM Actividades a WHERE A.idusuario.idusuario ="+BeanLogin.getIdusuario());
            } else {
                query = em.createQuery("SELECT a FROM Actividades a");
            }
            em.getTransaction().commit();
            listaA = query.getResultList();
            System.out.println(listaA + "si");
            return listaA;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        } finally {
            em.close();
        }
    }

    public int Actualizar(Actividades actividades) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Actividades acti = null;
        em.getTransaction().begin();
        int flag = 0;
        try {
            acti = em.find(Actividades.class, actividades.getIdactividad());
            acti.setIdactividad(actividades.getIdactividad());
            acti.setNombreactividad(actividades.getNombreactividad());
            acti.setIdcronograma(actividades.getIdcronograma());
            acti.setIdtipoactividad(actividades.getIdtipoactividad());
            acti.setFechaactividad(actividades.getFechaactividad());
            acti.setIdfase(actividades.getIdfase());
            acti.setIdusuario(actividades.getIdusuario());

            em.getTransaction().commit();
            flag = 1;
            System.out.println("exitoso");
        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;
            System.out.println("error" + e);
        } finally {
            em.close();
        }
        return flag;
    }

    public int eliminar(Actividades actividades) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Actividades act = null;
        em.getTransaction().begin();
        int flag = 0;
        try {
            act = em.find(Actividades.class, actividades.getIdactividad());
            em.remove(act);
            em.getTransaction().commit();
            flag = 1;
            System.out.println("exito al eliminar");
        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;
        } finally {
            em.close();
        }
        return flag;
    }
}
