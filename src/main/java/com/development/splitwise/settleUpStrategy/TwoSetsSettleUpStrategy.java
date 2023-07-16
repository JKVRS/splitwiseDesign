package com.development.splitwise.settleUpStrategy;

import ch.qos.logback.core.joran.sanity.Pair;
import com.development.splitwise.dao.ExpenseDao;
import com.development.splitwise.dao.UserExpenseDao;
import com.development.splitwise.model.Expense;
import com.development.splitwise.model.User;
import com.development.splitwise.model.UserExpense;
import com.development.splitwise.model.UserExpenseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
class PairUserAmount{
    User user;
    Double amount;
    public PairUserAmount(User user,Double amount){
        this.user=user;
        this.amount=amount;
    }
}
@Component("twoSetsSettleUpStrategy")
public class TwoSetsSettleUpStrategy implements SettleUpStrategy{

    private final ExpenseDao expenseDao;
    private final UserExpenseDao userExpenseDao;


    @Autowired
    public TwoSetsSettleUpStrategy(ExpenseDao expenseDao,
                                   UserExpenseDao userExpenseDao){
        this.expenseDao=expenseDao;
        this.userExpenseDao=userExpenseDao;
    }
    @Override
    public List<Transaction> settleUp(List<Expense> expenseList) {

        // now we have expense list of an user
        // first we will fetch all the Userexpense from database for each Expenses (UserexpenseDao.java)
        // select * from userExpense where expense in(val1,val2,val3...)
        List<UserExpense> allUserExpenses=userExpenseDao.findAllByExpenseIn(expenseList);
        // store all the positive and negative amount of all users
        HashMap<User,Double> moneyPaid=new HashMap<>();

        for(UserExpense allUserExp:allUserExpenses){
            User user=allUserExp.getExpenseUser();
            double paidMoney=0.0;
            if(moneyPaid.containsKey(user)){
              paidMoney=moneyPaid.get(user);

            }
            if(allUserExp.getUserExpenseType().equals(UserExpenseType.PAID)){
                moneyPaid.put(user,paidMoney+allUserExp.getAmountPaid());
            }else{
                moneyPaid.put(user,paidMoney-allUserExp.getAmountPaid());
            }

        }

        TreeSet<PairUserAmount> extraPay=new TreeSet<>();
        TreeSet<PairUserAmount> lessPay=new TreeSet<>();
        List<Transaction> transactionList=new ArrayList<>();

        for(Map.Entry<User,Double> entry:moneyPaid.entrySet()){
                if(entry.getValue()<0){
                    lessPay.add(new PairUserAmount(entry.getKey(),entry.getValue()));
                }else{
                    extraPay.add(new PairUserAmount(entry.getKey(),entry.getValue()));
                }
        }

        while(!lessPay.isEmpty()){
            PairUserAmount puaFrom=lessPay.pollFirst();
            PairUserAmount puaTo=extraPay.pollFirst();
            // create object for each transaction
            Transaction t=new Transaction();
            t.setTo(puaTo.user);
            t.setFrom(puaFrom.user);

            if(Math.abs(puaFrom.amount)<puaTo.amount) {
                t.setAmount(Math.abs(puaFrom.amount));
                if ((puaTo.amount - Math.abs(puaFrom.amount))!=0 ) {
                   extraPay.add(new PairUserAmount(puaTo.user,puaTo.amount-Math.abs(puaFrom.amount)));
                }
            }else{
                t.setAmount(puaTo.amount);
                if((Math.abs(puaFrom.amount)+puaTo.amount)!=0){
                    lessPay.add(new PairUserAmount(puaFrom.user,Math.abs(puaFrom.amount)+puaTo.amount));
                }
            }

           transactionList.add(t);

        }

        return transactionList;
    }
}
