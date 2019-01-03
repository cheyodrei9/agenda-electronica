/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeansCalendario;

import Mantenimiento.MantenimientoActividades;
import Mantenimiento.MantenimientoUsusario;
import Persistencia.Usuarios;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;

/**
 *
 * @author julio.benavidesusam
 */
@ManagedBean
@RequestScoped
public class BeanLogin {

    HttpSession session = SessionUtils.getSession();

    private String username;
    private String password;
    private int user_id;
    private int user_lvl;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUser_id() {
        user_id = Integer.parseInt(session.getAttribute("userid").toString());
        return user_id;
    }

    public int getUser_lvl() {
        user_lvl = Integer.parseInt(session.getAttribute("user_level").toString());
        return user_lvl;
    }

    /**
     * Creates a new instance of BeanLogin
     */
    public BeanLogin() {
    }

    //Metodo de inicio de sesion
    public String loginAgenda() throws IOException {
        MantenimientoUsusario manUsuario = new MantenimientoUsusario();
        Usuarios usuario = manUsuario.iniciarSesion(username, password);
        FacesMessage message = null;
        boolean loggedIn = false;

        if (usuario != null) {
            MantenimientoActividades ma = new MantenimientoActividades();
            //asignacion de datos del usuario a variables para su uso en restricciones u otros metodos
            session.setAttribute("userid", usuario.getIdusuario());
            session.setAttribute("user_level", usuario.getNiveldemando());
            session.setAttribute("tasks", ma.pendingTask());
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", usuario.getNombres());
            FacesContext.getCurrentInstance().addMessage(null, message);
            PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
            return "calendario";
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Credenciales invalidos");
            FacesContext.getCurrentInstance().addMessage(null, message);
            PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
            return "login";
        }
    }

    //metodo de cierre de session
    public String logoutAgenda() throws IOException {
        session.invalidate();
        return "login";
    }

    public void redirect(String redir) throws IOException {
        switch (redir) {
            case "TiposActividades":
                if (this.getUser_lvl() == 1 || this.getUser_lvl() == 2 || this.getUser_lvl() == 3) {
                    if (this.getUser_lvl() == 2 || this.getUser_lvl() == 3) {
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
