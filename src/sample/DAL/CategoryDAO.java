package sample.DAL;

import sample.BE.Category;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private DatabaseConnector databaseConnector;

    public CategoryDAO() throws IOException {
        databaseConnector = new DatabaseConnector();
    }

    public List<Category> getAllCategories() throws Exception {
        ArrayList<Category> allCategories = new ArrayList<>();

        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement())
        {
            String sql = "SELECT * FROM dbo.Category;";
            ResultSet rs = stmt.executeQuery(sql);

            // Loop through rows from the database result set
            while (rs.next()) {

                //Map DB row to Category object
                int id = rs.getInt("id");
                String name = rs.getString("name");

                Category category = new Category(id, name);
                allCategories.add(category);
            }
            return allCategories;

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not get categories from database", ex);
        }
    }

    public Category createCategory(Category category) throws Exception {
        // SQL command
        String sql = "INSERT INTO dbo.Category VALUES (?);";

        //
        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            // Bind parameters
            stmt.setString(1,category.getName());

            // Run the specified SQL statement
            stmt.executeUpdate();

            // Get the generated ID from the DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            // Create category object and send up the layers
            Category createdCategory = new Category(id, category.getName());

            return createdCategory;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not create category", ex);
        }
    }

    public void deleteCategory(Category category) throws Exception {
        // SQL command
        String sql = "DELETE FROM dbo.Category WHERE ID = (?);";
        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            stmt.setInt(1, category.getId());

            // Run the specified SQL statement
            stmt.executeUpdate();
        }
        catch (SQLException ex)
        {
            throw new Exception("Could not delete category,\n" +
                    "remove movies associated with this category", ex);
        }
    }
}
