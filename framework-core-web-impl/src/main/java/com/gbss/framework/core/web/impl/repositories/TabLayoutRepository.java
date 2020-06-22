package com.gbss.framework.core.web.impl.repositories;

import com.gbss.framework.core.web.model.TabLayout;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Akshay Misra on 05-04-2020.
 */
@Repository
public interface TabLayoutRepository extends MongoRepository<TabLayout, String> {
}
