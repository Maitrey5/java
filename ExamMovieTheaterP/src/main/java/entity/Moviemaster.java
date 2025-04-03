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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
@Table(name = "moviemaster")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Moviemaster.findAll", query = "SELECT m FROM Moviemaster m"),
    @NamedQuery(name = "Moviemaster.findByMovieid", query = "SELECT m FROM Moviemaster m WHERE m.movieid = :movieid"),
    @NamedQuery(name = "Moviemaster.findByMoviename", query = "SELECT m FROM Moviemaster m WHERE m.moviename = :moviename")})
public class Moviemaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "movieid")
    private Integer movieid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "moviename")
    private String moviename;
    @JoinTable(name = "movie_theater", joinColumns = {
        @JoinColumn(name = "movieid", referencedColumnName = "movieid")}, inverseJoinColumns = {
        @JoinColumn(name = "theaterid", referencedColumnName = "theaterid")})
    @ManyToMany
    private Collection<Theatermaster> theatermasterCollection;

    public Moviemaster() {
    }

    public Moviemaster(Integer movieid) {
        this.movieid = movieid;
    }

    public Moviemaster(Integer movieid, String moviename) {
        this.movieid = movieid;
        this.moviename = moviename;
    }

    public Integer getMovieid() {
        return movieid;
    }

    public void setMovieid(Integer movieid) {
        this.movieid = movieid;
    }

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<Theatermaster> getTheatermasterCollection() {
        return theatermasterCollection;
    }

    public void setTheatermasterCollection(Collection<Theatermaster> theatermasterCollection) {
        this.theatermasterCollection = theatermasterCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (movieid != null ? movieid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Moviemaster)) {
            return false;
        }
        Moviemaster other = (Moviemaster) object;
        if ((this.movieid == null && other.movieid != null) || (this.movieid != null && !this.movieid.equals(other.movieid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Moviemaster[ movieid=" + movieid + " ]";
    }
    
}
