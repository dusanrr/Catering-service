<%-- 
    Document   : proizvodi
    Created on : Mar 10, 2016, 10:26:50 AM
    Author     : Dusan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="beans.Korisnik"%>
<%@page import = "java.util.*" session="true"%>
<%@page import="java.io.*" %>
<%@page import="java.sql.*" %>
<%@page import="javax.servlet.*" %>
<%@page import="javax.servlet.http.*" %>
<%!
public int nullIntconv(String str)
{   
    int conv=0;
    if(str==null)
    {
        str="0";
    }
    else if((str.trim()).equals("null"))
    {
        str="0";
    }
    else if(str.equals(""))
    {
        str="0";
    }
    try{
        conv=Integer.parseInt(str);
    }
    catch(Exception e)
    {
    }
    return conv;
}
%>
<%

    Connection conn = null;
    Class.forName("com.mysql.cj.jdbc.Driver");
    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");

    ResultSet rsPagination = null;
    ResultSet rsRowCnt = null;
    
    PreparedStatement psPagination=null;
    PreparedStatement psRowCnt=null;
    
    int iShowRows=8;  // Number of records show on per page
    int iTotalSearchRecords=10;  // Number of pages index shown
    
    int iTotalRows=nullIntconv(request.getParameter("iTotalRows"));
    int iTotalPages=nullIntconv(request.getParameter("iTotalPages"));
    int iPageNo=nullIntconv(request.getParameter("iPageNo"));
    int cPageNo=nullIntconv(request.getParameter("cPageNo"));
    
    int iStartResultNo=0;
    int iEndResultNo=0;
    
    if(iPageNo==0)
    {
        iPageNo=0;
    }
    else{
        iPageNo=Math.abs((iPageNo-1)*iShowRows);
    }
    

    
    String sqlPagination="SELECT SQL_CALC_FOUND_ROWS * FROM proizvodi limit "+iPageNo+","+iShowRows+"";

    psPagination=conn.prepareStatement(sqlPagination);
    rsPagination=psPagination.executeQuery();
    
    //// this will count total number of rows
     String sqlRowCnt="SELECT FOUND_ROWS() as cnt";
     psRowCnt=conn.prepareStatement(sqlRowCnt);
     rsRowCnt=psRowCnt.executeQuery();
     
     if(rsRowCnt.next())
      {
         iTotalRows=rsRowCnt.getInt("cnt");
      }
