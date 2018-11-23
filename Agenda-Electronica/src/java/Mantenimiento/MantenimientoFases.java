/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenimiento;

import Persistencia.Fases;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gerson.ruizusam
 */
public class MantenimientoFases {
     public int Guardar(Fases fases) {

        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        try {
            em.persist(fases);
            em.getTransaction().commit();
            flag = 1;
            System.out.println("Las Fases fueron Guardadas Exitosamente");
        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;
            System.out.println("Error Al Guardar las Fases" + e);
        } finally {
            em.close();
        }
        return flag;
    }

    public Fases consultarid(Integer idFase) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Fases fases = null;
        em.getTransaction().begin();
        try {
            fases = em.find(Fases.class, idFase);
            em.getTransaction().commit();
            System.out.println("Su consulta por ID Fue exitosa");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error al consultar por ID " + e);
        } finally {
            em.close();
        }
        return fases;
    }

    public List<Fases> consultar() {
        List<Fases> listaF = null;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT e FROM Fases e");

            em.getTransaction().commit();
            listaF = query.getResultList();
            return listaF;

        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        } finally {
            em.close();
        }
    }

    public int Actualizar(Fases fases) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Fases Fa = null;
        em.getTransaction().begin();
        int flag = 0;
        try {
            
            Fa = em.find(Fases.class,fases.getIdfase());
            Fa.setEstado(fases.getEstado());
            Fa.setFecha(fases.getFecha());
            Fa.setIdtipofase(fases.getIdtipofase());
            
            em.getTransaction().commit();
            flag = 1;
            System.out.println("EXITOSO");
        } catch (Exception e) {

            em.getTransaction().rollback();
            flag = 0;
            System.out.println("ERROR. " + e);
        } finally {
            em.close();
        }
        return flag;
    }

    public int eliminar(Fases fases) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Fases Fa = null;
        em.getTransaction().begin();
        int flag = 0;
        try {
            Fa= em.find(Fases.class, fases.getIdfase());
            em.remove(Fa);

            em.getTransaction().commit();
            flag = 1;
            System.out.println("Exito al Eliminar Su Registro");
        } catch (Exception e) {

            em.getTransaction().rollback();
            flag = 0;
            System.out.println("Error al Borrar" + e);
        } finally {
            em.close();
        }
        return flag;
    }
}




