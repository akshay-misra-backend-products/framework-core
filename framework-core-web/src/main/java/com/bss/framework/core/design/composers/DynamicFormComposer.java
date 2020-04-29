package com.bss.framework.core.design.composers;

import com.bss.framework.core.design.model.DynamicFormConfig;
import com.bss.framework.core.schema.factory.ObjectTypeRepositoryMapperFacade;
import com.bss.framework.core.schema.model.Base;
import com.bss.framework.core.schema.service.api.AttributeSchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public class DynamicFormComposer<T extends DynamicFormConfig> implements Composer<T> {

    @Autowired
    ObjectTypeRepositoryMapperFacade objectTypeRepositoryMapperFacade;

    @Override
    public T compose(String objectTypeId, String objectId) {
        MongoRepository repository = objectTypeRepositoryMapperFacade.getRepositoryByObjectTypeId(objectTypeId);
        System.out.println("********** DynamicFormComposer, repository class: "+repository.getClass());
        Base base = (Base) repository.findById(objectTypeId).get();
        System.out.println("********** DynamicFormComposer, compose: "+base);
        return null;
    }
}
