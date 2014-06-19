<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="/fc/resources/assets/ico/favicon.ico">

<title>金融数据挖掘展示</title>

<!-- Bootstrap core CSS -->
<link href="/fc/resources/css/bootstrap.css" rel="stylesheet" />
<link href="/fc/resources/css/bootstrap-responsive.css" rel="stylesheet" />
<link href="/fc/resources/css/documentation.css" rel="stylesheet" />
<!-- Custom styles for this template -->
<link href="/fc/resources/css/navbar.css" rel="stylesheet" />
<link href="/fc/resources/css/fc.css" rel="stylesheet" />

</head>

<body>

	<div class="container">

		<!-- Static navbar -->
		<div class="navbar navbar-default" role="navigation">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">金融数据展示</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li id="stocks"><a href="#">行业股票</a></li>
					<li class="dropdown" id="his"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">历史数据<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li id="tranHis"><a href="#">历史交易数据</a></li>
							<li id="fundHis"><a href="#">历史资金流向</a></li>
						</ul>
					</li>
					<li id="financialReport"><a href="#">财务报表</a></li>
					<li id="stockNews"><a href="#">个股新闻</a></li>
					<li class="dropdown" id="report"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">研报数据<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li id="stockReport"><a href="#">个股研报数据</a></li>
							<li id="indusReport"><a href="#">行业研报流向</a></li>
						</ul>
					</li>
					
				</ul>
				<!-- <ul class="nav navbar-nav navbar-right">
					<li class="active"><a href="./">Default</a></li>
					<li><a href="">Static top</a></li>
					<li><a href="">Fixed top</a></li>
				</ul> -->
			</div>
			<!--/.nav-collapse -->
		</div>

		<!-- Main component for a primary marketing message or call to action -->
		<div class="jumbotron">
			<div id="records"></div>

			<ul id="display_page" class="pagination"></ul>
		</div>
		<!-- <div id="goToPage" class="pagination">
      	GoTo Page : <select id="show-page-select">
      			<option value="1">1</option>
      		  </select> 
      </div> -->


	</div>
	<!-- /container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="/fc/resources/js/jquery-1.11.0.js"></script>
	<script src="/fc/resources/js/bootstrap.js"></script>
	<script src="/fc/resources/js/bootstrap-paginator.js"></script>
	<script src="/fc/resources/js/fc-paginator.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$.bsPageStart('stocks',$.stocksHtml);
			
			$('#stocks').click(function(){
				$.rmActiveAll();
				$(this).addClass("active");
				$.bsPageStart('stocks',$.stocksHtml);
			});
			
			$('#tranHis').click(function(){
				$.rmActiveAll();
				$('#his').addClass("active");
				$('.nav navbar-nav').hide();
				$.bsPageStart('tranHis',$.tranHisHtml);
			});
			
			$('#fundHis').click(function(){
				$.rmActiveAll();
				$('#his').addClass("active");
				$('.nav navbar-nav').hide();
				$.bsPageStart('fundHis',$.fundHisHtml);
			});
			
			$('#financialReport').click(function(){
				$.rmActiveAll();
				$(this).addClass("active");
				$.bsPageStart('financialReport',$.financialReportHtml);
			});
			
			$('#stockNews').click(function(){
				$.rmActiveAll();
				$(this).addClass("active");
				$.bsPageStart('stockNews',$.stockNewsHtml);
			});
			
			$('#stockReport').click(function(){
				$.rmActiveAll();
				$('#report').addClass("active");
				$('.nav navbar-nav').hide();
				$.bsPageStart('stockReport',$.stockReportHtml);
			});
			
			$('#indusReport').click(function(){
				$.rmActiveAll();
				$('#report').addClass("active");
				$('.nav navbar-nav').hide();
				$.bsPageStart('indusReport',$.indusReportHtml);
			});
		});
		
		
	</script>

</body>
</html>
