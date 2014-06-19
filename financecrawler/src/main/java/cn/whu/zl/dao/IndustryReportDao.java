package cn.whu.zl.dao;

import org.springframework.stereotype.Repository;

import cn.whu.zl.dao.impl.GenericJPADao;
import cn.whu.zl.entity.IndustryNameDataTitlePK;
import cn.whu.zl.entity.IndustryReport;

@Repository("industryReportDao")
public class IndustryReportDao extends
		GenericJPADao<IndustryReport, IndustryNameDataTitlePK> {
	public void saveOrUpdate1(IndustryReport ir) {
		em.merge(ir);
		em.flush();
		em.clear();
	}
}
