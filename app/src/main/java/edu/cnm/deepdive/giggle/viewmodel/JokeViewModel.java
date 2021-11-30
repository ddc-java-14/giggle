package edu.cnm.deepdive.giggle.viewmodel;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.Transformations;
import edu.cnm.deepdive.giggle.R;
import edu.cnm.deepdive.giggle.model.entity.Joke;
import edu.cnm.deepdive.giggle.service.JokeRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;


/**
 * Gets to data to the controller.
 */
public class JokeViewModel extends AndroidViewModel
    implements DefaultLifecycleObserver {

  private final JokeRepository repository;
  private final MutableLiveData<Throwable> throwable;
  private final MutableLiveData<Long> jokeId;
  private final LiveData<Joke> joke;
  private final MutableLiveData<Joke> searchResult;
  private final CompositeDisposable pending;


  public JokeViewModel(@NonNull Application application) {
    super(application);
    repository = new JokeRepository(application);
    throwable = new MutableLiveData<>();
    jokeId = new MutableLiveData<>();
    joke = Transformations.switchMap(jokeId, repository::get);
    searchResult = new MutableLiveData<>();
    pending = new CompositeDisposable();
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public LiveData<Joke> getJoke() {
    return joke;
  }

  public void setJokeId(long id) {
    jokeId.setValue(id);
  }

  public LiveData<Joke> getSearchResult() {
    return searchResult;
  }

  /**
   * Gets list o jokes.
   *
   * @return This returns live data of a list of jokes.
   */
  public LiveData<List<Joke>> getJokes() {
    return repository.getAll();
  }

  /**
   * This method saves a joke.
   *
   * @param joke The joke to be saved.
   */
  public void save(Joke joke) {
    throwable.setValue(null);
    pending.add(
        repository
            .save(joke)
            .subscribe(
                (savedJoke) -> {
                },
                this::postThrowable
            )
    );
  }

  public void delete(Joke joke) {
    throwable.setValue(null);
    pending.add(
        repository
            .delete(joke)
            .subscribe(
                () -> {
                },
                this::postThrowable
            )
    );
  }

  public void search(String word) {
    throwable.setValue(null);
    pending.add(
        repository
            .search(word)
            .subscribe(
                searchResult::postValue,
                this::postThrowable
            )
    );
  }

  private void postThrowable(Throwable throwable) {
    Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable);
    this.throwable.postValue(throwable);
  }

  @Override
  public void onStop(@NonNull LifecycleOwner owner) {
    DefaultLifecycleObserver.super.onStop(owner);
    pending.clear();
  }

}
