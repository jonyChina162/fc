/**
 * 
 */
package cn.whu.zl.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author B506-13-1
 *
 */
@Embeddable
public class StockDataTitlePK implements Serializable {
	private static final long serialVersionUID = 1L;

	public String getStockID() {
		return stockID;
	}

	public void setStockID(String stockID) {
		this.stockID = stockID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "StockDataTitlePK [stockID=" + stockID + ", date=" + date
				+ ", linkURL=" + linkURL + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((linkURL == null) ? 0 : linkURL.hashCode());
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
		StockDataTitlePK other = (StockDataTitlePK) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (linkURL == null) {
			if (other.linkURL != null)
				return false;
		} else if (!linkURL.equals(other.linkURL))
			return false;
		if (stockID == null) {
			if (other.stockID != null)
				return false;
		} else if (!stockID.equals(other.stockID))
			return false;
		return true;
	}



	@Column(name="stockID",length=6)
	private String stockID;
	
	@Column(name="date")
	@DateTimeFormat(pattern="YYYY-MM-dd")
	private Date date;
	
	@Column(name="linkURL")
	private String linkURL;

	public String getLinkURL() {
		return linkURL;
	}

	public void setLinkURL(String linkURL) {
		this.linkURL = linkURL;
	}
}
