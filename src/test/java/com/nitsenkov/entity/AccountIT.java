package com.nitsenkov.entity;

import com.nitsenkov.BaseIntegrationTest;
import com.nitsenkov.util.TestObjectsBuilder;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountIT extends BaseIntegrationTest {

    @Test
    void create() {
        User user = TestObjectsBuilder.getUser();
        Account account = TestObjectsBuilder.getAccount(user);

        session.persist(account);
        session.flush();
        session.clear();

        assertThat(session.get(Account.class, account.getId())).isNotNull();
    }


    @Test
    void read() {
        User user1 = TestObjectsBuilder.getUser();
        Account account1 = TestObjectsBuilder.getAccount(user1);
        Account account2 = TestObjectsBuilder.getAccount(user1);

        session.persist(account1);
        session.persist(account2);
        session.flush();
        session.clear();
        Account actualAccount2 = session.get(Account.class, account2.getId());

        assertThat(actualAccount2.getId()).isEqualTo(account2.getId());
    }


    @Test
    void update() {
        User user = TestObjectsBuilder.getUser();
        Account account = TestObjectsBuilder.getAccount(user);

        session.persist(account);
        session.flush();
        session.clear();
        account.setBalance(BigDecimal.valueOf(2000,2));
        session.merge(account);
        session.flush();
        session.clear();
        Account actualAccount = session.get(Account.class, account.getId());

        assertThat(actualAccount.getBalance()).isEqualTo(account.getBalance());
    }


    @Test
    void remove() {
        User user = TestObjectsBuilder.getUser();
        Account account = TestObjectsBuilder.getAccount(user);

        session.persist(account);
        session.flush();
        session.clear();
        session.remove(account);
        session.flush();
        Account actualAccount = session.get(Account.class, account.getId());

        assertThat(actualAccount).isNull();
    }
}
