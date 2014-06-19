/**
 * 
 */
package cn.whu.zl.util;

/**
 * @author B506-13-1
 * 
 */
public class FinConstants {
	/**
	 * 股票类型常量
	 */
	public static final String STOCK_TYPE_0 = "上证A股";
	public static final String STOCK_TYPE_1 = "上证B股";
	public static final String STOCK_TYPE_2 = "深证A股";
	public static final String STOCK_TYPE_3 = "深证B股";
	public static final String STOCK_TYPE_4 = "沪深A股";

	/**
	 * 个股新闻评价等级常量
	 */
	public static final String NEWS_TENDENCY_0 = "利好";
	public static final String NEWS_TENDENCY_1 = "利空";
	public static final String NEWS_TENDENCY_2 = "无";

	/**
	 * 个股研报原文评级常量
	 */
	public static final String REPORT_GRADE_1 = "-";
	public static final String REPORT_GRADE_2 = "中性";
	public static final String REPORT_GRADE_3 = "持有";
	public static final String REPORT_GRADE_4 = "谨慎推荐";
	public static final String REPORT_GRADE_5 = "审慎推荐";
	public static final String REPORT_GRADE_6 = "推荐";
	public static final String REPORT_GRADE_7 = "强烈推荐";
	public static final String REPORT_GRADE_8 = "买入";
	public static final String REPORT_GRADE_9 = "增持";
	public static final String REPORT_GRADE_10 = "强推";

	public static final String[] REPORT_GRADE_ARR = { "-", "中性", "持有", "谨慎推荐",
			"审慎推荐", "推荐", "强烈推荐", "买入", "增持", "强推" };

	/**
	 * 个股研报评级变动常量
	 */
	public static final String REPORT_GRADE_CHANGE_OTHER = "-";
	public static final String REPORT_GRADE_CHANGE_4 = "无";
	public static final String REPORT_GRADE_CHANGE_3 = "维持";
	public static final String REPORT_GRADE_CHANGE_2 = "首次";
	public static final String REPORT_GRADE_CHANGE_1 = "下调";
	public static final String REPORT_GRADE_CHANGE_0 = "上调";

	public static final String[] REPORT_GRADE_CHANGE_ARR = { "上调", "下调", "首次",
			"维持", "无" };

	/**
	 * 行业研报评级常量
	 */
	public static final String INDUSTRY_REPORT_GRADE_0 = "-";
	public static final String INDUSTRY_REPORT_GRADE_1 = "中性";
	public static final String INDUSTRY_REPORT_GRADE_2 = "持有";
	public static final String INDUSTRY_REPORT_GRADE_3 = "跟随大市";
	public static final String INDUSTRY_REPORT_GRADE_4 = "同步大市";
	public static final String INDUSTRY_REPORT_GRADE_5 = "同步大势";
	public static final String INDUSTRY_REPORT_GRADE_6 = "中立";
	public static final String INDUSTRY_REPORT_GRADE_7 = "买入";
	public static final String INDUSTRY_REPORT_GRADE_8 = "推荐";
	public static final String INDUSTRY_REPORT_GRADE_9 = "标配";
	public static final String INDUSTRY_REPORT_GRADE_10 = "优于大势";
	public static final String INDUSTRY_REPORT_GRADE_11 = "谨慎推荐";
	public static final String INDUSTRY_REPORT_GRADE_12 = "看好";
	public static final String INDUSTRY_REPORT_GRADE_13 = "增持";
	public static final String INDUSTRY_REPORT_GRADE_14 = "领先大市";
	public static final String INDUSTRY_REPORT_GRADE_15 = "强于大市";

	public static final String[] INDUSTRY_REPORT_GRADE_ARR = { "-", "中性", "持有",
			"跟随大市", "同步大市", "同步大势", "中立", "买入", "推荐", "标配", "优于大势", "谨慎推荐",
			"看好", "增持", "领先大市", "强于大市" };

	/**
	 * 行业研报评级变动常量
	 */
	public static final String INDUSTRY_REPORT_GRADE_CHANGE_4 = "无";
	public static final String INDUSTRY_REPORT_GRADE_CHANGE_3 = "维持";
	public static final String INDUSTRY_REPORT_GRADE_CHANGE_2 = "首次";
	public static final String INDUSTRY_REPORT_GRADE_CHANGE_1 = "下调";
	public static final String INDUSTRY_REPORT_GRADE_CHANGE_0 = "上调";
	public static final String INDUSTRY_REPORT_GRADE_CHANGE_Other = "-";

	public static final String[] INDUSTRY_REPORT_GRADE_CHANGE_ARR = { "上调",
			"下调", "首次", "维持", "无" };

	public static final String[] JRJ_GRADE_ARR = { "买入", "增持", "中性", "减持", "卖出" };
	public static final String[] JRJ_GRADE_CHANGE_ARR = { "调高", "首次", "第一次维持",
			"维持", "调低", "无" };

}
