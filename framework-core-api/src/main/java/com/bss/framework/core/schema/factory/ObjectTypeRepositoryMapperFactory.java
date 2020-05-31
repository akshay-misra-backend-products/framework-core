package com.bss.framework.core.schema.factory;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class ObjectTypeRepositoryMapperFactory {

    @Resource
    private Map<String, MongoRepository> objectTypeRepositoriesMapper;

    public MongoRepository getRepositoryByObjectTypeId(String objectTypeId) {
        return objectTypeRepositoriesMapper.get(objectTypeId);
    }
}
