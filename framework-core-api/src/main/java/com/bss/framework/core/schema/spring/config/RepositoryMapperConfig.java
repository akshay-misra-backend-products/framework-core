package com.bss.framework.core.schema.spring.config;

import com.bss.framework.core.schema.repositories.AttributeGroupRepository;
import com.bss.framework.core.schema.repositories.AttributeRepository;
import com.bss.framework.core.schema.repositories.AttributeValueRepository;
import com.bss.framework.core.schema.repositories.ObjectTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RepositoryMapperConfig {

    @Autowired
    AttributeGroupRepository attributeGroupRepository;

    @Autowired
    AttributeRepository attributeRepository;

    @Autowired
    AttributeValueRepository attributeValueRepository;

    @Autowired
    ObjectTypeRepository objectTypeRepository;

    @Autowired
    private ApplicationContext appContext;

    @Bean
    public Map<String, MongoRepository> objectTypeRepositoriesMapper() {

        Map<String, MongoRepository> repositories = appContext.getBeansOfType(MongoRepository.class);
        repositories.forEach((k,v)->{
            System.out.println("****************** RepositoryMapperConfig, repositories: "+k + " - "+v);
        });

        Map<String, MongoRepository> config = new HashMap<>();
        config.put("5e934da667ed1fb0bcf0fca8", attributeGroupRepository);
        config.put("5e934d4567ed1fb0bcf0fca7", attributeRepository);
        config.put("5ea6c35f3fe39bd27a715a33", attributeValueRepository);
        config.put("5ea86babc8ae3bed0b307a4d", objectTypeRepository);

        return config;
    }
}
