package pavelpoley.tingz.repositories;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import pavelpoley.tingz.database.AppDatabase;
import pavelpoley.tingz.model.Movie;

class LocalMoviesRepository {

    private AppDatabase db;

    LocalMoviesRepository(AppDatabase appDatabase) {
        db = appDatabase;
    }

    Single<List<Movie>> getMovies() {
        return Single.create(emitter -> {

            List<Movie> movies = db.getMoviesDao().getAll();

            if (!emitter.isDisposed())
                emitter.onSuccess(movies);
        });
    }

    Completable insertAll(final List<Movie> movies) {
        return Completable.fromAction(() -> db.getMoviesDao().clearAndInsert(movies));
    }
}
