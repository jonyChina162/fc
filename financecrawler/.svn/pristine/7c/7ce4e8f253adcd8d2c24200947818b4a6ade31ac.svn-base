/**
 * 
 */
package cn.whu.zl.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author B506-13-1
 *
 */
@Embeddable
public class StockTimePK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public StockTimePK(){}
	
	public StockTimePK(String stockID, Timestamp time) {
		super();
		this.stockID = stockID;
		this.time = time;
	}

	public String getStockID() {
		return stockID;
	}

	public void setStockID(String stockID) {
		this.stockID = stockID;
	}

	
	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "StockTimePK [stockID=" + stockID + ", time=" + time + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stockID == null) ? 0 : stockID.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		StockTimePK other = (StockTimePK) obj;
		if (stockID == null) {
			if (other.stockID != null)
				return false;
		} else if (!stockID.equals(other.stockID))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}


	@Column(name="stockID",length=6)
	private String stockID;
	
	@Column(name="time")
	private Timestamp time;
}
