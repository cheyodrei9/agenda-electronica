
package BeansCalendario;

import Mantenimiento.MantenimientoTipoUsuario;
import Persistencia.Tiposusuarios;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean
@RequestScoped
public class BeanTiposUsuarios {
    
    private BeanTiposUsuarios Tusuario;
    private List<Tiposusuarios> listaTu = new ArrayList();
    private Tiposusuarios TU;
    private String Accion;
    
     @PostConstruct
    public void inicio() {
        Tusuario = new BeanTiposUsuarios();
        MantenimientoTipoUsuario MTusuario = new MantenimientoTipoUsuario();
        listaTu = MTusuario.consultar();        
    }

    public BeanTiposUsuarios getTusuario() {
        return Tusuario;
    }

    public void setTusuario(BeanTiposUsuarios Tusuario) {
        this.Tusuario = Tusuario;
    }

    public List<Tiposusuarios> getListaTu() {
        return listaTu;
    }

    public void setListaTu(List<Tiposusuarios> listaTu) {
        this.listaTu = listaTu;
    }

    public String getAccion() {
        return Accion;
    }

    public void setAccion(String Accion) {
        this.Accion = Accion;
    }
    
    public void guardar(){
        MantenimientoTipoUsuario tuser = new MantenimientoTipoUsuario();
        tuser.Guardar(TU);
    }
    
    public void eliminar (Tiposusuarios TU){
        MantenimientoTipoUsuario tuser = new MantenimientoTipoUsuario();
        tuser.eliminar(TU);
        listaTu = tuser.consultar();
    }
    
}
