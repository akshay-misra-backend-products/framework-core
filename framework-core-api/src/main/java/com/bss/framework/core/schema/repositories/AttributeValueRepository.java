package com.bss.framework.core.schema.repositories;

import com.bss.framework.core.schema.model.AttributeValue;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Akshay Misra on 17-06-2019.
 */
@Repository
public interface AttributeValueRepository extends MongoRepository<AttributeValue, String> {
}
