package servleti;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import beans.*;
public class register extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesija = request.getSession();
        String poruka = "";
        Korisnik korisnik = new Korisnik();
        String imeprezime = (String)request.getParameter("imeprezime");
        String username = (String)request.getParameter("username");
        String password = (String)request.getParameter("password");
        String email = (String)request.getParameter("email");
        String telefon = (String)request.getParameter("telefon");
        String adresa = (String)request.getParameter("adresa");
         
        if(username.isEmpty() || password.isEmpty() || imeprezime.isEmpty() || email.isEmpty() || telefon.isEmpty() || adresa.isEmpty())
        {
            poruka = "Niste popunili sva polja!";
            request.setAttribute("poruka", poruka);
            RequestDispatcher rd = request.getRequestDispatcher("customer/register.jsp");
            rd.forward(request, response);
        }
        Connection con = null;
        PreparedStatement pstmt = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
            pstmt = con.prepareStatement("insert into korisnik values(?,?,?,?,?,?,?,?)");
            pstmt.setInt(1, korisnik.getId());
            pstmt.setString(2, imeprezime);
            pstmt.setString(3, username);
            pstmt.setString(4, password);
            pstmt.setString(5, email);
            pstmt.setString(6, telefon);
            pstmt.setString(7, adresa);
            pstmt.setString(8, "Klijent");
            pstmt.executeUpdate(); 
            
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
            RequestDispatcher rd = request.getRequestDispatcher("customer/error.jsp");
            rd.forward(request, response);
        }
        catch(ClassNotFoundException cnf) {}
        request.setAttribute("poruka", "Uspesno ste se registrovali.");
        RequestDispatcher rd = request.getRequestDispatcher("customer/index.jsp");
        rd.forward(request, response);
  
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
