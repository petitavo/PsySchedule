package com.nas.psyschedule.iam.interfaces.rest.transform;

import com.nas.psyschedule.iam.domain.model.commands.SignInCommand;
import com.nas.psyschedule.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}
