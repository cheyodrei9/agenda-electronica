/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeansCalendario;

import Mantenimiento.MantenimientoActividades;
import Mantenimiento.MantenimientoNotificaciones;
import Mantenimiento.MantenimientoUsusario;
import Persistencia.Actividades;
import Persistencia.Notificaciones;

import Persistencia.Usuarios;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import static org.primefaces.component.contextmenu.ContextMenu.PropertyKeys.event;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author eliseo.garciausam
 */
@ManagedBean
@RequestScoped
public class BeanNotificaciones {

    private List<Notificaciones> ListaNotificaciones = new ArrayList();
    private List<Usuarios> ListaUsuarios = new ArrayList();
    private List<Actividades> ListaAct = new ArrayList();
    private MantenimientoNotificaciones MNot;
    private Notificaciones notificaciones;
    private String accion;
    private String claseIcono;

    public String getClaseIcono() {
        return claseIcono;
    }

    public void setClaseIcono(String claseIcono) {
        this.claseIcono = claseIcono;
    }
    
    

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
    public void init() {
        notificaciones = new Notificaciones();
        notificaciones.setIdusuario(new Usuarios());
        notificaciones.setIdactividad(new Actividades());
        MantenimientoNotificaciones not = new MantenimientoNotificaciones();
        MantenimientoUsusario usr = new MantenimientoUsusario();
        MantenimientoActividades Mact = new MantenimientoActividades();
        ListaNotificaciones = not.consultar3();
        ListaUsuarios = usr.consultar();
        ListaAct = Mact.consultar();
    }

    public void LimpiarFormulario() {
        this.ListaNotificaciones = MNot.consultar();
        this.notificaciones = new Notificaciones();
        notificaciones.setIdusuario(new Usuarios());
        notificaciones.setIdactividad(new Actividades());
        this.notificaciones.setIdnotificacion(0);
        accion = "Registrar";
    }

    public void AccionFormulario() {
        if (accion.equals("Registrar")) {
            MNot.guardar(this.notificaciones);
        } else if (accion.equals("Editar")) {
            MNot.Actualizar(this.notificaciones);
        }
        LimpiarFormulario();
    }

    public void guardar() {
        MantenimientoNotificaciones Mnot = new MantenimientoNotificaciones();
        Mnot.guardar(notificaciones);
        notificaciones = new Notificaciones();
        this.ListaNotificaciones = MNot.consultar();
    }

    public void eliminar(Notificaciones notificaciones) {
        MantenimientoNotificaciones MMMnot = new MantenimientoNotificaciones();
        MMMnot.eliminiar(notificaciones);
        ListaNotificaciones = MMMnot.consultar();
        String advertencia = "";

        if (MMMnot.eliminiar(notificaciones) == 1) {
            advertencia = "se ha eliminado correctamente";
        } else {
            advertencia = "no se ha podido eliminar";
        }
    }

    public void modificar(Notificaciones notificaciones) {
        MantenimientoNotificaciones MMMnot = new MantenimientoNotificaciones();
        notificaciones = MMMnot.consultarId(notificaciones.getIdnotificacion());
        String advertencia = "";
        if (notificaciones != null) {
            this.notificaciones = notificaciones;
            advertencia = "datos consultados corectamente";
        } else {
            advertencia = "consulta no realizada";
        }
    }

    public void Actualizar() {
        System.out.println("act" + notificaciones.getIdnotificacion());
        MantenimientoNotificaciones MMMnot = new MantenimientoNotificaciones();
        System.out.println("act" + notificaciones.getIdnotificacion());
        MMMnot.Actualizar(notificaciones);

    }

    public void consultar() {
        MantenimientoNotificaciones mn = new MantenimientoNotificaciones();
        String respuesta = mn.consultar2();
        System.out.println("esta haciendo la consulta");

    }

    public void execute() {
        MantenimientoNotificaciones mn = new MantenimientoNotificaciones();
        if (mn.consultar2().equals("SI")) {
            this.claseIcono="fa-globe";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Hay una nueva actividad", "por favor marcarla como leida"));
        }else{
            this.claseIcono="fa-pencil";
        }
        System.out.println("desde el bean: "+this.claseIcono);
//        System.out.println("esta haciendo la consulta");

    }

//    public void Notificaciones(Notificaciones notificaciones){
//        MantenimientoNotificaciones mn = new MantenimientoNotificaciones();
//        notificaciones.setEstadoNotificacion("visto");
//        mn.Actualizar(notificaciones);
//    }
    
//    public void consultar3() {
//        MantenimientoNotificaciones mn = new MantenimientoNotificaciones();
//        ListaNotificaciones = mn.consultar3();
//        System.out.println("esta haciendo la consulta en el bean");
//    }
//    
    public void actualizar2(Notificaciones n) {
        MantenimientoNotificaciones MMMnot = new MantenimientoNotificaciones();
        //Notificaciones not = MMMnot.consultarId(n.getIdnotificacion());
        //not.setEstadoNotificacion("visto");
        System.out.println("este es el id en actualizar2 "+n.getIdnotificacion());
        MMMnot.Actualizar2(n);
//        ListaNotificaciones = MMMnot.consultar3();
//        notificaciones = new Notificaciones();
    }
    
}