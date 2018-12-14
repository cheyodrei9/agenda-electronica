/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import BeansCalendario.BeanLogin;
import Mantenimiento.MantenimientoActividades;
import Mantenimiento.MantenimientoCronogramas;
import Mantenimiento.MantenimientoFases;
import Mantenimiento.MantenimientoTiposActividades;
import Mantenimiento.MantenimientoUsusario;
import Persistencia.Actividades;
import Persistencia.Cronogramas;
import Persistencia.Fases;
import Persistencia.Tiposactividades;
import Persistencia.Usuarios;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author ruth.ramosusam
 */
@ManagedBean
@ViewScoped
public class AgendaEjemplo implements Serializable {

    private ScheduleModel eventModel;
    private Actividades actividades;
    private List<Actividades> lista = new ArrayList();
    private List<Tiposactividades> listTA = new ArrayList();
    private List<Cronogramas> listCr = new ArrayList();
    private List<Fases> listF = new ArrayList();
    private List<Usuarios> listU = new ArrayList();
    private Integer[] listaUser;

    public Actividades getActividades() {
        return actividades;
    }

    public void setActividades(Actividades actividades) {
        this.actividades = actividades;
    }

    public List<Actividades> getLista() {
        return lista;
    }

    public void setLista(List<Actividades> lista) {
        this.lista = lista;
    }

    public List<Tiposactividades> getListTA() {
        return listTA;
    }

    public void setListTA(List<Tiposactividades> listTA) {
        this.listTA = listTA;
    }

    public List<Cronogramas> getListCr() {
        return listCr;
    }

    public void setListCr(List<Cronogramas> listCr) {
        this.listCr = listCr;
    }

    public List<Fases> getListF() {
        return listF;
    }

    public void setListF(List<Fases> listF) {
        this.listF = listF;
    }

    public List<Usuarios> getListU() {
        return listU;
    }

    public void setListU(List<Usuarios> listU) {
        this.listU = listU;
    }

    public Integer[] getListaUser() {
        return listaUser;
    }

    public void setListaUser(Integer[] listaUser) {
        this.listaUser = listaUser;
    }

    private ScheduleModel lazyEventModel;

    private ScheduleEvent event = new DefaultScheduleEvent();
    private final MantenimientoActividades mttoac = new MantenimientoActividades();

    @PostConstruct
    public void init() {

        MantenimientoCronogramas mc = new MantenimientoCronogramas();
        MantenimientoTiposActividades mta = new MantenimientoTiposActividades();
        MantenimientoFases mf = new MantenimientoFases();
        MantenimientoUsusario mu = new MantenimientoUsusario();
        lista = mttoac.consultar();
        listCr = mc.consultar();
        listF = mf.consultar();
        listTA = mta.consultar();
        listU = mu.consultar();

        eventModel = new DefaultScheduleModel();
        List<Actividades> a = mttoac.consultar();
        Iterator i = a.iterator();
        while (i.hasNext()) {

            Actividades actividades = (Actividades) i.next();
            eventModel.addEvent(new DefaultScheduleEvent(actividades.getNombreactividad(), actividades.getFechaactividad(), actividades.getFechaactividad()));

        }
        eventModel.addEvent(new DefaultScheduleEvent("Champios League Match", previousDay8Pm(), previousDay11Pm()));
        eventModel.addEvent(new DefaultScheduleEvent("Birthday Party", today1Pm(), today6Pm()));
        eventModel.addEvent(new DefaultScheduleEvent("Breakfast at Tiffanys", nextDay9Am(), nextDay11Am()));
        eventModel.addEvent(new DefaultScheduleEvent("Plant the new garden stuff", theDayAfter3Pm(), fourDaysLater3pm()));

        actividades = new Actividades();
        actividades.setIdcronograma(new Cronogramas());
        actividades.setIdtipoactividad(new Tiposactividades());
        actividades.setIdfase(new Fases());
        actividades.setIdusuario(new Usuarios());
        lazyEventModel = new LazyScheduleModel() {

            @Override
            public void loadEvents(Date start, Date end) {
                Date random = getRandomDate(start);
                addEvent(new DefaultScheduleEvent("Lazy Event 1", random, random));

                random = getRandomDate(start);
                addEvent(new DefaultScheduleEvent("Lazy Event 2", random, random));
            }
        };
    }

    public Date getRandomDate(Date base) {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random() * 30)) + 1);    //set random day of month

        return date.getTime();
    }

    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar.getTime();
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }

    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar;
    }

    private Date previousDay8Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 8);

        return t.getTime();
    }

    private Date previousDay11Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 11);

        return t.getTime();
    }

    private Date today1Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 1);

        return t.getTime();
    }

    private Date theDayAfter3Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 2);
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 3);

        return t.getTime();
    }

    private Date today6Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 6);

        return t.getTime();
    }

    private Date nextDay9Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 9);

        return t.getTime();
    }

    private Date nextDay11Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 11);

        return t.getTime();
    }

    private Date fourDaysLater3pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 4);
        t.set(Calendar.HOUR, 3);

        return t.getTime();
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public void addEvent() throws IOException {
        if (BeanLogin.getNiveldemando() == 1 || BeanLogin.getNiveldemando() == 2) {
            if (event.getId() == null) {
                for (int i = 0; i < 1; i++) {
                    for (Integer in : listaUser) {

                        eventModel.addEvent(event);
                        actividades.setNombreactividad(event.getTitle());
                        actividades.setFechaactividad(event.getStartDate());
                        actividades.setIdusuario(new Usuarios(in));
                        MantenimientoActividades mact = new MantenimientoActividades();
                        mact.guardar(actividades);
                        System.out.println(in);
                    }
                }
                actividades.setIdcronograma(new Cronogramas());
                actividades.setIdtipoactividad(new Tiposactividades());
                actividades.setIdfase(new Fases());
                actividades.setIdusuario(new Usuarios());

                MantenimientoActividades mact = new MantenimientoActividades();

                lista = mact.consultar();

            } else {
                eventModel.updateEvent(event);

                event = new DefaultScheduleEvent();
            }
        } else {
            if (BeanLogin.getNiveldemando() == 3) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "No permitido", "La accion requiere permisos de administración");
                PrimeFaces.current().dialog().showMessageDynamic(message);
            } else {
                BeanLogin beanLogin = new BeanLogin(true);
                FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "No permitido", "La accion requiere permisos de administración");
                PrimeFaces.current().dialog().showMessageDynamic(message);
            }
        }
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
