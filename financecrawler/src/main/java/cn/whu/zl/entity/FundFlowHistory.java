/**
 * 
 */
package cn.whu.zl.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.whu.zl.entity.TransactionHistory.Builder;

/**
 * @author B506-13-1
 * 
 */
@Entity
@Table(name="fundflowhistory")
public class FundFlowHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	public FundFlowHistory() {
	}

	public FundFlowHistory(StockDataPK sdPK) {
		super();
		this.sdPK = sdPK;
	}

	@Override
	public String toString() {
		return "FundFlowHistory [sdPK=" + sdPK + ", fundIn=" + fundIn
				+ ", fundOut=" + fundOut + ", netIn=" + netIn + ", mainIn="
				+ mainIn + ", mainOut=" + mainOut + ", mainNetIn=" + mainNetIn
				+ ", AllMFI1=" + AllMFI1 + ", AllMFI7=" + AllMFI7
				+ ", AllMFI14=" + AllMFI14 + ", MainMFI1=" + MainMFI1
				+ ", MainMFI7=" + MainMFI7 + ", MainMFI14=" + MainMFI14
				+ ", AllMFI1dummy=" + AllMFI1dummy + ", AllMFI7dummy="
				+ AllMFI7dummy + ", AllMFI14dummy=" + AllMFI14dummy
				+ ", MainMFI1dummy=" + MainMFI1dummy + ", MainMFI7dummy="
				+ MainMFI7dummy + ", MainMFI14dummy=" + MainMFI14dummy
				+ ", allmfi1avg=" + allmfi1avg + ", allmfi7avg=" + allmfi7avg
				+ ", allmfi14avg=" + allmfi14avg + ", mainmfi1avg="
				+ mainmfi1avg + ", mainmfi7avg=" + mainmfi7avg
				+ ", mainmfi14avg=" + mainmfi14avg + "]";
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
		FundFlowHistory other = (FundFlowHistory) obj;
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

	public int getFundIn() {
		return fundIn;
	}

	public void setFundIn(int fundIn) {
		this.fundIn = fundIn;
	}

	public int getFundOut() {
		return fundOut;
	}

	public void setFundOut(int fundOut) {
		this.fundOut = fundOut;
	}

	public int getNetIn() {
		return netIn;
	}

	public void setNetIn(int netIn) {
		this.netIn = netIn;
	}

	public int getMainIn() {
		return mainIn;
	}

	public void setMainIn(int mainIn) {
		this.mainIn = mainIn;
	}

	public int getMainOut() {
		return mainOut;
	}

	public void setMainOut(int mainOut) {
		this.mainOut = mainOut;
	}

	public int getMainNetIn() {
		return mainNetIn;
	}

	public void setMainNetIn(int mainNetIn) {
		this.mainNetIn = mainNetIn;
	}

	public Float getAllMFI1() {
		return AllMFI1;
	}

	public void setAllMFI1(Float allMFI1) {
		AllMFI1 = allMFI1;
	}

	public Float getAllMFI7() {
		return AllMFI7;
	}

	public void setAllMFI7(Float allMFI7) {
		AllMFI7 = allMFI7;
	}

	public Float getMainMFI1() {
		return MainMFI1;
	}

	public void setMainMFI1(Float mainMFI1) {
		MainMFI1 = mainMFI1;
	}

	public Float getMainMFI7() {
		return MainMFI7;
	}

	public void setMainMFI7(Float mainMFI7) {
		MainMFI7 = mainMFI7;
	}

	@EmbeddedId
	private StockDataPK sdPK;

	public Float getAllMFI14() {
		return AllMFI14;
	}

	public void setAllMFI14(Float allMFI14) {
		AllMFI14 = allMFI14;
	}

	public Float getMainMFI14() {
		return MainMFI14;
	}

	public void setMainMFI14(Float mainMFI14) {
		MainMFI14 = mainMFI14;
	}

	public Integer getAllMFI1dummy() {
		return AllMFI1dummy;
	}

	public void setAllMFI1dummy(Integer allMFI1dummy) {
		AllMFI1dummy = allMFI1dummy;
	}

	public Integer getAllMFI7dummy() {
		return AllMFI7dummy;
	}

	public void setAllMFI7dummy(Integer allMFI7dummy) {
		AllMFI7dummy = allMFI7dummy;
	}

	public Integer getAllMFI14dummy() {
		return AllMFI14dummy;
	}

	public void setAllMFI14dummy(Integer allMFI14dummy) {
		AllMFI14dummy = allMFI14dummy;
	}

	public Integer getMainMFI1dummy() {
		return MainMFI1dummy;
	}

	public void setMainMFI1dummy(Integer mainMFI1dummy) {
		MainMFI1dummy = mainMFI1dummy;
	}

	public Integer getMainMFI7dummy() {
		return MainMFI7dummy;
	}

	public void setMainMFI7dummy(Integer mainMFI7dummy) {
		MainMFI7dummy = mainMFI7dummy;
	}

	public Integer getMainMFI14dummy() {
		return MainMFI14dummy;
	}

	public void setMainMFI14dummy(Integer mainMFI14dummy) {
		MainMFI14dummy = mainMFI14dummy;
	}

	public Float getAllmfi1avg(){
		return allmfi1avg;
	}
	
	public void setAllmfi1avg(Float Allmfi1avg){
		allmfi1avg = Allmfi1avg;
	}
	
	public Float getAllmfi7avg(){
		return allmfi7avg;
	}
	
	public void setAllmfi7avg(Float Allmfi7avg){
		allmfi7avg = Allmfi7avg;
	}
	
	public Float getAllmfi14avg(){
		return allmfi14avg;
	}
	
	public void setAllmfi14avg(Float Allmfi14avg){
		allmfi14avg = Allmfi14avg;
	}
	
	public Float getMainmfi1avg(){
		return mainmfi1avg;
	}
	
	public void setMainmfi1avg(Float Mainmfi1avg){
		mainmfi1avg = Mainmfi1avg;
	}
	
	public Float getMainmfi7avg(){
		return mainmfi7avg;
	}
	
	public void setMainmfi7avg(Float Mainmfi7avg){
		mainmfi7avg = Mainmfi7avg;
	}
	
	public Float getMainmfi14avg(){
		return mainmfi14avg;
	}
	
	public void setMainmfi14avg(Float Mainmfi14avg){
		mainmfi14avg = Mainmfi14avg;
	}
	
	
	
	@Column(name = "fundIn")
	private int fundIn;

	@Column(name = "fundOut")
	private int fundOut;

	@Column(name = "netIn")
	private int netIn;

	@Column(name = "mainIn")
	private int mainIn;

	@Column(name = "mainOut")
	private int mainOut;

	@Column(name = "mainNetIn")
	private int mainNetIn;

	@Column(name = "AllMFI1", scale = 2)
	private Float AllMFI1;

	@Column(name = "AllMFI7", scale = 2)
	private Float AllMFI7;

	@Column(name = "AllMFI14", scale = 2)
	private Float AllMFI14;

	@Column(name = "MainMFI1", scale = 2)
	private Float MainMFI1;

	@Column(name = "MainMFI7", scale = 2)
	private Float MainMFI7;

	@Column(name = "MainMFI14", scale = 2)
	private Float MainMFI14;

	@Column(name = "AllMFI1dummy")
	private Integer AllMFI1dummy;

	@Column(name = "AllMFI7dummy")
	private Integer AllMFI7dummy;

	@Column(name = "AllMFI14dummy")
	private Integer AllMFI14dummy;

	@Column(name = "MAINMFI1dummy")
	private Integer MainMFI1dummy;

	@Column(name = "MAINMFI7dummy")
	private Integer MainMFI7dummy;

	@Column(name = "MAINMFI14dummy")
	private Integer MainMFI14dummy;
	
	@Column(name = "allmfi1avg")
	private Float allmfi1avg;
	
	@Column(name = "allmfi7avg")
	private Float allmfi7avg;
	
	@Column(name = "allmfi14avg")
	private Float allmfi14avg;
	
	@Column(name = "mainmfi1avg")
	private Float mainmfi1avg;
	
	@Column(name = "mainmfi7avg")
	private Float mainmfi7avg;
	
	@Column(name = "mainmfi14avg")
	private Float mainmfi14avg;
	

}
