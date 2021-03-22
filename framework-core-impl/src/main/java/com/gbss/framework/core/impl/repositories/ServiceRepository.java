package com.gbss.framework.core.impl.repositories;

import com.gbss.framework.core.meta.annotations.ObjectType;
import com.gbss.framework.core.model.constants.SystemConstants;
import com.gbss.framework.core.model.entities.Microservice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Akshay Misra on 14-03-2021.
 */
@Repository
@ObjectType(SystemConstants.ObjectTypes.MICROSERVICE)
public interface ServiceRepository extends MongoRepository<Microservice, String> {

    List<Microservice> findByParentId(String parentId);

    Microservice findByParentIdAndServiceType(String parentId, String serviceType);
}
