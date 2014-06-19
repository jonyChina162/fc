package cn.whu.zl.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nctable")
public class NcTable implements Serializable {
	private static final long serialVersionUID = 1L;

	public NcTable() {
		super();
	}

	@Override
	public String toString() {
		return "NcTable [sdPK=" + sdPK + ", time=" + time + ", stockName="
				+ stockName + ", indusName=" + indusName + ", avgCost="
				+ avgCost + ", ncGrade=" + ncGrade + ", ncGradePer="
				+ ncGradePer + ", indusInAPer=" + indusInAPer + ", stoRate="
				+ stoRate + ", tGrade=" + tGrade + ", tGradePer=" + tGradePer
				+ ", priComCost=" + priComCost + ", stoComIndex=" + stoComIndex
				+ ", stoComIndus=" + stoComIndus + ", psePrice=" + psePrice
				+ ", stdPrice=" + stdPrice + ", mGrade=" + mGrade
				+ ", mGradePer=" + mGradePer + ", mFlowTo=" + mFlowTo
				+ ", mFlowComIndus=" + mFlowComIndus + ", conDegree="
				+ conDegree + ", inGrade=" + inGrade + ", inGradePer="
				+ inGradePer + ", imGrade=" + imGrade + ", imGradePer="
				+ imGradePer + ", inTrend=" + inTrend + ", inRate=" + inRate
				+ ", bGrade=" + bGrade + ", bGradePer=" + bGradePer
				+ ", inStoRate=" + inStoRate + ", inProfor=" + inProfor + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sdPK == null) ? 0 : sdPK.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NcTable other = (NcTable) obj;
		if (sdPK == null) {
			if (other.sdPK != null)
				return false;
		} else if (!sdPK.equals(other.sdPK))
			return false;
		return true;
	}

	public StockDataPK getSdPK() {
		return sdPK;
	}

	public void setSdPK(StockDataPK sdPK) {
		this.sdPK = sdPK;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getIndusName() {
		return indusName;
	}

	public void setIndusName(String indusName) {
		this.indusName = indusName;
	}

	public float getAvgCost() {
		return avgCost;
	}

	public void setAvgCost(float avgCost) {
		this.avgCost = avgCost;
	}

	public float getNcGrade() {
		return ncGrade;
	}

	public void setNcGrade(float ncGrade) {
		this.ncGrade = ncGrade;
	}

	public float getIndusInAPer() {
		return indusInAPer;
	}

	public void setIndusInAPer(float indusInAPer) {
		this.indusInAPer = indusInAPer;
	}

	public float gettGrade() {
		return tGrade;
	}

	public void settGrade(float tGrade) {
		this.tGrade = tGrade;
	}

	public float gettGradePer() {
		return tGradePer;
	}

	public void settGradePer(float tGradePer) {
		this.tGradePer = tGradePer;
	}

	public String getPriComCost() {
		return priComCost;
	}

	public void setPriComCost(String priComCost) {
		this.priComCost = priComCost;
	}

	public String getStoComIndex() {
		return stoComIndex;
	}

	public void setStoComIndex(String stoComIndex) {
		this.stoComIndex = stoComIndex;
	}

	public String getStoComIndus() {
		return stoComIndus;
	}

	public void setStoComIndus(String stoComIndus) {
		this.stoComIndus = stoComIndus;
	}

	public float getPsePrice() {
		return psePrice;
	}

	public void setPsePrice(float psePrice) {
		this.psePrice = psePrice;
	}

	public float getStdPrice() {
		return stdPrice;
	}

	public void setStdPrice(float stdPrice) {
		this.stdPrice = stdPrice;
	}

	public float getmGrade() {
		return mGrade;
	}

	public void setmGrade(float mGrade) {
		this.mGrade = mGrade;
	}

	public float getmGradePer() {
		return mGradePer;
	}

	public void setmGradePer(float mGradePer) {
		this.mGradePer = mGradePer;
	}

	public String getmFlowTo() {
		return mFlowTo;
	}

	public void setmFlowTo(String mFlowTo) {
		this.mFlowTo = mFlowTo;
	}

	public String getmFlowComIndus() {
		return mFlowComIndus;
	}

	public void setmFlowComIndus(String mFlowComIndus) {
		this.mFlowComIndus = mFlowComIndus;
	}

	public String getConDegree() {
		return conDegree;
	}

	public void setConDegree(String conDegree) {
		this.conDegree = conDegree;
	}

	public float getInGrade() {
		return inGrade;
	}

	public void setInGrade(float inGrade) {
		this.inGrade = inGrade;
	}

	public float getInGradePer() {
		return inGradePer;
	}

	public void setInGradePer(float inGradePer) {
		this.inGradePer = inGradePer;
	}

	public float getImGrade() {
		return imGrade;
	}

	public void setImGrade(float imGrade) {
		this.imGrade = imGrade;
	}

	public float getImGradePer() {
		return imGradePer;
	}

	public void setImGradePer(float imGradePer) {
		this.imGradePer = imGradePer;
	}

	public String getInTrend() {
		return inTrend;
	}

	public void setInTrend(String inTrend) {
		this.inTrend = inTrend;
	}

	public String getInRate() {
		return inRate;
	}

	public void setInRate(String inRate) {
		this.inRate = inRate;
	}

	public float getbGrade() {
		return bGrade;
	}

	public void setbGrade(float bGrade) {
		this.bGrade = bGrade;
	}

	public float getbGradePer() {
		return bGradePer;
	}

	public void setbGradePer(float bGradePer) {
		this.bGradePer = bGradePer;
	}

	public String getInStoRate() {
		return inStoRate;
	}

	public void setInStoRate(String inStoRate) {
		this.inStoRate = inStoRate;
	}

	public String getInProfor() {
		return inProfor;
	}

	public void setInProfor(String inProfor) {
		this.inProfor = inProfor;
	}

	public float getNcGradePer() {
		return ncGradePer;
	}

	public void setNcGradePer(float ncGradePer) {
		this.ncGradePer = ncGradePer;
	}

	public String getStoRate() {
		return stoRate;
	}

	public void setStoRate(String stoRate) {
		this.stoRate = stoRate;
	}

	@EmbeddedId
	private StockDataPK sdPK;

	@Column(name = "time")
	private Timestamp time;

	@Column(name = "stockname")
	private String stockName;

	@Column(name = "indusname")
	private String indusName;

	@Column(name = "avgcost", scale = 2)
	private float avgCost;

	@Column(name = "ncgrade", scale = 2)
	private float ncGrade;

	@Column(name = "ncgradeper")
	private float ncGradePer;

	@Column(name = "indusinaper", scale = 2)
	private float indusInAPer;

	@Column(name = "storate")
	private String stoRate;

	@Column(name = "tgrage", scale = 2)
	private float tGrade;

	@Column(name = "tgrageper")
	private float tGradePer;

	@Column(name = "pricomcost")
	private String priComCost;

	@Column(name = "stocomindex")
	private String stoComIndex;

	@Column(name = "stocomindus")
	private String stoComIndus;

	@Column(name = "pseprice", scale = 2)
	private float psePrice;

	@Column(name = "stdprice", scale = 2)
	private float stdPrice;

	@Column(name = "mgrade", scale = 2)
	private float mGrade;

	@Column(name = "mgradeper")
	private float mGradePer;

	@Column(name = "mflowto")
	private String mFlowTo;

	@Column(name = "mflowcomindus")
	private String mFlowComIndus;

	@Column(name = "condegree")
	private String conDegree;

	@Column(name = "ingrade", scale = 2)
	private float inGrade;

	@Column(name = "ingradeper")
	private float inGradePer;

	@Column(name = "imgrade", scale = 2)
	private float imGrade;

	@Column(name = "imgradeper")
	private float imGradePer;

	@Column(name = "intrend")
	private String inTrend;

	@Column(name = "inrate")
	private String inRate;

	@Column(name = "bgrade", scale = 2)
	private float bGrade;

	@Column(name = "bgradeper")
	private float bGradePer;

	@Column(name = "instorate")
	private String inStoRate;

	@Column(name = "inprofor")
	private String inProfor;

}
