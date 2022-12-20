package com.example.betgame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {

    @Min(value=1, message="Number must be greater than 0")
    @Max(value=99, message="Number must be less than 100")
    int number;

    @Min(value=1, message="Bet must be greater than 0")
    double bet;
}
