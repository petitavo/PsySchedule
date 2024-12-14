package com.nas.psyschedule.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(Long id, String username, String token,
                                        java.util.Set<com.nas.psyschedule.iam.domain.model.entities.Role> roles) {

}
