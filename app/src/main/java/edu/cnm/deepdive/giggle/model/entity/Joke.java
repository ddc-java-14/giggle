package edu.cnm.deepdive.giggle.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Date;

@Entity(
    tableName = "joke",
    foreignKeys = {
        @ForeignKey(
            entity = User.class,
            parentColumns = "user_id",
            childColumns = "user_id",
            onDelete = ForeignKey.CASCADE
        )
    }

)
public class Joke {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "joke_id")
  private int id;


  @Expose
  @ColumnInfo(index = true)
  private int created;

  @NonNull
  @Expose
  private String name;

  @Expose
  @ColumnInfo(index = true)
  private int favorite_joke;

  @Expose
  @ColumnInfo(index = true)
  private int category;

  @Expose
  @NonNull
  private String text;

  @ColumnInfo(name = "user_id", index = true)
  private long userId;




}
