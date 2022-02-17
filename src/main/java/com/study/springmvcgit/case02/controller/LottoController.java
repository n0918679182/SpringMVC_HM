package com.study.springmvcgit.case02.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.springmvcgit.case02.service.LottoService;

@Controller
@RequestMapping("/case02/lotto")
public class LottoController {
	
	@Autowired
	private LottoService lottoService;
	
	//lotto�D�e��
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("lottos", lottoService.getLottos());
		model.addAttribute("countAllBalls", lottoService.getCount());
		return "case02/show_lotto";
	}
	
	//�q���︹
	@RequestMapping("/add")
	public String add() {
		lottoService.addLotto();
		return "redirect:./";
	}
	
	//�ק����
	@RequestMapping("/update/{index}")
	public String update(@PathVariable("index") int index) {
		lottoService.updateLotto(index);
		return "redirect:../";
	}
	
	//�R������
	@RequestMapping("/delete/{index}")
	public String delete(@PathVariable("index") int index) {
		lottoService.deleteLotto(index);
		lottoService.countLottos(lottoService.getLottos());
		return "redirect:../";
	}
	
	//�έp���X
	@RequestMapping("/count")
	public String countNum(Model model) {
		lottoService.countLottos(lottoService.getLottos());
		return "redirect:./";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
