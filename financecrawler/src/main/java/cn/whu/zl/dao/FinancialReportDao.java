package cn.whu.zl.dao;

import org.springframework.stereotype.Repository;

import cn.whu.zl.dao.impl.GenericJPADao;
import cn.whu.zl.entity.FinancialReport;
import cn.whu.zl.entity.StockDataPK;

@Repository("financialReportDao")
public class FinancialReportDao extends GenericJPADao<FinancialReport, StockDataPK>{

}
