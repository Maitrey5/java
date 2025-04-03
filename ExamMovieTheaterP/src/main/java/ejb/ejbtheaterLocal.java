/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SessionLocal.java to edit this template
 */
package ejb;

import entity.Moviemaster;
import entity.Theatermaster;
import jakarta.ejb.Local;
import java.util.Collection;

/**
 *
 * @author Admin
 */
@Local
public interface ejbtheaterLocal {
    
    void addmovie(String moviename , Collection<Integer> tids);
    Collection<Moviemaster> getallmovies();
    
    void deletemoviebyid(Integer id);
    
    Collection<Theatermaster> getalltheaters();

    
    Collection<Theatermaster> getalltheatersbymovie(Integer id);
    Collection<Moviemaster> getallmoviesbytheater(Integer id);
    
    
}
