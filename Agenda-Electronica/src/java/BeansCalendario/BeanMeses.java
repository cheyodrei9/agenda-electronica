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
     
     public void eliminar(Meses meses){
         MantenimientoMeses mmes=new MantenimientoMeses();
         mmes.eliminar(meses);
         ListaMeses=mmes.consultar();
         String advertencia="";
         
         if(mmes.eliminar(meses)==1){
             advertencia="se ha eliminado correctamente";
         }else{
             advertencia="no se ha podido eliminar";
         }
     }
     
     public void modificar(Meses meses){
         MantenimientoMeses mmes = new MantenimientoMeses();
         meses=mmes.consultarid(meses.getIdmes());
         
         String advertencia="";
         if(meses !=null){
             this.meses=meses;
             advertencia="Datos consultados corectamente";
         }else{
             advertencia="consulta no realizada";
         }
     }
     
     public void Actualizar(){
         MantenimientoMeses mmes = new MantenimientoMeses();
         mmes.actualizar(meses);
         ListaMeses=mmes.consultar();
         String advertencia="";
         
         if(mmes.actualizar(meses)==1){
             advertencia="actualizado correctamente";
         }else{
             advertencia="no se ha actualizado";
         }
     }
}
