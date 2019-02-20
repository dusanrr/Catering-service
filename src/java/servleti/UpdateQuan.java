package servleti;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import beans.*;
import java.util.*;


public class UpdateQuan extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
        
   
        HttpSession sesija = request.getSession();
        String poruka = "";
        String kolicina = (String)request.getParameter("kolicina");
        int id = Integer.parseInt(request.getParameter("id"));
        String ime = (String)request.getParameter("ime");
    
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
            Korisnik korisnik = (Korisnik)sesija.getAttribute("korisnik");
            if(request.getSession().getAttribute("korisnik") != null)
            {
                String upit = "select * from proizvodi where naziv_proizvoda='"+ime+"'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(upit);
                if(rs.next())
                {
                    int kolicinaizbaze = rs.getInt("kolicina_proizvoda");
                    if(kolicinaizbaze < Integer.parseInt(kolicina))
                    {
                        request.setAttribute("poruka", "Na stanju nema toliko proizvoda.");
                        RequestDispatcher rd = request.getRequestDispatcher("customer/korpa.jsp");
                        rd.forward(request, response);
                    }
                    else
                    {
                        String sql = "update korpa set kolicina='"+kolicina+"', ukupna_cena=proizvod_cena*kolicina where id='"+id+"'";
                        pstmt = con.prepareStatement(sql); 
                        pstmt.executeUpdate();
                        request.setAttribute("poruka", "Uspesno ste promenili kolicinu.");
                        RequestDispatcher rd = request.getRequestDispatcher("customer/korpa.jsp");
                        rd.forward(request, response);
                    }
                }
            }
            rs.close();
            pstmt.close();
            stmt.close();
            con.close();        
        }
        catch(SQLException ex)
        {
            sesija.invalidate();
            String errormsg = ex.getMessage();
            if(con!=null)
                try
                {
                    rs.close();
                    pstmt.close();
                    stmt.close();
                    con.close(); 
                }
                catch(Exception exc)
                {
                    errormsg = errormsg+exc.getMessage();
                }
            request.setAttribute("errormsg", errormsg);
            RequestDispatcher rd = request.getRequestDispatcher("customer/error.jsp");
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
