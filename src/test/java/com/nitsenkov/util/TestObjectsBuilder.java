package com.nitsenkov.util;

import com.nitsenkov.entity.Account;
import com.nitsenkov.entity.CurrencyRate;
import com.nitsenkov.entity.Transaction;
import com.nitsenkov.entity.User;
import com.nitsenkov.entity.enums.TransactionStatus;
import com.nitsenkov.entity.enums.TransactionType;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@UtilityClass
public class TestObjectsBuilder {


    public static User getUser() {
        return User.builder()
                .email("a.nitcenkov@gmail.com")
                .fullName("Alex Nitsenkov")
                .passwordHash("hash")
                .createAt(LocalDateTime.now())
                .build();
    }


    public static Account getAccount(User user) {
        return Account.builder()
                .balance(BigDecimal.valueOf(100))
                .createdAt(LocalDateTime.now())
                .currency("RUB")
                .user(user)
                .build();
    }

    public static Transaction getTransaction(Account account1, Account account2) {
        return Transaction.builder()
                .amount(BigDecimal.valueOf(10))
                .status(TransactionStatus.PENDING)
                .fromAccountId(account1.getId())
                .toAccountId(account2.getId())
                .type(TransactionType.TRANSFER)
                .description("test")
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static CurrencyRate getCurrencyRate() {
        return CurrencyRate.builder()
                .rate(BigDecimal.valueOf(100,2))
                .baseCurrency("USD")
                .targetCurrency("RUB")
//                .fetchedAt(LocalDateTime.now())
                .build();
    }
}
