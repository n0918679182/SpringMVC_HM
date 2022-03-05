package com.study.springmvcgit.case04.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.study.springmvcgit.case04.entity.Stock;

import yahoofinance.YahooFinance;

@Component //�S���S�O�k��������N�ϥ�Component
public class StockValidator implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		// ���ҬO�_���ڪ��ؼ�class
		return Stock.class.isAssignableFrom(clazz);
	}
	@Override
	public void validate(Object target, Errors errors) {
		Stock stock = (Stock) target;
		// ��¦���� �o�T����줣�i�H�O�ť�
		ValidationUtils.rejectIfEmpty(errors, "symbol", "stock.symbol.empty");
		ValidationUtils.rejectIfEmpty(errors, "price", "stock.price.empty");
		ValidationUtils.rejectIfEmpty(errors, "amount", "stock.amount.empty");
		
		// �i������ �ϥ� YahooFinance �� java API (�i�H���u�ꪺ�@�ɪѲ���������T)
		yahoofinance.Stock yStock = null; //�]��Stock�o�ӦW�r�ڭ̤w�g�ϥΤF, �ҥHYahooFinance��Stock�N�����n�Υ��W
		try {
			yStock =YahooFinance.get(stock.getSymbol()); // �N��J���Ѳ��N���ǵ�yahooFinance�����ݰ��j�M
			double previousClose = yStock.getQuote().getPreviousClose().doubleValue(); // �Q�馬�L��
			//�n���Ҫ����
			double price=stock.getPrice(); // ����J�����ʶR�ѻ�
			int amount=stock.getAmount(); // ����J�����ʶR�Ѽ�
			// 1. �R�i���楲���O�Q�馬�L������10%����
			if( price < previousClose*0.9 || price > previousClose*1.1) {
				//errors.rejectValue("price", "stock.price.range");
				// �U�д��Ѥ�k
				errors.rejectValue("price", "stock.price.range", 
						new Object[] {(previousClose*0.9), (previousClose*1.1)},
						"�R�i���楲���O�Q�馬�L������10%����");
				//���ܥثe���T����d��
				double currentPrice = yStock.getQuote().getPrice().doubleValue();
				errors.reject("price_info", String.format("�Q��: %.2f  �̷s�����: %.2f", previousClose,currentPrice));
			}
			// 2. �R�i�ƶq���� >= 1000
			if(amount < 1000) {
				errors.rejectValue("amount", "stock.amount.notenough");
			}
			// 3. �R�i�Ѽƥ����O1000������(1000�� = 1�i)
			if(amount % 1000 != 0) {
				errors.rejectValue("amount", "stock.amount.range");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(yStock == null) {
				// rejectValue�����~�T���]�w�i�H�䴩 .properties
				errors.rejectValue("symbol", "stock.symbol.notfound");
			}
		}
	}
}
