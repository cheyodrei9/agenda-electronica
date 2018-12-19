/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author eliseo.garciausam
 */
@Entity
@Table(name = "actividades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actividades.findAll", query = "SELECT a FROM Actividades a")
    , @NamedQuery(name = "Actividades.findByIdactividad", query = "SELECT a FROM Actividades a WHERE a.idactividad = :idactividad")
    , @NamedQuery(name = "Actividades.findByNombreactividad", query = "SELECT a FROM Actividades a WHERE a.nombreactividad = :nombreactividad")
    , @NamedQuery(name = "Actividades.findByFechaactividad", query = "SELECT a FROM Actividades a WHERE a.fechaactividad = :fechaactividad")})
public class Actividadesf implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idactividad")
    private Integer idactividad;
    @Basic(optional = false)
    @Column(name = "nombreactividad")
    private String nombreactividad;
    @Basic(optional = false)
    @Column(name = "fechaactividad")
    @Temporal(TemporalType.DATE)
    private Date fechaactividad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idactividad")
    private Collection<Notificaciones> notificacionesCollection;
    @JoinColumn(name = "idcronograma", referencedColumnName = "idcronograma")
    @ManyToOne(optional = false)
    private Cronogramas idcronograma;
    @JoinColumn(name = "idtipoactividad", referencedColumnName = "idtipoactividad")
    @ManyToOne(optional = false)
    private Tiposactividades idtipoactividad;
    @JoinColumn(name = "idfase", referencedColumnName = "idfase")
    @ManyToOne(optional = false)
    private Fases idfase;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuarios idusuario;
private String fecha;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public Actividadesf() {
    }

    public Actividadesf(Integer idactividad) {
        this.idactividad = idactividad;
    }

    public Actividadesf(Integer idactividad, String nombreactividad, Date fechaactividad) {
        this.idactividad = idactividad;
        this.nombreactividad = nombreactividad;
        this.fechaactividad = fechaactividad;
    }

    public Integer getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(Integer idactividad) {
        this.idactividad = idactividad;
    }

    public String getNombreactividad() {
        return nombreactividad;
    }

    public void setNombreactividad(String nombreactividad) {
        this.nombreactividad = nombreactividad;
    }

    public Date getFechaactividad() {
        return fechaactividad;
    }

    public void setFechaactividad(Date fechaactividad) {
        this.fechaactividad = fechaactividad;
    }

    @XmlTransient
    public Collection<Notificaciones> getNotificacionesCollection() {
        return notificacionesCollection;
    }

    public void setNotificacionesCollection(Collection<Notificaciones> notificacionesCollection) {
        this.notificacionesCollection = notificacionesCollection;
    }

    public Cronogramas getIdcronograma() {
        return idcronograma;
    }

    public void setIdcronograma(Cronogramas idcronograma) {
        this.idcronograma = idcronograma;
    }

    public Tiposactividades getIdtipoactividad() {
        return idtipoactividad;
    }

    public void setIdtipoactividad(Tiposactividades idtipoactividad) {
        this.idtipoactividad = idtipoactividad;
    }

    public Fases getIdfase() {
        return idfase;
    }

    public void setIdfase(Fases idfase) {
        this.idfase = idfase;
    }

    public Usuarios getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuarios idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idactividad != null ? idactividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actividadesf)) {
            return false;
        }
        Actividadesf other = (Actividadesf) object;
        if ((this.idactividad == null && other.idactividad != null) || (this.idactividad != null && !this.idactividad.equals(other.idactividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Actividades[ idactividad=" + idactividad + " ]";
    }
    
}
