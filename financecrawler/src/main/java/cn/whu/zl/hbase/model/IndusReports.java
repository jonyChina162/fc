package cn.whu.zl.hbase.model;


public class IndusReports {

	public long time;
	public int grade;
	public int graChange;
	public int orgImpact;
	public String url;
	public String orgName;
	public String indusName;
	public String priceCR;
	public String reContent;
	public long reportsCount;
	
	
	
	public IndusReports() {
	}

	

	public IndusReports(String key) {
		this();
		String[] ss = key.split("_");
		time = Long.parseLong(ss[0]);
		grade = Integer.parseInt(ss[1]);
		graChange = Integer.parseInt(ss[2]);
		orgImpact = Integer.parseInt(ss[3]);
		url = ss[4];
	}



	public IndusReports(long time, int grade, int graChange, int orgImpact,
			String url) {
		super();
		this.time = time;
		this.grade = grade;
		this.graChange = graChange;
		this.orgImpact = orgImpact;
		this.url = url;
	}



	public IndusReports(long time, int grade, int graChange, int orgImpact,
			String url, String orgName, String indusName, String priceCR,
			String reContent, long reportsCount) {
		super();
		this.time = time;
		this.grade = grade;
		this.graChange = graChange;
		this.orgImpact = orgImpact;
		this.url = url;
		this.orgName = orgName;
		this.indusName = indusName;
		this.priceCR = priceCR;
		this.reContent = reContent;
		this.reportsCount = reportsCount;
	}



	@Override
	public String toString() {
		return "IndusReports [time=" + time + ", grade=" + grade
				+ ", graChange=" + graChange + ", orgImpact=" + orgImpact
				+ ", url=" + url + ", orgName=" + orgName + ", indusName="
				+ indusName + ", priceCR=" + priceCR + ", reContent="
				+ reContent + ", reportsCount=" + reportsCount + "]";
	}
}
