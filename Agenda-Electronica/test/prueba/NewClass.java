/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import Mantenimiento.MantenimientoActividades;
import Mantenimiento.MantenimientoCalendarios;
import Mantenimiento.MantenimientoCronogramas;
import Mantenimiento.MantenimientoDias;
import Mantenimiento.MantenimientoFases;
import Mantenimiento.MantenimientoTiposActividades;
import Persistencia.Actividades;
import Persistencia.Calendarios;
import Persistencia.Cronogramas;
import Persistencia.Dias;
import Persistencia.Fases;
import Persistencia.Meses;
import Persistencia.Tiposactividades;
import Persistencia.Tiposfases;
import Persistencia.Usuarios;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eliseo.garciausam
 */
public class NewClass {
    public static void main(String[] args) {
       /* Actividades acti = new Actividades();
        Cronogramas cro = new Cronogramas();
        Tiposactividades tip = new Tiposactividades();
        Fases fa = new Fases();
        MantenimientoActividades act = new MantenimientoActividades();
        SimpleDateFormat formato = new SimpleDateFormat("YYYY-mm-dd");
        Date fecha=null;
        try {
            fecha = formato.parse("2018-01-01");
        } catch (ParseException ex) {
            System.out.println("ERROR AL PARSEAR LA FECHA. "+ex);
        }
        
        acti.setIdactividad(0);
        cro.setIdcronograma(1);
        acti.setIdcronograma(cro);
        tip.setIdtipoactividad(1);
        acti.setIdtipoactividad(tip);
        acti.setNombreactividad("administrativa");
        acti.setFechaactividad(fecha);
        fa.setIdfase(1);
        acti.setIdfase(fa);
        
        
        
        
        
        /*if (act.guardar(actividades)==1) {
            System.out.println("Guardado");
        }else{
            System.out.println("No se pudo guardar");
        }*/
        
        MantenimientoTiposActividades act = new MantenimientoTiposActividades();
        Tiposactividades tipos = new Tiposactividades();
        tipos.setIdtipoactividad(0);
        tipos.setTipoactividad("gerencial");
        
        /*MantenimientoCalendarios cale = new MantenimientoCalendarios();
        Calendarios ca = new Calendarios();
        Cronogramas cro = new Cronogramas();
        
        ca.setIdCalendario(0);
        cro.setIdCronograma(1);
        ca.setIdCronograma(cro);
        ca.setAño(2019);*/
        
        /*MantenimientoCronogramas crono = new MantenimientoCronogramas();
        Cronogramas cro = new Cronogramas();
        Usuarios usu = new Usuarios();
        
        cro.setIdCronograma(0);
        cro.setNombre("oficina");
        cro.setDescripcion("solo oficina");
        usu.setIdUsuario(1);
        cro.setIdUsuario(usu);*/
        
        /*MantenimientoDias dia = new MantenimientoDias();
        Dias di = new Dias();
        Meses me = new Meses();
        
        di.setIdDia(0);
        me.setIdMes(1);
        di.setIdMes(me);
        di.setNombreDia("jueves");*/
        
        /*MantenimientoFases fas = new MantenimientoFases();
        Fases fa = new Fases();
        TiposFases tipos = new TiposFases();
        SimpleDateFormat formato = new SimpleDateFormat("YYYY-mm-dd");
        Date fecha=null;
        try {
            fecha = formato.parse("2018-01-24");
        } catch (ParseException ex) {
            System.out.println("ERROR AL PARSEAR LA FECHA. "+ex);
        }
        
        fa.setIdFase(0);
        tipos.setIdTipoFase(1);
        fa.setIdTipoFase(tipos);
        fa.setEstado("activo");
        fa.setFecha(fecha);*/
        
        
        System.out.println(act.Guardar(tipos));       
        
    }
}
