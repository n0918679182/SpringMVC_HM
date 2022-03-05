package com.study.springmvcgit.lab.Validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.study.springmvcgit.lab.entity.Fundstock;

import yahoofinance.YahooFinance;

@Component
public class FundstockValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Fundstock.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Fundstock fundstock = (Fundstock) target;
		ValidationUtils.rejectIfEmpty(errors, "sid", "fundstock.sid.empty");
		ValidationUtils.rejectIfEmpty(errors, "symbol", "fundstock.symbol.empty");
		ValidationUtils.rejectIfEmpty(errors, "share", "fundstock.share.empty");
		ValidationUtils.rejectIfEmpty(errors, "fid", "fundstock.fid.empty");
		yahoofinance.Stock yStock = null;
		try {
			yStock =YahooFinance.get(fundstock.getSymbol());
			int amount = fundstock.getShare();
			if(amount < 1000) {
				errors.rejectValue("share", "fundstock.share.notenough");
			}
			if(amount % 1000 != 0) {
				errors.rejectValue("share", "fundstock.share.range");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(yStock == null) {
				errors.rejectValue("symbol", "fundstock.symbol.notfound");
			}
		}
		
	}

}
