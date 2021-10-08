
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
        <a class="flex-sm-fill text-sm-center nav-link active" aria-current="page" href="/product">Product Management</a>
        <a class="flex-sm-fill text-sm-center nav-link" href="/category">Category Management</a>
    </nav>
</div>
<div id="table_container" class="container-fluid">
    <div class="card text-center">
        <div class="card-header">
            <div class="float-start">
                <form action="product?action=search" method="post">
                    <input type="text" name="search">
                    <button class="btn btn-primary" type="submit">Search <img src="https://upload.wikimedia.org/wikipedia/commons/3/36/Search_Icon.png" alt="" height="30px" width="30px"></button>
                </form>
            </div>
            <div class="float-end">
                <a href="product?action=create" class="btn btn-outline-primary">Add New</a>
            </div>
        </div>
        <div class="card-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col"> Product ID </th>
                    <th scope="col"> Product Name</th>
                    <th scope="col"> Product Price</th>
                    <th scope="col"> Product Color</th>
                    <th scope="col"> Product Description</th>
                    <th scope="col"> Category</th>
                    <th scope="col"> Action</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${products}" var="product">
                    <tr>
                        <th scope="row">${product.id}</th>
                        <th scope="row">${product.name}</th>
                        <th scope="row"><fmt:formatNumber value="${product.price}"></fmt:formatNumber></th>
                        <th scope="row">${product.quantity}</th>
                        <th scope="row">${product.color}</th>
                        <th scope="row">${product.description}</th>
                        <th scope="row">${product.category.getName()}</th>
                        <th scope="row">
                            <a href="product?action=edit&id=${product.id}" class="btn btn-outline-info">Edit</a>
                            <a href="product?action=delete&id=${product.id}" class="btn btn-outline-danger">Delete</a>
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
