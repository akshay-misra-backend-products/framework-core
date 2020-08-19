package com.gbss.framework.core.impl.utils;

import com.gbss.framework.core.impl.factory.ObjectTypeRepositoryMapperFactory;
import com.gbss.framework.core.impl.repositories.ObjectTypeRepository;
import com.gbss.framework.core.api.utils.EntityBuilder;
import com.gbss.framework.core.model.entities.Base;
import com.gbss.framework.core.model.entities.DynamicObject;
import com.gbss.framework.core.model.entities.ObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.ejb.ObjectNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class EntityBuilderImpl implements EntityBuilder {

    @Autowired
    ObjectTypeRepository objectTypeRepository;

    @Autowired
    ObjectTypeRepositoryMapperFactory objectTypeRepositoryFactory;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Base getObjectById(@NotNull String objectTypeId, @NotNull String objectId) throws ObjectNotFoundException {
        /*Base base = null;
        MongoRepository repository = objectTypeRepositoryFactory.getBean(objectTypeId);
        if (repository == null) {
            ObjectType objectType = objectTypeRepository.findById(objectTypeId).get();
            if (!StringUtils.isEmpty(objectType.getCollectionName())) {
                if (mongoTemplate.getCollectionNames().contains(objectType.getCollectionName())) {
                    DynamicObject dynamicObject = mongoTemplate.findById(objectId, DynamicObject.class,
                            objectType.getCollectionName());
                    return dynamicObject;
                }
            }
        } else {
            Optional<Base> op = repository.findById(objectId);
            if (op.isPresent()) {
                base = op.get();
            }
        }

        if (base == null) {
            throw new ObjectNotFoundException("Object with objectTypeId: " + objectTypeId
                    + " and objectId: " + objectId + " not found.");
        }

        System.out.println("********** EntityBuilderImpl, base: " + base);*/

        return getObjectByChildOrCurrentOT(objectTypeId, objectId);
    }

    @Override
    @Deprecated
    public Base getObjectByChildOT(@NotNull String childObjectTypeId, @NotNull String objectId) throws ObjectNotFoundException {
        Optional<Base> parentOP = null;
        Base parent = null;
        ObjectType objectType = objectTypeRepository.findById(childObjectTypeId).get();
        if (objectType.getParentId() == null) {
            MongoRepository repository = objectTypeRepositoryFactory.getBean(childObjectTypeId);
            parentOP = repository.findById(objectId);
        } else {
            ObjectType parentOT = objectTypeRepository.findById(objectType.getParentId()).get();
            MongoRepository parentRepo = objectTypeRepositoryFactory.getBean(parentOT.getId());
            parentOP = parentRepo.findById(objectId);
        }

        if (parentOP == null || !parentOP.isPresent()) {
            throw new ObjectNotFoundException("Object with childObjectTypeId: " + childObjectTypeId
                    + " and objectId: " + objectId + " not found.");
        }

        parent = parentOP.get();
        System.out.println("********** EntityBuilderImpl, parent: " + parent);

        return parent;
    }

    @Override
    public Base getObjectByChildOrCurrentOT(@NotNull String objectTypeId,
                                            @NotNull String objectId) throws ObjectNotFoundException {
        System.out.println("********** $$$$$$$ EntityBuilderImpl, objectTypeId: " + objectTypeId);
        Base base = null;
        MongoRepository repository = objectTypeRepositoryFactory.getBean(objectTypeId);
        System.out.println("********** $$$$$$$ EntityBuilderImpl, repository: " + repository);
        if (repository == null) {
            ObjectType objectType = objectTypeRepository.findById(objectTypeId).get();
            System.out.println("********** $$$$$$$ EntityBuilderImpl, objectType: " + objectType);
            System.out.println("********** $$$$$$$ EntityBuilderImpl, getCollectionName: " +
                    objectType.getCollectionName());
            System.out.println("********** $$$$$$$ EntityBuilderImpl, collection exists? " +
                    mongoTemplate.getCollectionNames().contains(objectType.getCollectionName()));
            if (!StringUtils.isEmpty(objectType.getCollectionName())) {
                if (mongoTemplate.getCollectionNames().contains(objectType.getCollectionName())) {
                    DynamicObject dynamicObject = mongoTemplate.findById(objectId, DynamicObject.class,
                            objectType.getCollectionName());
                    System.out.println("********** $$$$$$$ EntityBuilderImpl, dynamicObject: " + dynamicObject);
                    if (dynamicObject == null) {
                        ObjectType parentOT = objectTypeRepository.findById(objectType.getParentId()).get();
                        if (!StringUtils.isEmpty(parentOT.getCollectionName())) {
                            if (mongoTemplate.getCollectionNames().contains(parentOT.getCollectionName())) {
                                DynamicObject object = mongoTemplate.findById(objectId, DynamicObject.class,
                                        parentOT.getCollectionName());
                                return object;
                            }
                        }
                    }
                    return dynamicObject;
                } else {
                    ObjectType parentOT = objectTypeRepository.findById(objectType.getParentId()).get();
                    if (!StringUtils.isEmpty(parentOT.getCollectionName())) {
                        if (mongoTemplate.getCollectionNames().contains(parentOT.getCollectionName())) {
                            DynamicObject dynamicObject = mongoTemplate.findById(objectId, DynamicObject.class,
                                    parentOT.getCollectionName());
                            return dynamicObject;
                        }
                    }
                }
            }
        } else {
            Optional<Base> op = repository.findById(objectId);
            if (op.isPresent()) {
                base = op.get();
            } else {
                ObjectType objectType = objectTypeRepository.findById(objectTypeId).get();
                if (objectType.getParentId() != null) {
                    ObjectType parentOT = objectTypeRepository.findById(objectType.getParentId()).get();
                    MongoRepository parentRepo = objectTypeRepositoryFactory.getBean(parentOT.getId());
                    Optional<Base> parent = parentRepo.findById(objectId);
                    if (parent.isPresent()) {
                        base = parent.get();
                    }
                }
            }
        }

        if (base == null) {
            throw new ObjectNotFoundException("Object with objectTypeId: " + objectTypeId
                    + " and objectId: " + objectId + " not found.");
        }

        return base;
    }
}
