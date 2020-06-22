package com.gbss.framework.core.impl.factory;

import com.gbss.framework.core.api.factory.BeanMapper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class ObjectTypeRepositoryMapperFactory  implements BeanMapper<MongoRepository> {

    @Resource
    private Map<String, MongoRepository> objectTypeRepositoriesMapper;

    @Override
    public MongoRepository getBean(String objectTypeId) {
        return objectTypeRepositoriesMapper.get(objectTypeId);
    }
}
