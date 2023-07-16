package com.development.splitwise.dao;

import com.development.splitwise.model.Expense;
import com.development.splitwise.model.User;
import com.development.splitwise.model.UserExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserExpenseDao extends JpaRepository<UserExpense, Long> {

    List<UserExpense> findAllByUser(User user);
    List<UserExpense> findAllByExpenseIn(List<Expense> expense);
}
