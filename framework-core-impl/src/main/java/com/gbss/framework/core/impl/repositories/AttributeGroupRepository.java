package com.gbss.framework.core.impl.repositories;

import com.gbss.framework.core.meta.annotations.ObjectType;
import com.gbss.framework.core.model.constants.SystemConstants;
import com.gbss.framework.core.model.entities.AttributeGroup;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Akshay Misra on 17-06-2019.
 */
@Repository
@ObjectType(SystemConstants.ObjectTypes.ATTRIBUTE_GROUP)
public interface AttributeGroupRepository extends MongoRepository<AttributeGroup, String> {

    List<AttributeGroup> findByParentId(String parentId);
}
