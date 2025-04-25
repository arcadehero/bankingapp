package com.nitsenkov.entity;

import com.nitsenkov.entity.enums.TransactionStatus;
import com.nitsenkov.entity.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fromAccountId;    // nullable

    private Long toAccountId;      // nullable

    private BigDecimal amount;

    private BigDecimal convertedAmount; // nullable

    private BigDecimal currencyRate;    // nullable

    private TransactionType type;                // "transfer", "deposit", "withdrawal"

    private String description;

    private TransactionStatus status;              // "completed", "pending", etc.

    private LocalDateTime createdAt;

}
