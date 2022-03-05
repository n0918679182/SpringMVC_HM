package com.study.springmvcgit.lab.repository;

import java.util.List;
import java.util.Optional;

import com.study.springmvcgit.lab.entity.Fundstock;

public interface FundstockDao {
	// –5掸
	int LIMIT = 5;
	// 场琩高
	List<Fundstock> queryAll();
	// だ穨琩高
	List<Fundstock> queryPage(int offset);
	// 眔虫掸
	Optional<Fundstock> get(Integer sid);
	// 穝糤
	int add(Fundstock fundstock);
	// э
	int update(Fundstock fundstock);
	// 埃
	int delete(Integer sid);
	// 琩高┮Τ掸计
	int count();
	
	
}
