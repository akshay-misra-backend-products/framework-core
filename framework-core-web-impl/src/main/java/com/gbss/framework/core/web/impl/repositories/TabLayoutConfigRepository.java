package com.gbss.framework.core.web.impl.repositories;

import com.gbss.framework.core.web.model.TabLayoutConfig;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Akshay Misra on 05-04-2020.
 */
@Repository
public interface TabLayoutConfigRepository extends MongoRepository<TabLayoutConfig, String> {
}
