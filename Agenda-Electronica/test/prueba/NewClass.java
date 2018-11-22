/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import Mantenimiento.MantenimientoActividades;
import Persistencia.Actividades;
import Persistencia.Cronogramas;
import Persistencia.Fases;
import Persistencia.TiposActividades;

/**
 *
 * @author eliseo.garciausam
 */
public class NewClass {
    public static void main(String[] args) {
        Actividades actividades = new Actividades();
        Cronogramas cronogramas = new Cronogramas();
        TiposActividades tipos = new TiposActividades();
        Fases fases = new Fases();
        MantenimientoActividades act = new MantenimientoActividades();
        
        actividades.setIdActividad(0);
        cronogramas.setIdCronograma(1);
        actividades.setIdCronograma(cronogramas);
        tipos.setIdTipoActividad(1);
        actividades.setIdTipoActividad(tipos);
        actividades.setNombreaActividad("hola");
        actividades.setFechaActividad();
        fases.setIdFase(1);
        actividades.setIdFases(fases);
        
        
        
    }
}
