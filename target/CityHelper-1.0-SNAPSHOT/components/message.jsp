<%
    String msg=(String)session.getAttribute("message");
    if(msg!=null)
    {
    
    %>
    
    <div class="alert alert-warning alert-dismissible fade show" style="" role="alert">
  <strong><%= msg%> </strong> 
  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
    
    <%
        session.removeAttribute("message");
        }
        %>