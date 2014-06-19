/**
 * 
 */
package cn.whu.zl.util.pager;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang.StringUtils;

/**
 * @author B506-13-1
 * 
 */
public class Pages extends SimpleTagSupport {
	private String href;

	// 当前页
	private String cparam;

	// 每页条数
	private String sparam;

	private int curr = 1;// 当前页

	private int size = 50 ;// 每页条数

	private int total= 10;// 总页数
	
	private String prefix = "/fc";

	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();

		if (StringUtils.isEmpty(cparam)) {
			cparam = "currentPage";
		}
		if (StringUtils.isEmpty(sparam)) {
			sparam = "pageSize";
		}

		if (!href.endsWith("?") && !href.endsWith("&")) {
			if (href.indexOf("?") == -1) {
				href = href + "?";
			} else {
				href = href + "&";
			}
		}

		if (curr <= 0) {
			curr = 1;
		} else if (curr > total) {
			curr = total;
		}

		out.append("<span>");
		// 首页
		if (curr == 1) {
			out.append("首页");
		} else {
			href(out, href, 1, "首页");
		}
		out.append(" | ");
		// 上一页
		if (curr == 1) {
			out.append("上一页");
		} else {
			href(out, href, curr - 1, "上一页");
		}
		out.append(" | ");
		// 下一页
		if (curr == total) {
			out.append("下一页");
		} else {
			href(out, href, curr + 1, "下一页");
		}
		out.append(" | ");
		// 末页
		if (curr == total) {
			out.append("末页");
		} else {
			href(out, href, total, "末页");
		}
		out.append("</span>");
		out.append("<span>第");
		out.append(curr + "/" + total);
		out.append("页</span>");

		super.doTag();
	}

	private void href(JspWriter out, String href, int curr, String title)
			throws IOException {
		out.append("<a href=\"").append(prefix).append(href).append(cparam).append("=")
				.append("" + curr).append("&").append(sparam).append("=")
				.append("" + size).append("\">").append(title).append("</a>");
	}

	public int getCurr() {
		return curr;
	}

	public void setCurr(int curr) {
		this.curr = curr;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getCparam() {
		return cparam;
	}

	public void setCparam(String cparam) {
		this.cparam = cparam;
	}

	public String getSparam() {
		return sparam;
	}

	public void setSparam(String sparam) {
		this.sparam = sparam;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
}
