package com.study.springmvcgit.lab.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.springmvcgit.lab.Validator.FundValidator;
import com.study.springmvcgit.lab.entity.Fund;
import com.study.springmvcgit.lab.entity.Fundstock;
import com.study.springmvcgit.lab.repository.FundDao;
import com.study.springmvcgit.lab.repository.FundstockDao;

@Controller
@RequestMapping("/lab/fund")
public class FundController {
	@Autowired
	private FundDao fundDao;
	
	@Autowired
	private FundValidator fundValidator;
	
	private int pageNumber =-1;
	
	@GetMapping("/")
	public String index(){
		return "redirect:./page/"+pageNumber;
	}
	
	@GetMapping("/page/{pageNumber}")
	public String page(@PathVariable("pageNumber") int pageNumber,@ModelAttribute Fund fund, Model model){
		this.pageNumber = pageNumber;
		int offset = (pageNumber-1) * FundDao.LIMIT;
		List<Fund> funds = fundDao.queryPage(offset);
		int pageTotalCount= fundDao.count() / FundDao.LIMIT;
		model.addAttribute("_method", "POST");
		model.addAttribute("funds",funds);
		model.addAttribute("pageTotalCount", pageTotalCount);
		return "lab/fund";
	}
	
	@GetMapping("/{fid}")
	public String get(@PathVariable("fid") Integer fid, Model model){
		Optional<Fund> fund= fundDao.get(fid);
		int pageTotalCount= fundDao.count() / FundDao.LIMIT;
		List<Fund> funds = fundDao.queryAll();
		model.addAttribute("_method", "PUT"); 
		model.addAttribute("funds",funds);
		model.addAttribute("pageTotalCount", pageTotalCount);
		model.addAttribute("fund", fund.get()); 
		return "lab/fund";
	}
	
	@PostMapping("/")
	public String add(@Valid Fund fund, BindingResult result, Model model) {
		fundValidator.validate(fund, result);
		List<Fund> funds = fundDao.queryAll();
		int pageTotalCount= fundDao.count() / FundDao.LIMIT;
		if(result.hasErrors()) {
			model.addAttribute("funds",funds);
			model.addAttribute("_method", "POST");
			model.addAttribute("pageTotalCount", pageTotalCount);
			return "lab/fund";
		}
		fundDao.add(fund);
		return "redirect:./";
	}
	
	@PutMapping("/{fid}")
	public String update(@PathVariable("fid") Integer fid, Fund fund) {
		fundDao.update(fund);
		return "redirect:./";
	}
	
	@DeleteMapping("/{fid}")
	public String delete(@PathVariable("fid") int fid) {
		fundDao.delete(fid);
		return "redirect:./";
	}
	
}
