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
@Table(name = "calendarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Calendarios.findAll", query = "SELECT c FROM Calendarios c")
    , @NamedQuery(name = "Calendarios.findByIdCalendario", query = "SELECT c FROM Calendarios c WHERE c.idCalendario = :idCalendario")
    , @NamedQuery(name = "Calendarios.findByA\u00f1o", query = "SELECT c FROM Calendarios c WHERE c.a\u00f1o = :a\u00f1o")})
public class Calendarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_calendario")
    private Integer idCalendario;
    @Basic(optional = false)
    @Column(name = "a\u00f1o")
    private int año;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCalendario")
    private Collection<Meses> mesesCollection;
    @JoinColumn(name = "id_cronograma", referencedColumnName = "id_cronograma")
    @ManyToOne(optional = false)
    private Cronogramas idCronograma;

    public Calendarios() {
    }

    public Calendarios(Integer idCalendario) {
        this.idCalendario = idCalendario;
    }

    public Calendarios(Integer idCalendario, int año) {
        this.idCalendario = idCalendario;
        this.año = año;
    }

    public Integer getIdCalendario() {
        return idCalendario;
    }

    public void setIdCalendario(Integer idCalendario) {
        this.idCalendario = idCalendario;
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

    public Cronogramas getIdCronograma() {
        return idCronograma;
    }

    public void setIdCronograma(Cronogramas idCronograma) {
        this.idCronograma = idCronograma;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCalendario != null ? idCalendario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calendarios)) {
            return false;
        }
        Calendarios other = (Calendarios) object;
        if ((this.idCalendario == null && other.idCalendario != null) || (this.idCalendario != null && !this.idCalendario.equals(other.idCalendario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Calendarios[ idCalendario=" + idCalendario + " ]";
    }
    
}
