/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fernando.medranousam
 */
@Entity
@Table(name = "notificaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notificaciones.findAll", query = "SELECT n FROM Notificaciones n")
    , @NamedQuery(name = "Notificaciones.findByIdnotificacion", query = "SELECT n FROM Notificaciones n WHERE n.idnotificacion = :idnotificacion")
    , @NamedQuery(name = "Notificaciones.findByFechaasignada", query = "SELECT n FROM Notificaciones n WHERE n.fechaasignada = :fechaasignada")
    , @NamedQuery(name = "Notificaciones.findByDiasrestantes", query = "SELECT n FROM Notificaciones n WHERE n.diasrestantes = :diasrestantes")
    , @NamedQuery(name = "Notificaciones.findByColor", query = "SELECT n FROM Notificaciones n WHERE n.color = :color")
    , @NamedQuery(name = "Notificaciones.findByEstadoNotificacion", query = "SELECT n FROM Notificaciones n WHERE n.estadoNotificacion = :estadoNotificacion")})
public class Notificaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnotificacion")
    private Integer idnotificacion;
    @Basic(optional = false)
    @Column(name = "fechaasignada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaasignada;
    @Basic(optional = false)
    @Column(name = "diasrestantes")
    private int diasrestantes;
    @Basic(optional = false)
    @Column(name = "color")
    private String color;
    @Basic(optional = false)
    @Column(name = "estado_notificacion")
    private String estadoNotificacion;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuarios idusuario;
    @JoinColumn(name = "idactividad", referencedColumnName = "idactividad")
    @ManyToOne(optional = false)
    private Actividades idactividad;

    public Notificaciones() {
    }

    public Notificaciones(Integer idnotificacion) {
        this.idnotificacion = idnotificacion;
    }

    public Notificaciones(Integer idnotificacion, Date fechaasignada, int diasrestantes, String color, String estadoNotificacion) {
        this.idnotificacion = idnotificacion;
        this.fechaasignada = fechaasignada;
        this.diasrestantes = diasrestantes;
        this.color = color;
        this.estadoNotificacion = estadoNotificacion;
    }

    public Integer getIdnotificacion() {
        return idnotificacion;
    }

    public void setIdnotificacion(Integer idnotificacion) {
        this.idnotificacion = idnotificacion;
    }

    public Date getFechaasignada() {
        return fechaasignada;
    }

    public void setFechaasignada(Date fechaasignada) {
        this.fechaasignada = fechaasignada;
    }

    public int getDiasrestantes() {
        return diasrestantes;
    }

    public void setDiasrestantes(int diasrestantes) {
        this.diasrestantes = diasrestantes;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEstadoNotificacion() {
        return estadoNotificacion;
    }

    public void setEstadoNotificacion(String estadoNotificacion) {
        this.estadoNotificacion = estadoNotificacion;
    }

    public Usuarios getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuarios idusuario) {
        this.idusuario = idusuario;
    }

    public Actividades getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(Actividades idactividad) {
        this.idactividad = idactividad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnotificacion != null ? idnotificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notificaciones)) {
            return false;
        }
        Notificaciones other = (Notificaciones) object;
        if ((this.idnotificacion == null && other.idnotificacion != null) || (this.idnotificacion != null && !this.idnotificacion.equals(other.idnotificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Notificaciones[ idnotificacion=" + idnotificacion + " ]";
    }
    
}
