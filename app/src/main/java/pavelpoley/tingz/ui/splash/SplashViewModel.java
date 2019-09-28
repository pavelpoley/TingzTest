package pavelpoley.tingz.ui.splash;

import io.reactivex.android.schedulers.AndroidSchedulers;
import pavelpoley.tingz.App;
import pavelpoley.tingz.SingleLiveEvent;
import pavelpoley.tingz.repositories.MoviesManager;
import pavelpoley.tingz.ui.base.BaseViewModel;

public class SplashViewModel extends BaseViewModel {

    private static final String TAG = "SplashViewModel";

    private final MoviesManager moviesManager = App.get().getMoviesManager();
    private SingleLiveEvent<Event> status = new SingleLiveEvent<>();

    enum Event {
        NEXT, ERROR
    }

    SingleLiveEvent<Event> getData() {
        addSubscription(moviesManager.syncMovies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> status.setValue(Event.NEXT), throwable -> status.setValue(Event.ERROR)));

        return status;
    }

}
