package com.example.springbootcrudapplication.Service;

import com.example.springbootcrudapplication.Entity.User;
import com.example.springbootcrudapplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(User updatedUser, long id) throws Exception {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found with id " + id));

        if (null != updatedUser.getName()) {
            user.setName(updatedUser.getName());
        }

        if (null != updatedUser.getEmail()) {
            user.setEmail(updatedUser.getEmail());
        }

        if (null != updatedUser.getFirstName()) {
            user.setFirstName(updatedUser.getFirstName());
        }

        userRepository.save(user);
        return user;
    }

    public Iterable<User> index() {
        return userRepository.findAll();
    }

    public User show(long id) throws Exception {
        return userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found with id " + id));
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }

    public Iterable<User> indexFilterFirstName(String firstName) {
        return userRepository.findAllByFirstName(firstName);
    }
}
