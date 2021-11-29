package edu.cnm.deepdive.giggle.service;

import android.app.Application;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.giggle.model.dao.JokeDao;
import edu.cnm.deepdive.giggle.model.entity.Joke;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.Date;
import java.util.List;

public class JokeRepository {

  //field
  private final Application context;
  private final JokeDao jokeDao;
  private final WebServiceProxy proxy;

  //constructor
  public JokeRepository(Application context) {
    this.context = context;
    jokeDao = GiggleDatabase
        .getInstance()
        .getJokeDao();
    proxy = WebServiceProxy.getInstance();
  }

  public LiveData<Joke> get(long jokeId) {
    return jokeDao.select(jokeId);
  }

  public LiveData<List<Joke>> getAll() {
    return jokeDao.selectAll();
  }

  public Single<Joke> save(Joke joke) {
    Single<Joke> task;
    if (joke.getId() == 0) {
      joke.setCreated(new Date());
      task = jokeDao
          .insert(joke)
          .map((id) -> {
            joke.setId(id);
            return joke;
          });
    } else {
      task = jokeDao
          .update(joke)
          .map((count) -> joke);
    }
    return task.subscribeOn(Schedulers.io());
  }


  public Completable delete(Joke joke) {
    return (joke.getId() == 0)
        ? Completable.complete()
        : jokeDao
            .delete(joke)
            .ignoreElement()
            .subscribeOn(Schedulers.io());
  }

  public Single<Joke> search(String word) {
    return proxy
        .getJoke(word)
        .subscribeOn(Schedulers.io());


  }
}




