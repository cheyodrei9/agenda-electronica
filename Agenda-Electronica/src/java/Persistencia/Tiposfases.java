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
@Table(name = "tiposfases")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tiposfases.findAll", query = "SELECT t FROM Tiposfases t")
    , @NamedQuery(name = "Tiposfases.findByIdtipofase", query = "SELECT t FROM Tiposfases t WHERE t.idtipofase = :idtipofase")
    , @NamedQuery(name = "Tiposfases.findByTipofase", query = "SELECT t FROM Tiposfases t WHERE t.tipofase = :tipofase")})
public class Tiposfases implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipofase")
    private Integer idtipofase;
    @Basic(optional = false)
    @Column(name = "tipofase")
    private String tipofase;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtipofase")
    private Collection<Fases> fasesCollection;

    public Tiposfases() {
    }

    public Tiposfases(Integer idtipofase) {
        this.idtipofase = idtipofase;
    }

    public Tiposfases(Integer idtipofase, String tipofase) {
        this.idtipofase = idtipofase;
        this.tipofase = tipofase;
    }

    public Integer getIdtipofase() {
        return idtipofase;
    }

    public void setIdtipofase(Integer idtipofase) {
        this.idtipofase = idtipofase;
    }

    public String getTipofase() {
        return tipofase;
    }

    public void setTipofase(String tipofase) {
        this.tipofase = tipofase;
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
        hash += (idtipofase != null ? idtipofase.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tiposfases)) {
            return false;
        }
        Tiposfases other = (Tiposfases) object;
        if ((this.idtipofase == null && other.idtipofase != null) || (this.idtipofase != null && !this.idtipofase.equals(other.idtipofase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Tiposfases[ idtipofase=" + idtipofase + " ]";
    }
    
}
