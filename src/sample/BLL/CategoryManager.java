package sample.BLL;

import sample.BE.Category;
import sample.DAL.CategoryDAO;

import java.io.IOException;
import java.util.List;

public class CategoryManager {
    private final CategoryDAO categoryDAO;
    public CategoryManager() throws IOException {
        categoryDAO = new CategoryDAO();
    }

    public List<Category> getAllCategories() throws Exception {
        return categoryDAO.getAllCategories();
    }

    public Category createCategory(Category newCategory) throws Exception {
        return categoryDAO.createCategory(newCategory);
    }

    public void updateCategory(Category selectedCategory) throws Exception{
        categoryDAO.updateCategory(selectedCategory);
    }

    public void deleteCategory(Category selectedCategory) throws Exception {
        categoryDAO.deleteCategory(selectedCategory);
    }
}
