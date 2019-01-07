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

    private List<Tiposusuarios> listaTu = new ArrayList();
    private MantenimientoTipoUsuario MTU;
    private Tiposusuarios TU;
    private String Accion;

    @PostConstruct
    public void inicio() {
        TU = new Tiposusuarios();
        MantenimientoTipoUsuario MTusuario = new MantenimientoTipoUsuario();
        listaTu = MTusuario.consultar();
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

    public MantenimientoTipoUsuario getMTU() {
        return MTU;
    }

    public void setMTU(MantenimientoTipoUsuario MTU) {
        this.MTU = MTU;
    }

    public Tiposusuarios getTU() {
        return TU;
    }

    public void setTU(Tiposusuarios TU) {
        this.TU = TU;
    }

    public void guardar() {
        MantenimientoTipoUsuario tuser = new MantenimientoTipoUsuario();
        tuser.Guardar(TU);
        TU = new Tiposusuarios();
        this.listaTu = MTU.consultar();
    }

    public void eliminar(Tiposusuarios tipoU) {
        MantenimientoTipoUsuario Muser = new MantenimientoTipoUsuario();
        Muser.eliminar(tipoU);
        listaTu = Muser.consultar();
    }

    public void modificar(Tiposusuarios tipoU) {
        MantenimientoTipoUsuario Muser = new MantenimientoTipoUsuario();
        tipoU = Muser.consultarid(tipoU.getIdtipousuario());
        String advertencia = "";
        if (tipoU != null) {
            this.TU = tipoU;
            advertencia = "Datos Consultados correctamente";
        } else {
            advertencia = "Consulta no realizada";
        }
    }

    public void actualizar() {
        MantenimientoTipoUsuario Muser = new MantenimientoTipoUsuario();
        Muser.Actualizar(TU);
        listaTu=Muser.consultar();
    }
}
