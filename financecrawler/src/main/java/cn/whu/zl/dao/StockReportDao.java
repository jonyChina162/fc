package cn.whu.zl.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import cn.whu.zl.dao.impl.GenericJPADao;
import cn.whu.zl.entity.StockDataTitlePK;
import cn.whu.zl.entity.StockReport;

@Repository("stockReportDao")
public class StockReportDao extends
		GenericJPADao<StockReport, StockDataTitlePK> {
	public void save1(StockReport sp) {
		List<StockReport> list = em.createQuery("select s from StockReport s",
				StockReport.class).getResultList();
		if (em.contains(sp) || list.contains(sp)) {
			em.merge(sp);
		} else {
			em.persist(sp);
		}
		em.clear();
	}
	
}
