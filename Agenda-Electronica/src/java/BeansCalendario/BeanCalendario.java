/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeansCalendario;

import Mantenimiento.MantenimientoCalendarios;
import Mantenimiento.MantenimientoCronogramas;
import Persistencia.Calendarios;
import Persistencia.Cronogramas;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author gerson.ruizusam
 */
@ManagedBean
@RequestScoped
public class BeanCalendario {
    private List<Calendarios> listaCalendario = new ArrayList();
    private List<Cronogramas> listaCrono = new ArrayList();
    private MantenimientoCalendarios Mcalendario;
    private Calendarios calendario;
    private String accion;

    public List<Calendarios> getListaCalendario() {
        return listaCalendario;
    }

    public void setListaCalendario(List<Calendarios> listaCalendario) {
        this.listaCalendario = listaCalendario;
    }

    public List<Cronogramas> getListaCrono() {
        return listaCrono;
    }

    public void setListaCrono(List<Cronogramas> listaCrono) {
        this.listaCrono = listaCrono;
    }

    public MantenimientoCalendarios getMcalendario() {
        return Mcalendario;
    }

    public void setMcalendario(MantenimientoCalendarios Mcalendario) {
        this.Mcalendario = Mcalendario;
    }

    public Calendarios getCalendario() {
        return calendario;
    }

    public void setCalendariio(Calendarios calendario) {
        this.calendario = calendario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
     @PostConstruct
    public void init() {
        calendario = new Calendarios();
        calendario.setIdcronograma(new Cronogramas());
        MantenimientoCalendarios mc = new MantenimientoCalendarios();
         MantenimientoCronogramas MCrono = new MantenimientoCronogramas();
         listaCalendario= mc.consultar();
         listaCrono=MCrono.consultar();
    }
    private void LimpiarFormulario(){
        this.listaCalendario=Mcalendario.consultar();
        this.calendario=new Calendarios();
        calendario.setIdcronograma(new Cronogramas());
        this.calendario.getIdcalendario();
        accion="Registrar";
    }
    public void AccionFormulario(){
        if(accion.equals("Registrar")){
            Mcalendario.guardar(this.calendario);
        }else if(accion.equals("Editar")){
            Mcalendario.Actualizar(this.calendario);
        }
        LimpiarFormulario();
    }
    public void guardar(){
        MantenimientoCalendarios mc = new MantenimientoCalendarios();
        mc.guardar(calendario);
        calendario= new Calendarios();
    }
    public void Borrar(Calendarios calendarios){
        Mcalendario.eliminar(calendarios);
        this.listaCalendario=Mcalendario.consultar();
    }
    public void Editar(Calendarios calendario){
        this.calendario=calendario;
        accion="Editar";
    }
    private void Actualizar(){
        Mcalendario.Actualizar(this.calendario);
    }
    
}
