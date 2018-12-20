/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import BeansCalendario.BeanLogin;
import Mantenimiento.MantenimientoUsusario;
import Persistencia.Usuarios;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author julio.benavidesusam
 */
public class loginprueba {

    public static void main(String[] args) throws ParseException {
//        BeanLogin login = new BeanLogin();
//        MantenimientoUsusario mantenimiento = new MantenimientoUsusario();
//        Usuarios usuario = new Usuarios();
//        
//        usuario = mantenimiento.iniciarSesion("EST3697", "3697");
//        
//        if (usuario != null) {
//            System.out.println("Usuario: "+usuario.toString());
//        }else{
//            System.out.println("Error, metodo Mantenimiento.iniciarSesion fallo");
//        }
        DateFormat df = new SimpleDateFormat("dd-MMMM-yyyy", new Locale("es", "SV"));
        Date today = Calendar.getInstance().getTime();
//        System.out.println("sin convertir: "+today);
        String hoy = df.format(today);
        System.out.println("Hoy: '" + hoy + "'");
//        Date convertida = df.parse(hoy);
//        System.out.println("fecha convertida: " + convertida);

        Date diaEvento = new Date(hoy);

        System.out.println("fecha: " + diaEvento);
        System.exit(0);
    }
}
