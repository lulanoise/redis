package com.develhope.redis.services;
import com.develhope.redis.entities.User;
import com.develhope.redis.entities.jpa.UserJPA;
import com.develhope.redis.entities.redis.UserRedis;
import com.develhope.redis.repositories.jpa.UserRepositoryJPA;
import com.develhope.redis.repositories.redis.UserRepositoryRedis;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepositoryJPA userRepositoryJPA;

    @Autowired
    private UserRepositoryRedis userRepositoryRedis;

    public UserRedis convertData(UserJPA user){
        UserRedis userRedis = new UserRedis();
        BeanUtils.copyProperties(user, userRedis);
        return userRedis;
    }

    public UserJPA convertData(UserRedis user){
        UserJPA userJPA = new UserJPA();
        BeanUtils.copyProperties(user, userJPA);
        return userJPA;
    }

    public UserJPA create(UserJPA user) {
        if (user == null) return null;
        user.setId(null);
        return userRepositoryJPA.save(user);
    }

    public List<? extends User> readAll() {
       return userRepositoryJPA.findAll();
    }

    public User readOne(Long id) {
       Optional<UserRedis> userRedis = userRepositoryRedis.findById(id);
       if (userRedis.isPresent()) {
           return convertData(userRedis.get());
       } else {
           UserJPA userFromDB = userRepositoryJPA.getById(id);
           if (userFromDB == null) return null;
           userRepositoryRedis.save(convertData(userFromDB));
           return userFromDB;
       }
    }

    public UserJPA update(Long id, UserJPA user) {
        if (user == null)return null;
        user.setId(id);
        UserJPA newUser = userRepositoryJPA.save(user);

        Optional<UserRedis> userRedis = userRepositoryRedis.findById(id);
        if (userRedis.isPresent()){
            //userRepositoryRedis.deleteById(id);
            userRepositoryRedis.save(convertData(newUser));
        }
        return user;
    }

    public void delete(Long id) {
        userRepositoryJPA.deleteById(id);
        userRepositoryRedis.deleteById(id);

    }

    public void readOneFast(Long id) {
    }

}
