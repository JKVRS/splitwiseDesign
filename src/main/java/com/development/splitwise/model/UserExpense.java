package com.development.splitwise.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserExpense extends BaseModel{

     @ManyToOne
     private  User expenseUser;
     @ManyToOne
     private Expense expense;
     private double amountPaid;
     @Enumerated(EnumType.ORDINAL)
     private UserExpenseType userExpenseType;  //(pay , Had to pay)
}
