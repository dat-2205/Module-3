
<%--
  Created by IntelliJ IDEA.
  User: dat01
  Date: 10/8/2021
  Time: 10:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
    <style>
        #table_container {
            position: absolute;
            margin-top:100px;
        }
        #menu_top {
            background-color: darkgray;
        }
    </style>
</head>
<body>
<div>
    <nav class="nav nav-pills flex-column flex-sm-row" id="menu_top">
        <a class="flex-sm-fill text-sm-center nav-link" aria-current="page" href="/product">Product Management</a>
        <a class="flex-sm-fill text-sm-center nav-link" href="/category">Category Management</a>
    </nav>
</div>
<div id="table_container" class="container-fluid">
    <div class="card text-center">
        <div class="card-header">
            <div class="float-start">
                <form action="category?action=search" method="post">
                    <input type="text" name="search">
                    <button class="btn btn-primary" type="submit">Search <img src="https://upload.wikimedia.org/wikipedia/commons/3/36/Search_Icon.png" alt="" height="30px" width="30px"></button>
                </form>
            </div>
            <div class="float-end">
                <a href="category?action=create" class="btn btn-outline-primary">Add New</a>
            </div>
        </div>
        <div class="card-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col"> Category ID </th>
                    <th scope="col"> Category Name</th>
                    <th scope="col"> Category Image</th>
                    <th scope="col"> Action</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${categories}" var="category">
                    <tr>
                        <th scope="row">${category.id}</th>
                        <th scope="row">${category.name}</th>
                        <th scope="row"><img src="${category.image}" alt="" width="100px" height="100px"></th>

                        <th scope="row">
                            <a href="category?action=edit&id=${category.id}" class="btn btn-outline-info">Edit</a>
                            <a href="category?action=delete&id=${category.id}" class="btn btn-outline-danger">Delete</a>
                        </th>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-kQtW33rZJAHjgefvhyyzcGF3C5TFyBQBA13V1RKPf4uH+bwyzQxZ6CmMZHmNBEfJ" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.min.js" integrity="sha384-PsUw7Xwds7x08Ew3exXhqzbhuEYmA2xnwc8BuD6SEr+UmEHlX8/MCltYEodzWA4u" crossorigin="anonymous"></script>
</body>
</html>
