package pavelpoley.tingz.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import pavelpoley.tingz.model.Movie;

@Dao
public abstract class MovieDao {

    @Query("SELECT * FROM movie ORDER BY releaseYear DESC")
    public abstract List<Movie> getAll();

    @Insert()
    public abstract void insertAll(List<Movie> movies);

    @Query("delete from movie")
    public abstract void deleteAll();

    @Transaction
    public void clearAndInsert(List<Movie> movies) {
        deleteAll();
        insertAll(movies);
    }
}
