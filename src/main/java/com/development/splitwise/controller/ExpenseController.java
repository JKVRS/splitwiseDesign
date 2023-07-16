package com.development.splitwise.controller;

import com.development.splitwise.dto.SettleUpUserRequestDto;
import com.development.splitwise.dto.SettleUpUserResponseDto;
import com.development.splitwise.service.ExpenseService;
import com.development.splitwise.settleUpStrategy.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ExpenseController {

     private final ExpenseService expenseService;

     @Autowired
     public ExpenseController(ExpenseService expenseService){
        this.expenseService=expenseService;
     }

     public SettleUpUserResponseDto SettleUpUser(SettleUpUserRequestDto requestDto){

        List<Transaction> transactionList=expenseService.settleUpUser(requestDto.getUserId());

        return null;
     }
}
