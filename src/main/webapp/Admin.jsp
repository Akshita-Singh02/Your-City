<%@page import="com.help.cityhelper.entities.User,com.help.cityhelper.entities.Category,com.help.cityhelper.entities.City,com.help.cityhelper.dao.CityDao,com.help.cityhelper.dao.PostDao,com.help.cityhelper.dao.CategoryDao,com.help.cityhelper.helper.FactoryProvider,java.util.*,com.help.cityhelper.dao.UserDao" %>

<%
User user=(User)session.getAttribute("current-user");
if(user==null)
{
session.setAttribute("message","You are not logged in!!");
response.sendRedirect("Login.jsp");
return;
}
else{
if(user.getuType().equals("normal"))
{
session.setAttribute("message","you are not admin!!");
response.sendRedirect("Login.jsp");
return;
 }
}

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .card {
    background-color: #fff;
   
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0.1, 0.1, 0.1);
   
    
}
.card:hover{
     background: #F5F5F5;
    cursor: pointer;
}
        </style>
    </head>
    <body>
         <%@include file="components/common-cs-js.jsp" %>
        <%@include file="components/Navbar.jsp" %>
        <br>
        <div class="container">
            <div class="container fluid">
            <%@include file="components/message.jsp"%>
            </div>
            
            
<!--            number of user-->

            <div class="row mt-4">
                <div class="col-md-3">
                    <div class="card">
                        <div class="card-body text-center">
                            <div class="container">
                                <img style="max-width: 125px;" class="img-fluid " src="img/office-man.png">
                            </div>
                            <%
                                UserDao udao=new UserDao(FactoryProvider.getFactory());
                                int c=udao.getuCount();
                               
                                %>
                            <h1><%=c%></h1>
                            <h1 class="text-uppercase text-muted">User</h1>
                        </div>
                    </div>
                </div><!-- comment -->
                
                
                
                
                
                
                
<!--                number of categories-->
                
                <div class="col-md-3">
                    <div class="card">
                        <div class="card-body text-center">
                            <div class="container">
                                <img style="max-width: 125px;" class="img-fluid " src="img/category.png">
                            </div>
                            <%
                                CategoryDao catdao=new CategoryDao(FactoryProvider.getFactory());
                                int ct=catdao.getcatCount();
                               
                                %>
                            <h1><%=ct%></h1>
                            <h1 class="text-uppercase text-muted">Category</h1>
                        </div>
                    </div>
                </div>







<!--number of post-->
                <div class="col-md-3">
                    <div class="card">
                        <div class="card-body text-center">
                            <div class="container">
                                <img style="max-width: 125px;" class="img-fluid " src="img/post.png">
                            </div>
                           <%
                                PostDao pdao=new PostDao(FactoryProvider.getFactory());
                                int p=pdao.getpCount();
                               
                                %>
                            <h1><%=p%></h1>
                            <h1 class="text-uppercase text-muted">Posts</h1>
                        </div>
                    </div>
                </div>






<!--number of cities-->

<div class="col-md-3">
                    <div class="card">
                        <div class="card-body text-center">
                            <div class="container">
                                <img style="max-width: 125px;" class="img-fluid " src="img/office-man.png">
                            </div>
                            <%
                                CityDao cdao=new CityDao(FactoryProvider.getFactory());
                                int f=cdao.getcCount();
                               
                                %>
                            <h1><%=f%></h1>
                            <h1 class="text-uppercase text-muted">Cities</h1>
                        </div>
                    </div>
                </div>

            </div>    
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
            <br>
        <div class="row mt-3">
            <div class="col-md-4">
            <div class="card" data-bs-toggle="modal" data-bs-target="#add-category-md">
                <div class="card-body text-center">
                            <div class="container">
                                <img style="max-width: 125px;" class="img-fluid " src="img/plus.png">
                            </div>
                          
                            <h1 class="text-uppercase text-muted">Add category</h1>
                        </div>
            </div>
            </div>
            
            
            
            <div class="col-md-4">
             <div class="card" data-bs-toggle="modal" data-bs-target="#add-post-md">
                <div class="card-body text-center">
                            <div class="container">
                                <img style="max-width: 125px;" class="img-fluid " src="img/plus.png">
                            </div>
                           
                            <h1 class="text-uppercase text-muted">Add post</h1>
                        </div>
            </div>
            </div>
            
            
            
             <div class="col-md-4">
            <div class="card" data-bs-toggle="modal" data-bs-target="#add-city-md">
                <div class="card-body text-center">
                            <div class="container">
                                <img style="max-width: 125px;" class="img-fluid " src="img/plus.png">
                            </div>
                          
                            <h1 class="text-uppercase text-muted">Add cities</h1>
                        </div>
            </div>
            </div>
        </div>
            <br>
            
            
            
