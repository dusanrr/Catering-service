package servleti;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import beans.*;


public class reklamiraj extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession sesija = request.getSession();
        String poruka = "";
        String nazivp = (String)request.getParameter("reklamiraj");
        String tekst = (String)request.getParameter("tekst");

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
            pstmt = con.prepareStatement("insert into reklamacije (reklamirao, proizvod_za_reklamaciju, status_reklamacije, opis_reklamacije) values(?,?,?,?)");
            Korisnik korisnik = (Korisnik)sesija.getAttribute("korisnik");
            if(request.getSession().getAttribute("korisnik") != null)
            {
                pstmt.setString(1, korisnik.getImeprezime());
                pstmt.setString(2, nazivp);
                pstmt.setString(3, "On hold");
                pstmt.setString(4, tekst);
                pstmt.executeUpdate();
                request.setAttribute("poruka", "Uspesno ste reklamirali proizvod.");
                RequestDispatcher rd = request.getRequestDispatcher("customer/reklamacije.jsp");
                rd.forward(request, response);
            }
            else
            {
                request.setAttribute("poruka", "Niste ulogovani.");
                RequestDispatcher rd = request.getRequestDispatcher("customer/index.jsp");
                rd.forward(request, response);
            }
            rs.close();
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
                    rs.close();
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
