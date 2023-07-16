package com.development.splitwise.settleUpStrategy;

import com.development.splitwise.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {

    private User from;
    private  User to;
    private  double amount;

}
