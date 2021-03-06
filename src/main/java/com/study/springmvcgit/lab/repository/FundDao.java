package com.study.springmvcgit.lab.repository;

import java.util.List;
import java.util.Optional;

import com.study.springmvcgit.lab.entity.Fund;

public interface FundDao {
		// 每頁5筆
		int LIMIT = 5;
		// 全部查詢
		List<Fund> queryAll();
		// 分業查詢
		List<Fund> queryPage(int offset);
		// 取得單筆
		Optional<Fund> get(Integer fid);
		// 新增
		int add(Fund fund);
		// 修改
		int update(Fund fund);
		// 刪除
		int delete(Integer fid);	
		
		int count();
}
