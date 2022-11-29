package org.procode.management.config;

import org.procode.management.model.UserEntity;
import org.procode.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserEntity user = (UserEntity) target;

        if (userService.findByUsername(user.getUsername()) != null && user.getId() == 0) {
            errors.rejectValue("username", "Duplicate.userForm.username",
                    "Such username already exists.");
        }

        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirmation", "Different.userForm.password",
                    "Password don't match.");
        }
    }
}
