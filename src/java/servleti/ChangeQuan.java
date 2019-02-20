package servleti;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import beans.*;

public class ChangeQuan extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
            out.println("<!DOCTYPE html>");
            out.println("<html\n" +
"    <head>\n" +
"        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
"        <link rel=\"shortcut icon\" href=\"customer/images/slider-img1.jpg\" type=\"image/x-icon\"/>\n"+
"        <link href=\"customer/css/style.css\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
"        <link href=\"https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
"        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'/>\n" +
"        <link href='http://fonts.googleapis.com/css?family=Great+Vibes' rel='stylesheet' type='text/css'>   \n" +
"        <script src=\"customer/js/jquery.min.js\" type=\"text/javascript\"></script>\n" +
"        <title>Catering Service - ORDERS</title>\n" +
"    </head>       \n" +
"    <body>\n" +
"           \n" +
"    <!-- POPUP LOGIN -->\n" +
"    <div class=\"lpopup\" >\n" +
"      <div class=\"popup-dialog\">\n" +
"        <div class=\"popup-header\">\n" +
"          <h2>Please Login</h2>\n" +
"          <a href=\"#\" class=\"btn-close closelpopup\" aria-hidden=\"true\">&times;</a>\n" +
"        </div>\n" +
"        <div class=\"popup-body\">\n" +
"            <form method=\"POST\" action=\"login\">\n" +
"            <label>Username</label><br>\n" +
"            <i class='fa fa-user'></i><input type=\"text\" name=\"username\" required placeholder=\"Username\" size=\"20\" /><br><br>\n" +
"            <label>Password</label><br>\n" +
"            <i class='fa fa-lock'></i><input type=\"password\" name=\"password\" required placeholder=\"Password\" size=\"20\" /></div>\n" +
"        <div class=\"popup-footer\">\n" +
"                <input type=\"submit\" value=\"Login\" class=\"btn\" id=\"btn_ingresar\"/>\n" +
"            </form>\n" +
"        </div>\n" +
"      </div>\n" +
"    </div>\n" +
"\n" +
"    <!-- POPUP REGISTER -->\n" +
"    <div class=\"rpopup\" >\n" +
"      <div class=\"popup-dialog\">\n" +
"        <div class=\"popup-header\">\n" +
"          <h2>Please Register</h2>\n" +
"          <a href=\"#\" class=\"btn-close closerpopup\" aria-hidden=\"true\">&times;</a>\n" +
"        </div>\n" +
"        <div class=\"popup-body\">\n" +
"            <form method=\"POST\" action=\"register\">\n" +
"            <label>Ime i prezime</label><br>\n" +
"            <i class='fa fa-user'></i><input type=\"text\" name=\"imeprezime\" required placeholder=\"Ime i prezime\" size=\"20\" /><br><br>\n" +
"            <label>Username</label><br>\n" +
"            <i class='fa fa-user'></i><input type=\"text\" name=\"username\" required placeholder=\"Username\" size=\"20\" /><br><br>\n" +
"            <label>Password</label><br>\n" +
"            <i class='fa fa-lock'></i><input type=\"password\" name=\"password\" required placeholder=\"Password\" size=\"20\" /><br><br>\n" +
"            <label>Email</label><br>\n" +
"            <i class='fa fa-user'></i><input type=\"text\" name=\"email\" required placeholder=\"Email\" size=\"20\" /><br><br>\n" +
"            <label>Telefon</label><br>\n" +
"            <i class='fa fa-lock'></i><input type=\"text\" name=\"telefon\" placeholder=\"Telefon\" size=\"20\" /><br><br>\n" +
"            <label>Adresa</label><br>\n" +
"            <i class='fa fa-lock'></i><input type=\"text\" name=\"adresa\" placeholder=\"Adresa\" size=\"20\" /><br><br>\n" +
"        </div>\n" +
"        <div class=\"popup-footer\">\n" +
"                <input type=\"submit\" value=\"Register\" class=\"btn\" id=\"btn_ingresar\"/>\n" +
"            </form>\n" +
"        </div>\n" +
"      </div>\n" +
"    </div>\n" +
"        \n" +
"    <!-- POPUP REKLAMACIJA -->\n" +
"    <div class=\"recpopup\" >\n" +
"      <div class=\"popup-dialog\">\n" +
"        <div class=\"popup-header\">\n" +
"          <h2>COMPLAINTS</h2>\n" +
"          <!--<label>Contact us today, and get reply with in 24 hours!</label>-->\n" +
"          <a href=\"#\" class=\"btn-close closerecpopup\" aria-hidden=\"true\">&times;</a>\n" +
"        </div>\n" +
"        <div class=\"popup-body\">\n" +
"            <form method=\"POST\" action=\"reklamiraj\">");
             
             Connection con = null;
             Statement stmt = null;
             ResultSet rs = null;
             HttpSession sesija = request.getSession();
             Korisnik korisnik = (Korisnik)sesija.getAttribute("korisnik");
         try
         {
             Class.forName("com.mysql.cj.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
             stmt = con.createStatement();
             String upit = "select * from narudzbine where korisnik_imeprezime='"+korisnik.getImeprezime()+"'";
             rs = stmt.executeQuery(upit);
             
             out.println("<label>Proizvod</label><br>\n" +
"            <select name=\"reklamiraj\">");
             while(rs.next())
             { 
                out.println("<option>"+rs.getString("proizvod_naziv")+"</option>");
             }
             out.println("</select><br><br>");
         }
         catch(Exception e){}

        out.println("<textarea placeholder=\"Your message\" required></textarea>\n" +
"        </div>\n" +
"        <div class=\"popup-footer\">\n" +
"                <input type=\"submit\" value=\"Submit\" class=\"btn\" id=\"btn_ingresar\"/>\n" +
"            </form>\n" +
"        </div>\n" +
"      </div>\n" +
"    </div>\n" +
"\n" +
"    <div id=\"particles-js\"></div>\n" +
"    <header>\n" +
"      <div class=\"header\">\n" +
"        <div class=\"logo\">\n" +
"          <h1 class=\"companyname\">Catering Service</h1>\n" +
"        </div>\n" +
"        <nav class=\"menu\">\n" +
"          <ul>");

         if(request.getSession().getAttribute("korisnik") == null)
         {
              out.println("<li><a href=\"customer/index.jsp\">Home</a></li>\n" +
"            <li><a href=\"customer/proizvodi.jsp\">Menu</a></li>\n" +
"            <li><a href=\"#\"class=\"openlpopup\">Login</a></li>\n" +
"            <li><a href=\"#\"class=\"openrpopup\">Register</a></li>");
         }
         else
         {
             out.println("<li><a href=\"customer/index.jsp\">Home</a></li>\n" +
"            <li><a href=\"customer/proizvodi.jsp\">Menu</a></li>\n" +
"            <li><a href=\"customer/korpa.jsp\">My cart</a></li>\n" +
"            <li><a href=\"customer/narudzbine.jsp\">My orders</a></li>\n" +
"            <li><a href=\"customer/reklamacije.jsp\">My reclamations</a></li>\n" +
"            <li><a href=\"#\" class=\"openrecpopup\">Complaints</a></li\n" +
"            <li><a href=\"logout\">Logout</a></li>");
         }
         out.println("</ul>\n" +
"        </nav>\n" +
"        \n" +
"      </div>\n" +
"    </header>\n" +
" \n" +
" <section class=\"main\">\n" +
"          <table class=\"center\">\n" +
"      <caption>MY CART</caption>\n" +
"      <thead>\n" +
"        <tr>\n" +
"          <th scope=\"col\">Redni Broj</th>\n" +
"          <th scope=\"col\">Narucio</th>\n" +
"          <th scope=\"col\">Naziv proizvoda</th>\n" +
"          <th scope=\"col\">Cena</th>\n" +
"          <th scope=\"col\">Kolicina</th>\n" +
"          <th scope=\"col\">Ukupno za placanje</th>\n" +
"          <th><i class=\"fa fa-edit\" aria-hidden=\"true\"></i></th>\n" +
"        </tr>\n" +
"      </thead>\n" +
"       <tbody>");
         
         try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
            Statement state = con.createStatement();
            String upit1 = "select * from korpa where korisnik_imeprezime='"+korisnik.getImeprezime()+"'";
            ResultSet r2s = state.executeQuery(upit1);
            int i=1;
            while(r2s.next())
            {
           
     
        out.println("<form method=\"POST\" action=\"UpdateQuan?id="+r2s.getInt("id")+"&ime="+r2s.getString("proizvod_naziv")+"\"><tr>\n" +
"          <th scope=\"row\">"+r2s.getInt("id")+"</th>\n" +
"          <td>"+r2s.getString("korisnik_imeprezime")+"</td>\n" +
"          <td>"+r2s.getString("proizvod_naziv")+"</td>\n" +
"          <td>"+r2s.getString("proizvod_cena")+"</td>\n" +
"          <td><input type=\"text\" name=\"kolicina\" value=\""+r2s.getInt("kolicina")+"\"</td>\n" +
"          <td>"+r2s.getString("ukupna_cena")+" RSD</td>\n" +
"          <td><input type=\"submit\" value=\"CHANGE QUAN\"/></td>\n" +
"        </tr></form>");
          }
             
             r2s.close();
             state.close();
             con.close();
         }
         catch(Exception e)
         {
             System.out.println("Error: " + e);
         }
        out.println("</tbody>\n" +
"         \n" +
"       </table>");

        out.println("</section>\n" +

"        <footer>\n" +
"            <small>Copyright &copy; 2016</small>\n" +
"        </footer>\n" +
"\n" +
"        <script src=\"customer/js/js.js\"></script>\n" +
"    </body>\n" +
"</html>");
       
        
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
