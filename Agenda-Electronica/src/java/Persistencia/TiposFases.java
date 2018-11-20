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
@Table(name = "tipos_fases")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TiposFases.findAll", query = "SELECT t FROM TiposFases t")
    , @NamedQuery(name = "TiposFases.findByIdTipoFase", query = "SELECT t FROM TiposFases t WHERE t.idTipoFase = :idTipoFase")
    , @NamedQuery(name = "TiposFases.findByTipoFase", query = "SELECT t FROM TiposFases t WHERE t.tipoFase = :tipoFase")})
public class TiposFases implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_fase")
    private Integer idTipoFase;
    @Basic(optional = false)
    @Column(name = "tipo_fase")
    private String tipoFase;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoFase")
    private Collection<Fases> fasesCollection;

    public TiposFases() {
    }

    public TiposFases(Integer idTipoFase) {
        this.idTipoFase = idTipoFase;
    }

    public TiposFases(Integer idTipoFase, String tipoFase) {
        this.idTipoFase = idTipoFase;
        this.tipoFase = tipoFase;
    }

    public Integer getIdTipoFase() {
        return idTipoFase;
    }

    public void setIdTipoFase(Integer idTipoFase) {
        this.idTipoFase = idTipoFase;
    }

    public String getTipoFase() {
        return tipoFase;
    }

    public void setTipoFase(String tipoFase) {
        this.tipoFase = tipoFase;
    }

    @XmlTransient
    public Collection<Fases> getFasesCollection() {
        return fasesCollection;
    }

    public void setFasesCollection(Collection<Fases> fasesCollection) {
        this.fasesCollection = fasesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoFase != null ? idTipoFase.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposFases)) {
            return false;
        }
        TiposFases other = (TiposFases) object;
        if ((this.idTipoFase == null && other.idTipoFase != null) || (this.idTipoFase != null && !this.idTipoFase.equals(other.idTipoFase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.TiposFases[ idTipoFase=" + idTipoFase + " ]";
    }
    
}
