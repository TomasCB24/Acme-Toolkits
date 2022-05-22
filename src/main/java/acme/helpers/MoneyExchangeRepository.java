package acme.helpers;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.exchange.MoneyExchangeCache;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface MoneyExchangeRepository extends AbstractRepository {

	@Query("select c from MoneyExchangeCache c where c.sourceCurrency = :sC AND c.targetCurrency = :tC")
	MoneyExchangeCache findMoneyExchangeInCache(String sC, String tC);

}