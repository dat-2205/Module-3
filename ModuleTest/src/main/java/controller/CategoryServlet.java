package controller;

import model.Category;
import service.category.CategoryService;
import service.product.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryServlet", value = "/category")
public class CategoryServlet extends HttpServlet {
    private ProductService productService = new ProductService();
    private CategoryService categoryService = new CategoryService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null) {
            action = "";
        }
        switch (action) {
            case "create" : {
                showCreateForm(request,response);
                break;
            }
            case "edit" : {
                showEditForm(request,response);
                break;
            }
            case "delete" : {
                delete(request,response);
                break;
            }
            default: {
                show(request,response);
                break;
            }
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        categoryService.delete(id);
        try {
            response.sendRedirect("category");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Category category = categoryService.select(id);
        request.setAttribute("category",category);
        RequestDispatcher dispatcher = request.getRequestDispatcher("category/edit.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("category/create.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void show(HttpServletRequest request, HttpServletResponse response) {
        List<Category> categories = categoryService.getAll();
        request.setAttribute("categories",categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("category/show.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null) {
            action = "";
        }
        switch (action) {
            case "create" : {
                create(request,response);
                break;
            }
            case "edit": {
                edit(request,response);
                break;
            }
            case "search": {
                showSearch(request,response);
                break;
            }
            default: {
                show(request,response);
                break;
            }
        }
    }

    private void showSearch(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("search");
        List<Category> categories ;
        if(name == null || name.equals("")) {
            categories = categoryService.getAll();
        }else {
            categories = categoryService.getByName(name);
        }
        request.setAttribute("categories",categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("category/show.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name  = request.getParameter("name");
        String image = request.getParameter("image");
        Category category = new Category(id,name,image);
        categoryService.update(category);
        request.setAttribute("category",category);
        String message = "Change save!";
        request.setAttribute("message",message);

        RequestDispatcher dispatcher = request.getRequestDispatcher("product/edit.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void create(HttpServletRequest request, HttpServletResponse response) {
        int id = 0;
        String name  = request.getParameter("name");
        String image = request.getParameter("image");
        Category category = new Category(id,name,image);
        categoryService.add(category);

        String message = "Add complete";
        request.setAttribute("message",message);

        RequestDispatcher dispatcher = request.getRequestDispatcher("category/create.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
