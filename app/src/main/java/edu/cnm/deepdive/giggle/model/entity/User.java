package edu.cnm.deepdive.giggle.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;

@Entity(
    tableName = "user")

public class User {


  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "user_id")
  private int id;


  @Expose
  @ColumnInfo(index = true)
  private int created;

  @Expose
  @ColumnInfo(index = true)
  private String name;


  @Expose
  @ColumnInfo(index = true)
 private String oauth;

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOauth() {
    return oauth;
  }

  public void setOauth(String oauth) {
    this.oauth = oauth;
  }
}
