package com.springbootrestful.restfulapis.users;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static long usersCount = 3;

    static {
        users.add(new User(1L, "John Doe", new Date()));
        users.add(new User(2L, "Jane Doe", new Date()));
        users.add(new User(3L, "Roe Smith", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }

        users.add(user);

        return user;
    }

    public User findOne(int userId) {
        for(User user : users) {
            if (user.getId() == userId) {
                return user;
            }
        }

        return null;
    }

    public User deleteById(int userId) {
        Iterator<User> iterator = users.iterator();

        while(iterator.hasNext()) {
            User user = iterator.next();

            if (user.getId() == userId) {
                iterator.remove();

                return  user;
            }
        }

        return null;
    }
}
