package com.example.springbootcrudapplication.Repository;

import com.example.springbootcrudapplication.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Iterable<User> findAllByFirstName(String name);
}
