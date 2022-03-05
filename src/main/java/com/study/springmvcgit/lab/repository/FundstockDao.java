package com.study.springmvcgit.lab.repository;

import java.util.List;
import java.util.Optional;

import com.study.springmvcgit.lab.entity.Fundstock;

public interface FundstockDao {
	// �C��5��
	int LIMIT = 5;
	// �����d��
	List<Fundstock> queryAll();
	// ���~�d��
	List<Fundstock> queryPage(int offset);
	// ���o�浧
	Optional<Fundstock> get(Integer sid);
	// �s�W
	int add(Fundstock fundstock);
	// �ק�
	int update(Fundstock fundstock);
	// �R��
	int delete(Integer sid);
	// �d�ߩҦ�����
	int count();
	
	
}
