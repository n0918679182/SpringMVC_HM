package com.study.springmvcgit.case04.controller;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.springmvcgit.case04.entity.Stock;
import com.study.springmvcgit.case04.validator.StockValidator;

@Controller
@RequestMapping("/case04/stock")
public class StockController {
	private List<Stock> stocks = new CopyOnWriteArrayList<>();
	
	@Autowired
	private StockValidator stockValidator;
	
	@GetMapping("/")
	public String index(@ModelAttribute Stock stock, Model model) {
		model.addAttribute("stocks", stocks);
		return "case04/stock";
	}
	
	@PostMapping("/")
	public String add(@Valid Stock stock, BindingResult result, Model model) {
		// 自主驗證錯誤 會把錯誤訊息都放在result裡面
		stockValidator.validate(stock, result);
		if(result.hasErrors()) {
			// 若有錯誤就重跑index
			model.addAttribute("stocks", stocks);
			return "case04/stock";
		}
		stocks.add(stock);
		return "redirect:./";
	}
	
	
}















