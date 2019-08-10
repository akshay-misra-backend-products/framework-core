package com.bss.framework.core.schema.repositories;

import com.bss.framework.core.schema.model.AttributeGroup;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Rocky on 17-06-2019.
 */
@Repository
public interface AttributeGroupRepository extends MongoRepository<AttributeGroup, String> {
}
