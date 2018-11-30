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
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u")
    , @NamedQuery(name = "Usuarios.findByIdusuario", query = "SELECT u FROM Usuarios u WHERE u.idusuario = :idusuario")
    , @NamedQuery(name = "Usuarios.findByNombres", query = "SELECT u FROM Usuarios u WHERE u.nombres = :nombres")
    , @NamedQuery(name = "Usuarios.findByApellidos", query = "SELECT u FROM Usuarios u WHERE u.apellidos = :apellidos")
    , @NamedQuery(name = "Usuarios.findByGenero", query = "SELECT u FROM Usuarios u WHERE u.genero = :genero")
    , @NamedQuery(name = "Usuarios.findByTelefono", query = "SELECT u FROM Usuarios u WHERE u.telefono = :telefono")
    , @NamedQuery(name = "Usuarios.findByCorreo", query = "SELECT u FROM Usuarios u WHERE u.correo = :correo")
    , @NamedQuery(name = "Usuarios.findByContra", query = "SELECT u FROM Usuarios u WHERE u.contra = :contra")
    , @NamedQuery(name = "Usuarios.findByNiveldemando", query = "SELECT u FROM Usuarios u WHERE u.niveldemando = :niveldemando")})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusuario")
    private Integer idusuario;
    @Basic(optional = false)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @Column(name = "apellidos")
    private String apellidos;
    @Basic(optional = false)
    @Column(name = "genero")
    private String genero;
    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @Column(name = "contra")
    private String contra;
    @Basic(optional = false)
    @Column(name = "niveldemando")
    private int niveldemando;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private Collection<Notificaciones> notificacionesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private Collection<Cronogramas> cronogramasCollection;
    @JoinColumn(name = "idtipousuario", referencedColumnName = "idtipousuario")
    @ManyToOne(optional = false)
    private Tiposusuarios idtipousuario;

    public Usuarios() {
    }

    public Usuarios(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Usuarios(Integer idusuario, String nombres, String apellidos, String genero, String telefono, String correo, String contra, int niveldemando) {
        this.idusuario = idusuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.genero = genero;
        this.telefono = telefono;
        this.correo = correo;
        this.contra = contra;
        this.niveldemando = niveldemando;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public int getNiveldemando() {
        return niveldemando;
    }

    public void setNiveldemando(int niveldemando) {
        this.niveldemando = niveldemando;
    }

    @XmlTransient
    public Collection<Notificaciones> getNotificacionesCollection() {
        return notificacionesCollection;
    }

    public void setNotificacionesCollection(Collection<Notificaciones> notificacionesCollection) {
        this.notificacionesCollection = notificacionesCollection;
    }

    @XmlTransient
    public Collection<Cronogramas> getCronogramasCollection() {
        return cronogramasCollection;
    }

    public void setCronogramasCollection(Collection<Cronogramas> cronogramasCollection) {
        this.cronogramasCollection = cronogramasCollection;
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
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

   /* @Override
    public String toString() {
        return "Persistencia.Usuarios[ idusuario=" + idusuario + " ]";
    }*/

    @Override
    public String toString() {
        return "Usuarios{" + "idusuario=" + idusuario + ", nombres=" + nombres + ", apellidos=" + apellidos + ", genero=" + genero + ", telefono=" + telefono + ", correo=" + correo + ", contra=" + contra + ", niveldemando=" + niveldemando + '}';
    }
    
    
    
}
