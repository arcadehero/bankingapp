package com.nitsenkov.entity;

import com.nitsenkov.BaseIntegrationTest;
import com.nitsenkov.util.TestObjectsBuilder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionIT extends BaseIntegrationTest {

    @Test
    void create() {
        User user1 = TestObjectsBuilder.getUser();
        User user2 = TestObjectsBuilder.getUser();
        Account account1 = TestObjectsBuilder.getAccount(user1);
        Account account2 = TestObjectsBuilder.getAccount(user2);
        Transaction transaction = TestObjectsBuilder.getTransaction(account1, account2);


        session.persist(transaction);
        session.flush();
        session.clear();

        assertThat(session.get(Transaction.class, transaction.getId())).isNotNull();
    }

    @Test
    void get() {
        User user1 = TestObjectsBuilder.getUser();
        User user2 = TestObjectsBuilder.getUser();
        Account account1 = TestObjectsBuilder.getAccount(user1);
        Account account2 = TestObjectsBuilder.getAccount(user2);
        Transaction transaction = TestObjectsBuilder.getTransaction(account1, account2);

        session.persist(transaction);
        session.flush();
        session.clear();
        Transaction actualTransaction = session.get(Transaction.class, transaction.getId());

        assertThat(actualTransaction.getAmount().compareTo(transaction.getAmount())).isZero();
    }

    @Test
    void update(){
        User user1 = TestObjectsBuilder.getUser();
        User user2 = TestObjectsBuilder.getUser();
        Account account1 = TestObjectsBuilder.getAccount(user1);
        Account account2 = TestObjectsBuilder.getAccount(user2);
        Transaction transaction = TestObjectsBuilder.getTransaction(account1, account2);

        session.persist(transaction);
        session.flush();
        session.clear();
        transaction.setDescription("new desc");
        session.merge(transaction);
        session.flush();
        session.clear();
        Transaction actualTransaction = session.get(Transaction.class, transaction.getId());

        assertThat(actualTransaction.getDescription()).isEqualTo(transaction.getDescription());
    }

    @Test
    void delete() {
        User user1 = TestObjectsBuilder.getUser();
        User user2 = TestObjectsBuilder.getUser();
        Account account1 = TestObjectsBuilder.getAccount(user1);
        Account account2 = TestObjectsBuilder.getAccount(user2);
        Transaction transaction = TestObjectsBuilder.getTransaction(account1, account2);

        session.persist(transaction);
        session.flush();
        session.clear();
        session.remove(transaction);
        session.flush();
        Transaction actualTransaction = session.get(Transaction.class, transaction.getId());

        assertThat(actualTransaction).isNull();
    }
}
