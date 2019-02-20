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
public class order extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession sesija = request.getSession();
        String poruka = "";
        
        Connection con = null;
        PreparedStatement pstmt = null;
        Statement stmt = null;
        ResultSet rs = null;
        Korisnik korisnik = (Korisnik)sesija.getAttribute("korisnik");
        int korisnikid;
        korisnikid = korisnik.getId();
        String imekorisnika = korisnik.getImeprezime();
        String sql = "insert into narudzbine(korisnik_id, korisnik_imeprezime, proizvod_id, proizvod_naziv, proizvod_cena, kolicina, ukupna_cena, adresa_dostave, datum_dostave, kategorija) values(?,?,?,?,?,?,?,?,?,?)";
        String dquery = "delete from korpa where korisnik_id='"+korisnikid+"'";
        String sql1 = "select * from korpa where korisnik_id='"+korisnikid+"'";
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
            stmt=con.createStatement();
            rs= stmt.executeQuery(sql1);
           
            int i = 0;
            while(rs.next())
            {
                int idpr = rs.getInt("proizvod_id");
                String nazivpr = rs.getString("proizvod_naziv");
                double cenapr = rs.getDouble("proizvod_cena");
                int kolicinapr = rs.getInt("kolicina");
                String ukupnacena = rs.getString("ukupna_cena");
                String kategorijapr = rs.getString("kategorija");
                String adresadostave = rs.getString("adresa_dostave");
                String datumdostave =  rs.getString("datum_dostave");
                
                pstmt = con.prepareStatement(sql); 
                pstmt.setInt(1, korisnikid);
                pstmt.setString(2, imekorisnika);
                pstmt.setInt(3, idpr);
                pstmt.setString(4, nazivpr);
                pstmt.setDouble(5, cenapr);
                pstmt.setInt(6, kolicinapr);
                pstmt.setString(7, ukupnacena);
                pstmt.setString(8, adresadostave);
                pstmt.setString(9, datumdostave);
                pstmt.setString(10, kategorijapr);
                pstmt.executeUpdate();
            }
            stmt = con.createStatement();
            stmt.executeUpdate(dquery);
            
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
        request.setAttribute("poruka", "Uspesno ste se narucili proizvod.");
        RequestDispatcher rd = request.getRequestDispatcher("customer/narudzbine.jsp");
        rd.forward(request, response);
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
