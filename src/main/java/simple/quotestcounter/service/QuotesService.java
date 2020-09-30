package simple.quotestcounter.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import simple.quotestcounter.domain.Quote;
import simple.quotestcounter.repository.QuotesRepository;

@Service
public class QuotesService {

	@Autowired
	private QuotesRepository quotesRepository;
	
	public Long createQuote(Quote quote) {
		quote= quotesRepository.save(quote);
		return quote.getQuoteId();
	}

	public List<String> getFrequentSymbols() {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -10);
		Date date = cal.getTime();
		return quotesRepository.getFrequentSymbols(date, PageRequest.of(0,5));
	}

	public List<Quote> getAllQuotes() {
		// TODO Auto-generated method stub
		return quotesRepository.findAll();
	}

}
