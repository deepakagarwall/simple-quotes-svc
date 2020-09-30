package simple.quotestcounter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import simple.quotestcounter.domain.Quote;
import simple.quotestcounter.service.QuotesService;

@RestController
@RequestMapping("v1/quotes")
public class QuotesController {

	@Autowired
	private QuotesService quotesService;

	/**
	 * API to submit a quote
	 * 
	 * @param quote
	 * @return
	 */
	@PostMapping(consumes = "application/json")
	public ResponseEntity<Long> createQuote(@RequestBody Quote quote) {
		Long quoteId = quotesService.createQuote(quote);
		return new ResponseEntity<Long>(quoteId, HttpStatus.CREATED);
	}

	/**
	 * API to get the (up to) 5 ticker symbols most frequently received within the
	 * last 10 minutes.
	 * 
	 * @param quote
	 * @return
	 */
	@GetMapping(value = "/getFrequentReceivedSymbols", produces = "application/json")
	public List<String> getFrequentSymbols() {
		return quotesService.getFrequentSymbols();
	}
	
	/**
	 * API to get all the available quotes
	 * @return
	 */
	
	@GetMapping(produces = "application/json")
	public List<Quote> getAllQuotes() {
		return quotesService.getAllQuotes();
	}

}
