/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeansCalendario;

import Mantenimiento.MantenimientoCronogramas;
import Mantenimiento.MantenimientoUsusario;
import Persistencia.Cronogramas;
import Persistencia.Usuarios;
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
public class BeanCronograma {

    private List<Cronogramas> ListaCrono = new ArrayList();
    private List<Usuarios> ListaUsuario = new ArrayList();
    private MantenimientoCronogramas Mcrono;
    private Cronogramas Crono;
    private String accion;
    private int idchrono;

    public List<Cronogramas> getListaCrono() {
        return ListaCrono;
    }

    public void setListaCrono(List<Cronogramas> ListaCrono) {
        this.ListaCrono = ListaCrono;
    }

    public List<Usuarios> getListaUsuario() {
        return ListaUsuario;
    }

    public void setListaUsuario(List<Usuarios> ListaUsuario) {
        this.ListaUsuario = ListaUsuario;
    }

    public MantenimientoCronogramas getMcrono() {
        return Mcrono;
    }

    public void setMcrono(MantenimientoCronogramas Mcrono) {
        this.Mcrono = Mcrono;
    }

    public Cronogramas getCrono() {
        return Crono;
    }

    public void setCrono(Cronogramas Crono) {
        this.Crono = Crono;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    @PostConstruct
    private void intit() {
        Crono = new Cronogramas();
        Crono.setIdusuario(new Usuarios());
        MantenimientoCronogramas MCrono = new MantenimientoCronogramas();
        MantenimientoUsusario MU = new MantenimientoUsusario();
        ListaCrono = MCrono.consultar();
        ListaUsuario = MU.consultar();
    }

    private void LimpiarFormulario() {
        this.ListaCrono = Mcrono.consultar();
        this.Crono = new Cronogramas();
        Crono.setIdusuario(new Usuarios());
        this.Crono.setIdcronograma(0);
        accion = "Registrar";
    }

    public void AccionFormulario() {
        if (accion.equals("Registrar")) {
            Mcrono.guardar(this.Crono);
        } else if (accion.equals("Editar")) {
            Mcrono.Actualizar(this.Crono);
        }
        LimpiarFormulario();
    }

    public void guardar() {
        MantenimientoCronogramas mcrono = new MantenimientoCronogramas();
        mcrono.guardar(Crono);
        Crono = new Cronogramas();
        this.ListaCrono = Mcrono.consultar();
    }

    public void eliminar(Cronogramas crono) {
        MantenimientoCronogramas mCrono = new MantenimientoCronogramas();
        mCrono.eliminar(crono);
        ListaCrono = mCrono.consultar();
        String advertencia = "";

        if (mCrono.eliminar(crono) == 1) {
            advertencia = "Se ha eliminado correctamente";
        } else {
            advertencia = "No se ha podido eliminar";

        }
    }

    public void modificar(Cronogramas crono) {
        MantenimientoCronogramas mCrono = new MantenimientoCronogramas();
        crono = mCrono.consultarid(crono.getIdcronograma());
        System.out.println("act" + Crono.getIdcronograma());
        String advertencia = "";
        if (crono != null) {
            this.Crono = crono;
            advertencia = "Datos Consultados correctamente";
        } else {
            advertencia = "Consulta no realizada";
        }
    }

    public void actualizar() {
        MantenimientoCronogramas mCrono = new MantenimientoCronogramas();
        System.out.println("act" + Crono.getIdcronograma());
        mCrono.Actualizar(Crono);
        
//private void Actualizar(){
//    Mcrono.Actualizar(this.Crono);
//}    
    }
}
