package BeansCalendario;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Mantenimiento.MantenimientoRoles;
import Mantenimiento.MantenimientoTipoUsuario;
import Persistencia.Roles;
import Persistencia.Tiposusuarios;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
/**
 *
 * @author fernando.medranousam
 */
@ManagedBean
@RequestScoped
public class BeanRoles {
    private List<Roles> listaRoles = new ArrayList();
    private List<Tiposusuarios> listaUser = new ArrayList();
    private MantenimientoRoles manr;
    private Roles roles;
    private String action;
    String adver = "";
    
    
    public List<Roles> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(List<Roles> listaRoles) {
        this.listaRoles = listaRoles;
    }

    public List<Tiposusuarios> getListaUser() {
        return listaUser;
    }

    public void setListaUser(List<Tiposusuarios> listaUser) {
        this.listaUser = listaUser;
    }

    public MantenimientoRoles getManr() {
        return manr;
    }

    public void setManr(MantenimientoRoles manr) {
        this.manr = manr;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    @PostConstruct
    public void init(){
        roles = new Roles();
        roles.setIdtipousuario(new Tiposusuarios());
        MantenimientoRoles rol = new MantenimientoRoles();
        MantenimientoTipoUsuario user = new MantenimientoTipoUsuario();
        listaRoles = rol.consultar();
        listaUser = user.consultar();
    }
    
    public void LimpiarFormulario(){
        this.listaRoles = manr.consultar();
        this.roles = new Roles();
        roles.setIdtipousuario(new Tiposusuarios());
        this.roles.setIdrol(0);
        action = "Registrar";
    }
    
    public void AccionFormulario(){
        if(action.equals("Registrar")){
            manr.guardar(this.roles);
        } else {
            manr.actualizar(this.roles);
        }
        LimpiarFormulario();
    }
    
    public void guardar(){
        MantenimientoRoles rol = new MantenimientoRoles();
        rol.guardar(roles);
        adver = "exito al guardar";
        System.out.println("bean" +adver);
    }
    
    public void eliminar (Roles roles){
        MantenimientoRoles rol = new MantenimientoRoles();
        rol.eliminar(roles);
        listaRoles = rol.consultar();
        if(rol.eliminar(roles)== 1){
            adver = "Exito en la eliminacion";
        } else {
            adver = "error en la eliminacion";
        }
        System.out.println("bean" +adver);
    }
    
    public void buscarId (Roles roles){
        MantenimientoRoles rol = new MantenimientoRoles();
        roles = rol.consultarId(roles.getIdrol());
        
        if(roles !=null){
            this.roles = roles;
            adver = "exito en la busqueda";
        } else {
            adver = "error en la busqueda";
        }
        System.out.println("bean" +adver);
    }
    
    public void modificar(){
        MantenimientoRoles rol = new MantenimientoRoles ();
        System.out.println("rol "+roles.getIdrol());
        rol.actualizar(roles);
    }
    
}
