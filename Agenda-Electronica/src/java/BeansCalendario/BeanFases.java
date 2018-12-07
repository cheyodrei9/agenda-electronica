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
    public void guardar(){
        MantenimientoFases Mfase= new MantenimientoFases();
        Mfase.Guardar(fase);
        fase=new Fases();
    }
    
    public void borrar(Fases Fase){
        MantenimientoFases Mfase= new MantenimientoFases();
        Mfase.eliminar(fase);
        this.ListaFase=MFases.consultar();
    }
    private void Actualizar(){
        MantenimientoFases Mfase= new MantenimientoFases();
        Mfase.Actualizar(this.fase);
    }
    public void Editar(Fases fase){
        MantenimientoFases Mfase= new MantenimientoFases();
        this.fase=fase;
        accion="Editar";
    }
    
}
