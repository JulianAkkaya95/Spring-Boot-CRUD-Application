package com.example.springbootcrudapplication.Service;

import com.example.springbootcrudapplication.Entity.User;
import com.example.springbootcrudapplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(User user) {
        return user;
    }

    public Iterable<User> index() {
        return userRepository.findAll();
    }

    public Optional<User> show(long id) {
        return userRepository.findById(id);
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
