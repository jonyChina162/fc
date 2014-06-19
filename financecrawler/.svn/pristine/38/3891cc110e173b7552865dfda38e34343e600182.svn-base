/**
 * 
 */
package cn.whu.zl.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.whu.zl.entity.FinancialReport;
import cn.whu.zl.entity.FundFlowHistory;
import cn.whu.zl.entity.IndustryReport;
import cn.whu.zl.entity.StockIndustry;
import cn.whu.zl.entity.StockNews;
import cn.whu.zl.entity.StockReport;
import cn.whu.zl.entity.TransactionHistory;
import cn.whu.zl.service.FinancialReportService;
import cn.whu.zl.service.FundFlowHistoryService;
import cn.whu.zl.service.IndustryReportService;
import cn.whu.zl.service.StockIndustryService;
import cn.whu.zl.service.StockNewsService;
import cn.whu.zl.service.StockReportService;
import cn.whu.zl.service.TransactionHistoryService;

/**
 * @author B506-13-1
 * 
 */
@Controller
public class DisplayDatasController {
	@RequestMapping("/stocks/list/all/{pageSize}/{totalCount}/{currentPage}")
	@ResponseBody
	public List<StockIndustry> listAllStocks(@PathVariable int currentPage,
			@PathVariable int pageSize, @PathVariable int totalCount,
			Model model) {

		if (currentPage < 1)
			currentPage = 1;

		int firstNo = (currentPage - 1) * pageSize;

		if (firstNo >= totalCount)
			firstNo = totalCount - 1;

		List<StockIndustry> list = stockIndustrySer.getAllList(firstNo,
				pageSize);

		/*
		 * model.addAttribute("currentPage", currentPage);
		 * model.addAttribute("pageSize", pageSize);
		 * model.addAttribute("totalCount", totalCount);
		 * model.addAttribute("records", list);
		 */

		return list;
	}

	@RequestMapping("/stocks/list/total")
	@ResponseBody
	public List<Long> getTotalStocks(@RequestParam int pageSize) {
		List<Long> list = new ArrayList<>(2);
		Long records = stockIndustrySer.getTotalRecords();
		Long pages = records / pageSize;
		if (records % pageSize != 0)
			pages++;

		list.add(records);
		list.add(pages);
		return list;
	}
	
	@RequestMapping("/tranHis/list/all/{pageSize}/{totalCount}/{currentPage}")
	@ResponseBody
	public List<TransactionHistory> listAllTransHis(@PathVariable int currentPage,
			@PathVariable int pageSize, @PathVariable int totalCount,
			Model model) {

		if (currentPage < 1)
			currentPage = 1;

		int firstNo = (currentPage - 1) * pageSize;

		if (firstNo >= totalCount)
			firstNo = totalCount - 1;

		List<TransactionHistory> list = transactionHistorySer.getAllList(firstNo,
				pageSize);

		return list;
	}

	@RequestMapping("/tranHis/list/total")
	@ResponseBody
	public List<Long> getTotalTransHis(@RequestParam int pageSize) {
		List<Long> list = new ArrayList<>(2);
		Long records = transactionHistorySer.getTotalRecords();
		Long pages = records / pageSize;
		if (records % pageSize != 0)
			pages++;

		list.add(records);
		list.add(pages);
		return list;
	}
	
	@RequestMapping("/fundHis/list/all/{pageSize}/{totalCount}/{currentPage}")
	@ResponseBody
	public List<FundFlowHistory> listAllFundHis(@PathVariable int currentPage,
			@PathVariable int pageSize, @PathVariable int totalCount,
			Model model) {

		if (currentPage < 1)
			currentPage = 1;

		int firstNo = (currentPage - 1) * pageSize;

		if (firstNo >= totalCount)
			firstNo = totalCount - 1;

		List<FundFlowHistory> list = fundFlowHistorySer.getAllList(firstNo,
				pageSize);

		return list;
	}

	@RequestMapping("/fundHis/list/total")
	@ResponseBody
	public List<Long> getTotalFundHis(@RequestParam int pageSize) {
		List<Long> list = new ArrayList<>(2);
		Long records = fundFlowHistorySer.getTotalRecords();
		Long pages = records / pageSize;
		if (records % pageSize != 0)
			pages++;

		list.add(records);
		list.add(pages);
		return list;
	}
	
