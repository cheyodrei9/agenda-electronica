/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeansCalendario;

import Mantenimiento.MantenimientoActividades;
import Persistencia.Actividades;
import Persistencia.Cronogramas;
import Persistencia.Fases;
import Persistencia.TiposActividades;
import Persistencia.TiposFases;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author guillermo.bermudes
 */
@ManagedBean
@RequestScoped
public class BeansActividades {
    
private Actividades act;
private List <Actividades> lista = new ArrayList();

 
    
    @PostConstruct
    public void inicio (){
    
    act= new Actividades ();
    act.setIdCronograma(new Cronogramas());
    act.setIdTipoActividad(new TiposActividades());
    act.setIdFases(new Fases());
MantenimientoActividades actividades = new MantenimientoActividades();    
  lista = actividades.consultar(); 
}
       public List<Actividades> getLista() {
        return lista;
    }

    public void setLista(List<Actividades> lista) {
        this.lista = lista;
    }
    
public Actividades getAct() {
        return act;
    }

    public void setAct(Actividades act) {
        this.act = act;
    }
    
    public void guardar (){
        System.out.println("este es el objeto lleno"+act);
        MantenimientoActividades actividades = new MantenimientoActividades();    
        actividades.guardar(act);
    }
    
    
}
