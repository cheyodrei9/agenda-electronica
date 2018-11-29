/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenimiento;

import Persistencia.Usuarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author gerson.ruizusam
 */
public class MantenimientoUsusario {

public void insertar(Usuarios usuario){
        EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(em.merge(usuario));
        em.getTransaction().commit();
}

    public Usuarios consultarid(Integer idUsuario) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Usuarios usuario = null;
        em.getTransaction().begin();
        try {
            usuario = em.find(Usuarios.class, idUsuario);
            em.getTransaction().commit();
            System.out.println("Su consulta por ID Fue exitosa");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error al consultar por ID " + e);
        } finally {
            em.close();
        }
        return usuario;
    }

    public List<Usuarios> consultar() {
        List<Usuarios> listaU = null;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT e FROM Usuarios e");

            em.getTransaction().commit();
            listaU = query.getResultList();
            return listaU;

        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        } finally {
            em.close();
        }
    }

    public int Actualizar(Usuarios usuarios) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Usuarios us = null;
        em.getTransaction().begin();
        int flag = 0;
        try {
            us = em.find(Usuarios.class, usuarios.getIdusuario());
            us.setIdtipousuario(usuarios.getIdtipousuario());
            us.setNombres(usuarios.getNombres());
            us.setApellidos(usuarios.getApellidos());
            us.setGenero(usuarios.getGenero());
            us.setTelefono(usuarios.getTelefono());
            us.setCorreo(usuarios.getCorreo());
            us.setContra(usuarios.getContra());
            us.setNiveldemando(usuarios.getNiveldemando());

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

    public int eliminar(Usuarios usuarios) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Usuarios us = null;
        em.getTransaction().begin();
        int flag = 0;
        try {
            us = em.find(Usuarios.class, usuarios.getIdusuario());
            em.remove(us);

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
    
    public Usuarios iniciarSesion(String user, String password){
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Usuarios usuario = new Usuarios();
        int id_usuario = 0;
        try {
            em.getTransaction().begin();
            id_usuario = Integer.parseInt(em.createNativeQuery("select idusuario from usuarios where correo = '"+user+"' and contra = '"+password+"';").getSingleResult().toString());
            em.getTransaction().commit();
            usuario = this.consultarid(id_usuario);
        } catch (NumberFormatException e) {
            em.getTransaction().rollback();
            usuario = null;
            System.out.println("Error: "+e);
        }finally{
            em.close();
        }
        return usuario;
    }
}
