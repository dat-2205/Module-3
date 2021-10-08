package service.product;

import dao.product.ProductDAO;
import model.Category;
import model.Product;
import service.category.CategoryService;

import java.util.List;

public class ProductService implements IProductService{
    private ProductDAO productDAO = new ProductDAO();
    private CategoryService categoryService = new CategoryService();

    @Override
    public List<Product> getAll() {
        List<Product> products = productDAO.getAll();
        for(Product product : products) {
            Category category = categoryService.select(product.getCategory_id());
            product.setCategory(category);
        }
        return products;
    }

    @Override
    public Product select(int id) {
        Product product = productDAO.select(id);
        Category category = categoryService.select(product.getCategory_id());
        product.setCategory(category);
        return product;
    }

    @Override
    public boolean delete(int id) {
        return productDAO.delete(id);
    }

    @Override
    public boolean update(Product product) {
        return productDAO.update(product);
    }

    @Override
    public void add(Product product) {
        productDAO.add(product);
    }

    public List<Product> getByName(String name) {
        name = "%"+name+ "%";
        return productDAO.getByName(name);
    }
}
