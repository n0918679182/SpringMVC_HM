package com.study.springmvcgit.case03.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.springmvcgit.case03.entity.Exam;
import com.study.springmvcgit.case03.service.ExamService;

@Controller
@RequestMapping("/case03/exam")
public class ExamController {
	
	@Autowired
	private ExamService examService;
	
	@GetMapping("/")
	public String index(@ModelAttribute Exam exam, Model model) {
		model.addAttribute("_method" , "POST");
		model.addAttribute("exams", examService.query());
		model.addAttribute("examSubjects", examService.queryExamSubjectList());
		model.addAttribute("examSlots", examService.queryExamSlotList());
		model.addAttribute("examPayTypes", examService.queryExamPayType());
		return "case03/exam";
	}
	
	@GetMapping("/{index}")
	public String get(@PathVariable("index") int index, Model model) {
		Optional<Exam> optExam = examService.get(index);		// 先要找到資料
		if(optExam.isPresent()) {								// 如果有資料的話
			model.addAttribute("_method", "PUT"); 				// 找到資料之後因為後面要去修改它, 所以要改成PUT
			model.addAttribute("exams", examService.query());	// 把資料擺下去
			model.addAttribute("examSubjects", examService.queryExamSubjectList());
			model.addAttribute("examSlots", examService.queryExamSlotList());
			model.addAttribute("examPayTypes", examService.queryExamPayType());
			model.addAttribute("exam", optExam.get());			// 要把找到的exam資料丟下去
			return "case03/exam";								// 最後回到case03/exam
		}
		return "redirect:./";									// 若無資料, 則回到上一層的index
	}															// 正常應該丟到統一錯誤處理頁面
	
	@PostMapping("/")
	public String add(Exam exam) { 	// 不加@ModelAttribute 若最後回傳是"case03/exam"才要加
		examService.add(exam);		// @ModelAttribute最主要的用意是將Exam的屬性當成是Model的屬性, 
		return "redirect:./";		// 然後傳給JSP, 但這邊是重導到首頁, 根本不會把物件傳過去, 所以可寫可不寫
	}
	
	@PutMapping("/{index}")
	public String update(@PathVariable("index") int index, Exam exam) {
		examService.update(index, exam);
		return "redirect:./";
	}
	
	@DeleteMapping("/{index}")
	public String delete(@PathVariable("index") int index) {
		examService.delete(index);
		return "redirect:./";
	}
	
	// 修改備註
	@PutMapping("/{index}/exam_note")	// 因為是修改所以還是要用put
	public String updateExamNote(@PathVariable("index") int index, Exam exam) {
		examService.updateExamNote(index, exam.getExamNote());
		return "redirect:../";
	}
	
	
	
	
}














