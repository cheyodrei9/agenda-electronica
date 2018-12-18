/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeansCalendario;

import Mantenimiento.MantenimientoTiposFases;
import Persistencia.Tiposfases;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author eliseo.garciausam
 */
@ManagedBean
@RequestScoped
public class BeanTipoFases {
    private List<Tiposfases> ListaTipoFases=new ArrayList();
    private MantenimientoTiposFases MTiposfa;
    private Tiposfases tiposf;
    private String accion;

    public List<Tiposfases> getListaTipoFases() {
        return ListaTipoFases;
    }

    public void setListaTipoFases(List<Tiposfases> ListaTipoFases) {
        this.ListaTipoFases = ListaTipoFases;
    }

    public MantenimientoTiposFases getMTiposfa() {
        return MTiposfa;
    }

    public void setMTiposfa(MantenimientoTiposFases MTiposfa) {
        this.MTiposfa = MTiposfa;
    }

    public Tiposfases getTiposf() {
        return tiposf;
    }

    public void setTiposf(Tiposfases tiposf) {
        this.tiposf = tiposf;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    @PostConstruct
    public void init(){
        tiposf=new Tiposfases();
        MantenimientoTiposFases fas = new MantenimientoTiposFases();
        ListaTipoFases=fas.consultar();
    }
    
    public void LimpiarFormulario(){
        this.ListaTipoFases=MTiposfa.consultar();
        this.tiposf=new Tiposfases();
        this.tiposf.setIdtipofase(0);
        accion="Registrar";
    }
    
    public void AccionFormulario(){
        if(accion.equals("Registrar")){
            MTiposfa.Guardar(this.tiposf);
        }else if(accion.equals("Editar")){
            MTiposfa.Actualizar(this.tiposf);
        }
        LimpiarFormulario();
    }
    
    public void guardar(){
        MantenimientoTiposFases mtp = new MantenimientoTiposFases();
        mtp.Guardar(tiposf);
        tiposf= new Tiposfases();
        this.ListaTipoFases = mtp.consultar();
    }
    
    public void eliminar(Tiposfases tiposfases){
        MantenimientoTiposFases mtpf = new MantenimientoTiposFases();
        mtpf.eliminar(tiposfases);
        this.ListaTipoFases=this.MTiposfa.consultar();
    }
    
    public void modificar(Tiposfases tiposfases){
        MantenimientoTiposFases Mtf = new MantenimientoTiposFases();
        tiposfases = Mtf.consultarid(tiposfases.getIdtipofase());
        String advertencia="";
        if(tiposfases != null){
            this.tiposf = tiposfases;
            advertencia ="Datos Consultados Correctamente"; 
        }else{
            advertencia="Consulta No Realizada Fallo";
        }
    }
    
    public void Actualizar(){
        MantenimientoTiposFases Mtf = new MantenimientoTiposFases();
        Mtf.Actualizar(tiposf);
    }
}
