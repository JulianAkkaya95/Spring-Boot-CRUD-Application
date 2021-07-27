package com.example.springbootcrudapplication.Controller;

import com.example.springbootcrudapplication.Entity.User;
import com.example.springbootcrudapplication.Service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@Tag(name = "User", description = "Basic crud functionality for users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<Iterable<User>> indexUsers(@RequestParam(value = "firstName", required = false) String firstName) {
        return ResponseEntity.ok().body( null == firstName ?  userService.index() : userService.indexFilterFirstName(firstName));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> showUser(@PathVariable(value = "id") long id) {
        User user = userService.show(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Validated @RequestBody User user) {
        return ResponseEntity.ok().body(userService.create(user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") long id, @RequestBody User user) {
        return ResponseEntity.ok().body(userService.update(user, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
