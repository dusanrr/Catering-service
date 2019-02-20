package servleti;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import beans.*;

public class DeleteUser extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession sesija = request.getSession();
        String poruka = "";
        String imeprezime = (String)request.getParameter("ikorisnika");
       
        Connection con = null;
        Statement stmt = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
            Korisnik korisnik = (Korisnik)sesija.getAttribute("korisnik");
            if(request.getSession().getAttribute("korisnik") != null && korisnik.getPower().equals("Administrator"))
            {
                stmt = con.createStatement();
                stmt.executeUpdate("delete from korisnik where korisnik_imeprezime='"+imeprezime+"'");
                request.setAttribute("poruka", "Uspesno ste obrisali korisnika.");
                RequestDispatcher rd = request.getRequestDispatcher("administration/index.jsp");
                rd.forward(request, response);
            }
            else
            {
                request.setAttribute("poruka", "Niste ulogovani ili niste administrator.");
                RequestDispatcher rd = request.getRequestDispatcher("administration/index.jsp");
                rd.forward(request, response);
            } 
            stmt.close();
            con.close();
        }
        catch(SQLException ex)
        {
           
            String errormsg = ex.getMessage();
            if(con!=null)
                try
                {
                    stmt.close();
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
