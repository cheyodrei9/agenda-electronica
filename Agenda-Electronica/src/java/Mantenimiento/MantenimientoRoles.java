/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenimiento;

import Persistencia.Roles;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author manuel.suarezusam
 */
public class MantenimientoRoles {
    
    public int guardar (Roles rol){
        
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        try {
            em.persist(rol);
            em.getTransaction().commit();
            flag = 1;
            System.out.println("El rol fue guardado exitosamente");
        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;
            System.out.println("Error al guardar el rol"+e);
        }finally{
            em.close();
        }
        return flag;
    }
    
    public Roles consultarId(Integer idRol){
    
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Roles rol = null;
        em.getTransaction().begin();
        try {
            rol = em.find(Roles.class, idRol);
            em.getTransaction().commit();
            System.out.println("La consulta por ID fue exitosa");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error al consultar por ID" + e);
        }finally{
            em.close();
        }
        return rol;
    }
    
    public List<Roles> consultar(){
        List<Roles> listaR = null;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT e FROM Roles e");
            
            em.getTransaction().commit();
            listaR = query.getResultList();
            return listaR;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }finally{
            em.close();
        }
    }
    
    public int actualizar(Roles rol){
        System.out.println("estoy en el mantenimiento");
        EntityManager em  = JpaUtil.getEntityManagerFactory().createEntityManager();
        Roles rols = null;
        em.getTransaction().begin();
        int flag = 0;
        try {
            rols = em.find(Roles.class, rol.getIdrol());
            rols.setRol(rol.getRol());
            rols.setIdtipousuario(rol.getIdtipousuario());
            em.getTransaction().commit();
            flag = 1;
            System.out.println("La actualizacion fue exitosa");
        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;
            System.out.println("Error al reaclizar la actualizacion" + e);
        }finally{
            em.close();
        }
        return flag;
    }
    
    public int eliminar (Roles rol){
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Roles rols = null;
        em.getTransaction().begin();
        int flag = 0;
        try {
            rols = em.find(Roles.class, rol.getIdrol());
            em.remove(rols);
            
            em.getTransaction().commit();
            flag = 1;
            System.out.println("Exito al eliminar el rol");
        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;
            System.out.println("Error al eliminar el rol" + e);
        }finally{
            em.close();
        }
        return flag; 
    }
}
