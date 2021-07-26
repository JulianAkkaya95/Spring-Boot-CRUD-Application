package com.example.springbootcrudapplication.Controller;

import com.example.springbootcrudapplication.Entity.User;
import com.example.springbootcrudapplication.Service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    @Test
    void indexUsers() {
        List<User> userList = List.of(mock(User.class), mock(User.class));

        when(userService.index()).thenReturn(userList);

        ResponseEntity<Iterable<User>> response = userController.indexUsers(null);
        ResponseEntity<Iterable<User>> expectedResponse = ResponseEntity.ok().body(userList);

        assertEquals(expectedResponse, response);
    }

    @Test
    void indexUsersFilterName() {
        List<User> userList = List.of(mock(User.class), mock(User.class));
        when(userService.indexFilterFirstName(anyString())).thenReturn(userList);

        ResponseEntity<Iterable<User>> response = userController.indexUsers(anyString());
        ResponseEntity<Iterable<User>> expectedResponse = ResponseEntity.ok().body(userList);

        assertEquals(expectedResponse, response);
    }

    @Test
    void showUser() {
        User user = mock(User.class);
        when(userService.show(anyLong())).thenReturn(user);

        ResponseEntity<User> response = userController.showUser(anyLong());
        ResponseEntity<User> expectedResponse = ResponseEntity.ok().body(user);

        assertEquals(expectedResponse, response);
    }

    @Test
    void showUserThrowsException() {
        when(userService.show(anyLong())).thenThrow(new OpenApiResourceNotFoundException("User not found with id 1"));

        assertThrows(OpenApiResourceNotFoundException.class, () -> userController.showUser(anyLong()));
    }

    @Test
    void createUser() {
        User user = mock(User.class);
        when(userService.create(user)).thenReturn(user);

        ResponseEntity<User> response = userController.createUser(user);
        ResponseEntity<User> expectedResponse = ResponseEntity.ok().body(user);

        assertEquals(expectedResponse, response);
    }

    @Test
    void updateUser() {
        User user = mock(User.class);
        when(userService.update(any(), anyLong())).thenReturn(user);

        ResponseEntity<User> response = userController.updateUser(anyLong(), any());
        ResponseEntity<User> expectedResponse = ResponseEntity.ok().body(user);

        assertEquals(expectedResponse, response);
    }

    @Test
    void deleteUser() {
        ResponseEntity<Object> response = userController.deleteUser(anyLong());

        assertNotNull(response);
    }

}