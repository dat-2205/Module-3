
<%--
  Created by IntelliJ IDEA.
  User: dat01
  Date: 10/8/2021
  Time: 10:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <a class="flex-sm-fill text-sm-center nav-link " aria-current="page" href="/product">Product Management</a>
        <a class="flex-sm-fill text-sm-center nav-link" href="/category">Category Management</a>
    </nav>
</div>
<div id="table_container" class="container-fluid">
    <div class="card text-center">
        <div class="card-header">

        </div>
        <div class="card-body">
            <section class="vh-100 gradient-custom">
                <div class="container py-5 h-100">
                    <div class="row justify-content-center align-items-center h-100">
                        <div class="col-12 col-lg-9 col-xl-7">
                            <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
                                <div class="card-body p-4 p-md-5">
                                    <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Product Information</h3>
                                    <p>${message}</p>
                                    <form action="/category?action=create" method="post">

                                        <div class="row">
                                            <div class="col-md-6 mb-4">

                                                <div class="form-outline">
                                                    <input type="text" id="firstName" class="form-control form-control-lg" name="name" />
                                                    <label class="form-label" for="firstName">Category Name</label>
                                                </div>

                                            </div>
                                            <div class="col-md-6 mb-4">

                                                <div class="form-outline">
                                                    <input type="text" id="lastName" class="form-control form-control-lg" name="image" value=""/>
                                                    <label class="form-label" for="lastName">Category Image</label>
                                                </div>

                                            </div>
                                        </div>
                                        <div class="mt-4 pt-2">
                                            <input class="btn btn-primary btn-lg" type="submit" value="Submit" />
                                        </div>

                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
        <div class="card-footer text-muted">

        </div>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-kQtW33rZJAHjgefvhyyzcGF3C5TFyBQBA13V1RKPf4uH+bwyzQxZ6CmMZHmNBEfJ" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.min.js" integrity="sha384-PsUw7Xwds7x08Ew3exXhqzbhuEYmA2xnwc8BuD6SEr+UmEHlX8/MCltYEodzWA4u" crossorigin="anonymous"></script>
</body>
</html>
