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


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getCreated() {
    return created;
  }

  public void setCreated(int created) {
    this.created = created;
  }

  @NonNull
  public String getName() {
    return name;
  }

  public void setName(@NonNull String name) {
    this.name = name;
  }

  public int getFavorite_joke() {
    return favorite_joke;
  }

  public void setFavorite_joke(int favorite_joke) {
    this.favorite_joke = favorite_joke;
  }

  public int getCategory() {
    return category;
  }

  public void setCategory(int category) {
    this.category = category;
  }

  @NonNull
  public String getText() {
    return text;
  }

  public void setText(@NonNull String text) {
    this.text = text;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }
}
