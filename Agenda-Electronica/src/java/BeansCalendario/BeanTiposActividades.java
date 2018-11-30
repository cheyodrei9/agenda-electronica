/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeansCalendario;

import Mantenimiento.MantenimientoTiposActividades;
import Persistencia.Tiposactividades;
import Persistencia.Tiposusuarios;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author eliseo.garciausam
 */
@ManagedBean
@RequestScoped
public class BeanTiposActividades {
    private Tiposactividades tip;
    private List<Tiposactividades> listTA = new ArrayList();
    private Tiposusuarios tipos;
    private String accion;
    
    @PostConstruct
    public void inicio() {
       tip = new Tiposactividades(); 
       MantenimientoTiposActividades mta = new MantenimientoTiposActividades();
       listTA= mta.consultar();
    }

    public Tiposusuarios getTipos() {
        return tipos;
    }

    public void setTipos(Tiposusuarios tipos) {
        this.tipos = tipos;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    

    public Tiposactividades getTip() {
        return tip;
    }

    public void setTip(Tiposactividades tip) {
        this.tip = tip;
    }

    public List<Tiposactividades> getListTA() {
        return listTA;
    }

    public void setListTA(List<Tiposactividades> listTA) {
        this.listTA = listTA;
    }
    
    public void guardar(){
        System.out.println("este es el objeto lleno"+tip);
        MantenimientoTiposActividades tipos = new MantenimientoTiposActividades();
        tipos.Guardar(tip);
    }
}
