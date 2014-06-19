/**
 * 
 */
package cn.whu.zl.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author B506-13-1
 * 
 */
@Entity
@Table(name="stocknews")
public class StockNews implements Serializable {
	public StockDataTitlePK getSdtPK() {
		return sdtPK;
	}

	public void setSdtPK(StockDataTitlePK sdtPK) {
		this.sdtPK = sdtPK;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getExtendNosql() {
		return extendNosql;
	}

	public void setExtendNosql(String extendNosql) {
		this.extendNosql = extendNosql;
	}

	public byte getTendency() {
		return tendency;
	}

	public void setTendency(byte tendency) {
		this.tendency = tendency;
	}

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "StockNews [sdtPK=" + sdtPK + ", title=" + title
				+ ", extendNosql=" + extendNosql + ", tendency=" + tendency
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sdtPK == null) ? 0 : sdtPK.hashCode());
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
		StockNews other = (StockNews) obj;
		if (sdtPK == null) {
			if (other.sdtPK != null)
				return false;
		} else if (!sdtPK.equals(other.sdtPK))
			return false;
		return true;
	}

	@EmbeddedId
	private StockDataTitlePK sdtPK;

	@Column(name = "title")
	private String title;

	@Column(name = "extendNosql")
	private String extendNosql;

	@Column(name = "tendency")
	private byte tendency;
}
