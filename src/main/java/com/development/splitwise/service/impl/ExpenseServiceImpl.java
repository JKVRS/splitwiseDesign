package com.development.splitwise.service.impl;

import com.development.splitwise.Exception.InvalidArgumentException;
import com.development.splitwise.dao.ExpenseDao;
import com.development.splitwise.dao.UserDao;
import com.development.splitwise.dao.UserExpenseDao;
import com.development.splitwise.model.Expense;
import com.development.splitwise.model.User;
import com.development.splitwise.model.UserExpense;
import com.development.splitwise.service.ExpenseService;
import com.development.splitwise.settleUpStrategy.SettleUpStrategy;
import com.development.splitwise.settleUpStrategy.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    private final UserDao userDao;
    private final ExpenseDao expenseDao;
    private final UserExpenseDao userExpenseDao;
    private final SettleUpStrategy settleUpStrategy;
   /*
     The @Qualifier annotation in Spring is used to disambiguate dependencies when multiple beans of the same type are present.
      It allows you to specify which bean should be injected into a particular dependency.
   */
    @Autowired
    public ExpenseServiceImpl(UserDao userDao,
                              ExpenseDao expenseDao,
                              UserExpenseDao userExpenseDao,
                              @Qualifier("twoSetsSettleUpStrategy") SettleUpStrategy settleUpStrategy){
        this.userDao=userDao;
        this.expenseDao=expenseDao;
        this.userExpenseDao=userExpenseDao;
        this.settleUpStrategy=settleUpStrategy;
    }
    @Override
    public List<Transaction> settleUpUser(long userId) throws InvalidArgumentException {
        // fetch the particular user with this userId from userDao.java
        // fetch all the expenses for this particular user from ExpenseDao.java
        Optional<User> optionalUser=userDao.findById(userId);
        if(optionalUser.isEmpty())
        {
            throw new InvalidArgumentException("User doesn't exist");
        }
        List<UserExpense> userExpenseList=userExpenseDao.findAllByUser(optionalUser.get());

        // store all the expenses in which user is involving
        List<Expense> userExpenseInvloving=new ArrayList<>();
        for(UserExpense uexp:userExpenseList){
            userExpenseInvloving.add(uexp.getExpense());
        }

        List<Transaction> transactionList= settleUpStrategy.settleUp(userExpenseInvloving);



          return null;
    }
}
