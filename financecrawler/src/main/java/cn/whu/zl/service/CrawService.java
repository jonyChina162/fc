package cn.whu.zl.service;

import java.util.List;

public interface CrawService extends Runnable{
	public void start();
	
	public void close();
	
	public List<String> getCodeList();
	
	public void setCodeList(List<String> codeList);
	
	public String getMinCode();

	public void setMinCode(String minCode);

	public String getMaxCode();

	public void setMaxCode(String maxCode);
}
