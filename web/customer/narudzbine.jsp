<%-- 
    Document   : narudzbine
    Created on : Apr 22, 2016, 2:37:27 PM
    Author     : Dusan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="beans.Korisnik"%>
<%@page import = "java.util.*" session="true"%>
<%@page import="java.io.*" %>
<%@page import="java.sql.*" %>
<%@page import="javax.servlet.*" %>
<%@page import="javax.servlet.http.*" %>
<!DOCTYPE html>
<html
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/customer/images/slider-img1.jpg" type="image/x-icon"/>
        <link href="${pageContext.request.contextPath}/customer/css/style.css" rel="stylesheet" type="text/css"/>
        <link href='http://fonts.googleapis.com/css?family=Nunito:400,300' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'/>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <link href='https://fonts.googleapis.com/css?family=Great+Vibes' rel='stylesheet' type='text/css'>
        <script src="${pageContext.request.contextPath}/customer/js/jquery.min.js" type="text/javascript"></script>
        <title>Catering Service - ORDERS</title>
    </head>       

    <body>
    
    <!-- POPUP CONTACT -->
    <div class="cpopup" >
      <div class="popup-dialog">
        <div class="popup-header">
          <h2>Quick Contact</h2>
          <label>Contact us today, and get reply with in 24 hours!</label>
          <a href="#" class="btn-close closecpopup" aria-hidden="true">&times;</a>
        </div>
        <div class="popup-body">
            <form method="POST" action="${pageContext.request.contextPath}/contact">
            <input type="text" name="ip" required placeholder="Ime i prezime" size="20" /><br><br>
            <input type="text" name="mail" required placeholder="Email" size="20" /><br><br>
            <textarea placeholder="Your message" required></textarea>
        </div>
        <div class="popup-footer">
                <input type="submit" value="Submit" class="btn" id="btn_ingresar"/>
            </form>
        </div>
      </div>
    </div>
            
    <!-- POPUP LOGIN -->
    <div class="lpopup" >
      <div class="popup-dialog">
        <div class="popup-header">
          <h2>Please Login</h2>
          <a href="#" class="btn-close closelpopup" aria-hidden="true">&times;</a>
        </div>
        <div class="popup-body">
            <form method="POST" action="${pageContext.request.contextPath}/login">
            <label>Username</label><br>
            <i class='fa fa-user'></i><input type="text" name="username" value="${korisnik.username}" required placeholder="Username" size="20" /><br><br>
            <label>Password</label><br>
            <i class='fa fa-lock'></i><input type="password" name="password" value="${korisnik.password}" required placeholder="Password" size="20" /></div>
        <div class="popup-footer">
                <input type="submit" value="Login" class="btn" id="btn_ingresar"/>
            </form>
        </div>
      </div>
    </div>

    <!-- POPUP REGISTER -->
    <div class="rpopup" >
      <div class="popup-dialog">
        <div class="popup-header">
          <h2>Please Register</h2>
          <a href="#" class="btn-close closerpopup" aria-hidden="true">&times;</a>
        </div>
        <div class="popup-body">
            <form method="POST" action="${pageContext.request.contextPath}/register">
            <label>Ime i prezime</label><br>
            <i class='fa fa-user'></i><input type="text" name="imeprezime" value="${korisnik.imeprezime}" required placeholder="Ime i prezime" size="20" /><br><br>
            <label>Username</label><br>
            <i class='fa fa-user'></i><input type="text" name="username" value="${korisnik.username}" required placeholder="Username" size="20" /><br><br>
            <label>Password</label><br>
            <i class='fa fa-lock'></i><input type="password" name="password" value="${korisnik.password}" required placeholder="Password" size="20" /><br><br>
            <label>Email</label><br>
            <i class='fa fa-user'></i><input type="text" name="email" value="${korisnik.email}" required placeholder="Email" size="20" /><br><br>
            <label>Telefon</label><br>
            <i class='fa fa-lock'></i><input type="text" name="telefon" value="${korisnik.telefon}" placeholder="Telefon" size="20" /><br><br>
            <label>Adresa</label><br>
            <i class='fa fa-lock'></i><input type="text" name="adresa" value="${korisnik.adresa}" placeholder="Adresa" size="20" /><br><br>
        </div>
        <div class="popup-footer">
                <input type="submit" value="Register" class="btn" id="btn_ingresar"/>
            </form>
        </div>
      </div>
    </div>
        
    <!-- POPUP REKLAMACIJA -->
    <div class="recpopup" >
      <div class="popup-dialog">
        <div class="popup-header">
          <h2>COMPLAINTS</h2>
          <!--<label>Contact us today, and get reply with in 24 hours!</label>-->
          <a href="#" class="btn-close closerecpopup" aria-hidden="true">&times;</a>
        </div>
        <div class="popup-body">
            <form method="POST" action="${pageContext.request.contextPath}/reklamiraj">
                <%
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
            %>
            <label>Proizvod</label><br>
            <select name="reklamiraj">
            <%  while(rs.next())
            { %>
                <option><%= rs.getString("proizvod_naziv")%></option>
            <%} %>
            </select><br><br>
            <%
            
        }
        catch(Exception e){}

    %>
            
            <textarea placeholder="Your message" required></textarea>
        </div>
        <div class="popup-footer">
                <input type="submit" value="Submit" class="btn" id="btn_ingresar"/>
            </form>
        </div>
      </div>
    </div>

    <div id="particles-js"></div>
    <header>
      <div class="header">
        <div class="logo">
          <h1 class="companyname">Catering Service</h1>
        </div>
        <nav class="menu">
          <ul>
        <%
       if(request.getSession().getAttribute("korisnik") == null)
        {%>
            <li><a href="${pageContext.request.contextPath}/customer/index.jsp">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/customer/proizvodi.jsp">Menu</a></li>
            <li><a href="#" class="opencpopup">Contact</a></li>
            <li><a href="#"class="openlpopup">Login</a></li>
            <li><a href="#"class="openrpopup">Register</a></li>
            <%}
        else
        {
        %>
         
            <li><a href="${pageContext.request.contextPath}/customer/index.jsp">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/customer/proizvodi.jsp">Menu</a></li>
            <li><a href="${pageContext.request.contextPath}/customer/korpa.jsp">My cart</a></li>
            <li><a class="activated" href="${pageContext.request.contextPath}/customer/narudzbine.jsp">My orders</a></li>
            <li><a href="${pageContext.request.contextPath}/customer/reklamacije.jsp">My reclamations</a></li>
            <li><a href="#" class="openrecpopup">Complaints</a></li
            <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
        <%}%>
          </ul>
        </nav>
        
      </div>
    </header>
 
 <section class="main">
          <table class="center">
      <caption>MY ORDERS</caption>
      <thead>
        <tr>
          <th scope="col">Redni Broj</th>
          <th scope="col">Narucio</th>
          <th scope="col">Naziv proizvoda</th>
          <th scope="col">Cena</th>
          <th scope="col">Kolicina</th>
          <th scope="col">Ukupno za placanje</th>
        </tr>
      </thead>
       <tbody>
