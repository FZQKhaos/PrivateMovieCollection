package sample.BLL;

import sample.DAL.REST.TMDBConnector;

public class IMDBRatingManager {

    private TMDBConnector tmdbConnector;

    public IMDBRatingManager() throws Exception {
        tmdbConnector = new TMDBConnector();
    }

    public String searchImdbRating(String title) throws Exception {
       return tmdbConnector.searchImdbRating(title);
    }
}
