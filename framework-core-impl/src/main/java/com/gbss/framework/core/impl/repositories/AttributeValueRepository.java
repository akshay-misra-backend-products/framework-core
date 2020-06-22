package com.gbss.framework.core.impl.repositories;

import com.gbss.framework.core.model.entities.AttributeValue;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Akshay Misra on 17-06-2019.
 */
@Repository
public interface AttributeValueRepository extends MongoRepository<AttributeValue, String> {

    List<AttributeValue> findByParentId(String parentId);
}
