package com.nas.psyschedule.iam.interfaces.rest.transform;

import com.nas.psyschedule.iam.domain.model.aggregates.User;
import com.nas.psyschedule.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getUsername(), token,user.getRoles());
    }
}
