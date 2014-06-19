/**
 * 
 */
package cn.whu.zl.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import cn.whu.zl.dao.impl.GenericJPADao;
import cn.whu.zl.entity.StockIndex;

/**
 * @author B506-13-1
 *
 */
@Repository("stockIndexDao")
public class StockIndexDao extends GenericJPADao<StockIndex, String> {
	@SuppressWarnings("unchecked")
	public List<String> getAllStockID(){
		Query q = em.createQuery("select id from StockIndex");
		List<String> list = q.getResultList();
		q=null;
		em.clear();
		return list;
	}

}
