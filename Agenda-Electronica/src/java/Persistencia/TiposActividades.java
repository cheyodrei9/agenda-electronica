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
 * @author eliseo.garciausam
 */
@Entity
@Table(name = "tipos_actividades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TiposActividades.findAll", query = "SELECT t FROM TiposActividades t")
    , @NamedQuery(name = "TiposActividades.findByIdTipoActividad", query = "SELECT t FROM TiposActividades t WHERE t.idTipoActividad = :idTipoActividad")
    , @NamedQuery(name = "TiposActividades.findByTipoActividad", query = "SELECT t FROM TiposActividades t WHERE t.tipoActividad = :tipoActividad")})
public class TiposActividades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_actividad")
    private Integer idTipoActividad;
    @Basic(optional = false)
    @Column(name = "tipo_actividad")
    private String tipoActividad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoActividad")
    private Collection<Actividades> actividadesCollection;

    public TiposActividades() {
    }

    public TiposActividades(Integer idTipoActividad) {
        this.idTipoActividad = idTipoActividad;
    }

    public TiposActividades(Integer idTipoActividad, String tipoActividad) {
        this.idTipoActividad = idTipoActividad;
        this.tipoActividad = tipoActividad;
    }

    public Integer getIdTipoActividad() {
        return idTipoActividad;
    }

    public void setIdTipoActividad(Integer idTipoActividad) {
        this.idTipoActividad = idTipoActividad;
    }

    public String getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
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
        hash += (idTipoActividad != null ? idTipoActividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposActividades)) {
            return false;
        }
        TiposActividades other = (TiposActividades) object;
        if ((this.idTipoActividad == null && other.idTipoActividad != null) || (this.idTipoActividad != null && !this.idTipoActividad.equals(other.idTipoActividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.TiposActividades[ idTipoActividad=" + idTipoActividad + " ]";
    }
    
}
