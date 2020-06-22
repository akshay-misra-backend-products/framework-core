package com.gbss.framework.core.impl.repositories;

import com.gbss.framework.core.model.entities.DynamicObject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DynamicObjectRepository extends MongoRepository<DynamicObject, String> {
}
