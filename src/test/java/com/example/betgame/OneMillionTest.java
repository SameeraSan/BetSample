package com.example.betgame;


import com.example.betgame.dto.RequestDto;
import com.example.betgame.dto.ResponseDto;
import com.example.betgame.service.BetService;
import com.example.betgame.service.BetServiceImpl;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OneMillionTest {

    private final BetService betService = new BetServiceImpl();

    final int rounds = 100; // set this value to 1 million
    final int noOfThreads = 24;
    final int threadTimeout = 1000;

    private BigDecimal income = new BigDecimal(0);
    private double spent = 0;


    @Test(threadPoolSize = noOfThreads, invocationCount = rounds, timeOut = threadTimeout)
    public void testMethod()
    {
        RequestDto request = new RequestDto();
        request.setNumber(50);
        request.setBet(40.5);

        ResponseDto response = betService.placeBet(request);

        getIncomeTotal(response.getWinningAmount());
        getSpentTotal(request.getBet());
    }

    private synchronized void getSpentTotal(double bet){
        spent += bet;
    }

    private synchronized void getIncomeTotal(BigDecimal bd){
        income = income.add(bd);
    }

    @AfterTest
    void calRTP() {

        System.out.println("Total Spent : " + spent);
        System.out.println("Total Income : " + income);
        System.out.println("RTP : " + income.divide(new BigDecimal(spent),2, RoundingMode.HALF_UP) + "%");
    }
}
