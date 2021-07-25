package com.example.springbootcrudapplication.Controller;

import com.example.springbootcrudapplication.Entity.User;
import com.example.springbootcrudapplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity<Iterable<User>> indexUsers() {
        return ResponseEntity.ok().body(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> showUser(@PathVariable(value = "id") Long id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new Exception("User not found with id" + id));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping
    public ResponseEntity<Object> delete() {
        return ResponseEntity.noContent().build();
    }
}
