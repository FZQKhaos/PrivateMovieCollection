package sample.GUI.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.BE.Category;
import sample.BLL.CategoryManager;

import java.io.IOException;
import java.util.List;

public class CategoryModel {

    private ObservableList<Category> observableCategories;

    private CategoryManager categoryManager;

    public CategoryModel() throws Exception {
        categoryManager = new CategoryManager();
        observableCategories = FXCollections.observableArrayList();
        observableCategories.addAll(categoryManager.getAllCategories());
    }

    public ObservableList<Category> getObservableCategories() throws Exception {
        return observableCategories;
    }

    public List<Category> getAllCategories() throws Exception {
        return categoryManager.getAllCategories();
    }

    public Category createCategory(Category newCategory) throws Exception {
        return categoryManager.createCategory(newCategory);
    }

    public void updateCategory(Category selectedCategory) throws Exception{
        categoryManager.updateCategory(selectedCategory);
    }

    public void deleteCategory(Category selectedCategory) throws Exception {
        categoryManager.deleteCategory(selectedCategory);
    }
}
