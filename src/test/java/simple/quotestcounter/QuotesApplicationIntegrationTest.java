package simple.quotestcounter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import simple.quotestcounter.domain.Quote;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class QuotesApplicationIntegrationTest {

	@LocalServerPort
	private int serverPort;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testCreateQuote() {
		String url = "http://localhost:" + serverPort + "/v1/quotes";
		Quote quote = new Quote("D05.SI", "5k", 26.55, "up", 0.17);
		ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, quote, Long.class);

		Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		Assert.assertEquals(1l, responseEntity.getBody().longValue());

	}

	@Test
	public void testGetFrequentReceivedSymbols() throws JsonParseException, JsonMappingException, IOException {
		
		//create the sample data for this particular test
		String createQuoteUrl = "http://localhost:" + serverPort + "/v1/quotes";
		Quote quote1 = new Quote("D01.SI", "5k", 26.55, "up", 0.17);
		Quote quote2 = new Quote("D02.SI", "2k", 26.55, "up", 0.17);
		Quote quote3 = new Quote("D03.SI", "3k", 26.55, "up", 0.17);
		Quote quote4 = new Quote("D04.SI", "4k", 26.55, "up", 0.17);
		Quote quote5 = new Quote("D05.SI", "6k", 26.55, "up", 0.17);
		Quote quote6 = new Quote("D05.SI", "2k", 26.55, "up", 0.17);
		Quote quote7 = new Quote("D01.SI", "2k", 26.55, "up", 0.17);
		Quote quote8 = new Quote("D06.SI", "2k", 26.55, "up", 0.17);
		Quote quote9 = new Quote("D06.SI", "6k", 26.55, "up", 0.17);
		Quote quote10 = new Quote("D06.SI", "6k", 26.55, "up", 0.17);
		restTemplate.postForEntity(createQuoteUrl, quote1, Long.class);
		restTemplate.postForEntity(createQuoteUrl, quote2, Long.class);
		restTemplate.postForEntity(createQuoteUrl, quote3, Long.class);
		restTemplate.postForEntity(createQuoteUrl, quote4, Long.class);
		restTemplate.postForEntity(createQuoteUrl, quote5, Long.class);
		restTemplate.postForEntity(createQuoteUrl, quote6, Long.class);
		restTemplate.postForEntity(createQuoteUrl, quote7, Long.class);
		restTemplate.postForEntity(createQuoteUrl, quote8, Long.class);
		restTemplate.postForEntity(createQuoteUrl, quote9, Long.class);
		restTemplate.postForEntity(createQuoteUrl, quote10, Long.class);

		//get the frequently received 5 ticker symbols within last 10 mins
		String getFrequentReceivedSymbolsUrl = "http://localhost:" + serverPort
				+ "/v1/quotes/getFrequentReceivedSymbols";
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(getFrequentReceivedSymbolsUrl, String.class);
		List<String> symbolsLst = new ObjectMapper().readValue(responseEntity.getBody(),
				new TypeReference<List<String>>() {
				});

		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		
		//these have more frequency and should come in output
		Assert.assertTrue(symbolsLst.contains("D05.SI"));
		Assert.assertTrue(symbolsLst.contains("D06.SI"));
		Assert.assertTrue(symbolsLst.contains("D01.SI"));

	}
}
