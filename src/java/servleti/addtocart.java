package servleti;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import beans.*;

/**
 *
 * @author Dusan
 */
public class addtocart extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession sesija = request.getSession();
        String poruka = "";
        String proizvod = (String)request.getParameter("proizvod");
        String kolicina = (String)request.getParameter("kolicina");
        String datumdostave = (String)request.getParameter("datumdostave");
        String adresadostave = (String)request.getParameter("adresa");
        if(proizvod.isEmpty()  || kolicina.isEmpty()  || datumdostave.isEmpty() || adresadostave.isEmpty())
        {
            poruka = "Niste popunili sva polja!";
            request.setAttribute("poruka", poruka);
            RequestDispatcher rd = request.getRequestDispatcher("customer/korpa.jsp");
            rd.forward(request, response);
        }
        Connection con = null;
        PreparedStatement pstmt = null;
        Statement stmt = null;
        ResultSet rs = null;
        Korisnik korisnik = (Korisnik)sesija.getAttribute("korisnik");
        int korisnikid = (int)korisnik.getId();
        String imekorisnika = korisnik.getImeprezime();
        String sql = "insert into korpa(korisnik_id, korisnik_imeprezime, proizvod_id, proizvod_naziv, proizvod_cena, kolicina, ukupna_cena, adresa_dostave, datum_dostave, kategorija) values(?,?,?,?,?,?,?,?,?,?)";
        String sql1 = "select * from proizvodi where naziv_proizvoda='"+proizvod+"'";
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
            stmt=con.createStatement();
            rs= stmt.executeQuery(sql1);
           
            if(rs.next())
            {   
                int idpr = rs.getInt("id_proizvoda");
                String nazivpr = rs.getString("naziv_proizvoda");
                double cenapr = rs.getDouble("cena_proizvoda");
                int kolicinapr = rs.getInt("kolicina_proizvoda");
                double ukupnacena = cenapr*Integer.parseInt(kolicina);
                String kategorijapr = rs.getString("kategorija_proizvoda");
                
                if(kolicinapr < Integer.parseInt(kolicina))
                {
                    request.setAttribute("poruka", "Na stanju nema toliko proizvoda.");
                    RequestDispatcher rd = request.getRequestDispatcher("customer/proizvodi.jsp");
                    rd.forward(request, response);
                }
                else
                {
                    pstmt = con.prepareStatement(sql); 
                    pstmt.setInt(1, korisnikid);
                    pstmt.setString(2, imekorisnika);
                    pstmt.setInt(3, idpr);
                    pstmt.setString(4, proizvod);
                    pstmt.setDouble(5, cenapr);
                    pstmt.setInt(6, Integer.parseInt(kolicina));
                    pstmt.setDouble(7, ukupnacena);
                    pstmt.setString(8, adresadostave);
                    pstmt.setString(9, datumdostave);
                    pstmt.setString(10, kategorijapr);
                    int i= pstmt.executeUpdate();
                    
                    request.setAttribute("poruka", "Uspesno ste dodali proizvod u korpu.");
                    RequestDispatcher rd = request.getRequestDispatcher("customer/korpa.jsp");
                    rd.forward(request, response);
                }
            }
            rs.close();
            pstmt.close();
            stmt.close();
            con.close();
        }
        catch(SQLException ex)
        {
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
