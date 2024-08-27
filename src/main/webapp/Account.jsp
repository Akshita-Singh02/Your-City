<%@page import="com.help.cityhelper.entities.User,com.help.cityhelper.entities.Category,com.help.cityhelper.entities.City,com.help.cityhelper.dao.CityDao,com.help.cityhelper.dao.PostDao,com.help.cityhelper.dao.CategoryDao,com.help.cityhelper.helper.FactoryProvider,java.util.*,com.help.cityhelper.dao.UserDao,com.help.cityhelper.entities.Post" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <%@include file="components/common-cs-js.jsp" %>
        <%@include file="components/Navbar.jsp" %>
        <%@include file="components/message.jsp" %>
         
        <main class="container mt-4">
            <section id="header" class="row">
               
                <section class="col-md-4 col-3 text-center">
                     <div data-bs-toggle="modal" data-bs-target="#change-profile-md">
                    <img src="img/posts/<%=user1.getuPic()%>?v=<%= System.currentTimeMillis() %>" class="rounded-circle img-fluid"  width="150px" />
                     </div>
                </section>
                <section class="col-md-8 col-7 ps-4 mt-3">
                    <h1 class="h4 fs-4"><%= user1.getuName()%></h1>
                    <%
                    PostDao ud=new PostDao(FactoryProvider.getFactory());
                    int np=ud.getuPostCount(user1.getuId());
                    %>
                    <strong><%=np%> </strong>posts
                    <h2 class="h6 mt-1">Bio:</h2>
                    <p><%=user1.getuBio()%></p>
                    
                    <button data-bs-toggle="modal" data-bs-target="#change-bio-md">Update bio</button>
                    
                     
                    <button data-bs-toggle="modal" data-bs-target="#add-post-md">Add post</button>
                    
                </section> 
            </section>
                    
                    <!--        change profile modal-->

<div class="modal fade"  id="change-profile-md" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" >
    <div class="modal-content">
      <div class="modal-header custom-bg">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Change profile</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
          <form action="OperationServlet" method="post" enctype="multipart/form-data">
<!--          add this for letting server know it is for add city-->
          <input type="hidden" name="operation" value="updatepic">
          <div class="text-center">
           <img src="img/posts/<%=user1.getuPic()%>" class=" img-fluid " width="200px" />
          </div>
     <div class="form-group">
               <label for="pPhoto">Select pic for your profile</label>
               <br>
              
               <input  type="file" id="pPhoto" name="pPhoto" accept="image/*" required>
           </div>
<br>


            <button type="submit" class="btn btn-outline-success">Change</button>
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

      <!--        change bio modal-->

<div class="modal fade"  id="change-bio-md" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" >
    <div class="modal-content">
      <div class="modal-header custom-bg">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Change bio</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
          <form action="OperationServlet" method="post" enctype="multipart/form-data">
<!--          add this for letting server know it is for add city-->
          <input type="hidden" name="operation" value="updatebio">
          
          
     <div class="form-group">
               <label for="pPhoto">Type new bio for your profile </label>
               <br>
              
              
               <input style="border-radius:9px ;border-color: black;" type="text" class="form-control" name="uBio" placeholder="enter bio" required/>
           
           </div>
<br>


            <button type="submit" class="btn btn-outline-success">Change</button>
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


    
    
    <%
                
        User user=(User)session.getAttribute("current-user");
                PostDao dao = new PostDao(FactoryProvider.getFactory());
                
                
                   
                  List<Post> li= dao.getAllPostByUserId(user.getuId());
               

               
            %>

  <!-- Show products -->
            <div class="col-md-10">
                <!-- Row -->
                <div class="row mt-4">
                    
                    
                    <!-- Col which is taking 12 grid alone -->
                    <div class="col-md-12">
                        <div class="card-columns">
                            <!-- Traversing products -->
                            <%
                                if (li != null && !li.isEmpty()) {
                                    for (Post pt : li) {
                            %>
                            <!-- Product Card -->
                            <div class="col-md-3 mb-4 ">
                                
                            <div class="card">
                                <div class="container text-center">
                                    <img src="img/posts/<%=pt.getpPic()%>" style="max-height: 200px; max-width: 100%; width: auto;" class="card-img-top m-2">
                                </div>
                                <div class="card-body">
                                    <h5 class="card-title"><%= pt.getpName() %></h5>
                                    <p class="card-text">
                                        <%= pt.getpContent() %>
                                    </p>
                                </div>
                                <div class="card-footer text-center">
                                    Rating : <%=pt.getHelpful() %>
                                   
                                </div>
                            
                            </div>
                            </div>
                                    <br>
                            <%
                                    }
                                } else {
                            %>
                            <h3>No items in this category</h3>
                            <%
                                }
                            %>
                        </div>
                    </div>
                        
                     </div>
                </div>
                    

<!--            <section class="row mt-5">
                <article class="col-md-4 mt-4">
                    <a data-bs-toggle="modal" data-bs-target="#exampleModal" type="button">
                        <div class="container-article">
                            <img src="https://unsplash.it/800/800.jpg?image=251" alt="post title" class="image" />
                            <div class="overlay">
                                <div class="text">Hello World</div>
                            </div>
                        </div>
                    </a>
                </article>
                 
               
            </section>-->
        </main>
        
        
        
    </body>
</html>
