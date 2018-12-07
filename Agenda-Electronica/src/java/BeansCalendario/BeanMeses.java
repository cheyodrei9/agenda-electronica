/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeansCalendario;

import Mantenimiento.MantenimientoCalendarios;
import Mantenimiento.MantenimientoMeses;
import Persistencia.Calendarios;
import Persistencia.Meses;
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
public class BeanMeses {
    private List<Meses> ListaMeses = new ArrayList();
    private List<Calendarios> ListaCalendario = new ArrayList();
    private MantenimientoMeses MMes;
    private Meses meses;
    private String accion;

    public List<Meses> getListaMeses() {
        return ListaMeses;
    }

    public void setListaMeses(List<Meses> ListaMeses) {
        this.ListaMeses = ListaMeses;
    }

    public List<Calendarios> getListaCalendario() {
        return ListaCalendario;
    }

    public void setListaCalendario(List<Calendarios> ListaCalendario) {
        this.ListaCalendario = ListaCalendario;
    }

    public MantenimientoMeses getMMes() {
        return MMes;
    }

    public void setMMes(MantenimientoMeses MMes) {
        this.MMes = MMes;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Meses getMeses() {
        return meses;
    }

    public void setMeses(Meses meses) {
        this.meses = meses;
    }
    
    
    @PostConstruct
    public void init(){
        meses= new Meses();
        meses.setIdcalendario(new Calendarios());
        MantenimientoMeses mes = new MantenimientoMeses();
        MantenimientoCalendarios cal = new MantenimientoCalendarios();
        ListaMeses=mes.consultar();
        ListaCalendario=cal.consultar();
            
        }
     public void LimpiarFormulario(){
         this.ListaMeses=MMes.consultar();
         this.meses=new Meses();
         meses.setIdcalendario(new Calendarios());
         this.meses.setIdmes(0);
         accion="Registrar";
    }
     
     public void AccionFormulario(){
         if(accion.equals("Registrar")){
             MMes.Guardar(this.meses);
         }else if(accion.equals("Editar")){
             MMes.actualizar(this.meses);
         }
         LimpiarFormulario();
     }
     
     public void guardar(){
         MantenimientoMeses mmeses= new MantenimientoMeses();
         mmeses.Guardar(meses);
         meses=new Meses();
     }
     
     public void borrar(Meses meses){
         MantenimientoMeses mmes=new MantenimientoMeses();
         mmes.eliminar(meses);
         this.ListaMeses=this.MMes.consultar();
     }
     
     public void Editar(Meses meses){
         this.meses=meses;
         accion="Editar";
     }
     
     public void Actualizar(){
         MMes.actualizar(this.meses);
     }
}
