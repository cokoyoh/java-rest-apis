package com.springbootrestful.restfulapis.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsersJpaController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/jpa/users")
    public List index() {
        return userRepository.findAll();
    }


    @GetMapping(path = "/jpa/users/{id}")
    public Optional<User> show(@PathVariable long id) {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent())
            throw new UserNotFoundException("id - " + id);

        return user;
    }

    /*

    @PostMapping(path = "/jpa/users")
    public ResponseEntity<Object> store(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/jpa/users/{id}")
    public void delete(@PathVariable int id) {
        User user = userRepository.deleteById(id);

        if (user == null)
            throw new UserNotFoundException("id - " + id);
    }
    */
}
