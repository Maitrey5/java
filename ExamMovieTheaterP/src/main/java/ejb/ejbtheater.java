/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ejb;

import entity.Moviemaster;
import entity.Theatermaster;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Admin
 */
@Stateless
public class ejbtheater implements ejbtheaterLocal {
    
    @PersistenceContext(unitName = "theaterpu")
    EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void addmovie(String moviename, Collection<Integer> tids) {
        
        Moviemaster m = new Moviemaster();
        
        m.setMoviename(moviename);
        
        Collection<Theatermaster> t = new ArrayList<>();
        
        for(Integer q:tids)
        {
            Theatermaster o = (Theatermaster) em.find(Theatermaster.class, q);
            
            if(o != null)
            {
                
                t.add(o);
//                Collection<Moviemaster> s = o.getMoviemasterCollection();
//                s.add(m);
//                o.setMoviemasterCollection(s);
//                em.merge(o);
//                t.add(o);
            }
        }
        
        m.setTheatermasterCollection(t);
        em.persist(m);
        
        
        for(Integer q:tids)
        {
            Theatermaster o = (Theatermaster) em.find(Theatermaster.class, q);
            
            if(o != null)
            {
                
                //t.add(o);
                Collection<Moviemaster> s = o.getMoviemasterCollection();
                s.add(m);
                o.setMoviemasterCollection(s);
                em.merge(o);
                
            }
        }
        
        
    }

    @Override
    public Collection<Moviemaster> getallmovies() {
        return em.createNamedQuery("Moviemaster.findAll").getResultList();
    }

    @Override
    public void deletemoviebyid(Integer id) {
        
        Moviemaster ml = (Moviemaster) em.find(Moviemaster.class,id);
        
        Collection<Theatermaster> k = ml.getTheatermasterCollection();
        
        for(Theatermaster m:k)
        {
            if(m!=null)
            {
                Collection<Moviemaster> d = m.getMoviemasterCollection();
                d.remove(ml);
                m.setMoviemasterCollection(d);
                em.merge(m);
            }
        }
        
        em.remove(ml);
        
        
    }

    @Override
    public Collection<Theatermaster> getalltheatersbymovie(Integer id) {
        
        Moviemaster mp = (Moviemaster) em.find(Moviemaster.class, id);
        
        return mp.getTheatermasterCollection();
    }

    @Override
    public Collection<Moviemaster> getallmoviesbytheater(Integer id) {
        
        Theatermaster tm = (Theatermaster) em.find(Theatermaster.class, id);
        
        return tm.getMoviemasterCollection();
        
    }

    @Override
    public Collection<Theatermaster> getalltheaters() {
        
        return em.createNamedQuery("Theatermaster.findAll").getResultList();
    }
    
    
}
