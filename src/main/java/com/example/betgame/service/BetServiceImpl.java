package com.example.betgame.service;

import com.example.betgame.dto.RequestDto;
import com.example.betgame.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Slf4j
@Service
public class BetServiceImpl implements BetService{
    @Override
    public ResponseDto placeBet(RequestDto request){

        int random = (int) (Math.random() * (100 - 1)) + 1;

        if(request.getNumber() > random){
            log.info("Bet Won");
            return new ResponseDto(true,calculateWin(request.getNumber(),request.getBet()));
        }
        log.info("Bet lost");
        return new ResponseDto(false,new BigDecimal("0.0"));
    }

    // Calculate the winning amount as given logic
    private BigDecimal calculateWin(int number, double bet){
        return BigDecimal.valueOf(bet * (99.0 / (100 - number))).round(new MathContext(4, RoundingMode.HALF_UP));
    }
}
