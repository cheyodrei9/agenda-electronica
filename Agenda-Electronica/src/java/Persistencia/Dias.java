/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eliseo.garciausam
 */
@Entity
@Table(name = "dias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dias.findAll", query = "SELECT d FROM Dias d")
    , @NamedQuery(name = "Dias.findByIddia", query = "SELECT d FROM Dias d WHERE d.iddia = :iddia")
    , @NamedQuery(name = "Dias.findByNombredia", query = "SELECT d FROM Dias d WHERE d.nombredia = :nombredia")})
public class Dias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddia")
    private Integer iddia;
    @Basic(optional = false)
    @Column(name = "nombredia")
    private String nombredia;
    @JoinColumn(name = "idmes", referencedColumnName = "idmes")
    @ManyToOne(optional = false)
    private Meses idmes;

    public Dias() {
    }

    public Dias(Integer iddia) {
        this.iddia = iddia;
    }

    public Dias(Integer iddia, String nombredia) {
        this.iddia = iddia;
        this.nombredia = nombredia;
    }

    public Integer getIddia() {
        return iddia;
    }

    public void setIddia(Integer iddia) {
        this.iddia = iddia;
    }

    public String getNombredia() {
        return nombredia;
    }

    public void setNombredia(String nombredia) {
        this.nombredia = nombredia;
    }

    public Meses getIdmes() {
        return idmes;
    }

    public void setIdmes(Meses idmes) {
        this.idmes = idmes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddia != null ? iddia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dias)) {
            return false;
        }
        Dias other = (Dias) object;
        if ((this.iddia == null && other.iddia != null) || (this.iddia != null && !this.iddia.equals(other.iddia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Dias[ iddia=" + iddia + " ]";
    }
    
}
