package com.study.springmvcgit.lab.repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.study.springmvcgit.lab.entity.Fund;
import com.study.springmvcgit.lab.entity.Fundstock;

@Repository
public class FundDaoImpl implements FundDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Fund> queryAll() {
		return queryAllCase3();
	}
	
	private List<Fund> queryAllCase1(){
		String sql = "select f.fid, f.fname, f.createtime from fund f order by f.fid";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Fund>(Fund.class));
	}
	
	private List<Fund> queryAllCase2(){
		String sql = "select f.fid, f.fname, f.createtime from fund f order by f.fid";
		// 依照資料表的欄位去做填寫
		RowMapper<Fund> rm = (ResultSet rs, int rowNum)->{
			Fund fund = new Fund();
			fund.setFid(rs.getInt("fid"));
			fund.setFname(rs.getString("fname"));
			fund.setCreatetime(rs.getDate("createtime"));
			// 根據fid查詢fundstocks列表
			String sql2 = "select s.sid, s.fid, s.symbol, s.share "
					+ "from fundstock s "
					+ "where s.fid=? "
					+ "order by s.sid";
			Object[] args = {fund.getFid()};
			List<Fundstock> fundstocks = jdbcTemplate.query(sql2, args, 
					new BeanPropertyRowMapper<Fundstock>(Fundstock.class));
			fund.setFundstocks(fundstocks);
			return fund;
		};
		return jdbcTemplate.query(sql, rm);
	}
	
	// Simple Flat Mapping 這種查詢比較好, 只需要下一段sql就可以把相關的資料查詢出來
	// 調用 org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory
	private List<Fund> queryAllCase3(){
		String sql="select f.fid, f.fname, f.createtime , "
				+ "s.sid as fundstocks_sid ,s.fid as fundstocks_fid ,"
				+ "s.symbol as fundstocks_symbol ,s.share as fundstocks_share "
				+ "from fund f left join fundstock s "
				+ "on f.fid = s.fid order by f.fid;";
		ResultSetExtractor<List<Fund>> resultSetExtractor = 
				JdbcTemplateMapperFactory.newInstance()
				.addKeys("fid") // Fund的主鍵
				.newResultSetExtractor(Fund.class);
		return jdbcTemplate.query(sql, resultSetExtractor);
	}

	@Override
	public List<Fund> queryPage(int offset) {
		if(offset < 0) { // 判斷起始筆數若小於0 則直接呼叫queryAll()
			return queryAll();
		}
		String sql="select f.fid, f.fname, f.createtime from fund f order by f.fid";
		sql += String.format(" limit %d offset %d", FundDao.LIMIT, offset);
		RowMapper<Fund> rm =(ResultSet rs, int rowNum)->{
			Fund fund = new Fund();
			fund.setFid(rs.getInt("fid"));
			fund.setFname(rs.getString("fname"));
			fund.setCreatetime(rs.getDate("createtime"));
			String sql2 = "select s.sid, s.fid, s.symbol, s.share "
					+ "from fundstock s where s.fid=? order by s.sid";
			Object[] args = {fund.getFid()};
			List<Fundstock> fundstocks = jdbcTemplate.query(sql2, args, 
					new BeanPropertyRowMapper<Fundstock>(Fundstock.class));
			fund.setFundstocks(fundstocks);
			return fund;
		};
		return jdbcTemplate.query(sql, rm);
	}

	@Override
	public Optional<Fund> get(Integer fid) {
		String sql = "select f.fid, f.fname, f.createtime from fund f where f.fid=?";
		Fund fund = jdbcTemplate.queryForObject(sql, new Object[] {fid}, new BeanPropertyRowMapper<Fund>(Fund.class));
		sql = "select s.sid, s.fid, s.symbol, s.share from fundstock s where s.fid=?";
		List<Fundstock> fundstocks = jdbcTemplate.query(sql, new Object[] {fund.getFid()},
				new BeanPropertyRowMapper<Fundstock>(Fundstock.class));
		fund.setFundstocks(fundstocks);
		return Optional.ofNullable(fund);
	}

	@Override
	public int add(Fund fund) {
		String sql = "insert into fund(fname) values(?)";
		return jdbcTemplate.update(sql, fund.getFname());
	}

	@Override
	public int update(Fund fund) {
		String sql = "update fund set fname =? where fid=?";
		return jdbcTemplate.update(sql, fund.getFname(), fund.getFid());
	}

	@Override
	public int delete(Integer fid) {
		String sql="delete from fund where fid=?";
		return jdbcTemplate.update(sql, fid);
	}

	@Override
	public int count() {
		String sql = "select count(*) from fund";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

}
