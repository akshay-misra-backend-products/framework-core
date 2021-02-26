package com.gbss.framework.core.impl.repositories;

import com.gbss.framework.core.model.entities.Currency;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CurrencyRepository extends MongoRepository<Currency, String> {
}
