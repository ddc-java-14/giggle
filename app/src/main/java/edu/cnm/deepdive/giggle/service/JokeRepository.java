package edu.cnm.deepdive.giggle.service;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.giggle.model.entity.Joke;
import edu.cnm.deepdive.giggle.model.entity.User;
import edu.cnm.deepdive.giggle.model.entity.dao.JokeDao;
import edu.cnm.deepdive.giggle.model.entity.dao.UserDao;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class JokeRepository {


  private final JokeDao jokeDao;
  private final UserDao userDao;

  public JokeRepository() {
    GiggleDatabase datasbase = GiggleDatabase.getInstance();
    jokeDao = datasbase.getJokeDao();
    userDao = datasbase.getUserDao();
  }

  public Single<Joke> save(Joke joke) {

  }


}




