package com.gbss.framework.core.impl.spring.config;

import com.gbss.framework.core.meta.annotations.ObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RepositoryMapperConfig {

    @Autowired
    private ApplicationContext appContext;

    @Bean
    public Map<String, MongoRepository> objectTypeRepositoriesMapper() {
        return getRepositoriesConfig();
    }

    private Map<String, MongoRepository> getRepositoriesConfig() {
        Map<String, MongoRepository> config = new HashMap<>();
        Map<String, MongoRepository> repositories = appContext.getBeansOfType(MongoRepository.class);
        repositories.forEach((k,v)->{
            String objectTypeId = getObjectTypeId(v);
            if (objectTypeId != null) {
                config.put(objectTypeId, v);
            }
        });

        System.out.println("****** ###### getRepositoriesConfig, config: " + config);
        return config;
    }

    private String getObjectTypeId(MongoRepository repository) {
        String objectTypeId = null;
        ObjectType objectType = repository.getClass().getInterfaces()[0].getAnnotation(ObjectType.class);
        if (objectType != null) {
            objectTypeId = objectType.value();
        }

        return objectTypeId;
    }
}
