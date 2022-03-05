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
	
	private List<Person> people = new CopyOnWriteArrayList<>();// person��ƪ��������X
	
	@GetMapping("/")
	public String index(@ModelAttribute Person person, Model model) {
		model.addAttribute("_method", "POST");
		model.addAttribute("people", people);
		return "case04/person";
	}
	
	@GetMapping("/{index}")
	public String get(@PathVariable("index") int index, Model model) {
		//@PathVariable("index") int index �|�۰ʱNindex�Ѽƶǻ���view
		model.addAttribute("_method", "PUT");
		model.addAttribute("people", people);
		model.addAttribute("person", people.get(index));
		return "case04/person";
	}
	
	@PostMapping("/")
	public String add(@Valid Person person, BindingResult result, Model model) {
		//�Ҧ����~��Ʒ|��bBindingResult
		if(result.hasErrors()) { //�p�G�����~
			model.addAttribute("_method", "POST");
			model.addAttribute("people", people);
			return "case04/person"; // �N��^������_�쥻���]�w
		}
		people.add(person);		// �Ϥ��Nperson��ƥ[��people���X���e�X
		return "redirect:./";
	}
	
	@PutMapping("/{index}")
	public String update(@PathVariable("index") int index, @Valid Person person, BindingResult result, Model model) {
		//�Ҧ����~��Ʒ|��bBindingResult
		if(result.hasErrors()) { //�p�G�����~
			model.addAttribute("_method", "PUT");
			model.addAttribute("people", people);
			return "case04/person"; // �N��^������_�쥻���]�w
		}
		people.set(index, person);	
		return "redirect:./";
	}
	
	
}





















