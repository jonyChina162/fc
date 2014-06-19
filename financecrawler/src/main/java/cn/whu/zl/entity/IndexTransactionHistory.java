/**
 * 
 */
package cn.whu.zl.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.whu.zl.util.EntityBuilder;

/**
 * @author B506-13-1
 * 
 */
@Entity
@Table(name="indextransactionhistory")
/*@NamedQuery(name="ithCloPri",query="select max(cloPrice) from "
		+ "(select cloPrice from IndexTransactionHistory where stockid=000300"
		+" and date >= ?1 limit ?2)as total")*/
public class IndexTransactionHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	public IndexTransactionHistory(){}
	
	private IndexTransactionHistory(Builder builder) {
		this.sdPK = builder.sdPK;
		this.name = builder.name;
		this.cloPrice = builder.cloPrice;
		this.highPrice = builder.highPrice;
		this.lowPrice = builder.lowPrice;
		this.openPrice = builder.openPrice;
		this.beforeClo=builder.beforeClo;
		this.changeAmount = builder.changeAmount;
		this.changeRatio = builder.changeRatio;
		this.turnover = builder.turnover;
		this.turnoverAmount = builder.turnoverAmount;
	}


	@Override
	public String toString() {
		return "IndexTransactionHistory [sdPK=" + sdPK + ", name=" + name
				+ ", cloPrice=" + cloPrice + ", highPrice=" + highPrice
				+ ", lowPrice=" + lowPrice + ", openPrice=" + openPrice
				+",beforeClo="+beforeClo
				+ ", changeAmount=" + changeAmount + ", changeRatio="
				+ changeRatio + ", turnover=" + turnover + ", turnoverAmount="
				+ turnoverAmount + "]";
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
		IndexTransactionHistory other = (IndexTransactionHistory) obj;
		if (sdPK == null) {
			if (other.sdPK != null)
				return false;
		} else if (!sdPK.equals(other.sdPK))
			return false;
		return true;
	}

	public static class Builder implements EntityBuilder<IndexTransactionHistory> {
		private StockDataPK sdPK;
		private String name;
		private float cloPrice;
		private float highPrice;
		private float lowPrice;
		private float openPrice;
		private float beforeClo;
		private float changeAmount;
		private float changeRatio;
		private long turnover;
		private float turnoverAmount;

		public Builder(StockDataPK sdPK) {
			this.sdPK = sdPK;
		}

		public Builder stockName(String val) {
			this.name = val;
			return this;
		}

		public Builder cloPrice(float val) {
			this.cloPrice = val;
			return this;
		}

		public Builder highPrice(float val) {
			this.highPrice = val;
			return this;
		}

		public Builder lowPrice(float val) {
			this.lowPrice = val;
			return this;
		}

		public Builder openPrice(float val) {
			this.openPrice = val;
			return this;
		}

		public Builder beforeClo(float val) {
			this.beforeClo = val;
			return this;
		}

		public Builder changeAmount(float val) {
			this.changeAmount = val;
			return this;
		}

		public Builder changeRatio(float val) {
			this.changeRatio = val;
			return this;
		}

		public Builder turnover(long val) {
			this.turnover = val;
			return this;
		}

		public Builder turnoverAmount(float val) {
			this.turnoverAmount = val;
			return this;
		}

		@Override
		public IndexTransactionHistory build() {
			return new IndexTransactionHistory(this);
		}

	}
	
	public StockDataPK getSdPK() {
		return sdPK;
	}

	public void setSdPK(StockDataPK sdPK) {
		this.sdPK = sdPK;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getCloPrice() {
		return cloPrice;
	}

	public void setCloPrice(float cloPrice) {
		this.cloPrice = cloPrice;
	}

	public float getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(float highPrice) {
		this.highPrice = highPrice;
	}

	public float getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(float lowPrice) {
		this.lowPrice = lowPrice;
	}

	public float getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(float openPrice) {
		this.openPrice = openPrice;
	}
	public float getbeforeClo() {
		return beforeClo;
	}

	public void setbeforeClo(float openPrice) {
		this.beforeClo = openPrice;
	}


	public float getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(float changeAmount) {
		this.changeAmount = changeAmount;
	}

	public float getChangeRatio() {
		return changeRatio;
	}

	public void setChangeRatio(float changeRatio) {
		this.changeRatio = changeRatio;
	}

	public long getTurnover() {
		return turnover;
	}

	public void setTurnover(long turnover) {
		this.turnover = turnover;
	}

	public float getTurnoverAmount() {
		return turnoverAmount;
	}

	public void setTurnoverAmount(float turnoverAmount) {
		this.turnoverAmount = turnoverAmount;
	}

	@EmbeddedId
	private StockDataPK sdPK;

	@Column(name = "name")
	private String name;

	@Column(name = "cloPrice", scale = 2)
	private float cloPrice;

	@Column(name = "highPrice", scale = 2)
	private float highPrice;

	@Column(name = "lowPrice", scale = 2)
	private float lowPrice;

	@Column(name = "openPrice", scale = 2)
	private float openPrice;
	
	@Column(name = "beforeClo", scale = 2)
	private float beforeClo;

	@Column(name = "changeAmount", scale = 2)
	private float changeAmount;

	@Column(name = "changeRatio", scale = 2)
	private float changeRatio;


	@Column(name = "turnover")
	private long turnover;

	@Column(name = "turnoverAmount", scale = 2)
	private float turnoverAmount;


}
