package com.example.springbootcrudapplication.Service;

import com.example.springbootcrudapplication.Entity.User;
import com.example.springbootcrudapplication.Repository.UserRepository;
import com.example.springbootcrudapplication.helper.NullableConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void create() {
        User user = mock(User.class);
        when(userRepository.save(any())).thenReturn(user);

        User response = userService.create(any());
        assertEquals(user, response);
    }

    @ParameterizedTest(name = "UpdatedFirstName: {0}; UpdatedName: {1}; UpdatedEmail: {2}")
    @CsvSource({"updated,null,null", "null,updated,null", "null,updated,null", "updated1,updated2,updated3"})
    void updateFirstName(
            @ConvertWith(NullableConverter.class) String updatedFirstName,
            @ConvertWith(NullableConverter.class) String updatedName,
            @ConvertWith(NullableConverter.class) String updatedEmail) {

        User user = new User();
        user.setFirstName("foo");
        user.setEmail("bar");
        user.setName("foobar");

        User userUpdates = new User();
        userUpdates.setFirstName(updatedFirstName);
        userUpdates.setName(updatedName);
        userUpdates.setEmail(updatedEmail);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        User response = userService.update(userUpdates, 1L);

        if (null != updatedFirstName) {
            assertEquals(userUpdates.getFirstName(), response.getFirstName());
        }
        if (null != updatedEmail) {
            assertEquals(userUpdates.getEmail(), response.getEmail());
        }
        if (null != updatedName) {
            assertEquals(userUpdates.getName(), response.getName());
        }
    }

    @Test
    void updateUserNotFound() {
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(OpenApiResourceNotFoundException.class, () -> userService.update(mock(User.class), 1L));
    }

    @Test
    void index() {
        List<User> userList = List.of(mock(User.class), mock(User.class));
        when(userRepository.findAll()).thenReturn(userList);

        Iterable<User> response = userService.index();

        assertEquals(userList, response);
    }

    @Test
    void show() {
        User user = mock(User.class);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        User response = userService.show(anyLong());

        assertEquals(user, response);
    }

    @Test
    void showUserNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(OpenApiResourceNotFoundException.class, () -> userService.show(anyLong()));
    }

    @Test
    void delete() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(mock(User.class)));

        userService.delete(anyLong());

        verify(userRepository).deleteById(anyLong());
    }

    @Test
    void deleteUserNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(OpenApiResourceNotFoundException.class, () -> userService.delete(anyLong()));
    }

    @Test
    void indexFilterFirstName() {
        List<User> userList = List.of(mock(User.class), mock(User.class));
        when(userRepository.findAllByFirstName(anyString())).thenReturn(userList);

        Iterable<User> response = userService.indexFilterFirstName(anyString());

        assertEquals(userList, response);
    }
}