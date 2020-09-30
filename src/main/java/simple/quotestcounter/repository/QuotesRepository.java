package simple.quotestcounter.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import simple.quotestcounter.domain.Quote;

@Repository
public interface QuotesRepository extends JpaRepository<Quote, Long>{
	
	@Query("SELECT q.symbol FROM Quote q WHERE q.timestamp >= :date group by q.symbol order by count(1) desc")
	List<String> getFrequentSymbols(@Param("date") Date date, Pageable pageRequest);	
}
