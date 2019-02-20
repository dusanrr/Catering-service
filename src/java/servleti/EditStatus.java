package servleti;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import beans.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Dusan
 */
public class EditStatus extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>"
                + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "        <link href=\"administration/css/style.css\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
                "        <script src=\"administration/js/jquery.min.js\"></script>\n" +
                "        <script src=\"administration/js/jquery-ui.min.js\"></script>\n" +
                "        <link href=\"https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
                "        <link href='https://fonts.googleapis.com/css?family=Great+Vibes' rel='stylesheet' type='text/css'>\n" +
                "        <title>Catering Service - ADMIN PANEL</title>");
        out.println("</head>");
        out.println("<body>\n" +
                "    \n" +
                "        \n" +
                "      <!-- POPUP ADD NEW USER -->\n" +
                "    <div class=\"addnewuserpopup\" >\n" +
                "      <div class=\"popup-dialog\">\n" +
                "        <div class=\"popup-header\">\n" +
                "          <h2>ADD NEW USER</h2>\n" +
                "          <a href=\"#\" class=\"btn-close closeaddnewuserpopup\" aria-hidden=\"true\">&times;</a>\n" +
                "        </div>\n" +
                "        <div class=\"popup-body\">\n" +
                "            <form method=\"POST\" action=\"AddNewUser\">\n" +
                "            <label>First and last name</label><br>\n" +
                "            <i class='fa fa-user'></i><input type=\"text\" name=\"fl\"  required placeholder=\"Name\" size=\"20\" /><br><br>\n" +
                "            <label>Username</label><br>\n" +
                "            <i class='fa fa-user'></i><input type=\"text\" name=\"uname\" required placeholder=\"Username\" size=\"20\" /><br><br>\n" +
                "            <label>Password</label><br>\n" +
                "            <i class='fa fa-key'></i><input type=\"password\" name=\"pwd\" required placeholder=\"Password\" size=\"20\" /><br><br>\n" +
                "            <label>Email</label><br>\n" +
                "            <i class='fa fa-envelope'></i><input type=\"text\" name=\"email\"  required placeholder=\"Email\" size=\"20\" /><br><br>\n" +
                "            <label>Phone</label><br>\n" +
                "            <i class='fa fa-phone'></i><input type=\"text\" name=\"phone\"  placeholder=\"Phone\" size=\"20\" /><br><br>\n" +
                "            <label>Address</label><br>\n" +
                "            <i class='fa fa-home'></i><input type=\"text\" name=\"address\" placeholder=\"Address\" size=\"20\" /><br><br>\n" +
                "            <label>Power</label><br>\n" +
                "            <i class='fa fa-flash'></i><select name=\"power\">\n" +
                "                <option>Klijent</option>\n" +
                "                <option>Administrator</option>\n" +
                "                <option>Glavni menadzer</option>\n" +
                "                <option>Sef poslovnice</option>\n" +
                "                <option>Sef kuhinje</option>\n" +
                "            </select><br><br>\n" +
                "        </div>\n" +
                "        <div class=\"popup-footer\">\n" +
                "                <input type=\"submit\" value=\"ADD\" class=\"btn\" id=\"btn_ingresar\"/>\n" +
                "            </form>\n" +
                "        </div>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "        \n" +
                "         <!-- POPUP EDIT USER -->\n" +
                "    <div class=\"edituserpopup\" >\n" +
                "      <div class=\"popup-dialog\">\n" +
                "        <div class=\"popup-header\">\n" +
                "          <h2>EDIT USER</h2>\n" +
                "          <a href=\"#\" class=\"btn-close closeedituserpopup\" aria-hidden=\"true\">&times;</a>\n" +
                "        </div>\n" +
                "        <div class=\"popup-body\">\n" +
                "            <form method=\"POST\" action=\"EditUser\">");
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        HttpSession sesija = request.getSession();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
            stmt = con.createStatement();
            String upit = "select * from korisnik";
            rs = stmt.executeQuery(upit);
            out.println("<label>USER</label><br>\n" +
                    "          <i class='fa fa-user'></i><select name=\"kime\" required>");
            
            while(rs.next())
            {
                out.println("<option>"+ rs.getString("korisnik_imeprezime")+"</option>");
            } 
            out.println("</select><br><br>");

        }
        catch(Exception e){}
        out.println("<label>First and last name</label><br>\n" +
                "            <i class='fa fa-user'></i><input type=\"text\" name=\"fl\" placeholder=\"Name\" size=\"20\" /><br><br>\n" +
                "            <label>Username</label><br>\n" +
                "            <i class='fa fa-user'></i><input type=\"text\" name=\"uname\" placeholder=\"Username\" size=\"20\" /><br><br>\n" +
                "            <label>Password</label><br>\n" +
                "            <i class='fa fa-key'></i><input type=\"password\" name=\"pwd\" placeholder=\"Password\" size=\"20\" /><br><br>\n" +
                "            <label>Email</label><br>\n" +
                "            <i class='fa fa-envelope'></i><input type=\"text\" name=\"email\"  placeholder=\"Email\" size=\"20\" /><br><br>\n" +
                "            <label>Phone</label><br>\n" +
                "            <i class='fa fa-phone'></i><input type=\"text\" name=\"phone\"  placeholder=\"Phone\" size=\"20\" /><br><br>\n" +
                "            <label>Address</label><br>\n" +
                "            <i class='fa fa-home'></i><input type=\"text\" name=\"address\" placeholder=\"Address\" size=\"20\" /><br><br>\n" +
                "            <label>Power</label><br>\n" +
                "            <i class='fa fa-flash'></i><select name=\"power\" placeholder=\"Choose\">\n" +
                "                <option selected=\"\"></option>\n" +
                "                <option>Klijent</option>\n" +
                "                <option>Administrator</option>\n" +
                "                <option>Glavni menadzer</option>\n" +
                "                <option>Sef poslovnice</option>\n" +
                "                <option>Sef kuhinje</option>\n" +
                "            </select><br><br>\n" +
                "        </div>\n" +
                "        <div class=\"popup-footer\">\n" +
                "                <input type=\"submit\" value=\"CONFIRM\" class=\"btn\" id=\"btn_ingresar\"/>\n" +
                "            </form>\n" +
                "        </div>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "         \n" +
                "             <!-- POPUP DELETE USER -->\n" +
                "    <div class=\"deleteuserpopup\" >\n" +
                "      <div class=\"popup-dialog\">\n" +
                "        <div class=\"popup-header\">\n" +
                "          <h2>DELETE USER</h2>\n" +
                "          <a href=\"#\" class=\"btn-close closedeleteuserpopup\" aria-hidden=\"true\">&times;</a>\n" +
                "        </div>\n" +
                "        <div class=\"popup-body\">\n" +
                "            <form method=\"POST\" action=\"DeleteUser\">");
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
            stmt = con.createStatement();
            String upit = "select * from korisnik";
            rs = stmt.executeQuery(upit);
            out.println("<label>USER</label><br>\n" +
                    "            <i class='fa fa-user'></i><select name=\"ikorisnika\">");
            while(rs.next())
            {
                out.println("<option>"+rs.getString("korisnik_imeprezime")+"</option>");
            }
            out.println("</select><br><br>");
            
        }
        catch(Exception e){}
        out.println("</div>\n" +
                "        <div class=\"popup-footer\">\n" +
                "                <input type=\"submit\" value=\"DELETE\" class=\"btn\" id=\"btn_ingresar\"/>\n" +
                "            </form>\n" +
                "        </div>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "        \n" +
                "           <!-- POPUP ADD NEW PRODUCT -->\n" +
                "    <div class=\"addnewproductpopup\" >\n" +
                "      <div class=\"popup-dialog\">\n" +
                "        <div class=\"popup-header\">\n" +
                "          <h2>ADD NEW PRODUCT</h2>\n" +
                "          <a href=\"#\" class=\"btn-close closeaddnewproductpopup\" aria-hidden=\"true\">&times;</a>\n" +
                "        </div>\n" +
                "        <div class=\"popup-body\">\n" +
                "            <form method=\"POST\" action=\"AddNewProduct\">\n" +
                "            <label>Product name</label><br>\n" +
                "            <i class='fa fa-product-hunt'></i><input type=\"text\" name=\"nazivp\"  required placeholder=\"Name\" size=\"20\" /><br><br>\n" +
                "            <label>Price</label><br>\n" +
                "            <i class='fa fa-money'></i><input type=\"text\" name=\"cenap\" required placeholder=\"Price\" size=\"20\" /><br><br>\n" +
                "            <label>Quantity</label><br>\n" +
                "            <i class='fa fa-archive'></i><input type=\"text\" name=\"kolicinap\" required placeholder=\"Quantity\" size=\"20\" /><br><br>\n" +
                "            <label>Category</label><br>\n" +
                "             <i class='fa fa-list-alt'></i><select name=\"kategorijap\">\n" +
                "                <option>Slatko</option>\n" +
                "                <option>Slano</option>\n" +
                "            </select><br><br>\n" +
                "        </div>\n" +
                "        <div class=\"popup-footer\">\n" +
                "                <input type=\"submit\" value=\"ADD\" class=\"btn\" id=\"btn_ingresar\"/>\n" +
                "            </form>\n" +
                "        </div>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "           \n" +
                "            <!-- POPUP EDIT PRODUCT -->\n" +
                "    <div class=\"editproductpopup\" >\n" +
                "      <div class=\"popup-dialog\">\n" +
                "        <div class=\"popup-header\">\n" +
                "          <h2>EDIT PRODUCT</h2>\n" +
                "          <a href=\"#\" class=\"btn-close closeeditproductpopup\" aria-hidden=\"true\">&times;</a>\n" +
                "        </div>\n" +
                "        <div class=\"popup-body\">\n" +
                "            <form method=\"POST\" action=\"EditProduct\">");
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
            stmt = con.createStatement();
            String upit = "select * from proizvodi";
            rs = stmt.executeQuery(upit);
            out.println("<label>PRODUCT</label><br>\n" +
                    "            <i class='fa fa-product-hunt'></i><select name=\"pnaziv\" required>");
            while(rs.next())
            {
                out.println("<option>"+rs.getString("naziv_proizvoda")+"</option>");
            }
            out.println("</select><br><br>");
        }
        catch(Exception e){}
        out.println("<label>Name</label><br>\n" +
                "            <i class='fa fa-pinterest-p'></i><input type=\"text\" name=\"nazivp\"  placeholder=\"Name\" size=\"20\" /><br><br>\n" +
                "            <label>Price</label><br>\n" +
                "            <i class='fa fa-money'></i><input type=\"text\" name=\"cenap\" placeholder=\"Price\" size=\"20\" /><br><br>\n" +
                "            <label>Quantity</label><br>\n" +
                "            <i class='fa fa-archive'></i><input type=\"text\" name=\"kolicinap\" placeholder=\"Quantity\" size=\"20\" /><br><br>\n" +
                "            <label>Category</label><br>\n" +
                "             <i class='fa fa-list-alt'></i><select name=\"kategorijap\">\n" +
                "                <option selected=\"\"></option>\n" +
                "                <option>Slatko</option>\n" +
                "                <option>Slano</option>\n" +
                "            </select><br><br>\n" +
                "        </div>\n" +
                "        <div class=\"popup-footer\">\n" +
                "                <input type=\"submit\" value=\"CONFIRM\" class=\"btn\" id=\"btn_ingresar\"/>\n" +
                "            </form>\n" +
                "        </div>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "            \n" +
                "                <!-- POPUP DELETE PRODUCT -->\n" +
                "    <div class=\"deleteproductpopup\" >\n" +
                "      <div class=\"popup-dialog\">\n" +
                "        <div class=\"popup-header\">\n" +
                "          <h2>DELETE PRODUCT</h2>\n" +
                "          <a href=\"#\" class=\"btn-close closedeleteproductpopup\" aria-hidden=\"true\">&times;</a>\n" +
                "        </div>\n" +
                "        <div class=\"popup-body\">\n" +
                "            <form method=\"POST\" action=\"DeleteProduct\">");
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
            stmt = con.createStatement();
            String upit = "select * from proizvodi";
            rs = stmt.executeQuery(upit);
            out.println("<label>PRODUCT</label><br>\n" +
                    "            <i class='fa fa-product-hunt'></i><select name=\"nazivpr\">");
            while(rs.next())
            {
                out.println("<option>"+rs.getString("naziv_proizvoda")+"</option>");
            }
            out.println("</select><br><br>");
        }
        catch(Exception e){}
        out.println("</div>\n" +
                "        <div class=\"popup-footer\">\n" +
                "                <input type=\"submit\" value=\"DELETE\" class=\"btn\" id=\"btn_ingresar\"/>\n" +
                "            </form>\n" +
                "        </div>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "        \n" +
                "      <div class=\"admin-header\">\n" +
                "          <a href=\"#\"><i class=\"fa fa-users\"></i>");
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
            stmt = con.createStatement();
            Korisnik korisnik = (Korisnik)sesija.getAttribute("korisnik");
            String upit = "select * from korisnik where korisnik_imeprezime='"+korisnik.getImeprezime()+"'";
            rs = stmt.executeQuery(upit);
            
            while(rs.next())
            {
                out.println("<span>"+rs.getString("korisnik_imeprezime")+" - "+rs.getString("power")+"</span></a>");
            }
        }
        catch(Exception e){}
        out.println("</div>\n" +
                "        <nav id=\"navigation\">\n" +
                "          <ul>\n" +
                "  \n" +
                "             <li><a href=\"administration/index.jsp\"><i class=\"fa fa-home\"></i><span>Home</span></a></li>\n" +
                "\n" +
                "                <li rel=\"1\" class=\"dropdown fa fa-chevron-right\"><a href=\"#\"><i class=\"fa fa-users\"></i><span>Users</span>\n" +
                "                <ul class=\"dropdown-1\">\n" +
                "                  <li><a href=\"#\" class=\"openaddnewuserpopup\"><i class=\"fa fa-user-plus\"></i><span>Add new user</span></a></li>\n" +
                "                  <li><a href=\"#\" class=\"openedituserpopup\"><i class=\"fa fa-pencil-square-o\"></i><span>Edit user</span></a></li>\n" +
                "                  <li><a href=\"#\" class=\"opendeleteuserpopup\"><i class=\"fa fa-minus-square-o\"></i><span>Delete user</span></a></li>\n" +
                "                </ul></a>\n" +
                "            </li>\n" +
                "            \n" +
                "            <li><a href=\"administration/reclamation.jsp\"><i class=\"fa fa-exclamation-triangle\"></i><span>Reclamation</span></a></li>\n" +
                "            \n" +
                "            <li><a href=\"administration/orders.jsp\"><i class=\"fa fa-opencart\"></i><span>Orders</span></a></li>\n" +
                "            \n" +
                "            <li rel=\"2\" class=\"dropdown fa fa-chevron-right\"><a href=\"#\"><i class=\"fa fa-product-hunt\"></i><span>Products</span>\n" +
                "                <ul class=\"dropdown-2\">\n" +
                "                  <li><a href=\"#\" class=\"openaddnewproductpopup\"><i class=\"fa fa-plus-square-o\"></i><span>Add new product</span></a></li>\n" +
                "                  <li><a href=\"#\" class=\"openeditproductpopup\"><i class=\"fa fa-pencil-square-o\"></i><span>Edit product</span></a></li>\n" +
                "                  <li><a href=\"#\" class=\"opendeleteproductpopup\"><i class=\"fa fa-minus-square-o\"></i><span>Delete product</span></a></li>\n" +
                "                </ul></a>\n" +
                "            </li>\n" +
                "            \n" +
                "            <li><a href=\"logout\"><i class=\"fa fa-sign-out\"></i><span>Logout</span></a></li>\n" +
                "          </ul>\n" +
                "        </nav>\n" +
                "        <div id=\"wrapper\">\n" +
                "       \n" +
                "            <div id=\"projectFacts\" class=\"sectionClass\">\n" +
                "                   \n" +
                "                    <div class=\"projectFactsWrap \">\n" +
                "                        \n" +
                "                        <div class=\"item wow fadeInUpBig animated animated\" style=\"visibility: visible;\">\n" +
                "                            <i class=\"fa fa-users\"></i>");
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
            stmt = con.createStatement();
            String upit = "select * from korisnik";
            rs = stmt.executeQuery(upit);
            int i=0;
            while(rs.next())
            {
                i++;
            }
            out.println("<p id=\"number1\" class=\"number\">"+i+"</p>");
            
        }
        catch(Exception e){}
        out.println("<span></span>\n" +
                "                            <p>Users</p>\n" +
                "                        </div>\n" +
                "                        \n" +
                "                        <div class=\"item wow fadeInUpBig animated animated\" style=\"visibility: visible;\">\n" +
"                            <i class=\"fa fa-product-hunt\"></i>");
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
            stmt = con.createStatement();
            String upit = "select * from proizvodi";
            rs = stmt.executeQuery(upit);
            int i=0;
            while(rs.next())
            {
                i++;
            }
            out.println("<p id=\"number2\" class=\"number\">"+i+"</p>");
            
        }
        catch(Exception e){}
        out.println("<span></span>\n" +
                "                            <p>Products</p>\n" +
                "                        </div>\n" +
                "                        \n" +
                "                        <div class=\"item wow fadeInUpBig animated animated\" style=\"visibility: visible;\">\n" +
                "                            <i class=\"fa fa-opencart\"></i>");
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
                                 stmt = con.createStatement();
                                 String upit = "select * from narudzbine";
                                 rs = stmt.executeQuery(upit);
                                 int i=0;
                                 while(rs.next())
                                 {
                                     i++;
                                 }
                                 out.println("<p id=\"number3\" class=\"number\">"+i+"</p>");
        }
        catch(Exception e){}
        out.println("<span></span>\n" +
                "                            <p>Orders</p>\n" +
                "                        </div>\n" +
                "                        \n" +
                "                        <div class=\"item wow fadeInUpBig animated animated\" style=\"visibility: visible;\">\n" +
                "                            <i class=\"fa fa-exclamation-triangle\"></i>");
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
            stmt = con.createStatement();
            String upit = "select * from reklamacije";
            rs = stmt.executeQuery(upit);
            int i=0;
            while(rs.next())
            {
                i++;
            }
            out.println("<p id=\"number4\" class=\"number\">"+i+"</p>");
                             }
                             catch(Exception e){}
        out.println("<span></span>\n" +
"                            <p>Reclamation</p>\n" +
                "                        </div>\n" +
                "                        \n" +
                "                    </div>\n" +
                "                              <div class=\"sectiontitle\">\n" +
                "    <h2 style=\"color:#eee;\">Reclamation</h2>\n" +
"    <span class=\"headerLine\"></span>\n" +
"            </div>\n" +
"                </div>   \n" +
"                        <div class='grid'>\n" +
"                            <div class='col'>\n" +
"                              <div class='head'>\n" +
"                                \n" +
"                              </div>\n" +
"                              <table>\n" +
"                                <tr>\n" +
"                                  <th>ID</th>\n" +
"                                  <th>Reklamirao</th>\n" +
"                                  <th>Proizvod</th>\n" +
"                                  <th>Status</th>\n" +
"                                  <th>Opis</th>\n" +
"                                  <th><i class=\"fa fa-edit\" aria-hidden=\"true\"></i></th>     \n" +
"                                </tr>\n" +
"                                <tr>");
        try
         {
             Class.forName("com.mysql.cj.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
             String idreklamacije = (String)request.getParameter("id");
             stmt = con.createStatement();
             String upit = "select * from reklamacije where reklamacija_id='"+idreklamacije+"'";
             rs = stmt.executeQuery(upit);
             int i=1;
             if(rs.next())
             {
           out.println("<form method=\"POST\" action=\"ChangeStatus?id="+rs.getInt("reklamacija_id")+"\">");
           out.println("<tr>\n" +
"          <td><input disabled type=\"text\" name=\"id\" value=\""+rs.getInt("reklamacija_id")+"\"/></td>\n" +
"          <td>"+rs.getString("reklamirao")+"</td>\n" +
"          <td>"+rs.getString("proizvod_za_reklamaciju")+"</td>\n" +
"          <td>\n" +
"              <select name=\"status\">\n" +
"                  <option>On hold</option>\n" +
"                  <option>Accepted</option>\n" +
"                  <option>Rejected</option>\n" +
"              </select>\n" +
"          </td>\n" +
"          <td><textarea disabled>"+rs.getString("opis_reklamacije")+"</textarea></td>\n" +
"          <td>\n" +
"              <input type=\"submit\" value=\"CHANGE STATUS\"\n" +
"        </tr></form>");
             }
             stmt.close();
             con.close();
         }
         catch(Exception e)
         {
            System.out.println("Error: " + e);
         }
        out.println("</div>\n" +
"                            </div>\n" +
"                          </div>\n" +
"               </div>");
        out.println("<script src=\"administration/js/js.js\"></script>\n" +
"</body>\n" +
"</html>");
        out.println("</body>");
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
