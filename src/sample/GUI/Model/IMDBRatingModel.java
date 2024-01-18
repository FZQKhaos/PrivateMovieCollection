package sample.GUI.Model;

import sample.BLL.IMDBRatingManager;

public class IMDBRatingModel {

    private IMDBRatingManager imdbRatingManager;

    public IMDBRatingModel() throws Exception {
        imdbRatingManager = new IMDBRatingManager();
    }

    public String searchImdbRating(String title) throws Exception {
        return imdbRatingManager.searchImdbRating(title);
    }

}
