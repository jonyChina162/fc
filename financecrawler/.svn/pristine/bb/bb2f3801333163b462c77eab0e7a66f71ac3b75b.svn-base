package cn.whu.zl.dao;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import cn.whu.zl.dao.impl.GenericJPADao;
import cn.whu.zl.entity.JrjStockReport;
import cn.whu.zl.entity.StockDataTitlePK;

@Repository("jrjStockReportDao")
public class JrjStockReportDao extends
		GenericJPADao<JrjStockReport, StockDataTitlePK> {
	@SuppressWarnings("unchecked")
	public List<JrjStockReport> getOrgReports(String insID,Date begin,Date end){
		Query q = em.createQuery("select jrj from JrjStockReport jrj where orgID = :insID and date between :begin and :end",JrjStockReport.class);
		q.setParameter("insID", insID);
		q.setParameter("begin", begin);
		q.setParameter("end", end);
		List<JrjStockReport> list = q.getResultList();
		q=null;
		em.clear();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<JrjStockReport> getStockReport(String stockID,Date begin,Date end){
		Query q = em.createQuery("select jrj from JrjStockReport jrj where stockID = :stockID and date between :begin and :end",JrjStockReport.class);
		q.setParameter("stockID", stockID);
		q.setParameter("begin", begin);
		q.setParameter("end", end);
		List<JrjStockReport> list = q.getResultList();
		q=null;
		em.clear();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<JrjStockReport> getOrgReportsE0(String insID,Date begin,Date end){
		Query q = em.createQuery("select jrj from JrjStockReport jrj where orgID = :insID and date between :begin and :end and tarPrice <> 0.0f",JrjStockReport.class);
		q.setParameter("insID", insID);
		q.setParameter("begin", begin);
		q.setParameter("end", end);
		List<JrjStockReport> list = q.getResultList();
		q=null;
		em.clear();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public Timestamp getReportDate(String stockID,Date date,String linkURL){
		Query q = em.createQuery("select reportDate from JrjStockReport where stockID = :stockID and date =:date and linkURL =:linkURL");
		q.setParameter("stockID", stockID);
		q.setParameter("date", date);
		q.setParameter("linkURL", linkURL);
		return (Timestamp)q.getResultList().get(0);
				
	}
	
	@SuppressWarnings("unchecked")
	public void updateReportDate(StockDataTitlePK sdt,Date ReportDate){
		String stockID = sdt.getStockID();
		Date date = sdt.getDate();
		String linkURL =  sdt.getLinkURL();
		Query q = em.createQuery("update JrjStockReport set ReportDate =:ReportDate where "
				+ "stockID = :stockID and date =:date and linkURL =:linkURL");
		q.setParameter("ReportDate", ReportDate);
		q.setParameter("stockID", stockID);
		q.setParameter("date", date);
		q.setParameter("linkURL", linkURL);
		q.executeUpdate();
	}
	

}
