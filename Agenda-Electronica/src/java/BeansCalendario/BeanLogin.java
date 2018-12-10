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

    private String user;
    private String password;
    private boolean show = false;
    private static int idusuario;
    private static int niveldemando;

    public BeanLogin(boolean show) {
        this.show = show;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
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

    public void loginAgenda() throws IOException {
        MantenimientoUsusario manUsuario = new MantenimientoUsusario();
        Usuarios usuario = manUsuario.iniciarSesion(user, password);
        FacesMessage message = null;
        boolean loggedIn = false;
        String page = null;

        if (usuario != null) {
            idusuario = usuario.getIdusuario();
            niveldemando = usuario.getNiveldemando();
            show = false;
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", usuario.getNombres());
            page = "Calendario.xhtml";
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Usuario o contraseña incorrecta");
            page = "Login.xhtml";
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("loggedin", loggedIn);
        FacesContext.getCurrentInstance().getExternalContext().redirect(page);
    }

    public void logoutAgenda() throws IOException {
        BeanLogin.idusuario = 0;
        BeanLogin.niveldemando = 0;
        FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
    }

    public void redirect(String redir) throws IOException {
        switch (redir) {
            case "":
                if (niveldemando == 1 || niveldemando == 2 || niveldemando == 3) {
                    if (niveldemando == 3) {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "No permitido", "La accion requiere permisos de administración");
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