<!--            next row -->


            <div class="row mt-4">
            <div class="col-md-3">
            <div class="card" data-bs-toggle="modal" data-bs-target="#remove-category-md">
                <div class="card-body text-center">
                            <div class="container">
                                <img style="max-width: 125px;" class="img-fluid " src="img/remove.png">
                            </div>
                          
                            <h1 class="text-uppercase text-muted">Remove category</h1>
                        </div>
            </div>
            </div>
            
            
            
            <div class="col-md-3">
             <div class="card" data-bs-toggle="modal" data-bs-target="#remove-post-md">
                <div class="card-body text-center">
                            <div class="container">
                                <img style="max-width: 125px;" class="img-fluid " src="img/remove.png">
                            </div>
                           
                            <h1 class="text-uppercase text-muted">Remove post</h1>
                        </div>
            </div>
            </div>
                <div class="col-md-3">
             <div class="card" data-bs-toggle="modal" data-bs-target="#remove-user-md">
                <div class="card-body text-center">
                            <div class="container">
                                <img style="max-width: 125px;" class="img-fluid " src="img/remove.png">
                            </div>
                           
                            <h1 class="text-uppercase text-muted">Remove user</h1>
                        </div>
            </div>
            </div>
            
            
            
             <div class="col-md-3">
            <div class="card" data-bs-toggle="modal" data-bs-target="#remove-city-md">
                <div class="card-body text-center">
                            <div class="container">
                                <img style="max-width: 125px;" class="img-fluid " src="img/remove.png">
                            </div>
                          
                            <h1 class="text-uppercase text-muted">Remove cities</h1>
                        </div>
            </div>
            </div>
        </div>
          <br>  
            
        </div>
                            
                 <br>            
                            
                            
                            
<!--        add category modal-->


<!-- Modal -->
<div class="modal fade"  id="add-category-md" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" >
    <div class="modal-content">
      <div class="modal-header custom-bg">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Add category</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
          <form action="OperationServlet" method="post">
<!--          add this for letting server know it is for add category-->
          <input type="hidden" name="operation" value="addcategory">
           <div class="form-group">
               <input style="border-radius:9px ;border-color: black;" type="text" class="form-control" name="catTitle" placeholder="enter category " required/>
           </div>
           <br><!-- comment -->
          
            <button type="submit" class="btn btn-outline-success">Add category</button>
                 <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
       </form>
          
          
      </div>
      <div class="modal-footer">
      <div class="container text-center">
                
       
           </div>
      </div>
    </div>
  </div>
</div>


<!--        add post modal-->

<div class="modal fade"  id="add-post-md" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" >
    <div class="modal-content">
      <div class="modal-header custom-bg">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Add post</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
          <form action="OperationServlet" method="post" enctype="multipart/form-data">
<!--          add this for letting server know it is for add city-->
          <input type="hidden" name="operation" value="addpost">
           <div class="form-group">
               <input style="border-radius:9px ;border-color: black;" type="text" class="form-control" name="pTitle" placeholder="enter place name " required/>
           </div>
           <br><!-- comment -->
           <div class="form-group">
               <textarea style="border-radius:9px ;border-color: black;" class="form-control" placeholder="enter your opinion" name="pDescription" required></textarea>
           </div>
           
           <br>
         
           <% 

CityDao x=new CityDao(FactoryProvider.getFactory());
List<City> list=x.getCity();
%>
<div class="mb-3">
<div class="form-group">
    <select name="cId" id="cId">
        <%
        for(City cop:list){
        
        %>
            
        <option value="<%=cop.getcId()%>"><%=cop.getcName()%></option>
        <%
        }
       
        %>
    </select>
</div>
  </div>
    
    <%
           CategoryDao m=new CategoryDao(FactoryProvider.getFactory());
List<Category> l=m.getCategory();

%>
<div class="mb-3">
<div class="form-group">
    <select name="catId" id="catId">
        <%
        for(Category ca:l){
        
        %>
            
        <option value="<%=ca.getCategoryId()%>"><%=ca.getCategoryTitle()%></option>
        <%
        }
        
        
        %>
    </select>
</div>
  </div>
       <label for="helpful">Ratings: </label>
            <div class="form-group">
                <select name="helpful" id="helpful">
                <option value="1">1</option>
                 <option value="2">2</option>
                  <option value="3">3</option>
                   <option value="4">4</option>
                    <option value="5">5</option>
                </select>
            </div>
