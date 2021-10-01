package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserServlet", value = "/users")
public class UserServlet extends HttpServlet {
    private UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action==null) {
            action = "";
        }
        switch(action) {
            case "create": {
                showCreate(request,response);
                break;
            }
            case "edit": {
                showEditForm(request,response);
                break;
            }
            case "delete": {
                deleteUser(request,response);
                break;
            }
            case "page": {
                showPage(request,response);
                break;
            }
            default: {
                showPage(request,response);
                break;
            }
        }
    }
    private void showPage(HttpServletRequest request, HttpServletResponse response) {
        int countRecord = userService.countRecord();
        int totalPage = countRecord/3 + 1;
        request.setAttribute("totalPage",totalPage);
        String offset = request.getParameter("offset");
        if (offset==null) {
            offset = "0";
        }
        int offset2 = Integer.parseInt(offset);
        String pre = "";
        String next = "";
        if(offset2==0) {
            pre = "disabled";
        }else if(offset2 == (totalPage-1)*3) {
            next = "disabled";
        }
        request.setAttribute("pre",pre);
        request.setAttribute("" +
                "next", next);

        String active = "active";
        request.setAttribute("active",active);
        request.setAttribute("offset2",offset2);
        List<User> users = userService.getUserOffset(offset2);
        request.setAttribute("users",users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/showAll.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/create.jsp");
        dispatcher.forward(request,response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        userService.delete(id);
        try {
            response.sendRedirect("user/showAll.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userService.findById(id);
        request.setAttribute("user",user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/edit.jsp");
        dispatcher.forward(request,response);
    }

    private void showAllUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String getID = request.getParameter("search");
        List<User> users = null;
        if(getID!=null && !getID.equals("")){
            int id = Integer.parseInt(getID);
            users = new ArrayList<>();
            users.add(userService.findById(id));

        }else{
            users = userService.getAll();
        }
        request.setAttribute("users",users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/showAll.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action==null) {
            action = "";
        }
        switch(action) {
            case "edit": {
                editUser(request,response);
                break;
            }
            case "create": {
                createUser(request,response);
                break;
            }
        }
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        User user = new User(name,email,country);
        userService.save(user);

        request.setAttribute("message","User is save!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/create.jsp");
        dispatcher.forward(request,response);
    }

    private void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        User user = this.userService.findById(id);
        RequestDispatcher dispatcher;
        if(user==null) {
           dispatcher = request.getRequestDispatcher("error-404.jsp");
        }
        else {
            user.setName(name);
            user.setEmail(email);
            user.setCountry(country);
            userService.update(id,user);
            request.setAttribute("user", user);
            request.setAttribute("message", "User information was updated");
            dispatcher = request.getRequestDispatcher("user/edit.jsp");
            dispatcher.forward(request,response);
        }
    }
}
