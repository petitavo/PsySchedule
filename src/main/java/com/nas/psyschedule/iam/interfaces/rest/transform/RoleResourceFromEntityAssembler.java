package com.nas.psyschedule.iam.interfaces.rest.transform;

import com.nas.psyschedule.iam.domain.model.entities.Role;
import com.nas.psyschedule.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}
