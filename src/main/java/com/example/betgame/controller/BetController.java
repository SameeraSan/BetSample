package com.example.betgame.controller;

import com.example.betgame.dto.RequestDto;
import com.example.betgame.dto.ResponseDto;
import com.example.betgame.service.BetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@Validated
@RestController
@RequestMapping("/v1")
public class BetController {

    BetService betService;

    @Autowired
    public BetController(BetService betService){
        this.betService = betService;
    }

    @GetMapping(path= "/bet", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> placeBet(@Valid @NotNull @RequestBody RequestDto request) {
        log.info("Bet Service");
        ResponseDto response = betService.placeBet(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
