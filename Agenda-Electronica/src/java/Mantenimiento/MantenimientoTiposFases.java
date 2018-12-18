/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenimiento;

import Persistencia.Tiposfases;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gerson.ruizusam
 */
public class MantenimientoTiposFases {

    public int Guardar(Tiposfases tiposFases) {

        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        try {
            em.persist(tiposFases);
            em.getTransaction().commit();
            flag = 1;
            System.out.println("El TiposFases fue Guardado Exitosamente");
        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;
            System.out.println("Error Al Guardar El TiposFases" + e);
        } finally {
            em.close();
        }
        return flag;
    }

    public Tiposfases consultarid(Integer idTipoFase) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Tiposfases tiposFases = null;
        em.getTransaction().begin();
        try {
            tiposFases = em.find(Tiposfases.class, idTipoFase);
            em.getTransaction().commit();
            System.out.println("Su consulta por ID Fue exitosa");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error al consultar por ID " + e);
        } finally {
            em.close();
        }
        return tiposFases;
    }

    public List<Tiposfases> consultar() {
        List<Tiposfases> listaTF = null;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT e FROM Tiposfases e");

            em.getTransaction().commit();
            listaTF = query.getResultList();
            return listaTF;

        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        } finally {
            em.close();
        }
    }

    public int Actualizar(Tiposfases tiposFases) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Tiposfases tf = null;
        em.getTransaction().begin();
        int flag = 0;
        try {
            tf = em.find(Tiposfases.class, tiposFases.getIdtipofase());
            tf.setIdtipofase(tiposFases.getIdtipofase());
            tf.setTipofase(tiposFases.getTipofase());

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

    public int eliminar(Tiposfases tiposFases) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Tiposfases tf = null;
        em.getTransaction().begin();
        int flag = 0;
        try {
            tf = em.find(Tiposfases.class, tiposFases.getIdtipofase());
            em.remove(tf);

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
