package edu.cnm.deepdive.giggle.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
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
  private long id;


  @Expose
  @ColumnInfo(index = true)
  private Date created;

  @NonNull
  @Expose
  @ColumnInfo(name = "search_word", index = true)
  private String searchWord;


  @Expose
  @SerializedName(value = "joke", alternate = {"setup"})
  @ColumnInfo(index = true)
  private String content;

  @Expose
  @Ignore
  private String delivery;

  @ColumnInfo(name = "user_id", index = true)
  private Long userId; // FIXME Replace with primitive


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  @NonNull
  public String getSearchWord() {
    return searchWord;
  }

  public void setSearchWord(@NonNull String searchWord) {
    this.searchWord = searchWord;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getDelivery() {
    return delivery;
  }

  public void setDelivery(String delivery) {
    this.delivery = delivery;
  }

  public Long getUserId() {
    return userId;
  } // FIXME Replace with primitive

  public void setUserId(Long userId) {
    this.userId = userId;
  } //FIXME replace with primitive
}
