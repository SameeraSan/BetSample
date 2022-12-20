package com.example.betgame.service;

import com.example.betgame.dto.RequestDto;
import com.example.betgame.dto.ResponseDto;
import org.springframework.stereotype.Service;


@Service
public interface BetService {

    ResponseDto placeBet(RequestDto requestDto);
}
