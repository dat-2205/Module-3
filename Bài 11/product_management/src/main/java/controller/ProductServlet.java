package controller;

import model.Product;
import service.ProductService;
import service.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    private ProductService product = new ProductServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action==null) {
            action="";
        }
        switch(action) {
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
                showAllProduct(request,response);
                break;
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                createNewProduct(request,response);
                break;
            case "edit":
                updateProduct(request,response);
                break;
            case "delete":
                break;
            default:
                break;
        }
    }

    private void createNewProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = (int) (Math.random()*1000);
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String description =  request.getParameter("description");

        Product product = new Product(id,name,price,description);
        this.product.save(product);

        response.sendRedirect("/products");
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/create.jsp");
        dispatcher.forward(request,response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        this.product.remove(id);
        response.sendRedirect("/products");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product =  this.product.findById(id);
        RequestDispatcher dispatcher;
        if(product==null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        }else {
            request.setAttribute("product", product);
            dispatcher = request.getRequestDispatcher("product/edit.jsp");
            dispatcher.forward(request,response);
        }
    }

    private void showAllProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> productList = this.product.findAll();
        request.setAttribute("productList", productList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/showAll.jsp");
        dispatcher.forward(request,response);
    }
    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");

        Product product = this.product.findById(id);
        RequestDispatcher dispatcher;

        if(product == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        }
        else {
            product.setName(name);
            product.setPrice(price);
            product.setDescription(description);
            this.product.update(id,product);
            request.setAttribute("product", product);
            request.setAttribute("message", "Product information was updated");
            dispatcher = request.getRequestDispatcher("product/edit.jsp");
            dispatcher.forward(request,response);
        }
    }
}
