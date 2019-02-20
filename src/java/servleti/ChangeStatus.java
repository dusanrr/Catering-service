package servleti;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import beans.*;
import java.util.*;


public class ChangeStatus extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
        
   
        HttpSession sesija = request.getSession();
        String poruka = "";
        String rStatus = (String)request.getParameter("status");
        int id = Integer.parseInt(request.getParameter("id"));
    
        Connection con = null;
        PreparedStatement pstmt = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
            Korisnik korisnik = (Korisnik)sesija.getAttribute("korisnik");
            if(request.getSession().getAttribute("korisnik") != null && korisnik.getPower().equals("Glavni menadzer"))
            {
                String sql = "update reklamacije set status_reklamacije='"+rStatus+"' where reklamacija_id='"+id+"'";
                pstmt = con.prepareStatement(sql); 
                pstmt.executeUpdate();
                request.setAttribute("poruka", "Uspesno ste izmenili status.");
                RequestDispatcher rd = request.getRequestDispatcher("administration/reclamation.jsp");
                rd.forward(request, response);
            }
            else
            {
                request.setAttribute("poruka", "Niste ulogovani ili niste glavni menadzer.");
                RequestDispatcher rd = request.getRequestDispatcher("administration/reclamation.jsp");
                rd.forward(request, response); 
            }
            pstmt.close();
            con.close();
        }
        catch(SQLException ex)
        {
            sesija.invalidate();
            String errormsg = ex.getMessage();
            if(con!=null)
                try
                {
                    pstmt.close();
                    con.close();
                }
                catch(Exception exc)
                {
                    errormsg = errormsg+exc.getMessage();
                }
            request.setAttribute("errormsg", errormsg);
            RequestDispatcher rd = request.getRequestDispatcher("administration/error.jsp");
            rd.forward(request, response);
        }
        catch(ClassNotFoundException cnf) {}
                
   
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
  
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
