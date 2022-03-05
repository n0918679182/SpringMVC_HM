package com.study.springmvcgit.case04.entity;

public class Stock {
	private String symbol; // 布腹布腹ゲ斗布腹璶Τ秈︽ユ
	private Double price; // 禦秈基禦秈基ゲ斗琌琎らΜ絃基∮10%ぇ丁
	private Integer amount; // 禦秈计禦秈计ゲ斗琌1000计(1000 = 1眎)
	
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	
	@Override
	public String toString() {
		return "Stock [symbol=" + symbol + ", price=" + price + ", amount=" + amount + "]";
	}
	
	
	
}
