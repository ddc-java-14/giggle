package edu.cnm.deepdive.giggle.viewmodel;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.Transformations;
import edu.cnm.deepdive.giggle.R;
import edu.cnm.deepdive.giggle.model.entity.Joke;
import edu.cnm.deepdive.giggle.service.JokeRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;

public class JokeViewModel  extends AndroidViewModel
    implements LifecycleObserver {

  private final JokeRepository repository;
  private final MutableLiveData<Throwable> throwable;
  private final MutableLiveData<Long> jokeId;
  private final LiveData<Joke> joke;
  private final CompositeDisposable pending;


  public JokeViewModel(@NonNull Application application) {
    super(application);
    repository = new JokeRepository(application);
    throwable = new MutableLiveData<>();
    jokeId = new MutableLiveData<>();
    joke = Transformations.switchMap(jokeId, repository::get);
    pending = new CompositeDisposable();
  }

  public MutableLiveData<Throwable> getThrowable() {
    return throwable;
  }

  public LiveData<Joke> getJoke() {
    return joke;
  }
  public void setJokeId(long id) {
    jokeId.setValue(id);
  }

  public LiveData<List<Joke>> getJokes() {
    return repository.getAll();
  }

  public void save(Joke joke) {

    joke.setContent(getApplication().getString(R.string.sample_joke));
    pending.add(
        repository
            .save(joke)
            .subscribe(
                (savedJoke) -> {},
                this::postThrowable
            )
    );
  }
  public void delete(Joke joke) {
    pending.add(
        repository
            .delete(joke)
            .subscribe(
                () -> {},
                this::postThrowable
            )
    );
  }
  private void postThrowable(Throwable throwable) {
    Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable);
    this.throwable.postValue(throwable);
  }

  @OnLifecycleEvent(Event.ON_STOP)
  private void clearPending() {
    pending.clear();
  }
}





