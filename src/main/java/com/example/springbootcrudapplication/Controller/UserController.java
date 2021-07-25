package com.example.springbootcrudapplication.Controller;

import com.example.springbootcrudapplication.Entity.User;
import com.example.springbootcrudapplication.Service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@Tag(name = "User", description = "Basic crud functionality for users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<Iterable<User>> indexUsers() {
        return ResponseEntity.ok().body(userService.index());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> showUser(@PathVariable(value = "id") long id) throws Exception {
        User user = userService.show(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.create(user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") long id, @RequestBody User user) throws Exception {
        return ResponseEntity.ok().body(userService.update(user, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
