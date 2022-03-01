package com.example.sb.repository;

import com.example.sb.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Number> {
}
