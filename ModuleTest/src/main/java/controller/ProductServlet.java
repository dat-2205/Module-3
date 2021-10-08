package controller;

import model.Category;
import model.Product;
import service.category.CategoryService;
import service.product.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/product")
public class ProductServlet extends HttpServlet {
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
                deleteProduct(request,response);
                break;
            }
            default: {
                showProductList(request,response);
                break;
            }
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        productService.delete(id);
        try {
            response.sendRedirect("product");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.select(id);
        request.setAttribute("product",product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/edit.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
        List<Category> categories = categoryService.getAll();
        request.setAttribute("categories",categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/create.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showProductList(HttpServletRequest request, HttpServletResponse response) {
        List<Product> products = productService.getAll();
        request.setAttribute("products",products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/showProduct.jsp");
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
                createProduct(request,response);
                break;
            }
            case "edit": {
                editProduct(request,response);
                break;
            }
            case "search": {
                showSearch(request,response);
                break;
            }
            default: {
                showProductList(request,response);
                break;
            }
        }
    }

    private void showSearch(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("search");
        List<Product> products ;
        if(name == null || name.equals("")) {
            products = productService.getAll();
        }else {
            products = productService.getByName(name);
        }
        request.setAttribute("products",products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/showProduct.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name  = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int category_id = Integer.parseInt(request.getParameter("category_id"));
        Product product = new Product(id,name,price,quantity,color,description,category_id);
        request.setAttribute("product",product);
        productService.update(product);

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

    private void createProduct(HttpServletRequest request, HttpServletResponse response) {
        String name  = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int category_id = Integer.parseInt(request.getParameter("category_id"));
        Product product = new Product(name,price,quantity,color,description,category_id);
        productService.add(product);

        String message = "Add complete";
        request.setAttribute("message",message);

        RequestDispatcher dispatcher = request.getRequestDispatcher("product/create.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
