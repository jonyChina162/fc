/**
 * 
 */
package cn.whu.zl.dao;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import cn.whu.zl.dao.impl.GenericJPADao;
import cn.whu.zl.entity.IndexTransactionHistory;
import cn.whu.zl.entity.StockDataPK;
import static cn.whu.zl.util.CrawlerUtil.minE0;

/**
 * @author B506-13-1
 * 
 */
@Repository("indexTransactionHistoryDao")
public class IndexTransactionHistoryDao extends
		GenericJPADao<IndexTransactionHistory, StockDataPK> {
	
	
	public float getCloPrice(Date date,String symbol1,String symbol2,int number){
		return getCloPrice(date, symbol1, symbol2, number, "000300");
	}
	
	@SuppressWarnings("unchecked")
	public float getCloPrice(Date date,String symbol1,String symbol2,int number,String stockID) {
		String sql ="select cloPrice from IndexTransactionHistory where stockID= :stockID and date >= :date order by date";
		if(!symbol2.equals("after")){
			sql.replaceAll(">=", "<");
			sql+=" desc";
		}
		Query q = em.createQuery(sql);
		if(number < 7 ) number = 7;
		
		q.setParameter("date", date);
		q.setParameter("stockID", stockID);
		q.setMaxResults(number);// 控制只取number个值
		List<Float> list = q.getResultList();
		float result = 0.0f;
		if (list != null && list.size() > 0 && number != 7){
			
			switch(symbol1.toLowerCase()){
			case "high" : result = Collections.max(list); break;
			case "low" : result = minE0(list); break;
			}
		}
			
		if (list != null && list.size() > 0 && number == 7)
			for (int i = 0; i < list.size(); i++)
				if ((list.get(i) > 1e-10))
				{
					result = list.get(i);
					break;
				}
		list = null;
		q = null;
		em.clear();
		return result;
	}
	
	public float getHighPrice(Date date, int number){
		return getHighPrice(date,number,"000300");
	}
	
	public float getHighPrice(Date date, int number,String stockID) {
		String sql = "select cloPrice from IndexTransactionHistory where stockID= :stockID and date >= :date order by date";

		Query q = em.createQuery(sql);
		if (number < 7)
			number = 7;

		q.setParameter("date", date);
		q.setParameter("stockID", stockID);
		q.setMaxResults(number);// 控制只取number个值
		List<Float> list = q.getResultList();
		float result = 0.0f;
		if (list != null && list.size() > 0 && number != 7) {
			result = Collections.max(list);
		}

		if (list != null && list.size() > 0 && number == 7)
			for (int i = 0; i < list.size(); i++)
				if ((list.get(i) > 1e-10)) {
					result = list.get(i);
					break;
				}

		list = null;
		q = null;
		em.clear();
		return result;
	}
	
	public float getLowPrice( Date date, int number){
		return getLowPrice(  date,  number, "000300");
	}
	
	public float getLowPrice( Date date, int number,String stockID) {
		String sql = "select lowPrice from IndexTransactionHistory where stockID= :stockID and date >= :date order by date";

		Query q = em.createQuery(sql);
		if (number < 7)
			number = 7;
		q.setParameter("stockID", stockID);
		q.setParameter("date", date);
		q.setMaxResults(number);// 控制只取number个值
		List<Float> list = q.getResultList();
		float result = 0.0f;
		if (list != null && list.size() > 0 && number != 7) {
			result = minE0(list);
		}

		if (list != null && list.size() > 0 && number == 7)
			for (int i = 0; i < list.size(); i++)
				if ((list.get(i) > 1e-10)) {
					result = list.get(i);
					break;
				}

		list = null;
		q = null;
		em.clear();
		return result;
	}
	
	@Deprecated
	@SuppressWarnings("unchecked")
	public float getCloPrice(String[] origExcel, String symbol, int number) {
		Query q = em
				.createQuery("select cloPrice from IndexTransactionHistory "
						+ "where stockID='000300' and date >= '" + origExcel[1]
						+ "'");
		if(number < 7) number = 7;
		q.setMaxResults(number);
		List<Float> list = q.getResultList();
		float result = 0.0f;
		if (list != null && list.size() > 0 && number != 7) {
			switch (symbol) {
			case "High":
				result = Collections.max(list);
				break;
			case "Low":
				result = Collections.min(list);
				break;
			}
		}

		if (list != null && list.size() > 0 && number == 7)
			for (int i = 0; i < list.size(); i++)
				if ((list.get(i) > 1e-10)) {
					result = list.get(i);
					break;
				}

		list = null;
		q = null;
		em.clear();
		return result;
	}

	@Deprecated
	@SuppressWarnings("unchecked")
	public float getPastCloPrice(String[] origExcel, String symbol, int number) {
		Query q = em
				.createQuery("select cloPrice from IndexTransactionHistory "
						+ "where stockID='000300' and date >= '" + origExcel[1]
						+ " order by '" + origExcel[1] + " desc' ");
		q.setMaxResults(number);
		List<Float> list = q.getResultList();
		float result = 0.0f;
		if (list != null && list.size() > 0 && number != 7) {
			switch (symbol) {
			case "High":
				result = Collections.max(list);
				break;
			case "Low":
				result = Collections.min(list);
				break;
			}
		}

		if (list != null && list.size() > 0 && number == 7)
			for (int i = 0; i < list.size(); i++)
				if ((list.get(i) > 1e-10)) {
					result = list.get(i);
					break;
				}
		em.clear();
		return result;
	}

}
