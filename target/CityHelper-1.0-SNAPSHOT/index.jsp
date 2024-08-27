<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <%@page import="com.help.cityhelper.helper.FactoryProvider"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>
             <%@include file="components/common-cs-js.jsp" %>
        <%@include file="components/Navbar.jsp" %>
        About Page
            <% out.println(FactoryProvider.getFactory());%></h1>
        
        
    </body>
</html>
