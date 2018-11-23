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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author david.floresusam
 */
@Entity
@Table(name = "tiposactividades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tiposactividades.findAll", query = "SELECT t FROM Tiposactividades t")
    , @NamedQuery(name = "Tiposactividades.findByIdtipoactividad", query = "SELECT t FROM Tiposactividades t WHERE t.idtipoactividad = :idtipoactividad")
    , @NamedQuery(name = "Tiposactividades.findByTipoactividad", query = "SELECT t FROM Tiposactividades t WHERE t.tipoactividad = :tipoactividad")})
public class Tiposactividades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipoactividad")
    private Integer idtipoactividad;
    @Basic(optional = false)
    @Column(name = "tipoactividad")
    private String tipoactividad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtipoactividad")
    private Collection<Actividades> actividadesCollection;

    public Tiposactividades() {
    }

    public Tiposactividades(Integer idtipoactividad) {
        this.idtipoactividad = idtipoactividad;
    }

    public Tiposactividades(Integer idtipoactividad, String tipoactividad) {
        this.idtipoactividad = idtipoactividad;
        this.tipoactividad = tipoactividad;
    }

    public Integer getIdtipoactividad() {
        return idtipoactividad;
    }

    public void setIdtipoactividad(Integer idtipoactividad) {
        this.idtipoactividad = idtipoactividad;
    }

    public String getTipoactividad() {
        return tipoactividad;
    }

    public void setTipoactividad(String tipoactividad) {
        this.tipoactividad = tipoactividad;
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
        hash += (idtipoactividad != null ? idtipoactividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tiposactividades)) {
            return false;
        }
        Tiposactividades other = (Tiposactividades) object;
        if ((this.idtipoactividad == null && other.idtipoactividad != null) || (this.idtipoactividad != null && !this.idtipoactividad.equals(other.idtipoactividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Tiposactividades[ idtipoactividad=" + idtipoactividad + " ]";
    }
    
}
