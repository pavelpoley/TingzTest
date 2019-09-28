package pavelpoley.tingz.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import pavelpoley.tingz.model.Movie;

@Database(entities = {Movie.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MovieDao getMoviesDao();
}
