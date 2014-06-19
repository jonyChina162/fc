package cn.whu.zl.dao;

import static cn.whu.zl.util.CrawlerUtil.sumList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import cn.whu.zl.dao.impl.GenericJPADao;
import cn.whu.zl.entity.FundFlowHistory;
import cn.whu.zl.entity.StockDataPK;
import cn.whu.zl.entity.TransactionHistory;

@Repository("fundFlowHistoryDao")
public class FundFlowHistoryDao extends GenericJPADao<FundFlowHistory, StockDataPK> {
	@SuppressWarnings("unchecked")
	public int getSumNetInDays(String stockID,Date date,int days){
		Query q = em.createQuery("select netIn from FundFlowHistory ts where stockID= :stockID and date < :date and netIn != 0.0 order by date desc ");
		q.setParameter("stockID", stockID);
		q.setParameter("date", date);
		q.setMaxResults(days);
//		List<Integer> ll = q.getResultList();
		List<Integer> list = q.getResultList();
		q=null;
		em.clear();
		return sumList(list);
	}
	
	@SuppressWarnings("unchecked")
	public int getSumMainNetInDays(String stockID,Date date,int days){
		Query q = em.createQuery("select mainNetIn from FundFlowHistory ts where stockID= :stockID and date < :date and mainNetIn != 0.0 order by date desc ");
		q.setParameter("stockID", stockID);
		q.setParameter("date", date);
		q.setMaxResults(days);
		List<Integer> list = q.getResultList();
		q=null;
		em.clear();
		return sumList(list);
	}
	
	@SuppressWarnings("unchecked")
	public List<FundFlowHistory> getOneStockAllFund(String stockID){
		Query q = em.createQuery("select ffh from FundFlowHistory ffh where stockID=:stockID");
		q.setParameter("stockID", stockID);
		List<FundFlowHistory> list = q.getResultList();
		em.clear();
		return list;
	}
	
	public List<Float> getMFI(String stockID,Date date){
		List<Float> results = new ArrayList<Float>();
		results.add(getALLMFI1(stockID,date));
		results.add(getALLMFI7(stockID,date));
		results.add(getALLMFI14(stockID,date));
		results.add(getMainMFI1(stockID,date));
		results.add(getMainMFI7(stockID,date));
		results.add(getMainMFI14(stockID,date));
		
		return results;
	}
	
	public float getALLMFI1(String stockID,Date date){
		
		Query q = em.createQuery("select AllMFI1 from "
				+ "FundFlowHistory where stockID = :stockid and date < :date order by date desc");
		q.setParameter("stockid", stockID);
		q.setParameter("date", date);
		q.setMaxResults(1);
		if(q.getResultList().size() == 0)
			return 0f;
		return (float)q.getResultList().get(0);
	}
	
	public float getALLMFI7(String stockID,Date date){
		Query q = em.createQuery("select AllMFI7 from "
				+ "FundFlowHistory where stockID = :stockid and date < :date order by date desc");
		q.setParameter("stockid", stockID);
		q.setParameter("date", date);
		q.setMaxResults(1);
		if(q.getResultList().size() == 0)
			return 0f;
		return (float)q.getResultList().get(0);
	}
	public float getALLMFI14(String stockID,Date date){
		Query q = em.createQuery("select AllMFI14 from "
				+ "FundFlowHistory where stockID = :stockid and date < :date order by date desc");
		q.setParameter("stockid", stockID);
		q.setParameter("date", date);
		q.setMaxResults(1);
		if(q.getResultList().size() == 0)
			return 0f;
		return (float)q.getResultList().get(0);
	}
	
	public float getMainMFI1(String stockID,Date date){
		Query q = em.createQuery("select MainMFI1 from "
				+ "FundFlowHistory where stockID = :stockid and date < :date order by date desc");
		q.setParameter("stockid", stockID);
		q.setParameter("date", date);
		q.setMaxResults(1);
		if(q.getResultList().size() == 0)
			return 0f;
		return (float)q.getResultList().get(0);
	}
	
