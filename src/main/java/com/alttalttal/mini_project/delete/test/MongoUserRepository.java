package com.alttalttal.mini_project.delete.test;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface MongoUserRepository extends MongoRepository<MongoUser, BigInteger> {
    Optional<MongoUser> findById(BigInteger id);
}
