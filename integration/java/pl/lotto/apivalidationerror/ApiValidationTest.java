package pl.lotto.apivalidationerror;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import pl.lotto.BaseIntegrationTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class ApiValidationTest extends BaseIntegrationTest {

    @Test
    void should_return_400_when_input_numbers_is_empty() throws Exception {
        //given
        //when
        ResultActions perform = mockMvc.perform(post("/inputNumbers")
                .content("""
                        {
                        "inputNumbers":
                        }
                        """.trim()
                ).contentType(MediaType.APPLICATION_JSON));
    }
}
