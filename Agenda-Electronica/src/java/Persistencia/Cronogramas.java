/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author eliseo.garciausam
 */
@Entity
@Table(name = "cronogramas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cronogramas.findAll", query = "SELECT c FROM Cronogramas c")
    , @NamedQuery(name = "Cronogramas.findByIdcronograma", query = "SELECT c FROM Cronogramas c WHERE c.idcronograma = :idcronograma")
    , @NamedQuery(name = "Cronogramas.findByNombre", query = "SELECT c FROM Cronogramas c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Cronogramas.findByDescripcion", query = "SELECT c FROM Cronogramas c WHERE c.descripcion = :descripcion")})
public class Cronogramas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcronograma")
    private Integer idcronograma;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcronograma")
    private Collection<Calendarios> calendariosCollection;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuarios idusuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcronograma")
    private Collection<Actividades> actividadesCollection;

    public Cronogramas() {
    }

    public Cronogramas(Integer idcronograma) {
        this.idcronograma = idcronograma;
    }

    public Cronogramas(Integer idcronograma, String nombre, String descripcion) {
        this.idcronograma = idcronograma;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getIdcronograma() {
        return idcronograma;
    }

    public void setIdcronograma(Integer idcronograma) {
        this.idcronograma = idcronograma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<Calendarios> getCalendariosCollection() {
        return calendariosCollection;
    }

    public void setCalendariosCollection(Collection<Calendarios> calendariosCollection) {
        this.calendariosCollection = calendariosCollection;
    }

    public Usuarios getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuarios idusuario) {
        this.idusuario = idusuario;
    }

    @XmlTransient
    public Collection<Actividades> getActividadesCollection() {
        return actividadesCollection;
    }

    public void setActividadesCollection(Collection<Actividades> actividadesCollection) {
        this.actividadesCollection = actividadesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcronograma != null ? idcronograma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cronogramas)) {
            return false;
        }
        Cronogramas other = (Cronogramas) object;
        if ((this.idcronograma == null && other.idcronograma != null) || (this.idcronograma != null && !this.idcronograma.equals(other.idcronograma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Cronogramas[ idcronograma=" + idcronograma + " ]";
    }

}
