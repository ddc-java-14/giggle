package edu.cnm.deepdive.giggle.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;

@Entity(
    indices = {
      @Index(value = "email", unique = true)
    },
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
 private String email;
}
