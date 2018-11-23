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
 * @author david.floresusam
 */
@Entity
@Table(name = "calendarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Calendarios.findAll", query = "SELECT c FROM Calendarios c")
    , @NamedQuery(name = "Calendarios.findByIdcalendario", query = "SELECT c FROM Calendarios c WHERE c.idcalendario = :idcalendario")
    , @NamedQuery(name = "Calendarios.findByA\u00f1o", query = "SELECT c FROM Calendarios c WHERE c.a\u00f1o = :a\u00f1o")})
public class Calendarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcalendario")
    private Integer idcalendario;
    @Basic(optional = false)
    @Column(name = "a\u00f1o")
    private int año;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcalendario")
    private Collection<Meses> mesesCollection;
    @JoinColumn(name = "idcronograma", referencedColumnName = "idcronograma")
    @ManyToOne(optional = false)
    private Cronogramas idcronograma;

    public Calendarios() {
    }

    public Calendarios(Integer idcalendario) {
        this.idcalendario = idcalendario;
    }

    public Calendarios(Integer idcalendario, int año) {
        this.idcalendario = idcalendario;
        this.año = año;
    }

    public Integer getIdcalendario() {
        return idcalendario;
    }

    public void setIdcalendario(Integer idcalendario) {
        this.idcalendario = idcalendario;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    @XmlTransient
    public Collection<Meses> getMesesCollection() {
        return mesesCollection;
    }

    public void setMesesCollection(Collection<Meses> mesesCollection) {
        this.mesesCollection = mesesCollection;
    }

    public Cronogramas getIdcronograma() {
        return idcronograma;
    }

    public void setIdcronograma(Cronogramas idcronograma) {
        this.idcronograma = idcronograma;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcalendario != null ? idcalendario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calendarios)) {
            return false;
        }
        Calendarios other = (Calendarios) object;
        if ((this.idcalendario == null && other.idcalendario != null) || (this.idcalendario != null && !this.idcalendario.equals(other.idcalendario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Calendarios[ idcalendario=" + idcalendario + " ]";
    }
    
}
