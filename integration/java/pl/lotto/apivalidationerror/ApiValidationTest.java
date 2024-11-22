package pl.lotto.apivalidationerror;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import pl.lotto.BaseIntegrationTest;
import pl.lotto.infrastructure.apivalidation.ApiValidationErrorDto;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ApiValidationTest extends BaseIntegrationTest {

    @Test
    public void should_return_400_bad_request_and_validation_message_when_request_has_empty_input_numbers() throws Exception {
        // given & when
        ResultActions perform = mockMvc.perform(post("/inputNumbers")
                .content("""
                        {
                        "inputNumbers" : []
                        }
                        """)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        // then
        MvcResult mvcResult = perform.andExpect(status().isBadRequest()).andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        ApiValidationErrorDto result = objectMapper.readValue(json, ApiValidationErrorDto.class);

    }

    @Test
    public void should_return_400_bad_request_and_validation_message_when_request_does_not_have_input_numbers() throws Exception {
        // given
        // when
        ResultActions perform = mockMvc.perform(post("/inputNumbers")
                .content("""
                        {}
                        """.trim()
                ).contentType(MediaType.APPLICATION_JSON)
        );
        // then
        MvcResult mvcResult = perform.andExpect(status().isBadRequest()).andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        ApiValidationErrorDto result = objectMapper.readValue(json, ApiValidationErrorDto.class);

    }
}
