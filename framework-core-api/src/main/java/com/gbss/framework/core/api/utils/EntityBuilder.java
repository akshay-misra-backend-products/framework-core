package com.gbss.framework.core.api.utils;

import com.gbss.framework.core.model.entities.Base;

import javax.ejb.ObjectNotFoundException;

public interface EntityBuilder {

    /**
     * It return base only params like, name, objectTypeId, parentId etc.
     * Useful for setting references to object or creating reference links for client side views.
     * @param objectTypeId of entity to instantiate.
     * @param objectId of entity to instantiate.
     */
    Base getLiteObjectById(String objectTypeId, String objectId) throws ObjectNotFoundException;

    /**
     * @param objectTypeId of entity to instantiate.
     * @param objectId of entity to instantiate.
     */
    Base getObjectById(String objectTypeId,
                       String objectId) throws ObjectNotFoundException;

    /**
     * @param childObjectTypeId of child entity to instantiate.
     * @param objectId object id of entity to instantiate.
     */
    Base getObjectByChildOT(String childObjectTypeId,
                            String objectId) throws ObjectNotFoundException;

    /**
     * @param objectTypeId of current or child entity to instantiate.
     * @param objectId object id of entity to instantiate.
     */
    Base getObjectByChildOrCurrentOT(String objectTypeId,
                                     String objectId) throws ObjectNotFoundException;
}
