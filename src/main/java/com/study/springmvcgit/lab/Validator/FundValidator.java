package com.study.springmvcgit.lab.Validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.study.springmvcgit.lab.entity.Fund;

@Component
public class FundValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Fund.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Fund fund = (Fund) target;
		ValidationUtils.rejectIfEmpty(errors, "fname", "fund.fname.empty");
		
	}

}
