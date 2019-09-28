package pavelpoley.tingz.ui.movielist;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pavelpoley.tingz.App;
import pavelpoley.tingz.repositories.MoviesManager;
import pavelpoley.tingz.model.Movie;
import pavelpoley.tingz.system.Gui;
import pavelpoley.tingz.ui.base.BaseViewModel;

public class MovieListViewModel extends BaseViewModel {

    private static final String TAG = "MovieListViewModel";

    private MutableLiveData<List<Movie>> moviesLiveData = new MutableLiveData<>();
    private MoviesManager moviesManager = App.get().getMoviesManager();

    LiveData<List<Movie>> loadMovies() {

        addSubscription(moviesManager.getMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> moviesLiveData.setValue(movies), throwable -> {
                    Log.e(TAG, "loadMovies: ", throwable);
                    Gui.showToast("Failed to load data");
                }));

        return moviesLiveData;
    }

    MutableLiveData<List<Movie>> getMoviesLiveData() {
        return moviesLiveData;
    }
}
