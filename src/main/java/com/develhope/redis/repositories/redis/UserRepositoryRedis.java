package com.develhope.redis.repositories.redis;

import com.develhope.redis.entities.jpa.UserJPA;
import com.develhope.redis.entities.redis.UserRedis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryRedis extends CrudRepository<UserRedis,Long> {

}