<%
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
            Statement state = con.createStatement();
            String upit1 = "select * from narudzbine where korisnik_imeprezime='"+korisnik.getImeprezime()+"'";
            ResultSet r2s = state.executeQuery(upit1);
            int i=1;
            while(r2s.next())
            { %>
           
     
        <tr>
            <th scope="row"><%=i++%></th>
          <td><%=r2s.getString("korisnik_imeprezime")%></td>
          <td><%=r2s.getString("proizvod_naziv")%></td>
          <td><%=r2s.getString("proizvod_cena")%></td>
          <td><%=r2s.getInt("kolicina")%></td>
          <td><%=r2s.getString("ukupna_cena")%> RSD</td>
        </tr>
      
   
            <%}
            if(!r2s.first())
            {%>
                <tr>
                <td scope="row">You still do not have an order.</td>
                <td scope="row"></td>
                <td scope="row"></td>
                <td scope="row"></td>
                <td scope="row"></td>
                <td scope="row"></td>
                </tr>
            <%}
            r2s.close();
            stmt.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e);
        }

    %>
    
         </tbody>
         
       </table>
    
      </section>
    

        <footer>
            <small>Copyright &copy; 2016</small>
        </footer>

        <script src="${pageContext.request.contextPath}/customer/js/js.js"></script>
    </body>
</html>