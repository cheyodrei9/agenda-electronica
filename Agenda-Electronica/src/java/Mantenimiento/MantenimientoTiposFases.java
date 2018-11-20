/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenimiento;

import Persistencia.TiposFases;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gerson.ruizusam
 */
public class MantenimientoTiposFases {
        public int Guardar(TiposFases tiposFases) {

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

    public TiposFases consultarid(Integer idTipoFase) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        TiposFases tiposFases = null;
        em.getTransaction().begin();
        try {
            tiposFases = em.find(TiposFases.class, idTipoFase);
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

    public List<TiposFases> consultar() {
        List<TiposFases> listaTF = null;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT e FROM TiposFases e");

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

    public int Actualizar(TiposFases tiposFases) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        TiposFases tf = null;
        em.getTransaction().begin();
        int flag = 0;
        try {
            tf = em.find(TiposFases.class, tiposFases.getIdTipoFase());
            tf.setTipoFase(tiposFases.getTipoFase());
            
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

    public int eliminar(TiposFases tiposFases) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        TiposFases tf = null;
        em.getTransaction().begin();
        int flag = 0;
        try {
            tf = em.find(TiposFases.class, tiposFases.getIdTipoFase());
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

