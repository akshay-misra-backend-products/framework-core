package com.bss.framework.core.schema.repositories;

import com.bss.framework.core.schema.model.AttributeValue;
import com.bss.framework.core.schema.model.ObjectType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Akshay Misra on 16-06-2019.
 */
@Repository
public interface ObjectTypeRepository extends MongoRepository<ObjectType, String> {
    List<ObjectType> findByParentId(String parentId);
}
