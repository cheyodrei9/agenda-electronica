/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenimiento;

import Persistencia.Tiposactividades;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author manuel.suarezusam
 */
public class MantenimientoTiposActividades {

    public int Guardar(Tiposactividades tActividad) {

        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        try {
            em.persist(tActividad);
            em.getTransaction().commit();
            flag = 1;
            System.out.println("El tipo de actividad fue Guardado Exitosamente");
        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;
            System.out.println("Error Al Guardar El tipo de actividad" + e);
        } finally {
            em.close();
        }
        return flag;
    }

    public Tiposactividades consultarid(Integer idTipoActividad) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Tiposactividades tipos = null;
        em.getTransaction().begin();
        try {
            tipos = em.find(Tiposactividades.class, idTipoActividad);
            em.getTransaction().commit();
            System.out.println("Su consulta por ID Fue exitosa");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error al consultar por ID " + e);
        } finally {
            em.close();
        }
        return tipos;
    }

    public List<Tiposactividades> consultar() {
        List<Tiposactividades> listaTA = null;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT e FROM TiposActividades e");

            em.getTransaction().commit();
            listaTA = query.getResultList();
            return listaTA;

        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        } finally {
            em.close();
        }
    }

    public int Actualizar(Tiposactividades tiposA) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Tiposactividades ta = null;
        em.getTransaction().begin();
        int flag = 0;
        try {
            ta = em.find(Tiposactividades.class, tiposA.getIdtipoactividad());
            ta.setTipoactividad(tiposA.getTipoactividad());

            em.getTransaction().commit();
            flag = 1;
            System.out.println("Actualizacion Exitosa");
        } catch (Exception e) {

            em.getTransaction().rollback();
            flag = 0;
            System.out.println("Error al actualizar. " + e);
        } finally {
            em.close();
        }
        return flag;
    }

    public int eliminar(Tiposactividades tiposAc) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Tiposactividades ta = null;
        em.getTransaction().begin();
        int flag = 0;
        try {
            ta = em.find(Tiposactividades.class, tiposAc.getIdtipoactividad());
            em.remove(ta);

            em.getTransaction().commit();
            flag = 1;
            System.out.println("Exito al Eliminar Su Registro");
        } catch (Exception e) {

            em.getTransaction().rollback();
            flag = 0;
            System.out.println("Error al eliminar el tipo de actividad" + e);
        } finally {
            em.close();
        }
        return flag;
    }
}
