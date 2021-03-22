package com.gbss.framework.core.impl.repositories;

import com.gbss.framework.core.model.constants.SystemConstants;
import com.gbss.framework.core.model.entities.ObjectType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Akshay Misra on 16-06-2019.
 */
@Repository
@com.gbss.framework.core.meta.annotations.ObjectType(SystemConstants.ObjectTypes.OBJECT_TYPE)
public interface ObjectTypeRepository extends MongoRepository<ObjectType, String> {

    List<ObjectType> findByParentId(String parentId);

    List<ObjectType> findAllByIdIn(List<String> ids);
}
