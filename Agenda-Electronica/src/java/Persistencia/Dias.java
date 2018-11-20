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
    , @NamedQuery(name = "Dias.findByIdDia", query = "SELECT d FROM Dias d WHERE d.idDia = :idDia")
    , @NamedQuery(name = "Dias.findByNombreDia", query = "SELECT d FROM Dias d WHERE d.nombreDia = :nombreDia")})
public class Dias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_dia")
    private Integer idDia;
    @Basic(optional = false)
    @Column(name = "nombre_dia")
    private String nombreDia;
    @JoinColumn(name = "id_mes", referencedColumnName = "id_mes")
    @ManyToOne(optional = false)
    private Meses idMes;

    public Dias() {
    }

    public Dias(Integer idDia) {
        this.idDia = idDia;
    }

    public Dias(Integer idDia, String nombreDia) {
        this.idDia = idDia;
        this.nombreDia = nombreDia;
    }

    public Integer getIdDia() {
        return idDia;
    }

    public void setIdDia(Integer idDia) {
        this.idDia = idDia;
    }

    public String getNombreDia() {
        return nombreDia;
    }

    public void setNombreDia(String nombreDia) {
        this.nombreDia = nombreDia;
    }

    public Meses getIdMes() {
        return idMes;
    }

    public void setIdMes(Meses idMes) {
        this.idMes = idMes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDia != null ? idDia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dias)) {
            return false;
        }
        Dias other = (Dias) object;
        if ((this.idDia == null && other.idDia != null) || (this.idDia != null && !this.idDia.equals(other.idDia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Dias[ idDia=" + idDia + " ]";
    }
    
}
