package com.study.springmvcgit.lab.repository;

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
public class FundstockDaoImpl implements FundstockDao{
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Fundstock> queryAll() {
		String sql="select s.sid, s.fid, s.symbol, s.share, "
				+ "f.fid as fund_fid, f.fname as fund_fname, f.createtime as fund_createtime "
				+ "from fundstock s left join fund f "
				+ "on f.fid = s.fid order by s.sid";
		ResultSetExtractor<List<Fundstock>> resultSetExtractor = 
				JdbcTemplateMapperFactory.newInstance()
				.addKeys("sid") // Fund���D��
				.newResultSetExtractor(Fundstock.class);
		return jdbcTemplate.query(sql, resultSetExtractor);
	}

	@Override
	public List<Fundstock> queryPage(int offset) {
		if(offset < 0) { // �P�_�_�l���ƭY�p��0 �h�����I�squeryAll()
			return queryAll();
		}
		String sql="select s.sid, s.fid, s.symbol, s.share, "
				+ "f.fid as fund_fid, f.fname as fund_fname, f.createtime as fund_createtime "
				+ "from fundstock s left join fund f "
				+ "on f.fid = s.fid order by s.sid";
		sql += String.format(" limit %d offset %d ", FundstockDao.LIMIT, offset);
		ResultSetExtractor<List<Fundstock>> resultSetExtractor = 
				JdbcTemplateMapperFactory.newInstance()
				.addKeys("sid") // Fund���D��
				.newResultSetExtractor(Fundstock.class);
		return jdbcTemplate.query(sql, resultSetExtractor);
	}

	@Override
	public Optional<Fundstock> get(Integer sid) {
		// �����fundstock
		String sql="select s.sid, s.fid, s.symbol, s.share from fundstock s where s.sid = ?";
		Fundstock fundstock = jdbcTemplate.queryForObject(sql,
				new BeanPropertyRowMapper<Fundstock>(Fundstock.class), sid);
		// �A�z�L fundstock.getFid() ��� fund
		sql = "select f.fid, f.fname, f.createtime from fund f where f.fid =?";
		Fund fund = jdbcTemplate.queryForObject(sql, 
				new BeanPropertyRowMapper<Fund>(Fund.class), fundstock.getFid());
		// �`�J fund
		fundstock.setFund(fund);
		return Optional.ofNullable(fundstock);
	}

	@Override
	public int add(Fundstock fundstock) {
		String sql = "insert into fundstock(fid, symbol, share) values(?,?,?)";
		int rowcount = jdbcTemplate.update(sql, fundstock.getFid(), fundstock.getSymbol(), fundstock.getShare());
		return rowcount;
	}

	@Override
	public int update(Fundstock fundstock) {
		String sql = "update fundstock set fid =?, symbol =?, share =? where sid=?";
		int rowcount = jdbcTemplate.update(sql, fundstock.getFid(), fundstock.getSymbol(), 
												fundstock.getShare(), fundstock.getSid());
		return rowcount;
	}

	@Override
	public int delete(Integer sid) {
		String sql = "delete from fundstock where sid=?";
		int rowcount = jdbcTemplate.update(sql, sid);
		return rowcount;
	}

	@Override
	public int count() {
		String sql = "select count(*) from fundstock";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

}
