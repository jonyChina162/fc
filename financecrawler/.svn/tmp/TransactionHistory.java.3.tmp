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
@Table(name="transactionhistory")
/*@NamedQuery(name="thCloPri",query="select max(cloPrice) from "
		+ "(select cloPrice from TransactionHistory where stockID=?1"
		+" and date >= ?2 limit ?3)as total")*/
public class TransactionHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	public TransactionHistory(){}
	
	private TransactionHistory(Builder builder) {
		this.sdPK = builder.sdPK;
		this.stockName = builder.stockName;
		this.cloPrice = builder.cloPrice;
		this.highPrice = builder.highPrice;
		this.lowPrice = builder.lowPrice;
		this.openPrice = builder.openPrice;
		this.beforeClo = builder.beforeClo;
		this.changeAmount = builder.changeAmount;
		this.changeRatio = builder.changeRatio;
		this.turnoverRate = builder.turnoverRate;
		this.turnover = builder.turnover;
		this.turnoverAmount = builder.turnoverAmount;
		this.MKTcap = builder.MKTcap;
		this.EFAMC = builder.EFAMC;
		this.RSI = builder.RSI;
		this.ATR7 = builder.ATR7;
		this.ATR14 = builder.ATR14;
		this.MACD = builder.MACD;
		this.Trade = builder.Trade;
		this.IfTradeup = builder.IfTradeup;
		this.IfTradedown = builder.IfTradedown;
		this.RSI6 = builder.RSI6;
		this.RSI12 = builder.RSI12;
		this.RSI24 = builder.RSI24;
		this.RSI6dummy = builder.RSI6dummy;
		this.RSI12dummy = builder.RSI12dummy;
		this.RSI24dummy = builder.RSI24dummy;
		this.RSI6avg = builder.RSI6avg;
		this.RSI12avg = builder.RSI12avg;
		this.RSI24avg = builder.RSI24avg;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sdPK == null) ? 0 : sdPK.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "TransactionHistory [sdPK=" + sdPK + ", stockName=" + stockName
				+ ", cloPrice=" + cloPrice + ", highPrice=" + highPrice
				+ ", lowPrice=" + lowPrice + ", openPrice=" + openPrice
				+ ", beforeClo=" + beforeClo + ", changeAmount=" + changeAmount
				+ ", changeRatio=" + changeRatio + ", turnoverRate="
				+ turnoverRate + ", turnover=" + turnover + ", turnoverAmount="
				+ turnoverAmount + ", MKTcap=" + MKTcap + ", EFAMC=" + EFAMC
				+ ", RSI=" + RSI + ", ATR7=" + ATR7 + ", ATR14=" + ATR14
				+ ", MACD=" + MACD + ", Trade=" + Trade + ", IfTradeup="
				+ IfTradeup + ", IfTradedown=" + IfTradedown + ", RSI6=" + RSI6
				+ ", RSI12=" + RSI12 + ", RSI24=" + RSI24 + ", RSI6dummy="
				+ RSI6dummy + ", RSI12dummy=" + RSI12dummy + ", RSI24dummy="
				+ RSI24dummy + ", RSI6avg=" + RSI6avg + ", RSI12avg="
				+ RSI12avg + ", RSI24avg=" + RSI24avg + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionHistory other = (TransactionHistory) obj;
		if (sdPK == null) {
			if (other.sdPK != null)
				return false;
		} else if (!sdPK.equals(other.sdPK))
			return false;
		return true;
	}

	public static class Builder implements EntityBuilder<TransactionHistory> {
		private StockDataPK sdPK;
		private String stockName;
		private Float cloPrice;
		private Float highPrice;
		private Float lowPrice;
		private Float openPrice;
		private Float beforeClo;
		private Float changeAmount;
		private Float changeRatio;
		private Float turnoverRate;
		private Long turnover;
		private Float turnoverAmount;
		private Float MKTcap;
		private Float EFAMC;
		private Float RSI;
		private Float ATR7;
		private Float ATR14;
		private Float MACD;
		private Integer Trade;
		private Integer IfTradeup;
		private Integer IfTradedown;
		private Float RSI6;
		private Float RSI12;
		private Float RSI24;
		private Integer RSI6dummy;
		private Integer RSI12dummy;
		private Integer RSI24dummy;
		private Float RSI6avg;
		private Float RSI12avg;
		private Float RSI24avg;
		

		@Override
		public String toString() {
			return "Builder [sdPK=" + sdPK + ", stockName=" + stockName
					+ ", cloPrice=" + cloPrice + ", highPrice=" + highPrice
					+ ", lowPrice=" + lowPrice + ", openPrice=" + openPrice
					+ ", beforeClo=" + beforeClo + ", changeAmount="
					+ changeAmount + ", changeRatio=" + changeRatio
					+ ", turnoverRate=" + turnoverRate + ", turnover="
					+ turnover + ", turnoverAmount=" + turnoverAmount
					+ ", MKTcap=" + MKTcap + ", EFAMC=" + EFAMC + ", RSI="
					+ RSI + ", ATR7=" + ATR7 + ", ATR14=" + ATR14 + ", MACD="
					+ MACD + ", Trade=" + Trade + ", IfTradeup=" + IfTradeup
					+ ", IfTradedown=" + IfTradedown + ", RSI6=" + RSI6
					+ ", RSI12=" + RSI12 + ", RSI24=" + RSI24 + ", RSI6dummy="
					+ RSI6dummy + ", RSI12dummy=" + RSI12dummy
					+ ", RSI24dummy=" + RSI24dummy + ", RSI6avg=" + RSI6avg
					+ ", RSI12avg=" + RSI12avg + ", RSI24avg=" + RSI24avg + "]";
		}

		public Builder(StockDataPK sdPK) {
			this.sdPK = sdPK;
		}

		public Builder stockName(String val) {
			this.stockName = val;
			return this;
		}

		public Builder cloPrice(Float val) {
			this.cloPrice = val;
			return this;
		}

		public Builder highPrice(Float val) {
			this.highPrice = val;
			return this;
		}

		public Builder lowPrice(Float val) {
			this.lowPrice = val;
			return this;
		}

		public Builder openPrice(Float val) {
			this.openPrice = val;
			return this;
		}

		public Builder changeAmount(Float val) {
			this.changeAmount = val;
			return this;
		}

		public Builder beforeClo(Float val) {
			this.beforeClo = val;
			return this;
		}

		public Builder changeRatio(Float val) {
			this.changeRatio = val;
			return this;
		}

		public Builder turnoverRate(Float val) {
			this.turnoverRate = val;
			return this;
		}

		public Builder turnover(long val) {
			this.turnover = val;
			return this;
		}

		public Builder turnoverAmount(Float val) {
			this.turnoverAmount = val;
			return this;
		}

		public Builder MKTcap(Float val) {
			this.MKTcap = val;
			return this;
		}

		public Builder EFAMC(Float val) {
			this.EFAMC = val;
			return this;
		}

		
		public Builder RSI(Float val){
			this.RSI = val;
			return this;
		}
		
		public Builder ATR7(Float val){
			this.ATR7 = val;
			return this;
		}
		
		public Builder ATR14(Float val){
			this.ATR14 = val;
			return this;
		}
		
		public Builder MACD(Float val){
			this.MACD = val;
			return this;
		}
		
		public Builder Trade(int val){
			this.Trade = val;
			return this;
		}
		
		public Builder IfTradeUp(int val){
			this.IfTradeup = val;
			return this;
		}
		
		public Builder IfTradeDown(int val){
			this.IfTradedown = val;
			return this;
		}
		
		public Builder RSI6(Float val){
			this.RSI6 = val;
			return this;
		}
		
		public Builder RSI12(Float val){
			this.RSI12 = val;
			return this;
		}
		
		public Builder RSI24(Float val){
			this.RSI24 = val;
			return this;
		}
		
		public Builder RSI6dummy(int val){
			this.RSI6dummy = val;
			return this;
		}
		
		public Builder RSI12dummy(int val){
			this.RSI12dummy = val;
			return this;
		}
		
		public Builder RSI24dummy(int val){
			this.RSI24dummy = val;
			return this;
		}
		
		public Builder RSI6avg(Float val){
			this.RSI6avg = val;
			return this;
		}
		
		public Builder RSI12avg(Float val){
			this.RSI12avg = val;
			return this;
		}
		
		public Builder RSI24avg(Float val){
			this.RSI24avg = val;
			return this;
		}
		
		@Override
		public TransactionHistory build() {
			return new TransactionHistory(this);
		}

	}
	
	public StockDataPK getSdPK() {
		return sdPK;
	}

	public void setSdPK(StockDataPK sdPK) {
		this.sdPK = sdPK;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public Float getCloPrice() {
		return cloPrice;
	}

	public void setCloPrice(Float cloPrice) {
		this.cloPrice = cloPrice;
	}

	public Float getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(Float highPrice) {
		this.highPrice = highPrice;
	}

	public Float getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(Float lowPrice) {
		this.lowPrice = lowPrice;
	}

	public Float getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(Float openPrice) {
		this.openPrice = openPrice;
	}

	public Float getBeforeClo() {
		return beforeClo;
	}

	public void setBeforeClo(Float beforeClo) {
		this.beforeClo = beforeClo;
	}

	public Float getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(Float changeAmount) {
		this.changeAmount = changeAmount;
	}

	public Float getChangeRatio() {
		return changeRatio;
	}

	public void setChangeRatio(Float changeRatio) {
		this.changeRatio = changeRatio;
	}

	public Float getTurnoverRate() {
		return turnoverRate;
	}

	public void setTurnoverRate(Float turnoverRate) {
		this.turnoverRate = turnoverRate;
	}

	public long getTurnover() {
		return turnover;
	}

	public void setTurnover(long turnover) {
		this.turnover = turnover;
	}

	public Float getTurnoverAmount() {
		return turnoverAmount;
	}

	public void setTurnoverAmount(Float turnoverAmount) {
		this.turnoverAmount = turnoverAmount;
	}

	public Float getMKTcap() {
		return MKTcap;
	}

	public void setMKTcap(Float mKTcap) {
		MKTcap = mKTcap;
	}

	public Float getEFAMC() {
		return EFAMC;
	}

	public void setEFAMC(Float eFAMC) {
		EFAMC = eFAMC;
	}
	
	
	public Float getRSI(){
		return RSI;
	}
	
	public void setRSI(Float rsi){
		RSI = rsi;
	}
	
	public Float getATR7(){
		return ATR7;
	}
	
	public void setATR7(Float atr7){
		ATR7 = atr7;
	}
	
	public Float getATR14(){
		return ATR14;
	}
	
	public void setATR14(Float atr14){
		ATR14 = atr14;
	}
	
	public Float getMACD(){
		return MACD;
	}
	
	public void setMACD(Float macd){
		MACD = macd;
	}
	
	public int getTrade(){
		return Trade;
	}
	
	public void setTrade(int trade){
		Trade = trade;
	}

	public Integer getIfTradeup() {
		return IfTradeup;
	}

	public void setIfTradeup(Integer ifTradeup) {
		IfTradeup = ifTradeup;
	}

	public Integer getIfTradedown() {
		return IfTradedown;
	}

	public void setIfTradedown(Integer ifTradedown) {
		IfTradedown = ifTradedown;
	}

	public Float getRSI6() {
		return RSI6;
	}

	public void setRSI6(Float rSI6) {
		RSI6 = rSI6;
	}

	public Float getRSI12() {
		return RSI12;
	}

	public void setRSI12(Float rSI12) {
		RSI12 = rSI12;
	}

	public Float getRSI24() {
		return RSI24;
	}

	public void setRSI24(Float rSI24) {
		RSI24 = rSI24;
	}

	public int getRSI6dummy() {
		return RSI6dummy;
	}

	public void setRSI6dummy(int rSI6dummy) {
		RSI6dummy = rSI6dummy;
	}

	public int getRSI12dummy() {
		return RSI12dummy;
	}

	public void setRSI12dummy(int rSI12dummy) {
		RSI12dummy = rSI12dummy;
	}

	public int getRSI24dummy() {
		return RSI24dummy;
	}

	public void setRSI24dummy(int rSI24dummy) {
		RSI24dummy = rSI24dummy;
	}
	
	public Float getRSI6avg(){
		return RSI6avg;
	}
	
	public void setRSI6avg(Float rsi6avg){
		RSI6avg = rsi6avg;
	}
	public Float getRSI12avg(){
		return RSI12avg;
	}
	
	public void setRSI12avg(Float rsi12avg){
		RSI12avg = rsi12avg;
	}
	public Float getRSI24avg(){
		return RSI24avg;
	}
	
	public void setRSI24avg(Float rsi24avg){
		RSI24avg = rsi24avg;
	}

	@EmbeddedId
	private StockDataPK sdPK;

	@Column(name = "stockName")
	private String stockName;

	@Column(name = "cloPrice", scale = 2)
	private Float cloPrice;

	@Column(name = "highPrice", scale = 2)
	private Float highPrice;

	@Column(name = "lowPrice", scale = 2)
	private Float lowPrice;

	@Column(name = "openPrice", scale = 2)
	private Float openPrice;

	@Column(name = "beforeClo", scale = 2)
	private Float beforeClo;

	@Column(name = "changeAmount", scale = 2)
	private Float changeAmount;

	@Column(name = "changeRatio", scale = 2)
	private Float changeRatio;

	@Column(name = "turnoverRate", scale = 2)
	private Float turnoverRate;

	@Column(name = "turnover")
	private Long turnover;

	@Column(name = "turnoverAmount", scale = 2)
	private Float turnoverAmount;

	@Column(name = "MKTcap", scale = 2)
	private Float MKTcap;

	@Column(name = "EFAMC", scale = 2)
	private Float EFAMC;
	
	@Column(name = "RSI",scale = 2)
	private Float RSI;
	
	@Column(name = "ATR7",scale = 2)
	private Float ATR7;
	
	@Column(name = "ATR14",scale = 2)
	private Float ATR14;
	
	@Column(name = "MACD",scale = 2)
	private Float MACD;
	
	@Column(name = "Trade")
	private Integer Trade;

	@Column(name = "IfTradeup")
	private Integer IfTradeup;
	
	@Column(name = "IfTradedown")
	private Integer IfTradedown;
	
	@Column(name = "RSI6",scale = 2)
	private Float RSI6;
	
	@Column(name = "RSI12",scale = 2)
	private Float RSI12;
	
	@Column(name = "RSI24",scale = 2)
	private Float RSI24;
	
	@Column(name = "rsi6dummy")
	private int RSI6dummy;
	
	@Column(name = "rsi12dummy")
	private int RSI12dummy;
	
	@Column(name = "rsi24dummy")
	private int RSI24dummy;
	
	@Column(name = "rsi6avg")
	private Float RSI6avg;
	
	@Column(name = "rsi12avg")
	private Float RSI12avg;
	
	@Column(name = "rsi24avg")
	private Float RSI24avg;
	
}
