package com.ufril.medtran.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<ValidUsername, String> {

    private Pattern pattern;
    private Matcher matcher;
    private static final String USERNAME_PATTERN = "^[A-Za-z0-9]+$";

    
    
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return (valid(s));
    }

    private boolean valid(final String email) {
        pattern = Pattern.compile(USERNAME_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

	@Override
	public void initialize(ValidUsername arg0) {
		// TODO Auto-generated method stub
		
	}
}
