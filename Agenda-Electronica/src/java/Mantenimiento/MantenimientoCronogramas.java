/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenimiento;

import Persistencia.Cronogramas;
import javax.persistence.EntityManager;

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
    
}
