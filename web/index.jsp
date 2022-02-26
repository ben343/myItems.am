<%@ page import="model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Item" %><%--
  Created by IntelliJ IDEA.
  User: Ben Khachatryan
  Date: 23.02.2022
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Item.am</title>
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
<div class="dropbtn">
    <h1>ITEM.AM</h1>
</div>
<% List<Category> categories = (List<Category>) session.getAttribute("category_id");
%>
<div class="dropdown">
    <a href="/">Գլխավոր</a>
    <%for (Category category : categories) {%>
    <a href="${pageContext.request.contextPath}/?category_id=<%=category.getId()%>"><%=category.getName()%>
    </a>
    <%}%>
    <a href="/logIn.jsp" class="right">Մուտք</a>
    <a href="/register.jsp" class="right">Գրանցվել</a>
</div>

<% List<Item> items = (List<Item>) request.getAttribute("items");
    if (items != null) {
        for (Item item : items) {%>
<div class="row">
    <div class="show">
        <h2><%=item.getTitle()%>
        </h2>
        <h3><%=item.getUser_id().getEmail()%>
        </h3>
        <%if (item.getPic_url() != null) {%>
        <div class="fakeImg" style="height:200px;">
            <img src="/image?path=<%=item.getPic_url()%>" width="220px">
        </div>
        <%}%>
    </div>
</div>
<%}%>
<%}%>


</form>
</body>
</html>
