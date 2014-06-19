package cn.whu.zl.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import cn.whu.zl.dao.impl.GenericJPADao;
import cn.whu.zl.entity.StockIndustry;

@Repository("StockIndustryDao")
public class StockIndustryDao extends GenericJPADao<StockIndustry, String> {
	@SuppressWarnings("unchecked")
	public List<String> getAllStockID(){
		Query q = em.createQuery("select stockID from StockIndustry");
		List<String> list =  q.getResultList();
		q = null;
		em.clear();
		return list;
	}
	
}