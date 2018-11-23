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
@Table(name = "meses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Meses.findAll", query = "SELECT m FROM Meses m")
    , @NamedQuery(name = "Meses.findByIdmes", query = "SELECT m FROM Meses m WHERE m.idmes = :idmes")
    , @NamedQuery(name = "Meses.findByMes", query = "SELECT m FROM Meses m WHERE m.mes = :mes")})
public class Meses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmes")
    private Integer idmes;
    @Basic(optional = false)
    @Column(name = "mes")
    private String mes;
    @JoinColumn(name = "idcalendario", referencedColumnName = "idcalendario")
    @ManyToOne(optional = false)
    private Calendarios idcalendario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmes")
    private Collection<Dias> diasCollection;

    public Meses() {
    }

    public Meses(Integer idmes) {
        this.idmes = idmes;
    }

    public Meses(Integer idmes, String mes) {
        this.idmes = idmes;
        this.mes = mes;
    }

    public Integer getIdmes() {
        return idmes;
    }

    public void setIdmes(Integer idmes) {
        this.idmes = idmes;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Calendarios getIdcalendario() {
        return idcalendario;
    }

    public void setIdcalendario(Calendarios idcalendario) {
        this.idcalendario = idcalendario;
    }

    @XmlTransient
    public Collection<Dias> getDiasCollection() {
        return diasCollection;
    }

    public void setDiasCollection(Collection<Dias> diasCollection) {
        this.diasCollection = diasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmes != null ? idmes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Meses)) {
            return false;
        }
        Meses other = (Meses) object;
        if ((this.idmes == null && other.idmes != null) || (this.idmes != null && !this.idmes.equals(other.idmes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Meses[ idmes=" + idmes + " ]";
    }
    
}
