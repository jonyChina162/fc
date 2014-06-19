package cn.whu.zl.dao;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import cn.whu.zl.dao.impl.GenericJPADao;
import cn.whu.zl.entity.NcTable;
import cn.whu.zl.entity.StockTimePK;

@Repository("nctableDao")
public class NcTableDao extends GenericJPADao<NcTable, StockTimePK>{
	public List<NcTable> getByDate(Date d){
		List<NcTable> list = new ArrayList<NcTable>(2100);
		Query q = em.createQuery("select nc from NcTable nc where date = :date");
		q.setParameter("date", d);
		list = q.getResultList();
		
		return list;
	}
	
}
