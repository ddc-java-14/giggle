package edu.cnm.deepdive.giggle;

import android.app.Application;
import com.facebook.stetho.Stetho;
import edu.cnm.deepdive.giggle.service.GiggleDatabase;
import edu.cnm.deepdive.giggle.service.GoogleSignInRepository;
import io.reactivex.schedulers.Schedulers;

/**
 * Initializes (in the {@link #onCreate()} method) application-level resources. This class
 * <strong>must</strong> be referenced in {@code AndroidManifest.xml}, or it will not be loaded and
 * used by the Android system.
 */
public class GiggleApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
    GoogleSignInRepository.setContext(this);
    GiggleDatabase.setContext(this);
    GiggleDatabase
        .getInstance()
        .getJokeDao()
        .delete()
        .subscribeOn(Schedulers.io())
        .subscribe();
  }

}
