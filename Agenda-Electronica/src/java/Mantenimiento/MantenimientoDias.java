/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenimiento;

import Persistencia.Dias;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author eliseo.garciausam
 */
public class MantenimientoDias {

    public int guardar(Dias dias) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        try {
            em.persist(dias);
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

    public Dias consultarid(int idDia) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Dias dias = null;
        em.getTransaction().begin();
        try {
            dias = em.find(Dias.class, idDia);
            em.getTransaction().commit();
            System.out.println("exito consultarid");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("error consultarid");
        } finally {
            em.close();
        }
        return dias;
    }

    public List<Dias> consultar() {
        List<Dias> listaD = null;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT d FROM Dias d");
            em.getTransaction().commit();
            listaD = query.getResultList();
            System.out.println(listaD + "si");
            return listaD;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        } finally {
            em.close();
        }
    }

    public int Actualizar(Dias dias) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Dias di = null;
        em.getTransaction().begin();
        int flag = 0;
        System.out.println("Algo nose");
        em.merge(em.merge(dias));
        try {
            System.out.println("Id" + dias.getIddia());
            di = em.find(Dias.class, dias.getIddia());
            di.setIddia(dias.getIddia());
            di.setIdmes(dias.getIdmes());
            di.setNombredia(dias.getNombredia());
            em.getTransaction().commit();
            flag = 1;
            System.out.println("exitoso");
        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;
            System.out.println("error En Mantenimiento Dias,Actualizar" + e);
        } finally {
            em.close();
        }
        return flag;
    }

    public int eliminar(Dias dias) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Dias dia = null;
        em.getTransaction().begin();
        int flag = 0;
        try {
            dia = em.find(Dias.class, dias.getIddia());
            em.remove(dia);
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
