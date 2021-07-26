package com.example.springbootcrudapplication.Entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserTest {

    User user;

    @BeforeEach
    void init() {
        user = new User();
    }

    @Test
    void attributeId() {
        Long id = 1L;
        user.setId(id);

        assertEquals(id, user.getId());
    }

    @Test
    void attributeFirstName() {
        String firstName = "Foobar";
        user.setFirstName(firstName);

        assertEquals(firstName, user.getFirstName());
    }

    @Test
    void attributeName() {
        String name = "Foobar";
        user.setName(name);

        assertEquals(name, user.getName());
    }

    @Test
    void attributeEmail() {
        String email = "Foobar";
        user.setEmail(email);

        assertEquals(email, user.getEmail());
    }
}