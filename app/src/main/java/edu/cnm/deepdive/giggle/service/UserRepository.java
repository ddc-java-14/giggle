package edu.cnm.deepdive.giggle.service;

import edu.cnm.deepdive.giggle.model.entity.User;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

  Optional<User> findByPrimaryKey(String oauthKey);

  Optional<User> findByPrimaryKey(UUID primaryKey);

  Iterable<User> getAllByOrderByDisplayNameAsc();
}
