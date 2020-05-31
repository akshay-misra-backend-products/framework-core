package com.bss.framework.core.schema.utils;

import com.bss.framework.core.schema.factory.ObjectTypeRepositoryMapperFactory;
import com.bss.framework.core.schema.model.Base;
import com.bss.framework.core.schema.model.ObjectType;
import com.bss.framework.core.schema.repositories.ObjectTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import javax.ejb.ObjectNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class EntityBuilderImpl implements EntityBuilder {

    @Autowired
    ObjectTypeRepository objectTypeRepository;

    @Autowired
    ObjectTypeRepositoryMapperFactory objectTypeRepositoryFactory;

    @Override
    public Base getObjectById(@NotNull String objectTypeId, @NotNull String objectId) throws ObjectNotFoundException {
        Base base = null;
        MongoRepository repository = objectTypeRepositoryFactory.getRepositoryByObjectTypeId(objectTypeId);
        Optional<Base> op = repository.findById(objectId);

        if (op.isPresent()) {
            base = op.get();
        } else {
            throw new ObjectNotFoundException("Object with objectTypeId: " + objectTypeId
                    + " and objectId: " + objectId + " not found.");
        }

        System.out.println("********** EntityBuilderImpl, base: " + base);

        return base;
    }

    @Override
    public Base getObjectByChildOT(@NotNull String childObjectTypeId, @NotNull String objectId) throws ObjectNotFoundException {
        Optional<Base> parentOP = null;
        Base parent = null;
        ObjectType objectType = objectTypeRepository.findById(childObjectTypeId).get();
        if (objectType.getParentId() == null) {
            MongoRepository repository = objectTypeRepositoryFactory.getRepositoryByObjectTypeId(childObjectTypeId);
            parentOP = repository.findById(objectId);
        } else {
            ObjectType parentOT = objectTypeRepository.findById(objectType.getParentId()).get();
            MongoRepository parentRepo = objectTypeRepositoryFactory.getRepositoryByObjectTypeId(parentOT.getId());
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
        Base base = null;
        MongoRepository repository = objectTypeRepositoryFactory.getRepositoryByObjectTypeId(objectTypeId);
        Optional<Base> op = repository.findById(objectId);

        if (op.isPresent()) {
            base = op.get();
        } else {
            ObjectType objectType = objectTypeRepository.findById(objectTypeId).get();
            if (objectType.getParentId() != null) {
                ObjectType parentOT = objectTypeRepository.findById(objectType.getParentId()).get();
                MongoRepository parentRepo = objectTypeRepositoryFactory.getRepositoryByObjectTypeId(parentOT.getId());
                Optional<Base> parent = parentRepo.findById(objectId);
                if (parent.isPresent()) {
                    base = parent.get();
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
