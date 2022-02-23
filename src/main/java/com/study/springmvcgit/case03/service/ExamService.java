package com.study.springmvcgit.case03.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.study.springmvcgit.case03.entity.Exam;
import com.study.springmvcgit.case03.entity.ExamPayType;
import com.study.springmvcgit.case03.entity.ExamSlot;
import com.study.springmvcgit.case03.entity.ExamSubject;

@Service
public class ExamService {
	/*
	 *  CopyOnWriteArrayList 是執行序安全的集合, 適合多執行序操作
	 *  ArrayList 是非執行序安全的集合, 適合單序操作
	 *  因為預設情況下, Spring會將ExamController定義為Singleton(單一實體)
	 *  所以可以使用CopyOnWriteArrayList來做為多執行序資料操作的集合類
	 */
	private List<Exam> exams = new CopyOnWriteArrayList<>();	// 註冊考試的紀錄集合
	private List<ExamSubject> examSubjects = new CopyOnWriteArrayList<>();	// 考試科目的集合
	private List<ExamSlot> examSlots = new CopyOnWriteArrayList<>();		// 考試時段的集合
	private List<ExamPayType> examPayTypes = new CopyOnWriteArrayList<>();	// 繳費狀況的集合
	{
		examSubjects.add(new ExamSubject("808","JavaSE 8 OCP I"));
		examSubjects.add(new ExamSubject("809","JavaSE 8 OCP II"));
		examSubjects.add(new ExamSubject("819","JavaSE 11 OCP"));
		examSubjects.add(new ExamSubject("900","JavaEE 7 OCP"));
		
		examSlots.add(new ExamSlot("A", "(A)上午  "));
		examSlots.add(new ExamSlot("B", "(B)下午  "));
		examSlots.add(new ExamSlot("C", "(C)晚上  "));
		
		examPayTypes.add(new ExamPayType("true", "已繳"));
		examPayTypes.add(new ExamPayType("false", "未繳"));
	}
	
	// 查詢所有 exam subject
	public List<ExamSubject> queryExamSubjectList() {
		return examSubjects;
	}
	
	// 查詢所有 exam slot
	public List<ExamSlot> queryExamSlotList() {
		return examSlots;
	}
	
	// 查詢所有 exam slot
	public List<ExamPayType> queryExamPayType() {
		return examPayTypes;
	}
	
	// 查詢多筆
	public List<Exam> query() {
		return exams;
	}
	// 查詢單筆
	public Optional<Exam> get(int index) {
		//先限制index參數的範圍, 及檢查集合是否有資料
		if(index < 0 || exams.size() == 0 || index >= exams.size())
			return Optional.ofNullable(null);
		return Optional.ofNullable(exams.get(index));
	}
	
	// 新增 放在synchronized確保資料是一致的
	public synchronized Boolean add(Exam exam) {
		// 利用新增前後, 集合的size來判斷有沒有新增成功
		int previousSize = exams.size();
		exams.add(exam);
		int nextSize = exams.size();
		return nextSize > previousSize;
	}
	// 修改
	public synchronized Boolean update(int index,Exam exam) {
		//先限制index參數的範圍, 及檢查集合是否有資料
		if(index < 0 || exams.size() == 0 || index >= exams.size())
			return false;
		exams.set(index, exam);
		return true;
	}
	// 刪除
	public synchronized Boolean delete(int index) {
		//先限制index參數的範圍, 及檢查集合是否有資料
		if(index < 0 || exams.size() == 0 || index >= exams.size())
			return false;
		exams.remove(index);
		return true;
	}
	
	// 修改 (修改備註)
	public synchronized Boolean updateExamNote(int index,String examNote) {
		if(index < 0 || exams.size() == 0 || index >= exams.size())
			return false;
		Exam exam = exams.get(index);	// 先抓到該筆exam		
		exam.setExamNote(examNote);		// 設定該筆的Note
		exams.set(index, exam);	// 可有可無
		return true;
	}
	
	
	
	
}
