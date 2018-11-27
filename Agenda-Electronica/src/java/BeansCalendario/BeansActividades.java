/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeansCalendario;

import Mantenimiento.MantenimientoActividades;
import Mantenimiento.MantenimientoCronogramas;
import Mantenimiento.MantenimientoFases;
import Mantenimiento.MantenimientoTiposActividades;
import Mantenimiento.MantenimientoUsusario;
import Persistencia.Actividades;
import Persistencia.Cronogramas;
import Persistencia.Fases;
import Persistencia.Tiposactividades;
import Persistencia.Usuarios;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author guillermo.bermudes
 */
@ManagedBean
@RequestScoped
public class BeansActividades {

    private Actividades act;
    private List<Actividades> lista = new ArrayList();
    private List<Tiposactividades> listTA = new ArrayList();
    private List<Cronogramas> listCr = new ArrayList();
    private List<Fases> listF = new ArrayList();
    private List<Usuarios> listU = new ArrayList();

    @PostConstruct
    public void inicio() {

        act = new Actividades();
        act.setIdcronograma(new Cronogramas());
        act.setIdtipoactividad(new Tiposactividades());
        act.setIdfase(new Fases());
        MantenimientoActividades actividades = new MantenimientoActividades();
        MantenimientoCronogramas mc = new MantenimientoCronogramas();
        MantenimientoTiposActividades mta = new MantenimientoTiposActividades();
        MantenimientoFases mf = new MantenimientoFases();
        MantenimientoUsusario mu = new MantenimientoUsusario();
        lista = actividades.consultar();
        listCr = mc.consultar();
        listF = mf.consultar();
        listTA = mta.consultar();
    }

    public List<Actividades> getLista() {
        return lista;
    }

    public void setLista(List<Actividades> lista) {
        this.lista = lista;
    }

    public Actividades getAct() {
        return act;
    }

    public void setAct(Actividades act) {
        this.act = act;
    }

    public List<Tiposactividades> getListTA() {
        return listTA;
    }

    public void setListTA(List<Tiposactividades> listTA) {
        this.listTA = listTA;
    }

    public List<Cronogramas> getListCr() {
        return listCr;
    }

    public void setListCr(List<Cronogramas> listCr) {
        this.listCr = listCr;
    }

    public List<Fases> getListF() {
        return listF;
    }

    public void setListF(List<Fases> listF) {
        this.listF = listF;
    }

    public List<Usuarios> getListU() {
        return listU;
    }

    public void setListU(List<Usuarios> listU) {
        this.listU = listU;
    }
    
    

    public void guardar() {
        System.out.println("este es el objeto lleno" + act);
        MantenimientoActividades actividades = new MantenimientoActividades();
        actividades.guardar(act);
    }

}
