package com.study.springmvcgit.case03.entity;

import java.util.Arrays;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Exam {
	private String studentId; 	// �ǭ��s��
	private String examId;		// �ҸեN��
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")	// ��^������A
	@DateTimeFormat(pattern = "yyyy-MM-dd")					// �����q���Ӫ��������
	private Date examDate;		// �Ҹդ��
	
	private String[] examSlot;	// �Ҹծɬq
	private Boolean examPay;	// ú�O���p:true(�wú�O)�Bfalse(��ú�O)
	private String examNote;	// �Ƶ�
	
	
	
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getExamId() {
		return examId;
	}
	public void setExamId(String examId) {
		this.examId = examId;
	}
	public Date getExamDate() {
		return examDate;
	}
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	public String[] getExamSlot() {
		return examSlot;
	}
	public void setExamSlot(String[] examSlot) {
		this.examSlot = examSlot;
	}
	public Boolean getExamPay() {
		return examPay;
	}
	public void setExamPay(Boolean examPay) {
		this.examPay = examPay;
	}
	public String getExamNote() {
		return examNote;
	}
	public void setExamNote(String examNote) {
		this.examNote = examNote;
	}
	@Override
	public String toString() {
		return "Exam [studentId=" + studentId + ", examId=" + examId + ", examDate=" + examDate + ", examSlot="
				+ Arrays.toString(examSlot) + ", examPay=" + examPay + ", examNote=" + examNote + "]";
	}
	
	
	
	
}
