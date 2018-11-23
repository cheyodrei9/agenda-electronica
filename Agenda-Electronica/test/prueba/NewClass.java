/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import Mantenimiento.MantenimientoActividades;
import Mantenimiento.MantenimientoTiposActividades;
import Persistencia.Actividades;
import Persistencia.Cronogramas;
import Persistencia.Fases;
import Persistencia.TiposActividades;
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
        /*Actividades actividades = new Actividades();
        Cronogramas cronogramas = new Cronogramas();
        TiposActividades tipos = new TiposActividades();
        Fases fases = new Fases();
        MantenimientoActividades act = new MantenimientoActividades();
        SimpleDateFormat formato = new SimpleDateFormat("YYYY-mm-dd");
        Date fecha=null;
        try {
            fecha = formato.parse("2018-01-24");
        } catch (ParseException ex) {
            System.out.println("ERROR AL PARSEAR LA FECHA. "+ex);
        }
        
        actividades.setIdActividad(0);
        cronogramas.setIdCronograma(1);
        actividades.setIdCronograma(cronogramas);
        tipos.setIdTipoActividad(1);
        actividades.setIdTipoActividad(tipos);
        actividades.setNombreaActividad("hola");
        actividades.setFechaActividad(fecha);
        fases.setIdFase(1);
        actividades.setIdFases(fases);
        
        /*if (act.guardar(actividades)==1) {
            System.out.println("Guardado");
        }else{
            System.out.println("No se pudo guardar");
        }*/
        
        MantenimientoTiposActividades act = new MantenimientoTiposActividades();
        TiposActividades tipos = new TiposActividades();
        tipos.setIdTipoActividad(0);
        tipos.setTipoActividad("gerencial");
        
        System.out.println(act.Guardar(tipos));       
        
    }
}
