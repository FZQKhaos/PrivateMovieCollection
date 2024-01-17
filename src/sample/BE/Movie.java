package sample.BE;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Movie {
    private int id;
    private double imdbRating;
    private double userRating;
    private String title;
    private String filePath;
    private LocalDate lastView;
    private List<Category> categories;

    public Movie(int id, double imdbRating, double userRating, String title, String filePath, LocalDate lastView) {
        this.id = id;
        this.imdbRating = imdbRating;
        this.userRating = userRating;
        this.title = title;
        this.filePath = filePath;
        this.lastView = lastView;

        categories = new ArrayList<>();
    }

    public Movie(double imdbRating, String title, String filePath, LocalDate lastView){
        this.imdbRating = imdbRating;
        this.title = title;
        this.filePath = filePath;
        this.lastView = lastView;
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

    public LocalDate getLastView() {
        return lastView;
    }

    public void setLastView(LocalDate lastView) {
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

    public void setAllCategories(List<Category> categories){
        this.categories.addAll(categories);
    }

    public List<Category> getCategories(){
        return categories;
    }

    @Override
    public String toString() {
        return title;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Movie movie = (Movie) obj;
        return Objects.equals(title, movie.title);
    }
    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
