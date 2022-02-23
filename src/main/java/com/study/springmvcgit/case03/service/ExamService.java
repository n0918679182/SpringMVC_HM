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
	 *  CopyOnWriteArrayList �O����Ǧw�������X, �A�X�h����Ǿާ@
	 *  ArrayList �O�D����Ǧw�������X, �A�X��Ǿާ@
	 *  �]���w�]���p�U, Spring�|�NExamController�w�q��Singleton(��@����)
	 *  �ҥH�i�H�ϥ�CopyOnWriteArrayList�Ӱ����h����Ǹ�ƾާ@�����X��
	 */
	private List<Exam> exams = new CopyOnWriteArrayList<>();	// ���U�Ҹժ��������X
	private List<ExamSubject> examSubjects = new CopyOnWriteArrayList<>();	// �Ҹլ�ت����X
	private List<ExamSlot> examSlots = new CopyOnWriteArrayList<>();		// �Ҹծɬq�����X
	private List<ExamPayType> examPayTypes = new CopyOnWriteArrayList<>();	// ú�O���p�����X
	{
		examSubjects.add(new ExamSubject("808","JavaSE 8 OCP I"));
		examSubjects.add(new ExamSubject("809","JavaSE 8 OCP II"));
		examSubjects.add(new ExamSubject("819","JavaSE 11 OCP"));
		examSubjects.add(new ExamSubject("900","JavaEE 7 OCP"));
		
		examSlots.add(new ExamSlot("A", "(A)�W��  "));
		examSlots.add(new ExamSlot("B", "(B)�U��  "));
		examSlots.add(new ExamSlot("C", "(C)�ߤW  "));
		
		examPayTypes.add(new ExamPayType("true", "�wú"));
		examPayTypes.add(new ExamPayType("false", "��ú"));
	}
	
	// �d�ߩҦ� exam subject
	public List<ExamSubject> queryExamSubjectList() {
		return examSubjects;
	}
	
	// �d�ߩҦ� exam slot
	public List<ExamSlot> queryExamSlotList() {
		return examSlots;
	}
	
	// �d�ߩҦ� exam slot
	public List<ExamPayType> queryExamPayType() {
		return examPayTypes;
	}
	
	// �d�ߦh��
	public List<Exam> query() {
		return exams;
	}
	// �d�߳浧
	public Optional<Exam> get(int index) {
		//������index�Ѽƪ��d��, ���ˬd���X�O�_�����
		if(index < 0 || exams.size() == 0 || index >= exams.size())
			return Optional.ofNullable(null);
		return Optional.ofNullable(exams.get(index));
	}
	
	// �s�W ��bsynchronized�T�O��ƬO�@�P��
	public synchronized Boolean add(Exam exam) {
		// �Q�ηs�W�e��, ���X��size�ӧP�_���S���s�W���\
		int previousSize = exams.size();
		exams.add(exam);
		int nextSize = exams.size();
		return nextSize > previousSize;
	}
	// �ק�
	public synchronized Boolean update(int index,Exam exam) {
		//������index�Ѽƪ��d��, ���ˬd���X�O�_�����
		if(index < 0 || exams.size() == 0 || index >= exams.size())
			return false;
		exams.set(index, exam);
		return true;
	}
	// �R��
	public synchronized Boolean delete(int index) {
		//������index�Ѽƪ��d��, ���ˬd���X�O�_�����
		if(index < 0 || exams.size() == 0 || index >= exams.size())
			return false;
		exams.remove(index);
		return true;
	}
	
	// �ק� (�ק�Ƶ�)
	public synchronized Boolean updateExamNote(int index,String examNote) {
		if(index < 0 || exams.size() == 0 || index >= exams.size())
			return false;
		Exam exam = exams.get(index);	// �����ӵ�exam		
		exam.setExamNote(examNote);		// �]�w�ӵ���Note
		exams.set(index, exam);	// �i���i�L
		return true;
	}
	
	
	
	
}
