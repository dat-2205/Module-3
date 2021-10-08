package service.category;

import dao.category.CategoryDAO;
import model.Category;
import model.Product;

import java.util.List;

public class CategoryService implements ICategoryService{
    private CategoryDAO categoryDAO = new CategoryDAO();
    @Override
    public List<Category> getAll() {
        return categoryDAO.getAll();
    }

    @Override
    public Category select(int id) {
        return categoryDAO.select(id);
    }

    @Override
    public boolean delete(int id) {
        return categoryDAO.delete(id);
    }

    @Override
    public boolean update(Category category) {
        return categoryDAO.update(category);
    }

    @Override
    public void add(Category category) {
        categoryDAO.add(category);
    }

    public List<Category> getByName(String name) {
        name = "%"+name+ "%";
        return categoryDAO.getByName(name);
    }
}
