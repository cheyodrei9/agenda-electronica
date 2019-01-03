/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeansCalendario;

import Mantenimiento.MantenimientoTipoUsuario;
import Mantenimiento.MantenimientoUsusario;
import Persistencia.Tiposusuarios;
import Persistencia.Usuarios;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author gerson.ruizusam
 */
@ManagedBean
@RequestScoped
public class BeanUsuario {

    private List<Usuarios> listaU = new ArrayList();
    private List<Tiposusuarios> listaTu = new ArrayList();
    private MantenimientoUsusario Musuario;
    private Usuarios usuario;
    private String accion;
    String adver = "";
    
    public List<Usuarios> getListaU() {
        return listaU;
    }

    public void setListaU(List<Usuarios> listaU) {
        this.listaU = listaU;
    }

    public MantenimientoUsusario getMusuario() {
        return Musuario;
    }

    public void setMusuario(MantenimientoUsusario Musuario) {
        this.Musuario = Musuario;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public List<Tiposusuarios> getListaTu() {
        return listaTu;
    }

    public void setListaTu(List<Tiposusuarios> listaTu) {
        this.listaTu = listaTu;
    }

    @PostConstruct
    public void init() {
        usuario = new Usuarios();
        usuario.setIdtipousuario(new Tiposusuarios());
        MantenimientoUsusario mu = new MantenimientoUsusario();
        MantenimientoTipoUsuario Mtu = new MantenimientoTipoUsuario();
        listaU = mu.consultar();
        listaTu = Mtu.consultar();
        System.out.println(listaU);
    }
    public void LimpiarFormulario(){
        this.listaU = Musuario.consultar();
        this.usuario=new Usuarios();
        usuario.setIdtipousuario(new Tiposusuarios());
        this.usuario.setIdusuario(0);
        accion ="Registrar";
    }

    public void AccionFormulario(){
        if(accion.equals("Registrar")){
            Musuario.insertar(this.usuario);
        } else if(accion.equals("Editar")){
            Musuario.Actualizar(this.usuario);
        }
        LimpiarFormulario();
    }
    
    
    public void guardar() {
        System.out.println("este es el objeto lleno" + usuario);
        MantenimientoUsusario Us = new MantenimientoUsusario();
        Us.insertar(usuario);
    }

    public void eliminar (Usuarios usuario){
        Musuario.eliminar(usuario);
        listaU = Musuario.consultar();
        
        if(Musuario.eliminar(usuario)==1){
            adver = "se elimino con exito";
        } else {
            adver = "ocurrio un error";
        }
        System.out.println(adver);
    }
    
    public void modificar (Usuarios user){
        MantenimientoUsusario Musuario = new MantenimientoUsusario();
        user = Musuario.consultarid(user.getIdusuario());
        if(user != null){
            this.usuario = user;
            adver = "consulta exitosa";
        } else {
            adver = "error consulta";
        }
        System.out.println(adver);
    }
    
    public void actualizar (){
        MantenimientoUsusario Musuario = new MantenimientoUsusario();
        System.out.println("usuario" +usuario.getIdusuario());
        String adv="";
        if (Musuario.Actualizar(usuario)==1) {
            listaU=Musuario.consultar();
            adv="Se ha actualizado Correctamente";
        } else {
            adv="Error al actualizar";
        }
        FacesMessage msg = new FacesMessage(adv);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
}
