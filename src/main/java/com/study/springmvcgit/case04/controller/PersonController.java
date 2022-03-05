package com.study.springmvcgit.case04.controller;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.springmvcgit.case04.entity.Person;

@Controller
@RequestMapping("/case04/person")
public class PersonController {
	
	private List<Person> people = new CopyOnWriteArrayList<>();// person資料的紀錄集合
	
	@GetMapping("/")
	public String index(@ModelAttribute Person person, Model model) {
		model.addAttribute("_method", "POST");
		model.addAttribute("people", people);
		return "case04/person";
	}
	
	@GetMapping("/{index}")
	public String get(@PathVariable("index") int index, Model model) {
		//@PathVariable("index") int index 會自動將index參數傳遞給view
		model.addAttribute("_method", "PUT");
		model.addAttribute("people", people);
		model.addAttribute("person", people.get(index));
		return "case04/person";
	}
	
	@PostMapping("/")
	public String add(@Valid Person person, BindingResult result, Model model) {
		//所有錯誤資料會放在BindingResult
		if(result.hasErrors()) { //如果有錯誤
			model.addAttribute("_method", "POST");
			model.addAttribute("people", people);
			return "case04/person"; // 就返回首頁恢復原本的設定
		}
		people.add(person);		// 反之將person資料加到people集合內送出
		return "redirect:./";
	}
	
	@PutMapping("/{index}")
	public String update(@PathVariable("index") int index, @Valid Person person, BindingResult result, Model model) {
		//所有錯誤資料會放在BindingResult
		if(result.hasErrors()) { //如果有錯誤
			model.addAttribute("_method", "PUT");
			model.addAttribute("people", people);
			return "case04/person"; // 就返回首頁恢復原本的設定
		}
		people.set(index, person);	
		return "redirect:./";
	}
	
	
}





















