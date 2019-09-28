package pavelpoley.tingz.repositories;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import pavelpoley.tingz.model.Movie;
import pavelpoley.tingz.service.MoviesApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class RemoteMoviesRepository {

    private static final String TAG = "RemoteMoviesRepository";

    private MoviesApi moviesApi;

    RemoteMoviesRepository(MoviesApi moviesApi) {
        this.moviesApi = moviesApi;
    }

    Single<List<Movie>> getMovies() {

        return Single.create(emitter -> moviesApi.getMovies().enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {

                if (emitter.isDisposed())
                    return;

                if (!response.isSuccessful()) {
                    Log.e(TAG, "onResponse: failed to fetch movies , error code - " + response.code());
                    emitter.onError(new Exception("failed to fetch movies"));
                    return;
                }

                //if the result success but response body is null return empty list
                List<Movie> movies = response.body();
                List<Movie> result = movies != null ? movies : new ArrayList<>();
                emitter.onSuccess(result);
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable throwable) {
                emitter.onError(new Exception(throwable));
            }
        }));

    }
}
