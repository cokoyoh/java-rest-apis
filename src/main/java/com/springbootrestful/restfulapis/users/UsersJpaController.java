package com.springbootrestful.restfulapis.users;

import com.springbootrestful.restfulapis.entities.Post;
import com.springbootrestful.restfulapis.entities.User;
import com.springbootrestful.restfulapis.posts.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UsersJpaController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

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

    @GetMapping(path = "/jpa/users/{id}/posts")
    public List<Post> posts(@PathVariable long id) {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent())
            throw new UserNotFoundException("id - " + id);

        return user.get().getPosts();
    }

    @PostMapping(path = "/jpa/users/{id}/posts")
    public ResponseEntity<Object> storePost(@PathVariable long id, @RequestBody Post post) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent())
            throw new UserNotFoundException("id - " + id);

        User user = optionalUser.get();

        post.setUser(user);

        postRepository.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();

        return ResponseEntity.created(location).build();
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
