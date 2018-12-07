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
    private List<Cronogramas> ListaCrono=new ArrayList();
    private List<Usuarios> ListaUsuario=new ArrayList();
    private MantenimientoCronogramas Mcrono;
    private Cronogramas Crono;
    private String accion;

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
    private void intit(){
        Crono = new Cronogramas();
        Crono.setIdusuario(new Usuarios());
        MantenimientoCronogramas MCrono = new MantenimientoCronogramas();
        MantenimientoUsusario MU = new MantenimientoUsusario();
        ListaCrono = MCrono.consultar();
        ListaUsuario=MU.consultar();
    }
    private void LimpiarFormulario(){
        this.ListaCrono=Mcrono.consultar();
        this.Crono=new Cronogramas();
        Crono.setIdusuario(new Usuarios());
        this.Crono.setIdcronograma(0);
        accion="Registrar";
    }
    public void AccionFormulario(){
        if(accion.equals("Registrar")){
            Mcrono.guardar(this.Crono);
        }else if(accion.equals("Editar")){
            Mcrono.Actualizar(this.Crono);
        }
        LimpiarFormulario();
    }
    public void guardar(){
        MantenimientoCronogramas mcrono = new MantenimientoCronogramas();
        mcrono.guardar(Crono);
        Crono = new Cronogramas();
    }
    public void borrar(Cronogramas crono){
        Mcrono.eliminar(crono);
        this.ListaCrono=Mcrono.consultar();
    }
    public void Editar(Cronogramas crono){
        this.Crono= crono;
        accion="Editar";
    }
    private void Actualizar(){
        Mcrono.Actualizar(this.Crono);
    }
            
    
    
    
}
