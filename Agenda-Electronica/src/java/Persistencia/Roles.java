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
 * @author david.floresusam
 */
@Entity
@Table(name = "roles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Roles r")
    , @NamedQuery(name = "Roles.findByIdrol", query = "SELECT r FROM Roles r WHERE r.idrol = :idrol")
    , @NamedQuery(name = "Roles.findByRol", query = "SELECT r FROM Roles r WHERE r.rol = :rol")})
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrol")
    private Integer idrol;
    @Basic(optional = false)
    @Column(name = "rol")
    private String rol;
    @JoinColumn(name = "idtipousuario", referencedColumnName = "idtipousuario")
    @ManyToOne(optional = false)
    private Tiposusuarios idtipousuario;

    public Roles() {
    }

    public Roles(Integer idrol) {
        this.idrol = idrol;
    }

    public Roles(Integer idrol, String rol) {
        this.idrol = idrol;
        this.rol = rol;
    }

    public Integer getIdrol() {
        return idrol;
    }

    public void setIdrol(Integer idrol) {
        this.idrol = idrol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Tiposusuarios getIdtipousuario() {
        return idtipousuario;
    }

    public void setIdtipousuario(Tiposusuarios idtipousuario) {
        this.idtipousuario = idtipousuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrol != null ? idrol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roles)) {
            return false;
        }
        Roles other = (Roles) object;
        if ((this.idrol == null && other.idrol != null) || (this.idrol != null && !this.idrol.equals(other.idrol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Roles[ idrol=" + idrol + " ]";
    }
    
}
