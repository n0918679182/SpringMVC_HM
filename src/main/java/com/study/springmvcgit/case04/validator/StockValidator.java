package com.study.springmvcgit.case04.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.study.springmvcgit.case04.entity.Stock;

import yahoofinance.YahooFinance;

@Component //沒有特別歸類的元件就使用Component
public class StockValidator implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		// 驗證是否為我的目標class
		return Stock.class.isAssignableFrom(clazz);
	}
	@Override
	public void validate(Object target, Errors errors) {
		Stock stock = (Stock) target;
		// 基礎驗證 這三個欄位不可以是空白
		ValidationUtils.rejectIfEmpty(errors, "symbol", "stock.symbol.empty");
		ValidationUtils.rejectIfEmpty(errors, "price", "stock.price.empty");
		ValidationUtils.rejectIfEmpty(errors, "amount", "stock.amount.empty");
		
		// 進階驗證 使用 YahooFinance 的 java API (可以抓到真實的一檔股票的相關資訊)
		yahoofinance.Stock yStock = null; //因為Stock這個名字我們已經使用了, 所以YahooFinance的Stock就必須要用全名
		try {
			yStock =YahooFinance.get(stock.getSymbol()); // 將輸入的股票代號傳給yahooFinance的雲端做搜尋
			double previousClose = yStock.getQuote().getPreviousClose().doubleValue(); // 昨日收盤價
			//要驗證的資料
			double price=stock.getPrice(); // 表單輸入的欲購買股價
			int amount=stock.getAmount(); // 表單輸入的欲購買股數
			// 1. 買進價格必須是昨日收盤價的±10%之間
			if( price < previousClose*0.9 || price > previousClose*1.1) {
				//errors.rejectValue("price", "stock.price.range");
				// 助教提供方法
				errors.rejectValue("price", "stock.price.range", 
						new Object[] {(previousClose*0.9), (previousClose*1.1)},
						"買進價格必須是昨日收盤價的±10%之間");
				//提示目前正確價格範圍
				double currentPrice = yStock.getQuote().getPrice().doubleValue();
				errors.reject("price_info", String.format("昨收: %.2f  最新成交價: %.2f", previousClose,currentPrice));
			}
			// 2. 買進數量必須 >= 1000
			if(amount < 1000) {
				errors.rejectValue("amount", "stock.amount.notenough");
			}
			// 3. 買進股數必須是1000的倍數(1000股 = 1張)
			if(amount % 1000 != 0) {
				errors.rejectValue("amount", "stock.amount.range");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(yStock == null) {
				// rejectValue的錯誤訊息設定可以支援 .properties
				errors.rejectValue("symbol", "stock.symbol.notfound");
			}
		}
	}
}
