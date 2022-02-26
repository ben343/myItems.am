<%@ page import="model.User" %>
<%@ page import="manager.CategoryManager" %>
<%@ page import="model.Category" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Ben Khachatryan
  Date: 24.02.2022
  Time: 22:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    .dropbtn {
        background-color: #3498DB;
        color: white;
        padding: 16px;
        font-size: 16px;
        border: none;
        cursor: pointer;
    }


    .dropbtn:hover, .dropbtn:focus {
        background-color: #2980B9;
    }


    .dropdown {
        position: relative;
        display: inline-block;
    }


    .dropdown-content {
        display: none;
        position: absolute;
        background-color: #f1f1f1;
        min-width: 160px;
        box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
        z-index: 1;
    }


    .dropdown-content a {
        color: black;
        padding: 12px 16px;
        text-decoration: none;
        display: block;
    }


    .dropdown-content a:hover {
        background-color: #ddd
    }


    .show {
        display: block;
    }
</style>
<body>
<%
    HttpSession currentSession = request.getSession();
    User user = (User) currentSession.getAttribute("user");
    CategoryManager categoryManager = new CategoryManager();
    List<Category> allCategories = categoryManager.getAllCategories();
%>
<form action="/createAd" method="post" enctype="multipart/form-data">
    <div class="dropbtn">
        <h1>Գրանցել հայտարարություն</h1>
        <hr>


        <label><b>Անունը</b></label>
        <input type="text" placeholder="Enter Title" name="title" id="title" required>
        <label><b>Ընտրել կատեգորիա</b></label><br>
        <select name="content">
            <%for (Category category : allCategories) {%>
            <option value="<%=category.getId()%>"><%=category.getName()%>></option>
            <%}%>
        </select>

        <label><b>Գինը</b></label>
        <input type="text" placeholder="Input the price" name="price" id="psw-repeat" required>
        <label><b>Ընտրել արժույթ</b></label>
        <select name="dropdown-content ">
            <option value="USD">USD</option>
            <option value="AMD">AMD</option>
            <option value="RUB">RUB</option>
            <option value="EUR">EUR</option>
        </select>

        <label>Ընտրել նկար</label><br>
        <input type="file" name="image"><br>
        <hr>
        <button type="submit" class="show">Ավելացնել</button>
    </div>
</form>

</body>
</html>
