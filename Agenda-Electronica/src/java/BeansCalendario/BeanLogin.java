/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeansCalendario;

import Mantenimiento.MantenimientoUsusario;
import Persistencia.Usuarios;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author julio.benavidesusam
 */
@ManagedBean
@RequestScoped
public class BeanLogin {
    
    private String user;
    private String password;
    private static int idusuario;
    private static int niveldemando;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static int getIdusuario() {
        return idusuario;
    }

    public static int getNiveldemando() {
        return niveldemando;
    }
    
    /**
     * Creates a new instance of BeanLogin
     */
    public BeanLogin() {
    }
    
    public String loginAgenda(){
        MantenimientoUsusario manUsuario = new MantenimientoUsusario();
        Usuarios usuario = manUsuario.iniciarSesion(user, password);
        FacesMessage message = null;
        boolean loggedIn;
        if (usuario != null) {
            idusuario = usuario.getIdusuario();
            niveldemando = usuario.getNiveldemando();
            loggedIn = true;
            
            
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", usuario.getNombres());
            return"Calendario.xhtml";
        }else{
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Usuario o contraseña incorrecta");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("loggedin", loggedIn);
    return null; 
    }
}
