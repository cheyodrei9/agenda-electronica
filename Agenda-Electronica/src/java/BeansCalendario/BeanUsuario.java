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
    }
    public void LimpiarFormulario(){
        this.listaU = Musuario.consultar();
        this.usuario=new Usuarios();
        usuario.setIdtipousuario(new Tiposusuarios());
        this.usuario.setIdusuario(0);
        accion ="Registrar";
    }

    public void guardar() {
        System.out.println("este es el objeto lleno" + usuario);
        MantenimientoUsusario Us = new MantenimientoUsusario();
        Us.insertar(usuario);
        usuario= new Usuarios();
    }

}
