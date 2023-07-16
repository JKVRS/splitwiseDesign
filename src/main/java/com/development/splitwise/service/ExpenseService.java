package com.development.splitwise.service;

import com.development.splitwise.Exception.InvalidArgumentException;
import com.development.splitwise.settleUpStrategy.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpenseService {

     List<Transaction> settleUpUser(long userId) throws InvalidArgumentException;
}
