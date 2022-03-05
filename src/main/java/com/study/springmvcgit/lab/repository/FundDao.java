package com.study.springmvcgit.lab.repository;

import java.util.List;
import java.util.Optional;

import com.study.springmvcgit.lab.entity.Fund;

public interface FundDao {
		// �C��5��
		int LIMIT = 5;
		// �����d��
		List<Fund> queryAll();
		// ���~�d��
		List<Fund> queryPage(int offset);
		// ���o�浧
		Optional<Fund> get(Integer fid);
		// �s�W
		int add(Fund fund);
		// �ק�
		int update(Fund fund);
		// �R��
		int delete(Integer fid);	
		
		int count();
}
