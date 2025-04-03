/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import client.realclient;
import ejb.ejbtheater;
import ejb.ejbtheaterLocal;
import entity.Moviemaster;
import entity.Theatermaster;
import jakarta.ejb.EJB;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Admin
 */
@WebServlet(name = "simpleservlet", urlPatterns = {"/simpleservlet"})
public class simpleservlet extends HttpServlet {
    
    
    @EJB ejbtheaterLocal em;
    Response rs;
    realclient rc = new realclient() ;
    
    Collection<Moviemaster> movies = new ArrayList<>();
    Collection<Theatermaster> theaters = new ArrayList<>();
    
    GenericType<Collection<Moviemaster>> gmovies = new GenericType<Collection<Moviemaster>>(){};
    GenericType<Collection<Theatermaster>> gtheaters = new GenericType<Collection<Theatermaster>>(){};
    
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet simpleservlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet simpleservlet at " + request.getContextPath() + "</h1>");
            
            String moviename = request.getParameter("movie");
            if(moviename != null)
            {
                String[] s = request.getParameterValues("theater");
                
                Collection<Integer> c = new ArrayList<>();
                
                for(String p:s)
                {
                    Integer a = Integer.parseInt(p);
                    c.add(a);
                }
                
                em.addmovie(moviename, c);
            }
            
//            Collection<Moviemaster> movielist = em.getallmovies();
            rs= rc.getallmovies(Response.class);
            movies = rs.readEntity(gmovies);

            
            out.println("<table border='1'><tr><td>Moviename</td><td>Theaters</td><td>City</td><td>State</td><td>location</td><td>TO Delete</td></tr>");
            
            for(Moviemaster k:movies)
            {
               out.println("<tr>"); 
               out.println("<td>"+k.getMoviename()+"</td>");
               
               rs = rc.getalltheatersbymovie(Response.class,k.getMovieid());
               theaters = rs.readEntity(gtheaters);
               
//               Collection<Theatermaster> tu = k.getTheatermasterCollection();
               Theatermaster temp = new Theatermaster();
               out.println("<td><ul>");
               for(Theatermaster as:theaters)
               {
                   out.println("<li>"+as.getTheatername()+"</li>");
                   temp = as;
               }
               out.println("</ul></td>");
                 
               out.println("<td>"+temp.getCity()+"</td><td>"+temp.getState()+"</td><td>"+temp.getLocation()+"</td><td><a href='simpleservlet?del_id="+k.getMovieid()+"'>delete</a></td>");
               out.println("</tr>"); 
                      
            }
            out.println("</table>");
            
            
            out.println("<br>");
            out.println("<br>");
            out.println("<br>");
            out.println("<br>");
            
            
            
//            Collection<Theatermaster> theaterlist = em.getalltheaters();

            rs = rc.getalltheaters(Response.class);
            theaters = rs.readEntity(gtheaters);

                        
            out.println("<table border='1'><tr><td>theatername</td><td>movies</td><td>City</td><td>State</td><td>location</td></tr>");
            
            for(Theatermaster z:theaters)
            {
               out.println("<tr>"); 
               out.println("<td>"+z.getTheatername()+"</td>");
               
               //Collection<Moviemaster> tu = z.getMoviemasterCollection();
               
               rs = rc.getallmoviesbytheater(Response.class,z.getTheaterid());
               movies = rs.readEntity(gmovies);
               
               
               out.println("<td><ul>");
               for(Moviemaster as:movies)
               {
                   out.println("<li>"+as.getMoviename()+"</li>");
               }
               out.println("</ul></td>");
                 
               out.println("<td>"+z.getCity()+"</td><td>"+z.getState()+"</td><td>"+z.getLocation()+"</td>");
               out.println("</tr>"); 
                      
            }
            out.println("</table>");

            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String del_id = request.getParameter("del_id");
        if(del_id != null)
        {
            Integer i = Integer.parseInt(del_id);
            em.deletemoviebyid(i);
        }
        
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