%>
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
        <title>Catering Service - MENU</title>
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
            <li><a id="start" class="activated"href="${pageContext.request.contextPath}/customer/proizvodi.jsp">Menu</a></li>
            <li><a href="#" class="opencpopup">Contact</a></li>
            <li><a href="#"class="openlpopup">Login</a></li>
            <li><a href="#"class="openrpopup">Register</a></li>
            <%}
        else
        {
        %>
         
            <li><a href="${pageContext.request.contextPath}/customer/index.jsp">Home</a></li>
            <li><a class="activated" href="${pageContext.request.contextPath}/customer/proizvodi.jsp">Menu</a></li>
            <li><a href="${pageContext.request.contextPath}/customer/korpa.jsp">My cart</a></li>
            <li><a href="${pageContext.request.contextPath}/customer/narudzbine.jsp">My orders</a></li>
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
      <caption>CATERING PRODUCTS</caption>
      <thead>
        <tr>
          <th scope="col">Redni Broj</th>
          <th scope="col">Naziv</th>
          <th scope="col">Cena</th>
          <th scope="col">Kolicina</th>
          <th scope="col">Kategorija</th>
        </tr>
      </thead>
       <tbody>
            <%
            try
            {
            
            int i=1;
            while(rsPagination.next())
            { %>
           
     
        <tr>
          <td scope="row"><%=rsPagination.getInt("id_proizvoda")%></td>
          <td><%=rsPagination.getString("naziv_proizvoda")%></td>
          <td><%=rsPagination.getString("cena_proizvoda")%></td>
          <td><%=rsPagination.getString("kolicina_proizvoda")%></td>
          <td><%=rsPagination.getString("kategorija_proizvoda")%></td>
        </tr>
      
   
            <%}
            if(!rsPagination.first())
            {%>
                <tr>
                <td scope="row">We do not have any products at the moment.</td>
                <td scope="row"></td>
                <td scope="row"></td>
                <td scope="row"></td>
                <td scope="row"></td>
                </tr>
            <%}
            stmt.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e);
        }
    %>
            <%
  //// calculate next record start record  and end record 
        try{
            if(iTotalRows<(iPageNo+iShowRows))
            {
                iEndResultNo=iTotalRows;
            }
            else
            {
                iEndResultNo=(iPageNo+iShowRows);
            }
           
            iStartResultNo=(iPageNo+1);
            iTotalPages=((int)(Math.ceil((double)iTotalRows/iShowRows)));
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

%>                     
         </tbody>
         
       </table>
  
 <div style="margin-top:0; margin-left: -350px;"class='btn-kupite'>
                                  <%
        //// index of pages 
        
        int i=0;
        int cPage=0;
        if(iTotalRows!=0)
        {
        cPage=((int)(Math.ceil((double)iEndResultNo/(iTotalSearchRecords*iShowRows))));
        
        int prePageNo=(cPage*iTotalSearchRecords)-((iTotalSearchRecords-1)+iTotalSearchRecords);
        if((cPage*iTotalSearchRecords)-(iTotalSearchRecords)>0)
        {
         %>
          <a class='btnpagi pagi' href="proizvodi.jsp?iPageNo=<%=prePageNo%>&cPageNo=<%=prePageNo%>"> << Previous</a>
         <%
        }
        
        for(i=((cPage*iTotalSearchRecords)-(iTotalSearchRecords-1));i<=(cPage*iTotalSearchRecords);i++)
        {
          if(i==((iPageNo/iShowRows)+1))
          {
          %>
           <a class='btnpagi pagi' href="proizvodi.jsp?iPageNo=<%=i%>" style="color: #59d"><b><%=i%></b></a>
          <%
          }
          else if(i<=iTotalPages)
          {
          %>
           <a class='btnpagi pagi' href="proizvodi.jsp?iPageNo=<%=i%>"><%=i%></a>
          <% 
          }
        }
        if(iTotalPages>iTotalSearchRecords && i<iTotalPages)
        {
         %>
         <a class='btn act' href="proizvodi.jsp?iPageNo=<%=i%>&cPageNo=<%=i%>"> >> Next</a> 
         <%
        }
        }
      %>
                                <!--<a class='btn sec'>Previous</a>
                                <a class='btn act'>Next</a>-->
                              </div>
    <% if(request.getSession().getAttribute("korisnik") != null)
    { %>
    <a href="" class="btn-kupite openbuypopup">Kupite</a>
    <%}%>
    
      </section>
      
     <!-- BUY POP UP -->
    <div class="buypopup" >
      <div class="popup-dialog">
        <div class="popup-header">
          <h2>ORDER</h2>
          <!--<label></label>-->
          <a href="#" class="btn-close closebuypopup" aria-hidden="true">&times;</a>
        </div>
        <div class="popup-body">
            <form method="POST" action="${pageContext.request.contextPath}/addtocart">
            
<%
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering", "root", "");
            stmt = con.createStatement();
            String upit = "select * from proizvodi";
            rs = stmt.executeQuery(upit);
            String prz = "";
            %>
            <label>Proizvod</label><br><br>
            <select class="styled" name="proizvod">
                <option selected="" disabled="">Izaberite </option>
            <%  while(rs.next())
            {
            %>
            <option><%= rs.getString("naziv_proizvoda")%></option>
            <%} %>
            </select>
            <br><br>
            
            <%     
            
        }
        catch(Exception e){}

    %>
    
            <input type="text" name="kolicina" placeholder="Kolicina" required /><br><br>
            <label>Datum dostave</label><br><br>
            <input type="date" name="datumdostave" required/><br><br>
            <input type="text" name="adresa" placeholder="Adresa dostave" required /><br><br>
            
    </div>
     <div class="popup-footer">
         <input type="submit" value="Naruci" class="btn"/>
            </form>
        </div>
      </div>
    </div>
    <!-- /Modal -->

        <footer>
            <small>Copyright &copy; 2016</small>
        </footer>

        <script src="${pageContext.request.contextPath}/customer/js/js.js"></script>
    </body>
</html>