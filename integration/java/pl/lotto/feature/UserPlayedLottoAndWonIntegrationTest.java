package pl.lotto.feature;

import com.github.tomakehurst.wiremock.client.WireMock;
import java.time.Duration;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import pl.lotto.BaseIntegrationTest;
import pl.lotto.domain.numbergenerator.NumberGeneratorFacade;
import pl.lotto.domain.numbergenerator.WinningNumbersNotFoundException;
import pl.lotto.domain.numberreciver.dto.InputNumbersResultDto;

import static org.awaitility.Awaitility.await;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserPlayedLottoAndWonIntegrationTest extends BaseIntegrationTest {

    @Autowired
    public NumberGeneratorFacade winningNumbersGeneratorFacade;

    @Test
    public void should_user_win_and_system_should_generate_winners() throws Exception {
        // step 1: external service returns 6 random numbers (1,2,3,4,5,6)
        // given
        wireMockServer.stubFor(WireMock.get("/api/v1.0/random?min=1&max=99&count=25")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type","application/json")
                        .withBody("""
                               [1, 2, 3, 4, 5, 6, 82, 82, 83, 83, 86, 57, 10, 81, 53, 93, 50, 54, 31, 88, 15, 43, 79, 32, 43]
                                """.trim()
                        )));
        //step 2: system fetched winning numbers for draw date: 19.11.2022 12:00
        // given
        LocalDateTime drawDate = LocalDateTime.of(2022, 11, 16, 10, 0, 0);
        // when && then
        await()
                .atMost(Duration.ofSeconds(20))
                .pollInterval(Duration.ofSeconds(1))
                .until(() -> {
                            try {
                                return !winningNumbersGeneratorFacade.findWinningNumbersByDate(drawDate).winningNumbers().isEmpty();
                            } catch (WinningNumbersNotFoundException e) {
                                return false;
                            }
                        }
                );
        //step 3: user made POST /inputNumbers with 6 numbers (1, 2, 3, 4, 5, 6) at 16-11-2022 10:00 and system returned OK(200) with message: “success” and Ticket (DrawDate:19.11.2022 12:00 (Saturday), TicketId: sampleTicketId)


        ResultActions perform = mockMvc.perform(post("/inputNumbers")
                        .content("""
                        {
                        "inputNumbers": [1,2,30,50,55,61]
                        }
                        """.trim()
                        ).contentType(MediaType.APPLICATION_JSON));

        MvcResult mvcResult = perform.andExpect(status().isOk()).andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        InputNumbersResultDto inputNumbersResultDto = objectMapper.readValue(json, InputNumbersResultDto.class);
        assertThat(inputNumbersResultDto.message(), equalTo("Success"));
        assertThat(inputNumbersResultDto.inputNumbers(), hasSize(6));
        //step 4: user made GET /results/notExistingId and system returned 404(NOT_FOUND) and body with (message: Not found for id: notExistingId and status NOT_FOUND)

        ResultActions perform1 = mockMvc.perform(get("/result" + "/notExistingId"));
        perform1.andExpect(status().isNotFound()).andExpect(
                content().json(
                        """ 
                        {
                                "message": "Not found for id: notExistingId",
                                "status": "NOT_FOUND"
                        }
                        """.trim()
                ));

        //step 5: 3 days and 1 minute passed, and it is 1 minute after the draw date (19.11.2022 10:01)
        clock.plusDaysAndMinutes(3,61);
        //step 6: system generated result for TicketId: sampleTicketId with draw date 19.11.2022 12:00, and saved it with 6 hits

        //step 7: 3 hours passed, and it is 1 minute after announcement time (19.11.2022 15:01)
        //step 8: user made GET /results/sampleTicketId and system returned 200 (OK)
    }

}
