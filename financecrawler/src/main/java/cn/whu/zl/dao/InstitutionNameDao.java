package cn.whu.zl.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import cn.whu.zl.dao.impl.GenericJPADao;
import cn.whu.zl.entity.InstitutionName;
import cn.whu.zl.entity.JrjStockReport;

@Repository("institutionNameDao")
public class InstitutionNameDao extends GenericJPADao<InstitutionName, String> {
	public String getInsID(String insName){
		Query q = em.createQuery("select institutionID from InstitutionName "
				+ "where institutionName='"+ insName +"'");
		String result = q.getResultList().toString();
		q=null;
		em.clear();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<InstitutionName> getAllIns(){
		Query q = em.createQuery("select ins from InstitutionName ins",InstitutionName.class);
		List<InstitutionName> list = q.getResultList();
		q=null;
		em.clear();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getAllInsID(){
		Query q = em.createQuery("select institutionID from InstitutionName");
		return q.getResultList();
	}
}
