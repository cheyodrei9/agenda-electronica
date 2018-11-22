/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eliseo.garciausam
 */
@Entity
@Table(name = "actividades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actividades.findAll", query = "SELECT a FROM Actividades a")
    , @NamedQuery(name = "Actividades.findByIdActividad", query = "SELECT a FROM Actividades a WHERE a.idActividad = :idActividad")
    , @NamedQuery(name = "Actividades.findByNombreaActividad", query = "SELECT a FROM Actividades a WHERE a.nombreaActividad = :nombreaActividad")
    , @NamedQuery(name = "Actividades.findByFechaActividad", query = "SELECT a FROM Actividades a WHERE a.fechaActividad = :fechaActividad")})
public class Actividades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_actividad")
    private Integer idActividad;
    @Basic(optional = false)
    @Column(name = "nombrea_actividad")
    private String nombreaActividad;
    @Basic(optional = false)
    @Column(name = "fecha_actividad")
    @Temporal(TemporalType.DATE)
    private Date fechaActividad;
    @JoinColumn(name = "id_cronograma", referencedColumnName = "id_cronograma")
    @ManyToOne(optional = false)
    private Cronogramas idCronograma;
    @JoinColumn(name = "id_tipo_actividad", referencedColumnName = "id_tipo_actividad")
    @ManyToOne(optional = false)
    private TiposActividades idTipoActividad;
    @JoinColumn(name = "id _fases", referencedColumnName = "id_fase")
    @ManyToOne(optional = false)
    private Fases idFases;

    public Actividades() {
    }

    public Actividades(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public Actividades(Integer idActividad, String nombreaActividad, Date fechaActividad) {
        this.idActividad = idActividad;
        this.nombreaActividad = nombreaActividad;
        this.fechaActividad = fechaActividad;
    }

    public Integer getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public String getNombreaActividad() {
        return nombreaActividad;
    }

    public void setNombreaActividad(String nombreaActividad) {
        this.nombreaActividad = nombreaActividad;
    }

    public Date getFechaActividad() {
        return fechaActividad;
    }

    public void setFechaActividad(Date fechaActividad) {
        this.fechaActividad = fechaActividad;
    }

    public Cronogramas getIdCronograma() {
        return idCronograma;
    }

    public void setIdCronograma(Cronogramas idCronograma) {
        this.idCronograma = idCronograma;
    }

    public TiposActividades getIdTipoActividad() {
        return idTipoActividad;
    }

    public void setIdTipoActividad(TiposActividades idTipoActividad) {
        this.idTipoActividad = idTipoActividad;
    }

    public Fases getIdFases() {
        return idFases;
    }

    public void setIdFases(Fases idFases) {
        this.idFases = idFases;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActividad != null ? idActividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actividades)) {
            return false;
        }
        Actividades other = (Actividades) object;
        if ((this.idActividad == null && other.idActividad != null) || (this.idActividad != null && !this.idActividad.equals(other.idActividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Actividades[ idActividad=" + idActividad + " ]";
    }
    
}
