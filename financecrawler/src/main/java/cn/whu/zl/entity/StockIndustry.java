package cn.whu.zl.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stockindustry")
public class StockIndustry implements Serializable {
	private static final long serialVersionUID = 1L;

	public StockIndustry() {
		super();
	}

	public StockIndustry(String stockID, String industryName) {
		super();
		this.stockID = stockID;
		this.industryName = industryName;
	}

	public StockIndustry(String stockID, String industryName, String stockName,
			String region, byte stockType) {
		super();
		this.stockID = stockID;
		this.industryName = industryName;
		this.stockName = stockName;
		this.region = region;
		this.stockType = stockType;
	}

	
	public String getStockID() {
		return stockID;
	}

	public void setStockID(String stockID) {
		this.stockID = stockID;
	}

	
	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	
	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	
	public byte getStockType() {
		return stockType;
	}

	public void setStockType(byte stockType) {
		this.stockType = stockType;
	}

	@Id
	@Column(name = "stockID",length=6)
	private String stockID;

	@Column(name = "industryName")
	private String industryName;

	@Column(name = "stockName")
	private String stockName;

	@Column(name = "region")
	private String region;

	@Column(name = "stockType")
	private byte stockType;

	@Override
	public String toString() {
		return "StockIndustry [stockID=" + stockID + ", industryName="
				+ industryName + ", stockName=" + stockName + ", region="
				+ region + ", stockType=" + stockType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stockID == null) ? 0 : stockID.hashCode());
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
		StockIndustry other = (StockIndustry) obj;
		if (stockID == null) {
			if (other.stockID != null)
				return false;
		} else if (!stockID.equals(other.stockID))
			return false;
		return true;
	}
}