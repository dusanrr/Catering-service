package servleti;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import beans.*;
public class EditProduct extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession sesija = request.getSession();
        String poruka = "";
        String pnaziv = (String)request.getParameter("pnaziv");
        String nazivp = (String)request.getParameter("nazivp");
        String cenap = (String)request.getParameter("cenap");
        String kolicinap = (String)request.getParameter("kolicinap");
        String kategorijap = (String)request.getParameter("kategorijap");

        Connection con = null;
        PreparedStatement pstmt = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
            Korisnik korisnik = (Korisnik)sesija.getAttribute("korisnik");
            if(request.getSession().getAttribute("korisnik") != null && korisnik.getPower().equals("Sef kuhinje"))
            {
                String sql = "update proizvodi set naziv_proizvoda=IF(TRIM('"+nazivp+"') != '', '"+nazivp+"', `naziv_proizvoda`), cena_proizvoda=IF(TRIM('"+cenap+"') != '', '"+cenap+"', `cena_proizvoda`), kolicina_proizvoda=IF(TRIM('"+kolicinap+"') != '', '"+kolicinap+"', `kolicina_proizvoda`), kategorija_proizvoda=IF(TRIM('"+kategorijap+"') != '', '"+kategorijap+"', `kategorija_proizvoda`) where naziv_proizvoda='"+pnaziv+"'";
                pstmt = con.prepareStatement(sql); 
                pstmt.executeUpdate();
                request.setAttribute("poruka", "Uspesno ste izmenili proizvod.");
                RequestDispatcher rd = request.getRequestDispatcher("administration/index.jsp");
                rd.forward(request, response);
            }
            else
            {
                request.setAttribute("poruka", "Niste ulogovani ili niste sef kuhinje.");
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
