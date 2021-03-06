package com.springbootrestful.restfulapis.users;

import com.springbootrestful.restfulapis.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UserDaoService service;

    @GetMapping(path = "/users")
    public List<User> index() {
        return service.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public User show(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null)
            throw new UserNotFoundException("id - " + id);

        return user;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<Object> store(@Valid @RequestBody User user) {
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/users/{id}")
    public void delete(@PathVariable int id) {
        User user = service.deleteById(id);

        if (user == null)
            throw new UserNotFoundException("id - " + id);
    }
}
