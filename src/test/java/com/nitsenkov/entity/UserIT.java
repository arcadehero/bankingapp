package com.nitsenkov.entity;

import com.nitsenkov.BaseIntegrationTest;
import com.nitsenkov.util.TestObjectsBuilder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class UserIT extends BaseIntegrationTest {

    @Test
    void create() {
        User user = TestObjectsBuilder.getUser();

        session.persist(user);
        session.flush();
        session.clear();

        assertThat(session.get(User.class, user.getId())).isNotNull();
    }

    @Test
    void read() {
        User user1 = TestObjectsBuilder.getUser();
        User user2 = TestObjectsBuilder.getUser();

        session.persist(user1);
        session.persist(user2);
        session.flush();
        session.clear();
        User actualUser2 = session.get(User.class, user2.getId());

        assertThat(actualUser2.getId()).isEqualTo(user2.getId());
    }


    @Test
    void update() {
        User user = TestObjectsBuilder.getUser();

        session.persist(user);
        session.flush();
        session.clear();
        user.setFullName("anotherName");
        session.merge(user);
        session.flush();
        session.clear();
        User actualUser = session.get(User.class, user.getId());

        assertThat(actualUser.getFullName()).isEqualTo(user.getFullName());
    }


    @Test
    void remove() {
        User user = TestObjectsBuilder.getUser();

        session.persist(user);
        session.flush();
        session.clear();
        session.remove(user);
        session.flush();
        User actualUser = session.get(User.class, user.getId());

        assertThat(actualUser).isNull();
    }
}
