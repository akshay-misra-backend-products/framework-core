package com.gbss.framework.core.impl.service.impl;

import com.gbss.framework.core.api.service.api.DynamicObjectsService;
import com.gbss.framework.core.api.service.api.ModulesService;
import com.gbss.framework.core.impl.json.builder.impl.JsonToDynamicObjectBuilderImpl;
import com.gbss.framework.core.impl.repositories.DynamicObjectRepository;
import com.gbss.framework.core.impl.repositories.ObjectTypeRepository;
import com.gbss.framework.core.model.constants.SystemConstants;
import com.gbss.framework.core.model.entities.Base;
import com.gbss.framework.core.model.entities.DynamicObject;
import com.gbss.framework.core.model.entities.Module;
import com.gbss.framework.core.model.entities.ObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Akshay Misra on 28-02-2021.
 */
@Service
public class DynamicObjectsServiceImpl extends ApplicationAuditServiceImpl implements DynamicObjectsService {

    @Autowired
    ObjectTypeRepository objectTypeRepository;

    @Autowired
    DynamicObjectRepository dynamicObjectRepository;

    @Autowired
    JsonToDynamicObjectBuilderImpl jsonToDynamicObjectBuilder;

    @Autowired
    ModulesService modulesService;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    WebClient.Builder webClientBuilder;

    @Override
    public DynamicObject createDynamicObject(String json) {
        System.out.println("******** ####### createDynamicObject, json: " + json);
        DynamicObject dynamicObject = jsonToDynamicObjectBuilder.build(json);
        System.out.println("******** ####### createDynamicObject, dynamicObject: " + dynamicObject);
        ObjectType objectType = objectTypeRepository.findById(dynamicObject.getObjectTypeId()).get();
        if (!StringUtils.isEmpty(objectType.getCollectionName())) {
            dynamicObject.setCollectionName(objectType.getCollectionName());
            Module module = modulesService.getModuleByObjectType(objectType);
            if (SystemConstants.Objects.FRAMEWORK_CORE_MODULE_ID.equals(module.getId())) {
                if (!mongoTemplate.getCollectionNames().contains(objectType.getCollectionName())) {
                    mongoTemplate.createCollection(objectType.getCollectionName());
                }
                mongoTemplate.save(dynamicObject, objectType.getCollectionName());
            } else {
                try {
                    String objectId = webClientBuilder
                            //.defaultCookie("cookieKey", "cookieValue")
                            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                            .build()
                            .post()
                            .uri("http:/" + objectType.getAddAPI())
                            .bodyValue(dynamicObject)
                            .retrieve()
                            .bodyToMono(String.class)
                            .block();
                    System.out.println("******** ####### createDynamicObject, objectId: " + objectId);
                    dynamicObject.setId(objectId);
                } catch (Exception e) {
                    System.out.println("******** ####### createDynamicObject exception, e: " + e);
                }
            }
        } else {
            System.out.println("*** createDynamicObject, unable to find CollectionName for object type: "
                    + objectType.getName() + ", hence saving object to framework-core database.");
            dynamicObjectRepository.save(dynamicObject);
        }

        return dynamicObject;
    }

    @Override
    public DynamicObject updateDynamicObject(String json) {
        DynamicObject dynamicObjectUI = jsonToDynamicObjectBuilder.build(json);
        System.out.println("******* updateDynamicObject, dynamicObjectUI: " + dynamicObjectUI);
        ObjectType objectType = objectTypeRepository.findById(dynamicObjectUI.getObjectTypeId()).get();
        if (!StringUtils.isEmpty(objectType.getCollectionName())) {
            dynamicObjectUI.setCollectionName(objectType.getCollectionName());
            if (!mongoTemplate.getCollectionNames().contains(objectType.getCollectionName())) {
                mongoTemplate.createCollection(objectType.getCollectionName());
            }
            DynamicObject dynamicObjectDB = mongoTemplate.findById(dynamicObjectUI.getId(), DynamicObject.class,
                    objectType.getCollectionName());
            handleAudit(dynamicObjectDB, dynamicObjectUI);
            mongoTemplate.save(dynamicObjectUI, objectType.getCollectionName());
        } else {
            Optional<DynamicObject> dynamicObjectDB = dynamicObjectRepository.findById(dynamicObjectUI.getId());
            handleAudit(dynamicObjectDB.get(), dynamicObjectUI);
            dynamicObjectRepository.save(dynamicObjectUI);
        }

        return dynamicObjectUI;
    }

    @Override
    public List<DynamicObject> getDynamicObjects(String objectTypeId) {
        List<DynamicObject> objects = new ArrayList<>();
        ObjectType objectType = objectTypeRepository.findById(objectTypeId).get();
        if (!StringUtils.isEmpty(objectType.getCollectionName())) {
            objects.addAll(mongoTemplate.findAll(DynamicObject.class, objectType.getCollectionName()));
        }

        return objects;
    }

    @Override
    public List<DynamicObject> getDynamicObjectsByParentId(String objectTypeId, String parentId) {
        List<DynamicObject> objects = new ArrayList<>();
        ObjectType objectType = objectTypeRepository.findById(objectTypeId).get();
        if (!StringUtils.isEmpty(objectType.getCollectionName())) {
            objects.addAll(mongoTemplate.find(Query.query(Criteria.where("parentId").is(parentId)),
                    DynamicObject.class, objectType.getCollectionName()));
        }

        return objects;
    }

    @Override
    public Map<String, Object> getDynamicParameters(String json, Class clazz) {
        return jsonToDynamicObjectBuilder.getDynamicParameters(json, clazz);
    }
}
