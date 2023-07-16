package com.development.splitwise.settleUpStrategy;

import com.development.splitwise.model.Expense;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SettleUpStrategy {

    List<Transaction> settleUp(List<Expense> expenseList);
}
