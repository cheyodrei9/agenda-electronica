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
 * @author david.floresusam
 */
@Entity
@Table(name = "fases")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fases.findAll", query = "SELECT f FROM Fases f")
    , @NamedQuery(name = "Fases.findByIdfase", query = "SELECT f FROM Fases f WHERE f.idfase = :idfase")
    , @NamedQuery(name = "Fases.findByEstado", query = "SELECT f FROM Fases f WHERE f.estado = :estado")
    , @NamedQuery(name = "Fases.findByFecha", query = "SELECT f FROM Fases f WHERE f.fecha = :fecha")})
public class Fases implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfase")
    private Integer idfase;
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idfase")
    private Collection<Actividades> actividadesCollection;
    @JoinColumn(name = "idtipofase", referencedColumnName = "idtipofase")
    @ManyToOne(optional = false)
    private Tiposfases idtipofase;

    public Fases() {
    }

    public Fases(Integer idfase) {
        this.idfase = idfase;
    }

    public Fases(Integer idfase, String estado, Date fecha) {
        this.idfase = idfase;
        this.estado = estado;
        this.fecha = fecha;
    }

    public Integer getIdfase() {
        return idfase;
    }

    public void setIdfase(Integer idfase) {
        this.idfase = idfase;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @XmlTransient
    public Collection<Actividades> getActividadesCollection() {
        return actividadesCollection;
    }

    public void setActividadesCollection(Collection<Actividades> actividadesCollection) {
        this.actividadesCollection = actividadesCollection;
    }

    public Tiposfases getIdtipofase() {
        return idtipofase;
    }

    public void setIdtipofase(Tiposfases idtipofase) {
        this.idtipofase = idtipofase;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfase != null ? idfase.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fases)) {
            return false;
        }
        Fases other = (Fases) object;
        if ((this.idfase == null && other.idfase != null) || (this.idfase != null && !this.idfase.equals(other.idfase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Fases[ idfase=" + idfase + " ]";
    }
    
}
