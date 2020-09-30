package simple.quotestcounter.service;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import simple.quotestcounter.domain.Quote;
import simple.quotestcounter.repository.QuotesRepository;

public class QuotesServiceUnitTest {

	@InjectMocks
	private QuotesService quotesService;

	@Mock
	private QuotesRepository quotesRepository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateQuote() {
		Quote quote = new Quote("D05.SI", "5k", 26.55, "up", 0.17);
		
		Quote quoteWithId = new Quote();
		quoteWithId.setQuoteId(1l);
		when(quotesRepository.save(quote)).thenReturn(quoteWithId);

		Long quoteId = quotesService.createQuote(quote);
		Assert.assertEquals(1l, quoteId.longValue());
	}

}
