package sample.BE;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Movie {
    private int id;
    private double imdbRating;
    private double userRating;
    private String title;
    private String filePath;
    private Timestamp lastView;
    private List<Category> categories;

    public Movie(int id, double imdbRating, double userRating, String title, String filePath, Timestamp lastView) {
        this.id = id;
        this.imdbRating = imdbRating;
        this.userRating = userRating;
        this.title = title;
        this.filePath = filePath;
        this.lastView = lastView;

        categories = new ArrayList<>();
    }

    public Movie(double imdbRating, String title, String filePath){
        this.imdbRating = imdbRating;
        this.title = title;
        this.filePath = filePath;
    }

    public Movie(int id, double imdbRating, String title, String filePath) {
        this.id = id;
        this.imdbRating = imdbRating;
        this.title = title;
        this.filePath = filePath;

        categories = new ArrayList<>();
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public double getUserRating() {
        return userRating;
    }

    public void setUserRating(double userRating) {
        this.userRating = userRating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Timestamp getLastView() {
        return lastView;
    }

    public void setLastView(Timestamp lastView) {
        this.lastView = lastView;
    }

    public void addCategoryToMovie(Category category) {
        if (!categories.contains(category)) {
            categories.add(category);
        }
    }

    public void deleteCategoryFromMovie(Category category) {
            categories.remove(category);
    }

    public Category getCategory() {
        return categories.getFirst();
    }

    @Override
    public String toString() {
        return title;
    }
}
