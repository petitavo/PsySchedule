package com.example.backendvillasystem.iam.domain.model.commands;

import com.example.backendvillasystem.iam.domain.model.entities.Role;

import java.util.List;

/**
 * Sign up command
 * <p>
 *     This class represents the command to sign up a user.
 * </p>
 * @param username the username of the user
 * @param password the password of the user
 * @param roles the roles of the user
 *
 * @see com.example.backendvillasystem.iam.domain.model.aggregates.User
 */
public record SignUpCommand(String username, String password, List<Role> roles) {
}
