<%-- 
    Document   : index
    Created on : Apr 25, 2016, 3:01:01 PM
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
        <link href="${pageContext.request.contextPath}/administration/css/style.css" rel="stylesheet" type="text/css"/>
        <!--<link href="http://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet"/>-->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <link href='https://fonts.googleapis.com/css?family=Great+Vibes' rel='stylesheet' type='text/css'>
        <script src="${pageContext.request.contextPath}/administration/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/administration/js/jquery-ui.min.js"></script>
        <title>Catering Service - ADMIN PANEL</title>
    </head>       

    <body>
    
        
        <!-- POPUP ADD NEW USER -->
    <div class="addnewuserpopup" >
      <div class="popup-dialog">
        <div class="popup-header">
          <h2>ADD NEW USER</h2>
          <a href="#" class="btn-close closeaddnewuserpopup" aria-hidden="true">&times;</a>
        </div>
        <div class="popup-body">
            <form method="POST" action="${pageContext.request.contextPath}/AddNewUser">
            <label>First and last name</label><br>
            <i class='fa fa-user'></i><input type="text" name="fl"  required placeholder="Name" size="20" /><br><br>
            <label>Username</label><br>
            <i class='fa fa-user'></i><input type="text" name="uname" required placeholder="Username" size="20" /><br><br>
            <label>Password</label><br>
            <i class='fa fa-key'></i><input type="password" name="pwd" required placeholder="Password" size="20" /><br><br>
            <label>Email</label><br>
            <i class='fa fa-envelope'></i><input type="text" name="email"  required placeholder="Email" size="20" /><br><br>
            <label>Phone</label><br>
            <i class='fa fa-phone'></i><input type="text" name="phone"  placeholder="Phone" size="20" /><br><br>
            <label>Address</label><br>
            <i class='fa fa-home'></i><input type="text" name="address" placeholder="Address" size="20" /><br><br>
            <label>Power</label><br>
            <i class='fa fa-flash'></i><select name="power">
                <option>Klijent</option>
                <option>Administrator</option>
                <option>Glavni menadzer</option>
                <option>Sef poslovnice</option>
                <option>Sef kuhinje</option>
            </select><br><br>
        </div>
        <div class="popup-footer">
                <input type="submit" value="ADD" class="btn" id="btn_ingresar"/>
            </form>
        </div>
      </div>
    </div>
        
         <!-- POPUP EDIT USER -->
    <div class="edituserpopup" >
      <div class="popup-dialog">
        <div class="popup-header">
          <h2>EDIT USER</h2>
          <a href="#" class="btn-close closeedituserpopup" aria-hidden="true">&times;</a>
        </div>
        <div class="popup-body">
            <form method="POST" action="${pageContext.request.contextPath}/EditUser">
                  <%
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
            %>
            <label>USER</label><br>
            <i class='fa fa-user'></i><select name="kime" required>
            <%  while(rs.next())
            { %>
                <option><%= rs.getString("korisnik_imeprezime")%></option>
            <%} %>
            </select><br><br>
            <%
            
        }
        catch(Exception e){}

    %>
           <label>First and last name</label><br>
            <i class='fa fa-user'></i><input type="text" name="fl" placeholder="Name" size="20" /><br><br>
            <label>Username</label><br>
            <i class='fa fa-user'></i><input type="text" name="uname" placeholder="Username" size="20" /><br><br>
            <label>Password</label><br>
            <i class='fa fa-key'></i><input type="password" name="pwd" placeholder="Password" size="20" /><br><br>
            <label>Email</label><br>
            <i class='fa fa-envelope'></i><input type="text" name="email"  placeholder="Email" size="20" /><br><br>
            <label>Phone</label><br>
            <i class='fa fa-phone'></i><input type="text" name="phone"  placeholder="Phone" size="20" /><br><br>
            <label>Address</label><br>
            <i class='fa fa-home'></i><input type="text" name="address" placeholder="Address" size="20" /><br><br>
            <label>Power</label><br>
            <i class='fa fa-flash'></i><select name="power" placeholder="Choose">
                <option selected=""></option>
                <option>Klijent</option>
                <option>Administrator</option>
                <option>Glavni menadzer</option>
                <option>Sef poslovnice</option>
                <option>Sef kuhinje</option>
            </select><br><br>
        </div>
        <div class="popup-footer">
                <input type="submit" value="CONFIRM" class="btn" id="btn_ingresar"/>
            </form>
        </div>
      </div>
    </div>
         
             <!-- POPUP DELETE USER -->
    <div class="deleteuserpopup" >
      <div class="popup-dialog">
        <div class="popup-header">
          <h2>DELETE USER</h2>
          <a href="#" class="btn-close closedeleteuserpopup" aria-hidden="true">&times;</a>
        </div>
        <div class="popup-body">
            <form method="POST" action="${pageContext.request.contextPath}/DeleteUser">
               <%
            
        try
        {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
            stmt = con.createStatement();
            String upit = "select * from korisnik";
            rs = stmt.executeQuery(upit);
            %>
            <label>USER</label><br>
            <i class='fa fa-user'></i><select name="ikorisnika">
            <%  while(rs.next())
            { %>
                <option><%= rs.getString("korisnik_imeprezime")%></option>
            <%} %>
            </select><br><br>
            <%
            
        }
        catch(Exception e){}

    %>
        </div>
        <div class="popup-footer">
                <input type="submit" value="DELETE" class="btn" id="btn_ingresar"/>
            </form>
        </div>
      </div>
    </div>
        
           <!-- POPUP ADD NEW PRODUCT -->
    <div class="addnewproductpopup" >
      <div class="popup-dialog">
        <div class="popup-header">
          <h2>ADD NEW PRODUCT</h2>
          <a href="#" class="btn-close closeaddnewproductpopup" aria-hidden="true">&times;</a>
        </div>
        <div class="popup-body">
            <form method="POST" action="${pageContext.request.contextPath}/AddNewProduct">
            <label>Product name</label><br>
            <i class='fa fa-product-hunt'></i><input type="text" name="nazivp"  required placeholder="Name" size="20" /><br><br>
            <label>Price</label><br>
            <i class='fa fa-money'></i><input type="text" name="cenap" required placeholder="Price" size="20" /><br><br>
            <label>Quantity</label><br>
            <i class='fa fa-archive'></i><input type="text" name="kolicinap" required placeholder="Quantity" size="20" /><br><br>
            <label>Category</label><br>
             <i class='fa fa-list-alt'></i><select name="kategorijap">
                <option>Slatko</option>
                <option>Slano</option>
            </select><br><br>
        </div>
        <div class="popup-footer">
                <input type="submit" value="ADD" class="btn" id="btn_ingresar"/>
            </form>
        </div>
      </div>
    </div>
           
            <!-- POPUP EDIT PRODUCT -->
    <div class="editproductpopup" >
      <div class="popup-dialog">
        <div class="popup-header">
          <h2>EDIT PRODUCT</h2>
          <a href="#" class="btn-close closeeditproductpopup" aria-hidden="true">&times;</a>
        </div>
        <div class="popup-body">
            <form method="POST" action="${pageContext.request.contextPath}/EditProduct">
                  <%     
            
        try
        {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
            stmt = con.createStatement();
            String upit = "select * from proizvodi";
            rs = stmt.executeQuery(upit);
            %>
            <label>PRODUCT</label><br>
            <i class='fa fa-product-hunt'></i><select name="pnaziv" required>
            <%  while(rs.next())
            { %>
                <option><%= rs.getString("naziv_proizvoda")%></option>
            <%} %>
            </select><br><br>
            <%
            
        }
        catch(Exception e){}

    %>
            <label>Name</label><br>
            <i class='fa fa-pinterest-p'></i><input type="text" name="nazivp"  placeholder="Name" size="20" /><br><br>
            <label>Price</label><br>
            <i class='fa fa-money'></i><input type="text" name="cenap" placeholder="Price" size="20" /><br><br>
            <label>Quantity</label><br>
            <i class='fa fa-archive'></i><input type="text" name="kolicinap" placeholder="Quantity" size="20" /><br><br>
            <label>Category</label><br>
             <i class='fa fa-list-alt'></i><select name="kategorijap">
                <option selected=""></option>
                <option>Slatko</option>
                <option>Slano</option>
            </select><br><br>
        </div>
        <div class="popup-footer">
                <input type="submit" value="CONFIRM" class="btn" id="btn_ingresar"/>
            </form>
        </div>
      </div>
    </div>
            
                <!-- POPUP DELETE PRODUCT -->
    <div class="deleteproductpopup" >
      <div class="popup-dialog">
        <div class="popup-header">
          <h2>DELETE PRODUCT</h2>
          <a href="#" class="btn-close closedeleteproductpopup" aria-hidden="true">&times;</a>
        </div>
        <div class="popup-body">
            <form method="POST" action="${pageContext.request.contextPath}/DeleteProduct">
               <%
            
        try
        {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
            stmt = con.createStatement();
            String upit = "select * from proizvodi";
            rs = stmt.executeQuery(upit);
            %>
            <label>PRODUCT</label><br>
            <i class='fa fa-product-hunt'></i><select name="nazivpr">
            <%  while(rs.next())
            { %>
                <option><%= rs.getString("naziv_proizvoda")%></option>
            <%} %>
            </select><br><br>
            <%
            
        }
        catch(Exception e){}

    %>
        </div>
        <div class="popup-footer">
                <input type="submit" value="DELETE" class="btn" id="btn_ingresar"/>
            </form>
        </div>
      </div>
    </div>
        
      <div class="admin-header">
          <a href="#"><i class="fa fa-users"></i>
                <%
            
        try
        {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
            stmt = con.createStatement();
            Korisnik korisnik = (Korisnik)sesija.getAttribute("korisnik");
            String upit = "select * from korisnik where korisnik_imeprezime='"+korisnik.getImeprezime()+"'";
            rs = stmt.executeQuery(upit);
            %>
            
            <%  while(rs.next())
            { %>
            <span><%=rs.getString("korisnik_imeprezime")%> - <%=rs.getString("power")%></span></a>
            <%} %>
           
            <%
            
        }
        catch(Exception e){}

    %>
              
      </div>
        <nav id="navigation">
          <ul>
  
             <li><a href="${pageContext.request.contextPath}/administration/index.jsp"><i class="fa fa-home"></i><span>Home</span></a></li>

                <li rel="1" class="dropdown fa fa-chevron-right"><a href="#"><i class="fa fa-users"></i><span>Users</span>
                <ul class="dropdown-1">
                  <li><a href="#" class="openaddnewuserpopup"><i class="fa fa-user-plus"></i><span>Add new user</span></a></li>
                  <li><a href="#" class="openedituserpopup"><i class="fa fa-pencil-square-o"></i><span>Edit user</span></a></li>
                  <li><a href="#" class="opendeleteuserpopup"><i class="fa fa-minus-square-o"></i><span>Delete user</span></a></li> 
                </ul></a>
            </li>
            
            <li><a href="${pageContext.request.contextPath}/administration/reclamation.jsp"><i class="fa fa-exclamation-triangle"></i><span>Reclamation</span></a></li>
            
            <li><a href="${pageContext.request.contextPath}/administration/orders.jsp"><i class="fa fa-opencart"></i><span>Orders</span></a></li>
            
            <li rel="2" class="dropdown fa fa-chevron-right"><a href="#"><i class="fa fa-product-hunt"></i><span>Products</span>
                <ul class="dropdown-2">
                  <li><a href="#" class="openaddnewproductpopup"><i class="fa fa-plus-square-o"></i><span>Add new product</span></a></li>
                  <li><a href="#" class="openeditproductpopup"><i class="fa fa-pencil-square-o"></i><span>Edit product</span></a></li>
                  <li><a href="#" class="opendeleteproductpopup"><i class="fa fa-minus-square-o"></i><span>Delete product</span></a></li>
                </ul></a>
            </li>
            
            <li><a href="${pageContext.request.contextPath}/logout"><i class="fa fa-sign-out"></i><span>Logout</span></a></li>
          </ul>
        </nav>
        <div id="wrapper">
       
            <div id="projectFacts" class="sectionClass">
                     <div class="sectiontitle">
    <h2 style="color:#eee;">Statisticts</h2>
    <span class="headerLine"></span>
    <h3>${poruka}</h3>
            </div>
                    <div class="projectFactsWrap ">
                        
                        <div class="item wow fadeInUpBig animated animated" style="visibility: visible;">
                            <i class="fa fa-users"></i>
                              <%
 
                            try
                            {

                                Class.forName("com.mysql.cj.jdbc.Driver");
                                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
                                stmt = con.createStatement();
                                String upit = "select * from korisnik";
                                rs = stmt.executeQuery(upit);
                                int i=0;
                                %>
                                <%  while(rs.next())
                                {
                                i++;
                                %>
                                   
                                <%} 
                                %>
                                <p id="number1" class="number"><%=i%></p>
                                
                                <%

                            }
                            catch(Exception e){}

                        %>
                            <span></span>
                            <p>Users</p>
                        </div>
                        
                        <div class="item wow fadeInUpBig animated animated" style="visibility: visible;">
                            <i class="fa fa-product-hunt"></i>
                             <%
                               
                            try
                            {

                                Class.forName("com.mysql.cj.jdbc.Driver");
                                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
                                stmt = con.createStatement();
                                String upit = "select * from proizvodi";
                                rs = stmt.executeQuery(upit);
                                int i=0;
                                %>
                                <%  while(rs.next())
                                {
                                i++;
                                %>
                                   
                                <%} 
                                %>
                                <p id="number2" class="number"><%=i%></p>
                                
                                <%

                            }
                            catch(Exception e){}

                        %>
                            <span></span>
                            <p>Products</p>
                        </div>
                        
                        <div class="item wow fadeInUpBig animated animated" style="visibility: visible;">
                            <i class="fa fa-opencart"></i>
                             <%
                               
                            try
                            {

                                Class.forName("com.mysql.cj.jdbc.Driver");
                                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
                                stmt = con.createStatement();
                                String upit = "select * from narudzbine";
                                rs = stmt.executeQuery(upit);
                                int i=0;
                                %>
                                <%  while(rs.next())
                                {
                                i++;
                                %>
                                   
                                <%} 
                                %>
                                <p id="number3" class="number"><%=i%></p>
                                
                                <%

                            }
                            catch(Exception e){}

                        %>
                            <span></span>
                            <p>Orders</p>
                        </div>
                        
                        <div class="item wow fadeInUpBig animated animated" style="visibility: visible;">
                            <i class="fa fa-exclamation-triangle"></i>
                             <%
                               
                            try
                            {

                                Class.forName("com.mysql.cj.jdbc.Driver");
                                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
                                stmt = con.createStatement();
                                String upit = "select * from reklamacije";
                                rs = stmt.executeQuery(upit);
                                int i=0;
                                %>
                                <%  while(rs.next())
                                {
                                i++;
                                %>
                                   
                                <%} 
                                %>
                                <p id="number4" class="number"><%=i%></p>
                                
                                <%

                            }
                            catch(Exception e){}

                        %>
                            <span></span>
                            <p>Reclamation</p>
                        </div>
                        
                    </div>
                </div>   
                     
               </div>      
                       
        <script src="${pageContext.request.contextPath}/administration/js/js.js"></script>
    </body>
</html>
