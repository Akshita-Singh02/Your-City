<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.help.cityhelper.helper.FactoryProvider,com.help.cityhelper.dao.PostDao,com.help.cityhelper.dao.CategoryDao,com.help.cityhelper.entities.Post,com.help.cityhelper.entities.Category"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
    <%@include file="components/common-cs-js.jsp" %>
    <%@include file="components/Navbar.jsp" %>

    <div class="container-fluid">
        <div class="row mt-3 mx-2">
            <%
                String cat = request.getParameter("category");
                PostDao dao = new PostDao(FactoryProvider.getFactory());
//                UserDao udao = new UserDao(FactoryProvider.getFactory());
//                int city_id=udao.getCityId(user1.getuId());
                List<Post> list = null;
                if (cat == null || cat.trim().equals("all")) {
                    list = dao.getAllPost();
                } else {
                    int cid = Integer.parseInt(cat.trim());
                    list = dao.getAllPostById(cid);
                }
              PostDao pdao = new PostDao(FactoryProvider.getFactory());

            CategoryDao cdao = new CategoryDao(FactoryProvider.getFactory());
                List<Category> clist = cdao.getCategory();
            %>

            <!-- Show categories -->
            <div class="col-md-2">
                <div class="list-group">
                    <a href="Home.jsp?category=all" class="list-group-item list-group-item-action active" aria-current="true">
                        All products
                    </a>
                    <%
                        for (Category cd : clist) {
                    %>
                    <a href="Home.jsp?category=<%= cd.getCategoryId() %>" class="list-group-item list-group-item-action">
                        <%= cd.getCategoryTitle() %>
                    </a>
                    <%
                        }
                    %>
                </div>
                <br>
            </div>
                
                
                

            <!-- Show products -->
            <div class="col-md-10">
                <!-- Row -->
                <div class="row mt-4">
                    
                    
                    <!-- Col which is taking 12 grid alone -->
                    <div class="col-md-12">
                        <div class="card-columns">
                            <!-- Traversing products -->
                            <%
                                if (list != null && !list.isEmpty()) {
                                    for (Post pt : list) {
                                    String profilePic = pdao.getProfileByPid(pt.getpId());
                            %>
                            <!-- Product Card -->
                            <div class="col-md-3 mb-4 ">
                                
                            <div class="card">
                                <div class="card-footer">
                                <br>
                                <div>
                                    <img src="img/posts/<%=profilePic%>" class="rounded-circle img-fluid" width="30px" />
                                <span><%= pdao.getNameByPid(pt.getpId())%></span>
                                </div>
                                </div>
                                
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
                        
                        
                        
                        
                <h1></h1>
           
        </div>
    </div>
</body>
</html>
