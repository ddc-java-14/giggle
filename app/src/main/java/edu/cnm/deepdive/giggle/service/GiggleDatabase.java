package edu.cnm.deepdive.giggle.service;

import android.app.Application;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import edu.cnm.deepdive.giggle.model.entity.Joke;
import edu.cnm.deepdive.giggle.model.entity.User;
import edu.cnm.deepdive.giggle.model.dao.JokeDao;
import edu.cnm.deepdive.giggle.model.dao.UserDao;
import edu.cnm.deepdive.giggle.service.GiggleDatabase.Converters;
import java.util.Date;

@Database(
    entities = {User.class, Joke.class},
    version = 1,
    exportSchema = true
)
@TypeConverters({Converters.class})
public abstract class GiggleDatabase extends RoomDatabase {

private static Application context;

  public static void setContext(Application context) {
    GiggleDatabase.context = context;
  }

  public static GiggleDatabase getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public abstract JokeDao getJokeDao();

  public abstract UserDao getUserDao();

  private static class InstanceHolder {

    private static final GiggleDatabase INSTANCE =
         Room.databaseBuilder(context, GiggleDatabase.class, "giggle-db")
            .build();

  }

  public static class Converters {

    @TypeConverter
    public static Long dateToLong(Date value) {
      return (value != null) ? value.getTime() : null;
    }

    @TypeConverter
    public static Date longToDate(Long value) {
      return (value != null) ? new Date(value) : null;
    }
  }
}
