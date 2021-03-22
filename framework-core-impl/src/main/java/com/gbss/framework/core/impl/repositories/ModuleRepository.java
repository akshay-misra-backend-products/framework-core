package com.gbss.framework.core.impl.repositories;

import com.gbss.framework.core.meta.annotations.ObjectType;
import com.gbss.framework.core.model.constants.SystemConstants;
import com.gbss.framework.core.model.entities.Module;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Akshay Misra on 28-02-2021.
 */
@Repository
@ObjectType(SystemConstants.ObjectTypes.MODULE)
public interface ModuleRepository extends MongoRepository<Module, String> {
}
