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
@Table(name = "tiposusuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tiposusuarios.findAll", query = "SELECT t FROM Tiposusuarios t")
    , @NamedQuery(name = "Tiposusuarios.findByIdtipousuario", query = "SELECT t FROM Tiposusuarios t WHERE t.idtipousuario = :idtipousuario")
    , @NamedQuery(name = "Tiposusuarios.findByTipo", query = "SELECT t FROM Tiposusuarios t WHERE t.tipo = :tipo")})
public class Tiposusuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipousuario")
    private Integer idtipousuario;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtipousuario")
    private Collection<Roles> rolesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtipousuario")
    private Collection<Usuarios> usuariosCollection;

    public Tiposusuarios() {
    }

    public Tiposusuarios(Integer idtipousuario) {
        this.idtipousuario = idtipousuario;
    }

    public Tiposusuarios(Integer idtipousuario, String tipo) {
        this.idtipousuario = idtipousuario;
        this.tipo = tipo;
    }

    public Integer getIdtipousuario() {
        return idtipousuario;
    }

    public void setIdtipousuario(Integer idtipousuario) {
        this.idtipousuario = idtipousuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public Collection<Roles> getRolesCollection() {
        return rolesCollection;
    }

    public void setRolesCollection(Collection<Roles> rolesCollection) {
        this.rolesCollection = rolesCollection;
    }

    @XmlTransient
    public Collection<Usuarios> getUsuariosCollection() {
        return usuariosCollection;
    }

    public void setUsuariosCollection(Collection<Usuarios> usuariosCollection) {
        this.usuariosCollection = usuariosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipousuario != null ? idtipousuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tiposusuarios)) {
            return false;
        }
        Tiposusuarios other = (Tiposusuarios) object;
        if ((this.idtipousuario == null && other.idtipousuario != null) || (this.idtipousuario != null && !this.idtipousuario.equals(other.idtipousuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Tiposusuarios[ idtipousuario=" + idtipousuario + " ]";
    }
    
}
