package edu.cnm.deepdive.giggle.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cnm.deepdive.giggle.model.entity.Joke;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Call;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface WebServiceProxy {

  @GET("Any")
  Single<Joke> getJoke(@Query("contains") String contains, @Query("blacklistFlags") String flags,
      @Query("type") String type);

  default Single<Joke> getJoke(String contains) {
    return getJoke(contains, "nsfw,religious,political,racist,sexist,explicit", "single");
  }

  static WebServiceProxy getInstance() {
    return InstanceHolder.INSTANCE;
  }

  class InstanceHolder {

    private static final WebServiceProxy INSTANCE;

    static {
      Gson gson = new GsonBuilder()
          .excludeFieldsWithoutExposeAnnotation()
          .create();
      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel(Level.BODY);
      OkHttpClient client = new OkHttpClient.Builder()
          .addInterceptor(interceptor)
          .build();
      Retrofit retrofit = new Retrofit.Builder()
//          .baseUrl("https://v2.jokeapi.dev/joke/Any?blacklistFlags=nsfw,religious,political,racist,sexist,explicit&type=single&contains=")
          .baseUrl("https://v2.jokeapi.dev/joke/")
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .addConverterFactory(GsonConverterFactory.create(gson))
          .client(client)
          .build();
      INSTANCE = retrofit.create(WebServiceProxy.class);


    }
  }

}
