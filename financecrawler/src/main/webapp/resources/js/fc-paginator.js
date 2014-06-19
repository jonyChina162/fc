var totalRecords = 0;
var totalPages = 0;
var pageSize = 20;
var INDUSTRY_REPORT_GRADE_ARR = [ "-", "中性", "持有", "跟随大市", "同步大市", "同步大势",
		"中立", "买入", "推荐", "标配", "优于大势", "谨慎推荐", "看好", "增持", "领先大市", "强于大市" ];
var REPORT_GRADE_CHANGE_ARR = [ "上调", "下调", "首次", "维持", "无" ];
var REPORT_GRADE_ARR = [ "-", "中性", "持有", "谨慎推荐", "审慎推荐", "推荐", "强烈推荐", "买入",
		"增持", "强推" ];

$.bsPageStart = function(id, func) {
	$.ajax({
		type : 'post',
		async : false,
		url : "/fc/" + id + "/list/total?pageSize=" + pageSize,
		contentType : "application/json; charset=utf-8",
		dataType : 'json',
		success : function(data) {
			totalRecords = data[0];
			totalPages = data[1];
		}
	});

	var pageUrl = "/fc/" + id + "/list/all/" + pageSize + "/" + totalRecords
			+ "/";
	var options = {
		bootstrapMajorVersion : 3,
		currentPage : 1,
		totalPages : totalPages,
		size : "normal",
		alignment : "left",
		useBootstrapTooltip : true,
		numberOfPages : 10,
		onPageChanged : function(e, opage, page) {
			$.get(pageUrl + page, function(data) {
				func(data);
			}, "json");

		}
	};

	$('#display_page').bootstrapPaginator(options);
	$.get(pageUrl + 1, function(data) {
		func(data);
	}, "json");
};

$.stocksHtml = function(data) {
	$('#records')
			.html(
					"<h3 class='tableTitle'>行业股票</h3><table class=\"table\"><thead><tr><th>股票代码</th><th>股票名称</th><th>所属行业</th></tr></thead><tbody></tbody></table>");

	$.each(data, function(index, content) {
		$('.table').append(
				"<tr><td>" + content['stockID'] + "</td><td>"
						+ content['stockName'] + "</td><td>"
						+ content['industryName'] + "</td></tr>");
	});

};

$.tranHisHtml = function(data) {
	$('#records')
			.html(
					"<h3 class='tableTitle'>历史交易数据</h3><table class=\"table\"><thead><tr><th>股票代码</th><th>股票名称</th><th>日期</th><th>开盘价</th><th>最高价</th><th>最低价</th><th>收盘价</th><th>涨跌额</th><th>涨跌幅(%)</th><th>成交量(手)</th><th>成交金额(万元)</th><th>换手率(%)</th></tr></thead><tbody></tbody></table>");

	$.each(data, function(index, content) {
		$('.table').append(
				"<tr><td>" + content['sdPK']['stockID'] + "</td><td>"
						+ content['stockName'] + "</td><td>"
						+ $.getDateStr(content['sdPK']['date']) + "</td><td>"
						+ content['openPrice'] + "</td><td>"
						+ content['highPrice'] + "</td><td>"
						+ content['lowPrice'] + "</td><td>"
						+ content['cloPrice'] + "</td><td>"
						+ content['changeAmount'] + "</td><td>"
						+ content['changeRatio'] + "</td><td>"
						+ content['turnover'] + "</td><td>"
						+ content['turnoverAmount'] + "</td><td>"
						+ content['turnoverRate'] + "</td></tr>");
	});

};

$.fundHisHtml = function(data) {
	$('#records')
			.html(
					"<h3 class='tableTitle'>历史资金流向</h3><table class=\"table\"><thead><tr><th>股票代码</th><th>日期</th><th>资金流入（万元）</th><th>资金流出（万元）</th><th>净流入（万元）</th><th>主力流入（万元）</th><th>主力流出（万元）</th><th>主力净流入（万元）</th></tr></thead><tbody></tbody></table>");

	$.each(data, function(index, content) {
		$('.table').append(
				"<tr><td>" + content['sdPK']['stockID'] + "</td><td>"
						+ $.getDateStr(content['sdPK']['date']) + "</td><td>"
						+ content['fundIn'] + "</td><td>" + content['fundOut']
						+ "</td><td>" + content['netIn'] + "</td><td>"
						+ content['mainIn'] + "</td><td>" + content['mainOut']
						+ "</td><td>" + content['mainNetIn'] + "</td></tr>");
	});

};