	public float getMainMFI7(String stockID,Date date){
		Query q = em.createQuery("select MainMFI7 from "
				+ "FundFlowHistory where stockID = :stockid and date < :date order by date desc");
		q.setParameter("stockid", stockID);
		q.setParameter("date", date);
		q.setMaxResults(1);
		if(q.getResultList().size() == 0)
			return 0f;
		return (float)q.getResultList().get(0);
	}
	
	public float getMainMFI14(String stockID,Date date){
		Query q = em.createQuery("select MainMFI14 from "
				+ "FundFlowHistory where stockID = :stockid and date < :date order by date desc");
		q.setParameter("stockid", stockID);
		q.setParameter("date", date);
		q.setMaxResults(1);
		if(q.getResultList().size() == 0)
			return 0f;
		return (float)q.getResultList().get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<FundFlowHistory> getAllByDate(Date e){
		Query q = em.createQuery("select fh from FundFlowHistory fh where date =:date");
		q.setParameter("date", e);
		List<FundFlowHistory> rs = q.getResultList();
		return rs;
	}
	
	public List<Integer> getMFIdummy(String stockID,Date date){
		List<Integer> results = new ArrayList<Integer>();
		results.add(getALLMFI1dummy(stockID,date));
		results.add(getALLMFI7dummy(stockID,date));
		results.add(getALLMFI14dummy(stockID,date));
		results.add(getMainMFI1dummy(stockID,date));
		results.add(getMainMFI7dummy(stockID,date));
		results.add(getMainMFI14dummy(stockID,date));
		
		return results;
	}
	
	public int getALLMFI1dummy(String stockID,Date date){
		Query q = em.createQuery("select AllMFI1dummy from "
				+ "FundFlowHistory where stockID = :stockid and date < :date order by date desc");
		q.setParameter("stockid", stockID);
		q.setParameter("date", date);
		q.setMaxResults(1);
		if(q.getResultList().size() == 0)
			return 0;
		return (int)q.getResultList().get(0);
	}
	
	public int getALLMFI7dummy(String stockID,Date date){
		Query q = em.createQuery("select AllMFI7dummy from "
				+ "FundFlowHistory where stockID = :stockid and date < :date order by date desc");
		q.setParameter("stockid", stockID);
		q.setParameter("date", date);
		q.setMaxResults(1);
		if(q.getResultList().size() == 0)
			return 0;
		return (int)q.getResultList().get(0);
	}
	
	public int getALLMFI14dummy(String stockID,Date date){
		Query q = em.createQuery("select AllMFI14dummy from "
				+ "FundFlowHistory where stockID = :stockid and date < :date order by date desc");
		q.setParameter("stockid", stockID);
		q.setParameter("date", date);
		q.setMaxResults(1);
		if(q.getResultList().size() == 0)
			return 0;
		return (int)q.getResultList().get(0);
	}
	
	public int getMainMFI1dummy(String stockID,Date date){
		Query q = em.createQuery("select MainMFI1dummy from "
				+ "FundFlowHistory where stockID = :stockid and date < :date order by date desc");
		q.setParameter("stockid", stockID);
		q.setParameter("date", date);
		q.setMaxResults(1);
		if(q.getResultList().size() == 0)
			return 0;
		return (int)q.getResultList().get(0);
	}
	
	public int getMainMFI7dummy(String stockID,Date date){
		Query q = em.createQuery("select MainMFI7dummy from "
				+ "FundFlowHistory where stockID = :stockid and date < :date order by date desc");
		q.setParameter("stockid", stockID);
		q.setParameter("date", date);
		q.setMaxResults(1);
		if(q.getResultList().size() == 0)
			return 0;
		return (int)q.getResultList().get(0);
	}
	
	public int getMainMFI14dummy(String stockID,Date date){
		Query q = em.createQuery("select MainMFI14dummy from "
				+ "FundFlowHistory where stockID = :stockid and date < :date order by date desc");
		q.setParameter("stockid", stockID);
		q.setParameter("date", date);
		q.setMaxResults(1);
		if(q.getResultList().size() == 0)
			return 0;
		return (int)q.getResultList().get(0);
	}
	
	
	@SuppressWarnings("unchecked")
	public void updateMFIavg(String stockID,Date date,float[] MFIavg){
		Query q = em.createQuery("update FundFlowHistory set"
				+ " allmfi1avg =:allmfi1avg,allmfi7avg =:allmfi7avg,allmfi14avg =:allmfi14avg,"
				+ "mainmfi1avg =:mainmfi1avg,mainmfi7avg =:mainmfi7avg,mainmfi14avg =:mainmfi14avg "
				+ "where stockid =:stockid and date =:date");
		q.setParameter("allmfi1avg", MFIavg[0]);
		q.setParameter("allmfi7avg", MFIavg[1]);
		q.setParameter("allmfi14avg", MFIavg[2]);
		q.setParameter("mainmfi1avg", MFIavg[3]);
		q.setParameter("mainmfi7avg", MFIavg[4]);
		q.setParameter("mainmfi14avg", MFIavg[5]);
		q.setParameter("stockid", stockID);
		q.setParameter("date", date);
		q.executeUpdate();
	}
	
	public List<Float> getMFIa(String stockID,Date date){
		List<Float> results = new ArrayList<Float>();
		results.add(getALLMFI1a(stockID,date));
		results.add(getALLMFI7a(stockID,date));
		results.add(getALLMFI14a(stockID,date));
		results.add(getMainMFI1a(stockID,date));
		results.add(getMainMFI7a(stockID,date));
		results.add(getMainMFI14a(stockID,date));
		
		return results;
	}
	
	public float getALLMFI1a(String stockID,Date date){
		Query q = em.createQuery("select allmfi1avg from "
				+ "FundFlowHistory where stockID = :stockid and date < :date order by date desc");
		q.setParameter("stockid", stockID);
		q.setParameter("date", date);
		q.setMaxResults(1);
		if(q.getResultList().size() == 0)
			return 0;
		return (float)q.getResultList().get(0);
	}
	
	public float getALLMFI7a(String stockID,Date date){
		Query q = em.createQuery("select allmfi7avg from "
				+ "FundFlowHistory where stockID = :stockid and date < :date order by date desc");
		q.setParameter("stockid", stockID);
		q.setParameter("date", date);
		q.setMaxResults(1);
		if(q.getResultList().size() == 0)
			return 0;
		return (float)q.getResultList().get(0);
	}
	
	public float getALLMFI14a(String stockID,Date date){
		Query q = em.createQuery("select allmfi14avg from "
				+ "FundFlowHistory where stockID = :stockid and date < :date order by date desc");
		q.setParameter("stockid", stockID);
		q.setParameter("date", date);
		q.setMaxResults(1);
		if(q.getResultList().size() == 0)
			return 0;
		return (float)q.getResultList().get(0);
	}
	
	public float getMainMFI1a(String stockID,Date date){
		Query q = em.createQuery("select mainmfi1avg from "
				+ "FundFlowHistory where stockID = :stockid and date < :date order by date desc");
		q.setParameter("stockid", stockID);
		q.setParameter("date", date);
		q.setMaxResults(1);
		if(q.getResultList().size() == 0)
			return 0;
		return (float)q.getResultList().get(0);
	}
	
	public float getMainMFI7a(String stockID,Date date){
		Query q = em.createQuery("select mainmfi7avg from "
				+ "FundFlowHistory where stockID = :stockid and date < :date order by date desc");
		q.setParameter("stockid", stockID);
		q.setParameter("date", date);
		q.setMaxResults(1);
		if(q.getResultList().size() == 0)
			return 0;
		return (float)q.getResultList().get(0);
	}
	
	public float getMainMFI14a(String stockID,Date date){
		Query q = em.createQuery("select mainmfi14avg from "
				+ "FundFlowHistory where stockID = :stockid and date < :date order by date desc");
		q.setParameter("stockid", stockID);
		q.setParameter("date", date);
		q.setMaxResults(1);
		if(q.getResultList().size() == 0)
			return 0;
		return (float)q.getResultList().get(0);
	}
}
