package sample.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import sample.BE.Category;
import sample.BE.Movie;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {
    private DatabaseConnector databaseConnector;

    public MovieDAO() throws IOException {
        databaseConnector = new DatabaseConnector();
    }

    public List<Movie> getAllMovies() throws Exception {
        ArrayList<Movie> allMovies = new ArrayList<>();

        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement())
        {
            String sql = "SELECT * From dbo.Movie";
            ResultSet rs = stmt.executeQuery(sql);

            // Loop through rows from the database result set
            while (rs.next()) {

                //Map DB row to Movie object
                int id = rs.getInt("id");
                double imdbrating = rs.getDouble("imdbrating");
                double userrating = rs.getDouble("userrating");
                String title = rs.getString("title");
                String filepath = rs.getString("filepath");
                Timestamp lastview = rs.getTimestamp("lastview");



                Movie movie = new Movie(id, imdbrating, userrating, title, filepath, lastview);
                allMovies.add(movie);
            }
            return allMovies;

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not get movies from database", ex);
        }
    }

    public Movie createMovie(Movie movie) throws Exception {
        // SQL command
        String sql = "INSERT INTO dbo.Movie (imdbrating, title, filepath) VALUES (?,?,?);";

        //
        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            // Bind parameters
            stmt.setDouble(1,movie.getImdbRating());
            stmt.setString(2, movie.getTitle());
            stmt.setString(3, movie.getFilePath());


            // Run the specified SQL statement
            stmt.executeUpdate();

            // Get the generated ID from the DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            // Create movie object and send up the layers
            Movie createdMovie = new Movie(id, movie.getImdbRating(), movie.getTitle(), movie.getFilePath());

            return createdMovie;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not create movie", ex);
        }
    }

    public void updateMovie(Movie movie) throws Exception {
        // SQL command
        String sql = "UPDATE dbo.Movie SET userrating = ?, title = ?, filepath = ? WHERE Id = ?";

        //
        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            // Bind parameters
            stmt.setDouble(1, movie.getUserRating());
            stmt.setString(2, movie.getTitle());
            stmt.setString(3, movie.getFilePath());

            stmt.setInt(4, movie.getId());

            // Run the specified SQL statement
            stmt.executeUpdate();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not update movie", ex);
        }
    }

    public void deleteMovie(Movie movie) throws Exception {
        // SQL command
        String sql = "DELETE FROM dbo.Movie WHERE ID = (?);";
        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            stmt.setInt(1, movie.getId());

            // Run the specified SQL statement
            stmt.executeUpdate();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not delete movie", ex);
        }
    }

    public void addCategoryToMovie(Movie movie, Category category) throws SQLException {
        // SQL command
        String sql = "INSERT INTO dbo.CategoryMovie (CategoryId, MovieId) VALUES (?, ?);";
        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            stmt.setInt(1, category.getId());
            stmt.setInt(2, movie.getId());

            stmt.executeUpdate();

            movie.addCategoryToMovie(category);
        }
    }

    public List<Movie> getMoviesByCategory(Category category) throws Exception {
        ArrayList<Movie> moviesByCategory = new ArrayList<>();
        String sql = "SELECT * From dbo.Movie " +
                     "JOIN dbo.CategoryMovie CM " +
                     "on Movie.id = CM.MovieId " +
                     "WHERE cm.CategoryId = ?;";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            stmt.setInt(1, category.getId());

            ResultSet rs = stmt.executeQuery();

            // Loop through rows from the database result set
            while (rs.next()) {

                //Map DB row to Movie object
                int id = rs.getInt("id");
                double imdbrating = rs.getDouble("imdbrating");
                double userrating = rs.getDouble("userrating");
                String title = rs.getString("title");
                String filepath = rs.getString("filepath");
                Timestamp lastview = rs.getTimestamp("lastview");

                Movie movie = new Movie(id, imdbrating, userrating, title, filepath, lastview);
                moviesByCategory.add(movie);
            }
        return moviesByCategory;
        }
    }

    public List<Category> getCategoriesByMovie(Movie movie) throws Exception {
        ArrayList<Category> categoriesByMovie = new ArrayList<>();
        String sql = "SELECT CategoryId, name From dbo.Movie\n" +
                "JOIN dbo.CategoryMovie CM on Movie.id = CM.MovieId\n" +
                "JOIN dbo.Category C on C.id = CM.CategoryId\n" +
                "WHERE MovieId = (?)";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            stmt.setInt(1, movie.getId());

            ResultSet rs = stmt.executeQuery();

            // Loop through rows from the database result set
            while (rs.next()) {

                //Map DB row to Movie object
                int id = rs.getInt("CategoryId");
                String name = rs.getString("name");

                Category category = new Category(id, name);
                categoriesByMovie.add(category);
            }
            return categoriesByMovie;
        }
    }
}