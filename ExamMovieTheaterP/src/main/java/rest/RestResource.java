/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package rest;

import ejb.ejbtheaterLocal;
import entity.Moviemaster;
import entity.Theatermaster;
import jakarta.ejb.EJB;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collection;

/**
 * REST Web Service
 *
 * @author Admin
 */
@Path("generic")
@RequestScoped
public class RestResource {
    
    @EJB ejbtheaterLocal em;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RestResource
     */
    public RestResource() {
    }

    /**
     * Retrieves representation of an instance of rest.RestResource
     * @return an instance of java.lang.String
     */
    
    
    
    @POST
    @Path("addmovie/{moviename}")
    @Consumes("application/json")
    public void addmovie(@PathParam("moviename") String moviename, Collection<Integer> tids) {
        
        em.addmovie(moviename, tids);        
    }

    @GET
    @Path("getallmovies")
    @Produces("application/json")
    
    public Collection<Moviemaster> getallmovies() {
        return em.getallmovies();
    }

    @DELETE
    @Path("deletemoviebyid/{id}")
    public void deletemoviebyid(@PathParam("id") Integer id) {
        
        em.deletemoviebyid(id);
    }

    @GET
    @Path("getalltheatersbymovie/{id}")
    @Produces("application/json")
    public Collection<Theatermaster> getalltheatersbymovie(@PathParam("id") Integer id) {
                
        return em.getalltheatersbymovie(id);
    }
    
    
    @GET
    @Path("getallmoviesbytheater/{id}")
    @Produces("application/json")
    public Collection<Moviemaster> getallmoviesbytheater(@PathParam("id") Integer id) {
        
        return em.getallmoviesbytheater(id);
        
    }

    @GET
    @Path("getalltheaters")
    @Produces("application/json")
    public Collection<Theatermaster> getalltheaters() {
        
        return em.getalltheaters();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of RestResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
