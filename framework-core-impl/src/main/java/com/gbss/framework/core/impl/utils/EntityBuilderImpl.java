package com.gbss.framework.core.impl.utils;

import com.gbss.framework.core.api.service.api.AttributeSchemaService;
import com.gbss.framework.core.api.service.api.ModulesService;
import com.gbss.framework.core.impl.factory.ObjectTypeRepositoryMapperFactory;
import com.gbss.framework.core.impl.repositories.ModuleRepository;
import com.gbss.framework.core.impl.repositories.ObjectTypeRepository;
import com.gbss.framework.core.api.utils.EntityBuilder;
import com.gbss.framework.core.model.constants.SystemConstants;
import com.gbss.framework.core.model.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;

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
    AttributeSchemaService attributeSchemaService;

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    ModulesService modulesService;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    WebClient.Builder webClientBuilder;


    @Override
    public Base getLiteObjectById(String objectTypeId, String objectId) throws ObjectNotFoundException {
        System.out.println("********** $$$$$$$ EntityBuilderImpl, getLiteObjectById, "
                + "objectTypeId: " + objectTypeId
                + ", objectId: " + objectId);

        ObjectType objectType = attributeSchemaService.getObjectTypeById(objectTypeId);
        if (!StringUtils.isEmpty(objectType.getCollectionName())) {
            if (mongoTemplate.getCollectionNames().contains(objectType.getCollectionName())) {
                BaseLite base = mongoTemplate.findById(objectId, BaseLite.class,
                        objectType.getCollectionName());
                System.out.println("********** $$$$$$$ EntityBuilderImpl, getLiteObjectById, base: " + base);
            }
        }


        return null;
    }

    @Override
    public Base getObjectById(@NotNull String objectTypeId, @NotNull String objectId) throws ObjectNotFoundException {
        System.out.println("********** $$$$$$$ EntityBuilderImpl, getObjectById, objectId: " + objectId);
        ObjectType objectType = attributeSchemaService.getObjectTypeById(objectTypeId);
        System.out.println("********** $$$$$$$ EntityBuilderImpl, objectType: " + objectType);
        System.out.println("********** $$$$$$$ EntityBuilderImpl, getCollectionName: "
                + objectType.getCollectionName());
        Base base = null;

        Module module = modulesService.getModuleByObjectType(objectType);
        if (SystemConstants.Objects.FRAMEWORK_CORE_MODULE_ID.equals(module.getId())) {
            MongoRepository repository = objectTypeRepositoryFactory.getBean(objectTypeId);
            if (repository == null) {
                if (mongoTemplate.getCollectionNames().contains(objectType.getCollectionName())) {
                    base = mongoTemplate.findById(objectId, Base.class, objectType.getCollectionName());
                }
            } else {
                Optional<Base> baseOp = repository.findById(objectId);
                if (baseOp.isPresent()) {
                    base = baseOp.get();
                }
            }
        } else {
            try {
                System.out.println("******** ####### getObjectById, load by id uri: " +
                        "http:/" + objectType.getLoadByIdAPI() + objectId);
                DynamicObject dynamicObject = webClientBuilder
                        //.defaultCookie("cookieKey", "cookieValue")
                        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .build()
                        .get()
                        .uri("http:/" + objectType.getLoadByIdAPI() + objectId)
                        .retrieve()
                        .bodyToMono(DynamicObject.class)
                        .block();
                System.out.println("******** ####### getObjectById, dynamicObject: " + dynamicObject);
                base = dynamicObject;
            } catch (Exception e) {
                System.out.println("******** ####### getObjectById exception, e: " + e);
            }
        }

        if (base == null) {
            throw new ObjectNotFoundException("Object with objectTypeId: " + objectTypeId
                    + " and objectId: " + objectId + " not found.");
        }

        return base;
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
        System.out.println("********** $$$$$$$ EntityBuilderImpl, objectTypeId: " + objectTypeId
        + ", objectId: " + objectId);
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
                ObjectType objectType = attributeSchemaService.getObjectTypeById(objectTypeId);
                System.out.println("********** ############### EntityBuilderImpl, objectType: " + objectType);
                if (objectType.getParentId() != null) {
                    ObjectType parentOT = attributeSchemaService.getObjectTypeById(objectType.getParentId());
                    if (parentOT == null) {
                        base = moduleRepository.findById(objectId).get(); //objectType.getParentId()
                    } else {
                        System.out.println("********** ############### EntityBuilderImpl, parentOT: " + parentOT);
                        MongoRepository parentRepo = objectTypeRepositoryFactory.getBean(parentOT.getId());
                        Optional<Base> parent = parentRepo.findById(objectId);
                        if (parent.isPresent()) {
                            base = parent.get();
                        }
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
