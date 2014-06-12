package org.pharmacy.domain;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

@Component
public class LetterValidator implements Validator{

	@Override
	public boolean supports(Class clazz) {
		return Letter.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Letter letter = (Letter)target;
		MultipartFile file = letter.getFile();
		
		//not empty
		/*
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "number", "validation.number_empty", "*");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "validation.date_empty", "*");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subject", "validation.subject_empty", "*");
		*/
		if ( file == null || file.isEmpty() ){
			errors.rejectValue("file", "validation.file_empty");	
		}
		
		//size
		/*
		String number = letter.getNumber();
		if(number != null && ( number.length() < 1 && number.length() > 20)){
			errors.rejectValue("number", "validation.number_size");
		}
		*/
	}
}