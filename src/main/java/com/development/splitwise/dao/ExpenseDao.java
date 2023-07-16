package com.development.splitwise.dao;

import com.development.splitwise.model.Expense;
import com.development.splitwise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseDao extends JpaRepository<Expense,Long> {

    List<Expense> findAll();
}
