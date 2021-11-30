package edu.cnm.deepdive.giggle.service;

import android.app.Application;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.giggle.R;
import edu.cnm.deepdive.giggle.model.dao.JokeDao;
import edu.cnm.deepdive.giggle.model.entity.Joke;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.Date;
import java.util.List;

public class JokeRepository {

  private final Application context;
  private final JokeDao jokeDao;
  private final WebServiceProxy proxy;
  private final String jokeNotFoundMessage;

  public JokeRepository(Application context) {
    this.context = context;
    jokeDao = GiggleDatabase
        .getInstance()
        .getJokeDao();
    proxy = WebServiceProxy.getInstance();
    jokeNotFoundMessage = context.getString(R.string.joke_not_found_message);
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
        .map((joke) -> {
          if (joke.isError()) {
            throw new JokeNotFoundException(jokeNotFoundMessage);
          }
          if (joke.getDelivery() != null && !joke.getDelivery().trim().isEmpty()) {
            String combined =
                String.format("%s\n\n%s", joke.getContent().trim(), joke.getDelivery().trim());
            joke.setContent(combined);
            joke.setDelivery(null);
          }
          return joke;
        })
        .flatMap((joke) -> jokeDao
            .getByServiceKey(joke.getServiceKey())
            .map((retrievedJoke) -> {
              retrievedJoke.setFavorite(true);
              return retrievedJoke;
            })
            .switchIfEmpty(Single.just(joke))
        )
        .subscribeOn(Schedulers.io());


  }

  public static class JokeNotFoundException extends Exception {

    public JokeNotFoundException() {
    }

    public JokeNotFoundException(String message) {
      super(message);
    }

    public JokeNotFoundException(String message, Throwable cause) {
      super(message, cause);
    }

    public JokeNotFoundException(Throwable cause) {
      super(cause);
    }

    public JokeNotFoundException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
    }
  }
}
