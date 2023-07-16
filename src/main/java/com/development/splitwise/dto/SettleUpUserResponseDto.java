package com.development.splitwise.dto;

import com.development.splitwise.settleUpStrategy.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SettleUpUserResponseDto {

    private String httpResponseMessage;
    private int httpResponseStatus;
    List<Transaction> transactionList;
}
