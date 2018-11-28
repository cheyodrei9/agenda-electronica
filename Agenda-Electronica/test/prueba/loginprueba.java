/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import BeansCalendario.BeanLogin;
import Mantenimiento.MantenimientoUsusario;
import Persistencia.Usuarios;

/**
 *
 * @author julio.benavidesusam
 */
public class loginprueba {
    public static void main(String[] args) {
        BeanLogin login = new BeanLogin();
        MantenimientoUsusario mantenimiento = new MantenimientoUsusario();
        Usuarios usuario = new Usuarios();
        
        usuario = mantenimiento.iniciarSesion("EST3697", "3697");
        
        if (usuario != null) {
            System.out.println("Usuario: "+usuario.toString());
        }else{
            System.out.println("Error, metodo Mantenimiento.iniciarSesion fallo");
        }
    }
}
