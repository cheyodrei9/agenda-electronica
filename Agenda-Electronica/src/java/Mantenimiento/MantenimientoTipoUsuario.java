/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenimiento;

import Persistencia.TiposUsuarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gerson.ruizusam
 */
public class MantenimientoTipoUsuario {
    
     public int Guardar(TiposUsuarios tiposUsuarios) {

        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        try {
            em.persist(tiposUsuarios);
            em.getTransaction().commit();
            flag = 1;
            System.out.println("El TiposUsuarios fue Guardado Exitosamente");
        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;
            System.out.println("Error Al Guardar El TiposUsuarios" + e);
        } finally {
            em.close();
        }
        return flag;
    }

    public TiposUsuarios consultarid(Integer idTipoUsuario) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        TiposUsuarios tiposUsuarios = null;
        em.getTransaction().begin();
        try {
            tiposUsuarios = em.find(TiposUsuarios.class, idTipoUsuario);
            em.getTransaction().commit();
            System.out.println("Su consulta por ID Fue exitosa");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error al consultar por ID " + e);
        } finally {
            em.close();
        }
        return tiposUsuarios;
    }

    public List<TiposUsuarios> consultar() {
        List<TiposUsuarios> listaTU = null;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT e FROM TiposUsuarios e");

            em.getTransaction().commit();
            listaTU = query.getResultList();
            return listaTU;

        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        } finally {
            em.close();
        }
    }

    public int Actualizar(TiposUsuarios tiposUsuarios) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        TiposUsuarios tu = null;
        em.getTransaction().begin();
        int flag = 0;
        try {
            tu = em.find(TiposUsuarios.class, tiposUsuarios.getIdTipoUsuario());
            tu.setTipo(tiposUsuarios.getTipo());
            
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

    public int eliminar(TiposUsuarios tiposUsuarios) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        TiposUsuarios tu = null;
        em.getTransaction().begin();
        int flag = 0;
        try {
            tu = em.find(TiposUsuarios.class, tiposUsuarios.getIdTipoUsuario());
            em.remove(tu);

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



