package servleti;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import beans.*;
import javax.swing.JOptionPane;
public class EditUser extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession sesija = request.getSession();
        String poruka = "";
        String kime = (String)request.getParameter("kime");
        String imeprezime = (String)request.getParameter("fl");
        String username = (String)request.getParameter("uname");
        String password = (String)request.getParameter("pwd");
        String email = (String)request.getParameter("email");
        String telefon = (String)request.getParameter("phone");
        String adresa = (String)request.getParameter("address");
        String power = (String)request.getParameter("power");
        
        Connection con = null;
        PreparedStatement pstmt = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
            Korisnik korisnik = (Korisnik)sesija.getAttribute("korisnik");
            if(request.getSession().getAttribute("korisnik") != null && korisnik.getPower().equals("Administrator"))
            {
                String sql = "update korisnik set korisnik_imeprezime=IF(TRIM('"+imeprezime+"') != '', '"+imeprezime+"', `korisnik_imeprezime`), korisnik_username=IF(TRIM('"+username+"') != '', '"+username+"', `korisnik_username`), korisnik_password=IF(TRIM('"+password+"') != '', '"+password+"', `korisnik_password`), korisnik_email=IF(TRIM('"+email+"') != '', '"+email+"', `korisnik_email`), korisnik_telefon=IF(TRIM('"+telefon+"') != '', '"+telefon+"', `korisnik_telefon`), korisnik_adresa=IF(TRIM('"+adresa+"') != '', '"+adresa+"', `korisnik_adresa`), power=IF(TRIM('"+power+"') != '', '"+power+"', `power`) where korisnik_imeprezime='"+kime+"'";
                pstmt = con.prepareStatement(sql); 
                pstmt.executeUpdate();
                request.setAttribute("poruka", "Uspesno ste izmenili korisnika.");
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
