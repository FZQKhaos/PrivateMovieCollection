package sample.BE;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Movie {
    private int id;
    private String name;
    private double rating;
    private String filePath;
    private Timestamp lastView;
    private List<Category> categories;

    public Movie(int id, String name, double rating, String filePath) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.filePath = filePath;

        categories = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
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

    @Override
    public String toString() {
        return name + ", " + rating + ", " + filePath + ", " + lastView + ", " + categories;
    }
}
