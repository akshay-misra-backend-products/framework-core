package com.bss.framework.core.schema.repositories;

import com.bss.framework.core.schema.model.ObjectType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Rocky on 16-06-2019.
 */
@Repository
public interface ObjectTypeRepository extends MongoRepository<ObjectType, String> {
}
