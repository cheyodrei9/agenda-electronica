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

    /*metodo para guardar*/
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

    /*metodo para consultar por id*/
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

    /*metodo para consultar con lista*/
    public List<Cronogramas> consultar() {
        List<Cronogramas> listaCR = null;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT c FROM Cronogramas c");
            em.getTransaction().commit();
            listaCR = query.getResultList();
            System.out.println(listaCR + "si");
            return listaCR;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        } finally {
            em.close();
        }
    }

    /*metodo para actualizar*/
    public int Actualizar(Cronogramas cronogramas) {
        System.out.println("Entro en mantenimiento ");
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        List<Cronogramas> listaCR = null;
        Cronogramas crono = null;
        em.getTransaction().begin();
        int flag = 0;
        try {
            System.out.println("id" + cronogramas.getIdcronograma());
            crono = em.find(Cronogramas.class, cronogramas.getIdcronograma());
            crono.setIdcronograma(cronogramas.getIdcronograma());
            crono.setNombre(cronogramas.getNombre());
            crono.setDescripcion(cronogramas.getDescripcion());
            crono.setIdusuario(cronogramas.getIdusuario());
            em.getTransaction().commit();
            flag = 1;
            System.out.println("exitoso");
        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;
            System.out.println("Error en mantenimiento cronograma, Actualizar: " + e);
        } finally {
            em.close();
        }
        return flag;
    }

    /*metodo para elimnar*/
    public int eliminar(Cronogramas cronogramas) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Cronogramas cro = null;
        em.getTransaction().begin();
        int flag = 0;
        try {
            cro = em.find(Cronogramas.class, cronogramas.getIdcronograma());
            em.remove(cro);
            em.getTransaction().commit();
            flag = 1;
            System.out.println("exito al eliminar");
        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;
            System.out.println("error eliminar " + e);
        } finally {
            em.close();
        }
        return flag;
    }
}
