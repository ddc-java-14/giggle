package edu.cnm.deepdive.giggle.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;
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
  private Date created;

  @NonNull
  @Expose
  private String search_word;

  @Expose
  @ColumnInfo(index = true)
  private int content;

  @ColumnInfo(name = "user_id", index = true)
  private long userId;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  @NonNull
  public String getSearch_word() {
    return search_word;
  }

  public void setSearch_word(@NonNull String search_word) {
    this.search_word = search_word;
  }

  public int getContent() {
    return content;
  }

  public void setContent(int content) {
    this.content = content;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }
}