	@RequestMapping("/financialReport/list/all/{pageSize}/{totalCount}/{currentPage}")
	@ResponseBody
	public List<FinancialReport> listAllFinancialReport(@PathVariable int currentPage,
			@PathVariable int pageSize, @PathVariable int totalCount,
			Model model) {

		if (currentPage < 1)
			currentPage = 1;

		int firstNo = (currentPage - 1) * pageSize;

		if (firstNo >= totalCount)
			firstNo = totalCount - 1;

		List<FinancialReport> list = financialReportSer.getAllList(firstNo,
				pageSize);

		return list;
	}

	@RequestMapping("/financialReport/list/total")
	@ResponseBody
	public List<Long> getTotalFinancialReport(@RequestParam int pageSize) {
		List<Long> list = new ArrayList<>(2);
		Long records = financialReportSer.getTotalRecords();
		Long pages = records / pageSize;
		if (records % pageSize != 0)
			pages++;

		list.add(records);
		list.add(pages);
		return list;
	}
	
	@RequestMapping("/indusReport/list/all/{pageSize}/{totalCount}/{currentPage}")
	@ResponseBody
	public List<IndustryReport> listAllIndusReport(@PathVariable int currentPage,
			@PathVariable int pageSize, @PathVariable int totalCount,
			Model model) {

		if (currentPage < 1)
			currentPage = 1;

		int firstNo = (currentPage - 1) * pageSize;

		if (firstNo >= totalCount)
			firstNo = totalCount - 1;

		List<IndustryReport> list = industryReportSer.getAllList(firstNo,
				pageSize);

		return list;
	}

	@RequestMapping("/indusReport/list/total")
	@ResponseBody
	public List<Long> getTotalIndusReport(@RequestParam int pageSize) {
		List<Long> list = new ArrayList<>(2);
		Long records = industryReportSer.getTotalRecords();
		Long pages = records / pageSize;
		if (records % pageSize != 0)
			pages++;

		list.add(records);
		list.add(pages);
		return list;
	}
	
	@RequestMapping("/stockReport/list/all/{pageSize}/{totalCount}/{currentPage}")
	@ResponseBody
	public List<StockReport> listAllStockReport(@PathVariable int currentPage,
			@PathVariable int pageSize, @PathVariable int totalCount,
			Model model) {

		if (currentPage < 1)
			currentPage = 1;

		int firstNo = (currentPage - 1) * pageSize;

		if (firstNo >= totalCount)
			firstNo = totalCount - 1;

		List<StockReport> list = stockReportSer.getAllList(firstNo,
				pageSize);

		return list;
	}

	@RequestMapping("/stockReport/list/total")
	@ResponseBody
	public List<Long> getTotalStockReport(@RequestParam int pageSize) {
		List<Long> list = new ArrayList<>(2);
		Long records = stockReportSer.getTotalRecords();
		Long pages = records / pageSize;
		if (records % pageSize != 0)
			pages++;

		list.add(records);
		list.add(pages);
		return list;
	}
	
	@RequestMapping("/stockNews/list/all/{pageSize}/{totalCount}/{currentPage}")
	@ResponseBody
	public List<StockNews> listAllStockNews(@PathVariable int currentPage,
			@PathVariable int pageSize, @PathVariable int totalCount,
			Model model) {

		if (currentPage < 1)
			currentPage = 1;

		int firstNo = (currentPage - 1) * pageSize;

		if (firstNo >= totalCount)
			firstNo = totalCount - 1;

		List<StockNews> list = stockNewsSer.getAllList(firstNo,
				pageSize);

		return list;
	}

	@RequestMapping("/stockNews/list/total")
	@ResponseBody
	public List<Long> getTotalStockNews(@RequestParam int pageSize) {
		List<Long> list = new ArrayList<>(2);
		Long records = stockNewsSer.getTotalRecords();
		Long pages = records / pageSize;
		if (records % pageSize != 0)
			pages++;

		list.add(records);
		list.add(pages);
		return list;
	}

	@Autowired
	private StockIndustryService stockIndustrySer;
	@Autowired
	private FinancialReportService financialReportSer;
	@Autowired
	private FundFlowHistoryService fundFlowHistorySer;
	@Autowired
	private IndustryReportService industryReportSer;
	@Autowired
	private StockNewsService stockNewsSer;
	@Autowired
	private StockReportService stockReportSer;
	@Autowired
	private TransactionHistoryService transactionHistorySer;
}
