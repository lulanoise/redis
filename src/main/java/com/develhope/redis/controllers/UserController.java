package com.develhope.redis.controllers;

import com.develhope.redis.entities.User;
import com.develhope.redis.entities.jpa.UserJPA;
import com.develhope.redis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

@Autowired
private UserService userService;


    @PostMapping
    public User create(@RequestBody UserJPA user){
        return userService.create(user);
    }

    @GetMapping
    public List<? extends User> readAll(){
        return userService.readAll();
    }

    @GetMapping("/{id}")
    public void readOne(@PathVariable Long id){
        userService.readOne(id);
    }
    @GetMapping("/{id}/fast")
    public void readOneFast(@PathVariable Long id){
        userService.readOneFast(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody UserJPA user){
        userService.update(id,user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }


}
