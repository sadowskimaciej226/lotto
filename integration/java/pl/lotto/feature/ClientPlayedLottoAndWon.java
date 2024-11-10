package pl.lotto.feature;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import pl.lotto.BaseIntegrationTest;
import pl.lotto.domain.numbergenerator.NumberGeneratorFacade;
import pl.lotto.domain.numbergenerator.WinningNumbersNotFoundException;


import java.time.Duration;
import java.time.LocalDateTime;

import static org.awaitility.Awaitility.await;

public class ClientPlayedLottoAndWon extends BaseIntegrationTest {

    @Autowired
    NumberGeneratorFacade numberGeneratorFacade;

    @Test
    public void client_played_and_won_a_game(){
//        1.	step 1: external service returns 6 random numbers (1,2,3,4,5,6)
        //given
        wireMockServer.stubFor(WireMock.get("/api/v1.0/random?min=1&max=99&count=25")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type","application/json")
                        .withBody("""
                               [1, 2, 3, 4, 5, 6, 82, 82, 83, 83, 86, 57, 10, 81, 53, 93, 50, 54, 31, 88, 15, 43, 79, 32, 43]
                                """.trim()
                        )));

//        2.	step 2: system generated winning numbers for draw date: 19.11.2022 12:00
      //given
        LocalDateTime drawDate = LocalDateTime.of(2023, 2, 25, 12, 0, 0);
      //when
        await()
                .atMost(Duration.ofSeconds(2))
                .pollInterval(Duration.ofSeconds(1))
                .until(()->{
                    try {
                        return !numberGeneratorFacade.findWinningNumbersByDate(drawDate).winningNumbers().isEmpty();
                    } catch (WinningNumbersNotFoundException e){
                        return false;
                    }
                });
//        3.	step 3: user made POST /inputNumbers with 6 numbers (1, 2, 3, 4, 5, 6) at 16-11-2022 10:00 and system returned OK(200) with message: “success” and Ticket (DrawDate:19.11.2022 12:00 (Saturday), TicketId: sampleTicketId)
//        4.	step 4: user made GET /results/notExistingId and system returned 404(NOT_FOUND) and body with (“message”: “Not found for id: notExistingId” and “status”: “NOT_FOUND”)
//        5.	step 5: 3 days and 55 minutes passed, and it is 5 minute before draw (19.11.2022 11:55)
//        6.	step 6: system generated result for TicketId: sampleTicketId with draw date 19.11.2022 12:00, and saved it with 6 hits
//        7.	step 7: 6 minutes passed and it is 1 minute after the draw (19.11.2022 12:01)
//        8.	step 8: user made GET /results/sampleTicketId and system returned 200 (OK)

    }
}
