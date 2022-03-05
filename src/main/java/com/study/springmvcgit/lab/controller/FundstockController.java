package com.study.springmvcgit.lab.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

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

import com.study.springmvcgit.lab.Validator.FundstockValidator;
import com.study.springmvcgit.lab.entity.Fund;
import com.study.springmvcgit.lab.entity.Fundstock;
import com.study.springmvcgit.lab.repository.FundDao;
import com.study.springmvcgit.lab.repository.FundstockDao;

@Controller
@RequestMapping("/lab/fundstock")
public class FundstockController {
	@Autowired
	private FundstockDao fundstockDao;
	
	@Autowired
	private FundDao fundDao;
	
	@Autowired
	private FundstockValidator fundstockValidator;
	
	private int pageNumber =-1;
	
	@GetMapping("/")
	public String index(@ModelAttribute Fundstock fundstock, Model model){
		// 因為和page頁面幾乎一樣, 所以直接導過去page頁面並帶pageNumber(預設-1)參數即可
		return "redirect:./page/"+pageNumber;
	}
	
	@GetMapping("/page/{pageNumber}")
	public String page(@PathVariable("pageNumber") int pageNumber,@ModelAttribute Fundstock fundstock, Model model){
		this.pageNumber = pageNumber;
		int offset = (pageNumber-1) * FundstockDao.LIMIT;
		List<Fundstock> fundstocks= fundstockDao.queryPage(offset);
		List<Fund> funds = fundDao.queryAll();
		//int pageTotalCount=fundstocks.size() / FundstockDao.LIMIT;
		int pageTotalCount= fundstockDao.count() / FundstockDao.LIMIT;
		model.addAttribute("_method", "POST");
		model.addAttribute("fundstocks",fundstocks);
		model.addAttribute("funds",funds);
		model.addAttribute("pageTotalCount", pageTotalCount);
		model.addAttribute("groupMap", getGroupMap());
		return "lab/fundstock";
	}
	
	@GetMapping("/{sid}")
	public String get(@PathVariable("sid") Integer sid, Model model){
		Optional<Fundstock> fundstock= fundstockDao.get(sid);
		List<Fundstock> fundstocks= fundstockDao.queryAll();
		int pageTotalCount= fundstockDao.count() / FundstockDao.LIMIT;
		List<Fund> funds = fundDao.queryAll();
		model.addAttribute("_method", "PUT"); 
		model.addAttribute("fundstocks",fundstocks);
		model.addAttribute("funds",funds);
		model.addAttribute("pageTotalCount", pageTotalCount);
		model.addAttribute("groupMap", getGroupMap());
		model.addAttribute("fundstock", fundstock.get()); 
		return "lab/fundstock";
	}
	
	
	private Map<String, Integer> getGroupMap(){
		// 不透過 sql 語法, 而是直接用 java 8 的方式取得相同的結果
		List<Fundstock> fundstocks = fundstockDao.queryAll();
		return fundstocks.stream().collect(groupingBy(Fundstock::getSymbol, 
										   summingInt(Fundstock::getShare)));
	}
	
	
	@PostMapping("/")
	public String add(@Valid Fundstock fundstock, BindingResult result, Model model) {
		fundstockValidator.validate(fundstock, result);
		int offset = (pageNumber-1) * FundstockDao.LIMIT;
		List<Fundstock> fundstocks= fundstockDao.queryPage(offset);
		List<Fund> funds = fundDao.queryAll();
		int pageTotalCount= fundstockDao.count() / FundstockDao.LIMIT;
		if(result.hasErrors()) {
			model.addAttribute("_method", "POST");
			model.addAttribute("fundstocks",fundstocks);
			model.addAttribute("funds",funds);
			model.addAttribute("pageTotalCount", pageTotalCount);
			model.addAttribute("groupMap", getGroupMap());
			return "lab/fundstock";
		}
		
		fundstockDao.add(fundstock);
		return "redirect:./";
	}
	
	@PutMapping("/{sid}")
	public String update(@PathVariable("sid") Integer sid, @Valid  Fundstock fundstock, BindingResult result, Model model) {
		fundstockValidator.validate(fundstock, result);
		int offset = (pageNumber-1) * FundstockDao.LIMIT;
		List<Fundstock> fundstocks= fundstockDao.queryPage(offset);
		List<Fund> funds = fundDao.queryAll();
		int pageTotalCount= fundstockDao.count() / FundstockDao.LIMIT;
		if(result.hasErrors()) {
			model.addAttribute("_method", "PUT");
			model.addAttribute("fundstocks",fundstocks);
			model.addAttribute("funds",funds);
			model.addAttribute("pageTotalCount", pageTotalCount);
			model.addAttribute("groupMap", getGroupMap());
			return "lab/fundstock";
		}
		fundstockDao.update(fundstock);
		return "redirect:./";
	}
	
	@DeleteMapping("/{sid}")
	public String delete(@PathVariable("sid") int sid) {
		fundstockDao.delete(sid);
		return "redirect:./";
	}
	
	
	
}














