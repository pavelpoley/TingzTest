package pavelpoley.tingz.service;

import java.util.List;

import pavelpoley.tingz.model.Movie;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MoviesApi {

    @GET("/json/movies.json")
    Call<List<Movie>> getMovies();
}
