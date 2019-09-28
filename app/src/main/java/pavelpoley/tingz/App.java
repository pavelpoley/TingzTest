package pavelpoley.tingz;

import android.app.Application;

import pavelpoley.tingz.repositories.MoviesManager;

public class App extends Application {

    public static App instance = null;

    //single instance for MoviesManager to avoid creating multiple AppDatabase/Retrofit instances
    //prefer to inject with dagger, but not used for this project
    private MoviesManager moviesManager;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }

    public static App get() {
        return instance;
    }

    public MoviesManager getMoviesManager() {
        if (moviesManager == null)
            moviesManager = new MoviesManager();

        return moviesManager;
    }
}
