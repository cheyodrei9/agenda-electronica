/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeansCalendario;

import Mantenimiento.MantenimientoNotificaciones;
import Mantenimiento.MantenimientoUsusario;
import Persistencia.Notificaciones;
import Persistencia.Usuarios;
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
public class BeanNotificaciones {
    private List<Notificaciones> ListaNotificaciones = new ArrayList();
    private List<Usuarios> ListaUsuarios = new ArrayList();
    private MantenimientoNotificaciones MNot;
    private Notificaciones notificaciones;
    private String accion;

    public List<Notificaciones> getListaNotificaciones() {
        return ListaNotificaciones;
    }

    public void setListaNotificaciones(List<Notificaciones> ListaNotificaciones) {
        this.ListaNotificaciones = ListaNotificaciones;
    }

    public List<Usuarios> getListaUsuarios() {
        return ListaUsuarios;
    }

    public void setListaUsuarios(List<Usuarios> ListaUsuarios) {
        this.ListaUsuarios = ListaUsuarios;
    }

    public Notificaciones getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(Notificaciones notificaciones) {
        this.notificaciones = notificaciones;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public MantenimientoNotificaciones getMNot() {
        return MNot;
    }

    public void setMNot(MantenimientoNotificaciones MNot) {
        this.MNot = MNot;
    }
    
    
     @PostConstruct
    public void init(){
        notificaciones=new Notificaciones();
        notificaciones.setIdusuario(new Usuarios());
        MantenimientoNotificaciones not=new MantenimientoNotificaciones();
        MantenimientoUsusario usr = new MantenimientoUsusario();
        ListaNotificaciones=not.consultar();
        ListaUsuarios=usr.consultar();
    }
    
    public void LimpiarFormulario(){
        this.ListaNotificaciones=MNot.consultar();
        this.notificaciones=new Notificaciones();
        notificaciones.setIdusuario(new Usuarios());
        this.notificaciones.setIdnotificacion(0);
        accion="Registrar";
    }
    
    public void AccionFormulario(){
        if(accion.equals("Registrar")){
            MNot.guardar(this.notificaciones);
        }else if(accion.equals("Editar")){
            MNot.actualizar(this.notificaciones);
        }
        LimpiarFormulario();
    }
    
    public void guardar(){
        MantenimientoNotificaciones Mnot = new MantenimientoNotificaciones();
        Mnot.guardar(notificaciones);
        notificaciones=new Notificaciones();
    }
    
    public void borrar(Notificaciones notificaciones){
        MantenimientoNotificaciones MMMnot = new MantenimientoNotificaciones();
        MMMnot.eliminiar(notificaciones);
        this.ListaNotificaciones=this.MNot.consultar();
    }
    
    public void Editar(Notificaciones notificaciones){
        this.notificaciones=notificaciones;
        accion="Editar";
    }
    
    public void Actualizat(){
        MNot.actualizar(this.notificaciones);
    }
}
