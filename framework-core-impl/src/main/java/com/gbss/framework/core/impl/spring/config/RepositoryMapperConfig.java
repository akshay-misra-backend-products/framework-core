package com.gbss.framework.core.impl.spring.config;

import com.gbss.framework.core.impl.repositories.AttributeGroupRepository;
import com.gbss.framework.core.impl.repositories.AttributeRepository;
import com.gbss.framework.core.impl.repositories.AttributeValueRepository;
import com.gbss.framework.core.impl.repositories.ObjectTypeRepository;
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
            System.out.println("****************** RepositoryMapperConfig, repositories: "+ k + " - "+ v );
        });

        Map<String, MongoRepository> config = new HashMap<>();
        config.put("5e934da667ed1fb0bcf0fca8", attributeGroupRepository);
        config.put("5e934d4567ed1fb0bcf0fca7", attributeRepository);
        config.put("5ea6c35f3fe39bd27a715a33", attributeValueRepository);
        config.put("5ea86babc8ae3bed0b307a4d", objectTypeRepository);

        return config;
    }
}
