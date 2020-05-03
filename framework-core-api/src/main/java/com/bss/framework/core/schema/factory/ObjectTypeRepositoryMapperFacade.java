package com.bss.framework.core.schema.factory;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class ObjectTypeRepositoryMapperFacade {

    @Resource
    private Map<String, MongoRepository> objectTypeRepositoriesMapper;

    public MongoRepository getRepositoryByObjectTypeId(String objectTypeId) {
        System.out.println("@@@@@@@ getRepositoryByObjectTypeId, objectTypeRepositoriesMapper size: "+ objectTypeRepositoriesMapper.size());
        System.out.println("@@@@@@@ getRepositoryByObjectTypeId, objectTypeId: "+ objectTypeId);
        System.out.println("@@@@@@@ getRepositoryByObjectTypeId, objectTypeRepositoriesMapper: "+ objectTypeRepositoriesMapper);
        return objectTypeRepositoriesMapper.get(objectTypeId);
    }
}
