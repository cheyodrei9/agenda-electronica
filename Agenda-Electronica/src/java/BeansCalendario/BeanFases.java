/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeansCalendario;

import Mantenimiento.MantenimientoFases;
import Mantenimiento.MantenimientoTiposFases;
import Persistencia.Fases;
import Persistencia.Tiposfases;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author gerson.ruizusam
 */
@ManagedBean
@RequestScoped
public class BeanFases {

    private List<Fases> ListaFase = new ArrayList();
    private List<Tiposfases> ListaTipoFase = new ArrayList();
    private MantenimientoFases MFases;
    private Fases fase;
    private String accion;

    public List<Fases> getListaFase() {
        return ListaFase;
    }

    public void setListaFase(List<Fases> ListaFase) {
        this.ListaFase = ListaFase;
    }

    public List<Tiposfases> getListaTipoFase() {
        return ListaTipoFase;
    }

    public void setListaTipoFase(List<Tiposfases> ListaTipoFase) {
        this.ListaTipoFase = ListaTipoFase;
    }

    public MantenimientoFases getMFases() {
        return MFases;
    }

    public void setMFases(MantenimientoFases MFases) {
        this.MFases = MFases;
    }

    public Fases getFase() {
        return fase;
    }

    public void setFase(Fases fase) {
        this.fase = fase;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    @PostConstruct
    public void init() {
        fase = new Fases();
        fase.setIdtipofase(new Tiposfases());
        MantenimientoFases Mfases = new MantenimientoFases();
        MantenimientoTiposFases MTF = new MantenimientoTiposFases();
        ListaFase = Mfases.consultar();
        ListaTipoFase = MTF.consultar();
    }

    public void LimpiarFormulario() {
        this.ListaFase = MFases.consultar();
        this.fase = new Fases();
        fase.setIdtipofase(new Tiposfases());
        this.fase.setIdfase(0);
        accion = "Registrar";
    }
    public void AccionFormulario(){
        if(accion.equals("Registrar")){
            MFases.Guardar(this.fase);
        }else if(accion.equals("Editar")){
            MFases.Actualizar(this.fase);
        }
        LimpiarFormulario();
    }
    public void guardar(){
        MantenimientoFases Mfase= new MantenimientoFases();
        Mfase.Guardar(fase);
        fase=new Fases();
    }

    public void eliminar(Fases fase) {
        MantenimientoFases Mfase = new MantenimientoFases();
        Mfase.eliminar(fase);
        ListaFase = Mfase.consultar();
        String advertencia = "";

        if (Mfase.eliminar(fase) == 1) {
            advertencia = "Se ha eliminado correctamente";
        } else {
            advertencia = "No se ha podido eliminar";

        }
    }
    
    public void modificar(Fases fase) {
        MantenimientoFases Mfase = new MantenimientoFases();
        fase=Mfase.consultarid(fase.getIdfase());

        String advertencia = "";
        if (fase != null) {
            this.fase = fase;
            advertencia = "Datos Consultados correctamente";
        } else {
            advertencia = "Consulta no realizada";
        }
    }
    public void actualizar() {
        MantenimientoFases Mfase = new MantenimientoFases();
        Mfase.Actualizar(fase);
        ListaFase = Mfase.consultar();
        String advertencia = "";
        
        ListaFase=Mfase.consultar();

        if (Mfase.Actualizar(fase) == 1) {
            advertencia = "Actualizado correctamente";
        } else {
            advertencia = "No se ha actualizado";
        }
    }
    
}
