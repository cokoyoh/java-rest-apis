package com.springbootrestful.restfulapis.entities;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Long id;

    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private Post() {}

    public Post(String body, User user) {
        this.body = body;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", body='" + body + '\'' +
                '}';
    }
}
