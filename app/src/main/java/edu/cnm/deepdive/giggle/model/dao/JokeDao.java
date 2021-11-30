package edu.cnm.deepdive.giggle.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.giggle.model.entity.Joke;
import io.reactivex.Maybe;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface JokeDao {

  @Insert
  Single<Long> insert(Joke joke);

  @Insert
  Single<List<Long>> insert(Joke... jokes);

  @Insert
  Single<List<Long>> insert(Collection<Joke> jokes);

  @Update
  Single<Integer> update(Joke jokes);

  @Update
  Single<Integer> update(Joke... jokes);

  @Update
  Single<Integer> update(Collection<Joke> jokes);

  @Delete
  Single<Integer> delete(Joke joke);

  @Delete
  Single<Integer> delete(Joke... jokes);

  @Delete
  Single<Integer> delete(Collection<Joke> jokes);

  @Query("SELECT * FROM joke ORDER BY created DESC")
  LiveData<List<Joke>> selectAll();

  @Query("SELECT * FROM joke WHERE joke_id = :jokeId")
  LiveData<Joke> select(long jokeId);

  @Query("SELECT * FROM joke WHERE service_key = :serviceKey")
  Maybe<Joke> getByServiceKey(long serviceKey);


}
