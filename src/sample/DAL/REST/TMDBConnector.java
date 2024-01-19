package sample.DAL.REST;


import java.io.FileInputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Properties;

public class TMDBConnector {

    private static final String PROP_FILE = "config/config.api";

    private final String apiKey;

    public TMDBConnector() throws Exception {
        Properties apiProperties = new Properties();
        apiProperties.load(new FileInputStream((PROP_FILE)));
        apiKey = apiProperties.getProperty("Apikey");
    }
    public String searchImdbRating(String title) throws Exception{
        String baseUrl = "https://api.themoviedb.org/3/search/movie";
        String encodedQuery = URLEncoder.encode(title, StandardCharsets.UTF_8);
        String url = baseUrl + "?api_key=" + apiKey + "&query=" + encodedQuery;

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            String responseBody = response.body();
            return getImdbRating(responseBody);
        } else {
            return "0";
        }
    }

    private static String getImdbRating(String responseBody){
        String[] allMovies = (responseBody.split("}"));
        String[] vote_average = allMovies[0].split("vote_average");
        return (vote_average[1].substring(2,5));
    }
}

