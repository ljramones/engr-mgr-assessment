package com.spothero.engmgr.db;

import com.spothero.engmgr.db.model.Users;
import com.spothero.engmgr.db.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
@DirtiesContext
public class UsersRepositoryTest extends DatabaseTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void usersGetAllUsers() {

        var user1 = new Users();
        user1.setId(1);
        user1.setManagerId(1);
        user1.setFirstName("larry");
        user1.setLastName("mitchell");
        user1.setEmail("larry.mitchell@email.com");
        user1.setActive(true);

        var user2 = new Users();
        user2.setId(2);
        user1.setManagerId(2);
        user2.setFirstName("jane");
        user2.setLastName("mitchell");
        user1.setEmail("jane.mitchell@email.com");
        user2.setActive(true);

        var user3 = new Users();
        user3.setId(3);
        user1.setManagerId(3);
        user3.setFirstName("tom");
        user3.setLastName("clancy");
        user1.setEmail("tom.clancy@email.com");
        user3.setActive(true);

        var val1 = userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        Map<Integer, Users> users = change(userRepository.findAll());
        assertEquals(3, users.size());

        var user1Save = users.get(1);
        assertEquals(user1.getId(), user1Save.getId());
        assertEquals(user1.getFirstName(), user1Save.getFirstName());
        assertEquals(user1.getLastName(), user1Save.getLastName());
        assertEquals(user1.getEmail(), user1Save.getEmail());
        assertEquals(user1.getManagerId(), user1Save.getManagerId());
        assertTrue(user1Save.isActive());

        var user2Save = users.get(2);
        assertEquals(user2.getId(), user2Save.getId());
        assertEquals(user2.getFirstName(), user2Save.getFirstName());
        assertEquals(user2.getLastName(), user2Save.getLastName());
        assertEquals(user2.getEmail(), user2Save.getEmail());
        assertEquals(user2.getManagerId(), user2Save.getManagerId());
        assertTrue(user2Save.isActive());

        var user3Save = users.get(3);
        assertEquals(user3.getId(), user3Save.getId());
        assertEquals(user3.getFirstName(), user3Save.getFirstName());
        assertEquals(user3.getLastName(), user3Save.getLastName());
        assertEquals(user3.getEmail(), user3Save.getEmail());
        assertEquals(user3.getManagerId(), user3Save.getManagerId());
        assertTrue(user3Save.isActive());
    }

    Map<Integer, Users> change(Iterable<Users> users) {
        Map<Integer, Users> userMap = new HashMap<>();
        users.forEach(user -> userMap.put(user.getId(), user));
        return userMap;
    }

}
