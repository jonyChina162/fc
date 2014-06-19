package cn.whu.zl.dao;

import org.springframework.stereotype.Repository;

import cn.whu.zl.dao.impl.GenericJPADao;
import cn.whu.zl.entity.StockDataTitlePK;
import cn.whu.zl.entity.StockNews;

@Repository("stockNewsDao")
public class StockNewsDao extends GenericJPADao<StockNews, StockDataTitlePK> {
	public void saveArr(StockNews[] sns){
		for (StockNews sn : sns) {
			if (null != sn){
					update(sn);
			}
			sn = null;
		}

		sns = null;
		flush();
		em.clear();
	}
}
