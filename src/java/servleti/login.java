package servleti;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import beans.*;



public class login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesija = request.getSession();
        String poruka = "";
        String username = (String)request.getParameter("username");
        String password = (String)request.getParameter("password");
        Korisnik korisnik = new Korisnik();
        korisnik.setUsername(username);
        korisnik.setPassword(password);
        sesija.setAttribute("korisnik", korisnik);
        if(username.isEmpty() || password.isEmpty())
        {
            poruka = "Niste popunili sva polja!";
            request.setAttribute("poruka", poruka);
            RequestDispatcher rd = request.getRequestDispatcher("customer/index.jsp");
            rd.forward(request, response);
        }
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
            stmt = con.createStatement();
            String upit = "select * from korisnik where korisnik_username='"+username+"' and korisnik_password='"+password+"'";
            rs = stmt.executeQuery(upit);
            if(rs.next())
            {
                int id = rs.getInt("korisnik_id");
                String imeprezime = rs.getString("korisnik_imeprezime");
                String email = rs.getString("korisnik_email");
                String telefon = rs.getString("korisnik_telefon");
                String adresa = rs.getString("korisnik_adresa");
                String power = rs.getString("power");
                korisnik.setId(id);
                korisnik.setImeprezime(imeprezime);
                korisnik.setEmail(email);
                korisnik.setTelefon(telefon);
                korisnik.setAdresa(adresa);
                korisnik.setPower(power);
                
                rs.close();
                stmt.close();
                con.close();
            }
            else
            {
                poruka = "Neispravno korisnicko ime i lozinka!Pokusajte ponovo.";
                request.setAttribute("poruka", poruka);
                korisnik.setPassword("");
                RequestDispatcher rd = request.getRequestDispatcher("customer/index.jsp");
                sesija.invalidate();
                rs.close();
                stmt.close();
                con.close();
                rd.forward(request, response);
            }
        }
        catch(SQLException ex)
        {
            sesija.invalidate();
            String errormsg = ex.getMessage();
            if(con!=null)
                try
                {
                    rs.close();
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
        
        if(korisnik.getPower().matches("Klijent"))
        {
            request.setAttribute("poruka", "Uspesno ste se ulogovali kao klijent");
            RequestDispatcher rd = request.getRequestDispatcher("customer/index.jsp");
            rd.forward(request, response);
        }
        else
        {
            request.setAttribute("poruka", "Uspesno ste se ulogovali kao admin");
            RequestDispatcher rd = request.getRequestDispatcher("administration/index.jsp");
            rd.forward(request, response);
        }
           
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
