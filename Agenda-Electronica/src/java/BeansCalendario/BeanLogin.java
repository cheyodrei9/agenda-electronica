/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeansCalendario;

import Mantenimiento.MantenimientoUsusario;
import Persistencia.Usuarios;
import java.io.IOException;
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

    private String username;
    private String user_id;
    private String password;
    private static int idusuario;
    private static int niveldemando;
    private int user_lvl;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public int getUser_lvl() {
        user_lvl = niveldemando;
        return user_lvl;
    }

    public void setUser_lvl(int user_lvl) {
        this.user_lvl = user_lvl;
    }

    /**
     * Creates a new instance of BeanLogin
     */
    public BeanLogin() {
    }

    public void loginAgenda() throws IOException {
        MantenimientoUsusario manUsuario = new MantenimientoUsusario();
        Usuarios usuario = manUsuario.iniciarSesion(username, password);
        FacesMessage message = null;
        boolean loggedIn = false;
        
        if (usuario != null) {
            idusuario = usuario.getIdusuario();
            user_id = usuario.getNombres();
            niveldemando = usuario.getNiveldemando();
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", usuario.getNombres());
            FacesContext.getCurrentInstance().getExternalContext().redirect("Calendario.xhtml");
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Credenciales invalidos");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
    }

    public void logoutAgenda() throws IOException {
        BeanLogin.idusuario = 0;
        BeanLogin.niveldemando = 0;
        FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
    }

    public void redirect(String redir) throws IOException {
        switch (redir) {
            case "TiposActividades":
                if (niveldemando == 1 || niveldemando == 2 || niveldemando == 3) {
                    if (niveldemando == 2 || niveldemando == 3) {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "No permitido", "La accion requiere permisos de administración superior");
                        PrimeFaces.current().dialog().showMessageDynamic(message);
                    } else {
                        FacesContext.getCurrentInstance().getExternalContext().redirect("TiposActividades.xhtml");
                    }
                } else {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
                }
                break;
        }
    }
}
