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
@Table(name = "actividades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actividades.findAll", query = "SELECT a FROM Actividades a")
    , @NamedQuery(name = "Actividades.findByIdActividad", query = "SELECT a FROM Actividades a WHERE a.idActividad = :idActividad")
    , @NamedQuery(name = "Actividades.findByNombreaActividad", query = "SELECT a FROM Actividades a WHERE a.nombreaActividad = :nombreaActividad")})
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
    @JoinColumn(name = "id_cronograma", referencedColumnName = "id_cronograma")
    @ManyToOne(optional = false)
    private Cronogramas idCronograma;
    @JoinColumn(name = "id_tipo_actividad", referencedColumnName = "id_tipo_actividad")
    @ManyToOne(optional = false)
    private TiposActividades idTipoActividad;

    public Actividades() {
    }

    public Actividades(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public Actividades(Integer idActividad, String nombreaActividad) {
        this.idActividad = idActividad;
        this.nombreaActividad = nombreaActividad;
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