$.financialReportHtml = function(data) {
	$('#records')
			.html(
					"<h3 class='tableTitle'>个股财务报表</h3><div class=\"table-responsive\"><table class=\"table table-condensed\"><thead><tr><th>股票代码</th><th>日期</th><th>营业收入（万元）</th><th>营业利润（万元）</th><th>利润总额（万元）</th><th>净利润（万元）</th><th>基本每股收益</th><th>货币资金（万元）</th><th>资产总计（万元）</th><th>负债合计（万元）</th><th>所有者权益合计（万元）</th></tr></thead><tbody></tbody></table></div>");

	$.each(data, function(index, content) {
		$('.table').append(
				"<tr><td>" + content['sdPK']['stockID'] + "</td><td>"
						+ $.getDateStr(content['sdPK']['date']) + "</td><td>"
						+ content['income'] + "</td><td>"
						+ content['operProfit'] + "</td><td>"
						+ content['profitAmount'] + "</td><td>"
						+ content['netProfit'] + "</td><td>" + content['eps']
						+ "</td><td>" + content['monCap'] + "</td><td>"
						+ content['totalAsset'] + "</td><td>"
						+ content['totalLiab'] + "</td><td>"
						+ content['totalEquity'] + "</td></tr>");
	});

};

$.stockNewsHtml = function(data) {
	$('#records')
			.html(
					"<h3 class='tableTitle'>个股新闻</h3><table class=\"table\"><thead><tr><th>股票代码</th><th>公布日期</th><th>新闻标题</th><th>评价等级</th></tr></thead><tbody></tbody></table>");

	$.each(data, function(index, content) {
		$('.table')
				.append(
						"<tr><td>" + content['sdtPK']['stockID'] + "</td><td>"
								+ $.getDateStr(content['sdtPK']['date'])
								+ "</td><td><a href=\""
								+ content['sdtPK']['linkURL'] + "\">"
								+ content['title'] + "</a></td><td>"
								+ $.getNewsTendency(content['tendency'])
								+ "</td></tr>");
	});

};

$.stockReportHtml = function(data) {
	$('#records')
			.html(
					"<h3 class='tableTitle'>个股研报</h3><table class=\"table table-condensed\"><thead><tr><th>股票代码</th><th>股票名称</th><th>日期</th><th>研报标题</th><th>原文评级</th><th>评级变动</th><th>机构名称</th><th>2013预测收益</th><th>2013预测市盈率</th><th>2014预测收益</th><th>2014预测市盈率</th></tr></thead><tbody></tbody></table>");

	$.each(data, function(index, content) {
		$('.table').append(
				"<tr><td>" + content['sdtPK']['stockID'] + "</td><td>"
						+ content['stockName'] + "</td><td>"
						+ $.getDateStr(content['sdtPK']['date'])
						+ "</td><td><a href=\"" + content['sdtPK']['linkURL']
						+ "\">" + content['title'] + "</a></td><td>"
						+ $.getReportGrade(content['grade']) + "</td><td>"
						+ $.getGradeChange(content['gradeChange']) + "</td><td>"
						+ content['orgName'] + "</td><td>"
						+ content['preIncomeThis'] + "</td><td>"
						+ content['prePEratioThis'] + "</td><td>"
						+ content['preIncomeNext'] + "</td><td>"
						+ content['prePEratioNext'] + "</td></tr>");
	});

};

$.indusReportHtml = function(data) {
	$('#records')
			.html(
					"<h3 class='tableTitle'>行业研报</h3><table class=\"table table-condensed\"><thead><tr><th>行业名称</th><th>日期</th><th>研报标题</th><th>评级类别</th><th>评级变动</th><th>机构名称</th><th>机构影响力</th></tr></thead><tbody></tbody></table>");

	$.each(data, function(index, content) {
		$('.table').append(
				"<tr><td>" + content['idtPK']['industryName'] + "</td><td>"
						+ $.getDateStr(content['idtPK']['date'])
						+ "</td><td><a href=\"" + content['idtPK']['linkURL']
						+ "\">" + content['title'] + "</a></td><td>"
						+ $.getIndusReportGrade(content['grade']) + "</td><td>"
						+ $.getGradeChange(content['gradeChange']) + "</td><td>"
						+ content['orgName'] + "</td><td>"
						+ content['orgWeight'] + "</td></tr>");
	});

};

$.rmActiveAll = function() {
	$('.navbar-collapse li').each(function() {
		if ($(this).hasClass('active')) {
			$(this).removeClass('active');
		}
	});
};

$.getDateStr = function(str) {
	var strDate = new Date(str);
	var sDate = strDate.toLocaleString().split(' ')[0];
	return sDate.replace(/年|月/g, '-').replace(/日/g, '');
};

$.getNewsTendency = function(grade) {
	switch (grade) {
	case 0:
		return '利好';
		break;
	case 1:
		return '利空';
		break;
	default:
		return '无';
		break;
	}
};

$.getGradeChange=function(gcInt){
	return REPORT_GRADE_CHANGE_ARR[gcInt];
};

$.getReportGrade=function(gradeInt){
	return REPORT_GRADE_ARR[gradeInt];
};

$.getIndusReportGrade=function(gradeInt){
	if(gradeInt == -1) return '无';
	return INDUSTRY_REPORT_GRADE_ARR[gradeInt];
};
