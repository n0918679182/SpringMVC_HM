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
		Optional<Exam> optExam = examService.get(index);		// ���n�����
		if(optExam.isPresent()) {								// �p�G����ƪ���
			model.addAttribute("_method", "PUT"); 				// ����Ƥ���]���᭱�n�h�ק復, �ҥH�n�令PUT
			model.addAttribute("exams", examService.query());	// �����\�U�h
			model.addAttribute("examSubjects", examService.queryExamSubjectList());
			model.addAttribute("examSlots", examService.queryExamSlotList());
			model.addAttribute("examPayTypes", examService.queryExamPayType());
			model.addAttribute("exam", optExam.get());			// �n���쪺exam��ƥ�U�h
			return "case03/exam";								// �̫�^��case03/exam
		}
		return "redirect:./";									// �Y�L���, �h�^��W�@�h��index
	}															// ���`���ӥ��Τ@���~�B�z����
	
	@PostMapping("/")
	public String add(Exam exam) { 	// ���[@ModelAttribute �Y�̫�^�ǬO"case03/exam"�~�n�[
		examService.add(exam);		// @ModelAttribute�̥D�n���ηN�O�NExam���ݩʷ��OModel���ݩ�, 
		return "redirect:./";		// �M��ǵ�JSP, ���o��O���ɨ쭺��, �ڥ����|�⪫��ǹL�h, �ҥH�i�g�i���g
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
	
	// �ק�Ƶ�
	@PutMapping("/{index}/exam_note")	// �]���O�ק�ҥH�٬O�n��put
	public String updateExamNote(@PathVariable("index") int index, Exam exam) {
		examService.updateExamNote(index, exam.getExamNote());
		return "redirect:../";
	}
	
	
	
	
}














