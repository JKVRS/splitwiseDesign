package com.development.splitwise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Expense extends BaseModel{

     private String  description;
     private double amount;
     @ManyToOne
     private User  expenseCreatedByUser;
     private Date expenseCreationDate;
     @Enumerated(EnumType.ORDINAL)
     private ExpenseType expensetype ;   //(expense, transaction)
     @ManyToOne
     private Group  group;
}
