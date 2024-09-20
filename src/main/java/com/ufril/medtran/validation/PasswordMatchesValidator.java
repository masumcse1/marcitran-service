package com.ufril.medtran.validation;

import com.ufril.medtran.dto.account.ChangePasswordDTO;
import com.ufril.medtran.dto.account.CreateUserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by moin on 10/20/15.
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches passwordMatches) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (o instanceof CreateUserDTO) {
            final CreateUserDTO userData = (CreateUserDTO) o;
            return userData.getPassword().equals(userData.getMatchingPassword());
        } else if (o instanceof ChangePasswordDTO) {
            final ChangePasswordDTO userData = (ChangePasswordDTO) o;
            return userData.getPassword().equals(userData.getMatchingPassword());
        }
        return false;
    }
}
