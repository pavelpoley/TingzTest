package pavelpoley.tingz.repositories;

import androidx.room.Room;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import pavelpoley.tingz.App;
import pavelpoley.tingz.Constants;
import pavelpoley.tingz.database.AppDatabase;
import pavelpoley.tingz.model.Movie;
import pavelpoley.tingz.service.MoviesApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Facade class provides simple interface to handle movies
 */
public class MoviesManager {

    private RemoteMoviesRepository remoteMoviesRepository;
    private LocalMoviesRepository localMoviesRepository;

    public MoviesManager() {
        remoteMoviesRepository = new RemoteMoviesRepository(getMovieApi());
        localMoviesRepository = new LocalMoviesRepository(getAppDatabase());
    }

    //fetches movies from server and save it locally
    public Completable syncMovies() {
        return remoteMoviesRepository.getMovies()
                .flatMapCompletable(movies -> localMoviesRepository.insertAll(movies).subscribeOn(Schedulers.io()));
    }

    //load movies from local db
    public Single<List<Movie>> getMovies() {
        return localMoviesRepository.getMovies();
    }


    private MoviesApi getMovieApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_MOVIES_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(MoviesApi.class);
    }

    private AppDatabase getAppDatabase(){
        return Room.databaseBuilder(App.get(), AppDatabase.class, "tingz-movies").build();
    }
}