<br>
     <div class="form-group">
               <label for="pPhoto">Select pic for your post</label>
               <br>
              
               <input  type="file" id="pPhoto" name="pPhoto" accept="image/*" required>
           </div>
<br>


            <button type="submit" class="btn btn-outline-success">Add post</button>
                 <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
       </form>
          
          
      </div>
      <div class="modal-footer">
      <div class="container text-center">
                
       
           </div>
      </div>
    </div>
  </div>
</div>



<!--        add city modal-->


<!-- Modal -->
<div class="modal fade"  id="add-city-md" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" >
    <div class="modal-content">
      <div class="modal-header custom-bg">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Add city</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
          <form action="OperationServlet" method="post">
<!--          add this for letting server know it is for add city-->
          <input type="hidden" name="operation" value="addcity">
           <div class="form-group">
               <input style="border-radius:9px ;border-color: black;" type="text" class="form-control" name="cityName" placeholder="enter city name " required/>
           </div>
           <br><!-- comment -->
           <div class="form-group">
               <textarea style="border-radius:9px ;border-color: black;" class="form-control" placeholder="enter city zipcode" name="cityZipcode" required></textarea>
           </div>
           <br>
            <button type="submit" class="btn btn-outline-success">Add city</button>
                 <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
       </form>
          
          
      </div>
      <div class="modal-footer">
      <div class="container text-center">
                
       
           </div>
      </div>
    </div>
  </div>
</div>


<!--        remove category modal-->


<!-- Modal -->
<div class="modal fade"  id="remove-category-md" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" >
    <div class="modal-content">
      <div class="modal-header custom-bg">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Remove category</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
          <form action="OperationServlet" method="post">
<!--          add this for letting server know it is for add category-->
          <input type="hidden" name="operation" value="removecategory">
           <div class="form-group">
               <input style="border-radius:9px ;border-color: black;" type="text" class="form-control" name="categoryid" placeholder="enter category ID" required/>
           </div>
           <br><!-- comment -->
          
            <button type="submit" class="btn btn-outline-success">Remove category</button>
                 <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
       </form>
          
          
      </div>
      <div class="modal-footer">
      <div class="container text-center">
                
       
           </div>
      </div>
    </div>
  </div>
</div>


<!--        remove city modal-->


<!-- Modal -->
<div class="modal fade"  id="remove-city-md" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" >
    <div class="modal-content">
      <div class="modal-header custom-bg">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Remove city</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
          <form action="OperationServlet" method="post">
<!--          add this for letting server know it is for add category-->
          <input type="hidden" name="operation" value="removecity">
           <div class="form-group">
               <input style="border-radius:9px ;border-color: black;" type="text" class="form-control" name="cityid" placeholder="enter city ID" required/>
           </div>
           <br><!-- comment -->
          
            <button type="submit" class="btn btn-outline-success">Remove city</button>
                 <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
       </form>
          
          
      </div>
      <div class="modal-footer">
      <div class="container text-center">
                
       
           </div>
      </div>
    </div>
  </div>
</div>


<!--        remove user modal-->


<!-- Modal -->
<div class="modal fade"  id="remove-user-md" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" >
    <div class="modal-content">
      <div class="modal-header custom-bg">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Remove user</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
          <form action="OperationServlet" method="post">
<!--          add this for letting server know it is for add category-->
          <input type="hidden" name="operation" value="removeUser">
           <div class="form-group">
               <input style="border-radius:9px ;border-color: black;" type="text" class="form-control" name="userid" placeholder="enter user ID" required/>
           </div>
           <br><!-- comment -->
          
            <button type="submit" class="btn btn-outline-success">Remove user</button>
                 <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
       </form>
          
          
      </div>
      <div class="modal-footer">
      <div class="container text-center">
                
       
           </div>
      </div>
    </div>
  </div>
</div>

<!--        remove post modal-->


<!-- Modal -->
<div class="modal fade"  id="remove-post-md" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" >
    <div class="modal-content">
      <div class="modal-header custom-bg">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Remove post</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
          <form action="OperationServlet" method="post">
<!--          add this for letting server know it is for add category-->
          <input type="hidden" name="operation" value="removePost">
           <div class="form-group">
               <input style="border-radius:9px ;border-color: black;" type="text" class="form-control" name="postid" placeholder="enter post ID" required/>
           </div>
           <br><!-- comment -->
          
            <button type="submit" class="btn btn-outline-success">Remove post</button>
                 <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
       </form>
          
          
      </div>
      <div class="modal-footer">
      <div class="container text-center">
                
       
           </div>
      </div>
    </div>
  </div>
</div>




    </body>
</html>
