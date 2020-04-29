package com.bss.framework.core.schema.spring.config;

import com.bss.framework.core.schema.repositories.AttributeGroupRepository;
import com.bss.framework.core.schema.repositories.AttributeRepository;
import com.bss.framework.core.schema.repositories.AttributeValueRepository;
import com.bss.framework.core.schema.repositories.ObjectTypeRepository;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;

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

    @Bean
    public ImmutableMap<String, MongoRepository> objectTypeRepositoriesMapper() {
        ImmutableMap.Builder<String, MongoRepository> builder = new ImmutableMap.Builder();
        builder.put("5e934da667ed1fb0bcf0fca8", attributeGroupRepository);
        builder.put("5e934d4567ed1fb0bcf0fca7", attributeRepository);
        builder.put("5ea6c35f3fe39bd27a715a33", attributeValueRepository);
        builder.put("0", objectTypeRepository);

        return builder.build();
    }
}
