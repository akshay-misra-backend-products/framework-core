package com.gbss.framework.core.impl.repositories;

import com.gbss.framework.core.model.entities.Locale;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocaleRepository extends MongoRepository<Locale, String> {
}
