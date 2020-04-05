package com.bss.framework.core.design.repositories;

import com.bss.framework.core.design.model.Tab;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Akshay Misra on 05-04-2020.
 */
@Repository
public interface TabRepository extends MongoRepository<Tab, String> {
}
