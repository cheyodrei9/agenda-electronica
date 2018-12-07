/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeansCalendario;

import Mantenimiento.MantenimientoDias;
import Mantenimiento.MantenimientoMeses;
import Persistencia.Dias;
import Persistencia.Meses;
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
public class BeanDias {
    
    private List<Dias> ListaDias = new ArrayList();
    private List<Meses> ListaMes = new ArrayList();
    private MantenimientoDias MDia;
    private Dias Dia;
    private String accion;

    public List<Dias> getListaDias() {
        return ListaDias;
    }

    public void setListaDias(List<Dias> ListaDias) {
        this.ListaDias = ListaDias;
    }

    public List<Meses> getListaMes() {
        return ListaMes;
    }

    public void setListaMes(List<Meses> ListaMes) {
        this.ListaMes = ListaMes;
    }

    public MantenimientoDias getMDia() {
        return MDia;
    }

    public void setMDia(MantenimientoDias MDia) {
        this.MDia = MDia;
    }

    public Dias getDia() {
        return Dia;
    }

    public void setDia(Dias Dia) {
        this.Dia = Dia;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    @PostConstruct
    public void init(){
        Dia = new Dias();
        Dia.setIdmes(new Meses());
        MantenimientoDias md = new MantenimientoDias();
        MantenimientoMeses MMes = new MantenimientoMeses();
        ListaDias=md.consultar();
        ListaMes=MMes.consultar();
    } 
    public void LimpiarFormulario(){
        this.ListaDias=MDia.consultar();
        this.Dia= new Dias();
        Dia.setIdmes(new Meses());
        this.Dia.setIddia(0);
        accion="Registrar";
    }
    public void guardar(){
        MantenimientoDias Mdias= new MantenimientoDias();
        Mdias.guardar(Dia);
        Dia= new Dias();
    }
    public void borrar(Dias dia){
        MantenimientoDias MDia= new MantenimientoDias();
        MDia.eliminar(dia);
        this.ListaDias= this.MDia.consultar();
    }
    public void Editar(Dias dia){
        this.Dia=dia;
        accion="Editar";
    }
    private void Actualizar(){
        MDia.Actualizar(this.Dia);
    }
}
