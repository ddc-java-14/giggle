package edu.cnm.deepdive.giggle.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
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
    },
    indices = {
        @Index(value = "service_key", unique = true)
    }
)
public class Joke {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "joke_id")
  private long id;

  @Expose
  @SerializedName("id")
  @ColumnInfo(name = "service_key")
  private long serviceKey;


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

  @Ignore
  @Expose
  private boolean error;

  @Ignore
  private boolean favorite;

  @ColumnInfo(name = "user_id", index = true)
  private Long userId; // FIXME Replace with primitive


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getServiceKey() {
    return serviceKey;
  }

  public void setServiceKey(long serviceKey) {
    this.serviceKey = serviceKey;
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

  public boolean isError() {
    return error;
  }

  public void setError(boolean error) {
    this.error = error;
  }

  public boolean isFavorite() {
    return favorite;
  }

  public void setFavorite(boolean favorite) {
    this.favorite = favorite;
  }

  public Long getUserId() {
    return userId;
  } // FIXME Replace with primitive

  public void setUserId(Long userId) {
    this.userId = userId;
  } //FIXME replace with primitive
}
