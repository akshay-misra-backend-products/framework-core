package com.gbss.framework.core.api.utils;

import com.gbss.framework.core.model.entities.Base;

import javax.ejb.ObjectNotFoundException;
import javax.validation.constraints.NotNull;

public interface EntityBuilder {

    /**
     * @param objectTypeId of entity to instantiate.
     * @param objectId of entity to instantiate.
     */
    Base getObjectById(@NotNull String objectTypeId,
                       @NotNull String objectId) throws ObjectNotFoundException;

    /**
     * @param childObjectTypeId of child entity to instantiate.
     * @param objectId object id of entity to instantiate.
     */
    Base getObjectByChildOT(@NotNull String childObjectTypeId,
                            @NotNull String objectId) throws ObjectNotFoundException;

    /**
     * @param objectTypeId of current or child entity to instantiate.
     * @param objectId object id of entity to instantiate.
     */
    Base getObjectByChildOrCurrentOT(@NotNull String objectTypeId,
                                     @NotNull String objectId) throws ObjectNotFoundException;
}
