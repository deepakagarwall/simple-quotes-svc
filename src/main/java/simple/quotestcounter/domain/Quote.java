package simple.quotestcounter.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Quote {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long quoteId;

	// ticker symbol
	private String symbol;

	// volume for the trade being quoted
	private String sharesTraded;

	// bid price
	private Double priceTraded;

	// indicates whether stock is trading higher or lower
	private String changeDirection;

	// difference in price from previous day
	private Double changeAmount;

	// timestamp of the quote
	@CreationTimestamp
	private Date timestamp;

	public Quote() {
		super();
	}

	public Quote(String symbol, String sharesTraded, Double priceTraded, String changeDirection, Double changeAmount) {
		super();
		this.symbol = symbol;
		this.sharesTraded = sharesTraded;
		this.priceTraded = priceTraded;
		this.changeDirection = changeDirection;
		this.changeAmount = changeAmount;
	}

	public Long getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(Long quoteId) {
		this.quoteId = quoteId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSharesTraded() {
		return sharesTraded;
	}

	public void setSharesTraded(String sharesTraded) {
		this.sharesTraded = sharesTraded;
	}

	public String getChangeDirection() {
		return changeDirection;
	}

	public void setChangeDirection(String changeDirection) {
		this.changeDirection = changeDirection;
	}

	public Double getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(Double changeAmount) {
		this.changeAmount = changeAmount;
	}

	public void setPriceTraded(Double priceTraded) {
		this.priceTraded = priceTraded;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Quote [quoteId=" + quoteId + ", symbol=" + symbol + ", sharesTraded=" + sharesTraded + ", priceTraded="
				+ priceTraded + ", changeDirection=" + changeDirection + ", changeAmount=" + changeAmount
				+ ", timestamp=" + timestamp + "]";
	}

}
