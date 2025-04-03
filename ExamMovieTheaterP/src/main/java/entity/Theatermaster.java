/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "theatermaster")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Theatermaster.findAll", query = "SELECT t FROM Theatermaster t"),
    @NamedQuery(name = "Theatermaster.findByTheaterid", query = "SELECT t FROM Theatermaster t WHERE t.theaterid = :theaterid"),
    @NamedQuery(name = "Theatermaster.findByTheatername", query = "SELECT t FROM Theatermaster t WHERE t.theatername = :theatername"),
    @NamedQuery(name = "Theatermaster.findByCity", query = "SELECT t FROM Theatermaster t WHERE t.city = :city"),
    @NamedQuery(name = "Theatermaster.findByState", query = "SELECT t FROM Theatermaster t WHERE t.state = :state"),
    @NamedQuery(name = "Theatermaster.findByLocation", query = "SELECT t FROM Theatermaster t WHERE t.location = :location")})
public class Theatermaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "theaterid")
    private Integer theaterid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "theatername")
    private String theatername;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "city")
    private String city;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "state")
    private String state;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "location")
    private String location;
    @ManyToMany(mappedBy = "theatermasterCollection")
    private Collection<Moviemaster> moviemasterCollection;

    public Theatermaster() {
    }

    public Theatermaster(Integer theaterid) {
        this.theaterid = theaterid;
    }

    public Theatermaster(Integer theaterid, String theatername, String city, String state, String location) {
        this.theaterid = theaterid;
        this.theatername = theatername;
        this.city = city;
        this.state = state;
        this.location = location;
    }

    public Integer getTheaterid() {
        return theaterid;
    }

    public void setTheaterid(Integer theaterid) {
        this.theaterid = theaterid;
    }

    public String getTheatername() {
        return theatername;
    }

    public void setTheatername(String theatername) {
        this.theatername = theatername;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<Moviemaster> getMoviemasterCollection() {
        return moviemasterCollection;
    }

    public void setMoviemasterCollection(Collection<Moviemaster> moviemasterCollection) {
        this.moviemasterCollection = moviemasterCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (theaterid != null ? theaterid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Theatermaster)) {
            return false;
        }
        Theatermaster other = (Theatermaster) object;
        if ((this.theaterid == null && other.theaterid != null) || (this.theaterid != null && !this.theaterid.equals(other.theaterid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Theatermaster[ theaterid=" + theaterid + " ]";
    }
    
}
