package com.example.betgame;

import com.example.betgame.dto.RequestDto;
import com.example.betgame.dto.ResponseDto;
import com.example.betgame.service.BetService;
import com.example.betgame.service.BetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ApplicationUnitTests {

	private BetService betService;

	@BeforeEach
	void setUp() {
		betService = new BetServiceImpl();
	}

	@Test
	void testPlaceBet_success() {

		RequestDto request = new RequestDto();
		request.setNumber(50);
		request.setBet(100);

		ResponseDto response = betService.placeBet(request);

		assertNotNull(response);
		if(response.isWin()){
			assertThat(new BigDecimal("198.0").round(new MathContext(4, RoundingMode.HALF_UP))).isEqualTo(response.getWinningAmount());
		} else {
			assertThat(new BigDecimal("0.0")).isEqualTo(response.getWinningAmount());
		}
	}
}
