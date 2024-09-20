package com.ufril.medtran.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharValidator implements ConstraintValidator<ValidChars, String> {
	private Pattern pattern;
	private Matcher matcher;
	private static final String CHAR_PATTERN ="^[A-Za-z. ]+$";

	@Override
	public void initialize(ValidChars arg0) {
		// TODO Auto-generated method stub

	}

	private boolean validateChars(final String email) {
        pattern = Pattern.compile(CHAR_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
	@Override
	public boolean isValid(String s, ConstraintValidatorContext arg1) {
		 return (validateChars(s));
	}

}
