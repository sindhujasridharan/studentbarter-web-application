package com.studentbarter.web.application.validator;

import com.studentbarter.web.application.model.*;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @SuppressWarnings("unused")
	@Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        
    }
}
