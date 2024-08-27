<%@page import="com.help.cityhelper.entities.User,com.help.cityhelper.entities.Category,com.help.cityhelper.dao.CategoryDao,com.help.cityhelper.helper.FactoryProvider,java.util.*,com.help.cityhelper.dao.UserDao"%>

<%
User user1=(User)session.getAttribute("current-user");
%>
  

<nav class="navbar navbar-expand-lg bg-body-tertiary small-navbar" style="
    background-color: #80DEEA!important;">
     <div class="container"  >
        <div class="container-fluid" >
           
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <a class="navbar-brand" href="index.jsp"><img
                    src="img/logo.png" 
                    alt="" 
                    width="35"
                    height="35"></a>
                     <a class="navbar-brand" href="index.jsp"><b>Your city</b></a>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="Home.jsp">Home</a>
                    </li>
                    <li class="nav-item">
                       
                    </li>
                    <li class="nav-item dropdown">
                       
   
                    </li>
                   
                </ul>
                
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                      <%
          if(user1==null)
          {
          %>
                     <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="Login.jsp">Login</a></li>
          <a class="nav-link active" aria-current="page" href="Registration.jsp">Register</a>
          <%
          
          }
else if ("Admin".equals(user1.getuName()))
{
%>
 <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="Account.jsp"><%= user1.getuName()%></a></li>
 <a class="nav-link active" aria-current="page" href="Admin.jsp">Dashboard</a></li>
          <a class="nav-link active" aria-current="page" href="Logout">Logout</a>
<%

}
          else{
      %>
      <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="Account.jsp"><%= user1.getuName()%></a></li>
          <a class="nav-link active" aria-current="page" href="Logout">Logout</a>
      <%
          
           }
          
          %>
                    
                </ul>
<!--                <form class="d-flex" role="search">
                    
                    
                    <button class="btn btn-success custom-btn" type="submit">Login</button>
                    <button class="btn btn-success custom-btn ms-2" type="submit"><a href="register.jsp" style="color: white">Register</a></button>
    
                </form>-->
            </div>
        </div>
     </div>
    </nav>
