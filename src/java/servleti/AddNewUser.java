package servleti;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import beans.*;
public class AddNewUser extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession sesija = request.getSession();
        String poruka = "";
        String imeprezime = (String)request.getParameter("fl");
        String username = (String)request.getParameter("uname");
        String password = (String)request.getParameter("pwd");
        String email = (String)request.getParameter("email");
        String telefon = (String)request.getParameter("phone");
        String adresa = (String)request.getParameter("address");
        String power = (String)request.getParameter("power");
        
       if(username.isEmpty() || password.isEmpty())
        {
            poruka = "Niste popunili sva polja!";
            request.setAttribute("poruka", poruka);
            RequestDispatcher rd = request.getRequestDispatcher("administration/index.jsp");
            rd.forward(request, response);
        }
        Connection con = null;
        PreparedStatement pstmt = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
            Korisnik korisnik = (Korisnik)sesija.getAttribute("korisnik");
            if(request.getSession().getAttribute("korisnik") != null && korisnik.getPower().equals("Administrator"))
            {
                pstmt = con.prepareStatement("insert into korisnik(korisnik_imeprezime, korisnik_username, korisnik_password, korisnik_email, korisnik_telefon, korisnik_adresa, power) values(?,?,?,?,?,?,?)");
                pstmt.setString(1, imeprezime);
                pstmt.setString(2, username);
                pstmt.setString(3, password);
                pstmt.setString(4, email);
                pstmt.setString(5, telefon);
                pstmt.setString(6, adresa);
                pstmt.setString(7, power);
                pstmt.executeUpdate();
                request.setAttribute("poruka", "Uspesno ste dodali novog korisnika.");
                RequestDispatcher rd = request.getRequestDispatcher("administration/index.jsp");
                rd.forward(request, response);
            }
            else
            {
                request.setAttribute("poruka", "Niste ulogovani ili niste administrator.");
                RequestDispatcher rd = request.getRequestDispatcher("administration/index.jsp");
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
