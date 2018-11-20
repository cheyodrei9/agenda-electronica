/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenimiento;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author eliseo.garciausam
 */
public class JpaUtil {
   private static final EntityManagerFactory emf;
    static{
        try{
           emf=Persistence.createEntityManagerFactory("Agenda-ElectronicaPU");
        }catch(Throwable ex){
            System.err.println("Creacion de sesion fallo"+ ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static EntityManagerFactory getEntityManagerFactory(){
        return emf;
    }
}  

