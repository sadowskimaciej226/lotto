package pl.lotto.infrastructure.numbergenerator.http;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.lotto.domain.numbergenerator.RandomNumberGenerable;
import pl.lotto.domain.numbergenerator.dto.SixRandomNumbersDto;
import org.springframework.http.HttpHeaders;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Log4j2
public class RandomNumberGeneratorRestTemplate implements RandomNumberGenerable {
    private final RestTemplate restTemplate;
    private final String uri;
    private final int port;



    @Override
    public SixRandomNumbersDto generate6RandomNumbers(int count, int lowerBand, int upperBand) {
        HttpHeaders headers = new HttpHeaders();
        final HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(headers);
        final String url = UriComponentsBuilder.fromHttpUrl(getUrlForService())
                .queryParam("min", lowerBand)
                .queryParam("max", upperBand)
                .queryParam("count", count)
                .toUriString();

        ResponseEntity<List<Integer>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });
        System.out.println();
        Set<Integer> body = new HashSet<>(response.getBody()).stream().limit(6).collect(Collectors.toSet());
        return SixRandomNumbersDto.builder()
                .randomNumbers(body)
                .build();
    }

    private String getUrlForService() {
        return uri + ":" + port + "/api/v1.0/random";
    }
}
