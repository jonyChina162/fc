/**
 * 
 */
package cn.whu.zl.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.log4j.Logger;

import cn.whu.zl.entity.FundFlowHistory;
import cn.whu.zl.entity.TransactionHistory;

/**
 * @author B506-13-1
 *
 */
public class FormulaUtil {
	private static final Logger log = Logger.getLogger("FormulaUtil.class");
	
	public static float[] CalMFI(List<FundFlowHistory> stockFund,
			String AllOrMain, int day) {

		log.debug("calculate " + AllOrMain + "_MFI_" + day + "begin");

		int[] pn = { 0, 0 };
		int number = 0;
		float[] mfi = new float[stockFund.size()];
		Queue<Integer> qIn = new LinkedList<Integer>();
		Queue<Integer> qOut = new LinkedList<Integer>();

		for (int i = 0; i < stockFund.size(); i++) {

			if (number < day - 1) {
				AddFund(qIn, qOut, stockFund.get(i), AllOrMain, pn);
				number++;
			}

			if (number >= day - 1 && number < stockFund.size()) {
				if (day == 1 && qIn.size() != 0) {
					pn[0] -= qIn.poll();
					pn[1] -= qOut.poll();
				}

				if (day == 7 && number != 6) {
					pn[0] -= qIn.poll();
					pn[1] -= qOut.poll();
				}

				if (day == 14 && number !=13){
					pn[0] -= qIn.poll();
					pn[1] -= qOut.poll();
				}
				
				AddFund(qIn, qOut, stockFund.get(number), AllOrMain, pn);
				
				//in case the divisor is 0 
				if((pn[0]+pn[1]) > -(1e-10) && (pn[0]+pn[1]) < 1e-10)
					mfi[number] = 0;
				else
					mfi[number] = 100 * ((float) pn[0] / (float) (pn[0] + pn[1]));
				
				number++;
			}
		}

		//log.info(AllOrMain + "_MFI_" + day + ":" + Arrays.toString(mfi));

		log.debug("calculate " + AllOrMain + "_MFI_" + day + "end");
		return mfi;
	}

	public static void AddFund(Queue<Integer> qIn, Queue<Integer> qOut,
			FundFlowHistory ffh, String AllOrMain, int[] pn) {

		switch (AllOrMain) {
		case "all": {
			qIn.offer(ffh.getFundIn());
			qOut.offer(ffh.getFundOut());
			pn[0] += ffh.getFundIn();
			pn[1] += ffh.getFundOut();
			break;
		}
		case "main": {
			qIn.offer(ffh.getMainIn());
			qOut.offer(ffh.getMainOut());
			pn[0] += ffh.getFundIn();
			pn[1] += ffh.getFundOut();
			break;
		}
		}

	}

	public static float[] CalRSI(List<TransactionHistory> stockTransaction,int day) {
		log.debug("calculate RSI begin");

		float[] rsi = new float[stockTransaction.size()];
		float AG = 0f;
		float AL = 0f;
		float RS = 0f;
		float[] GL = new float[2];

		for (int i = 1; i < stockTransaction.size(); i++) {

			TransactionHistory temp = stockTransaction.get(i);

			if (i <= day) {
				rsi[i] = 0f;
				GL = CalGL(temp, GL);
				if (i == day) {
					if (GL[1] > -(1e-10) && GL[1] < 1e-10)
						rsi[i] = 100;
					else {
						AG = GL[0] / day;
						AL = GL[1] / day;
						RS = GL[0] / GL[1];
						rsi[i] = 100f - (100f / (1 + RS));
					}
				}
			}

			if (i > day) {
				GL[0] = AG * (day-1);
				GL[1] = AL * (day-1);
				GL = CalGL(temp, GL);
				if (GL[1] > -(1e-10) && GL[1] < 1e-10)
					rsi[i] = 100;
				else {
					AG = GL[0] / day;
					AL = GL[1] / day;
					RS = GL[0] / GL[1];
					rsi[i] = 100f - (100f / (1 + RS));
				}
			}

		}
		//log.info("RSI:"+Arrays.toString(rsi));
		log.debug("calculate RSI end");
		
		return rsi;
	}

	public static float[] CalGL(TransactionHistory temp, float[] gl) {

		if (temp.getChangeAmount() > 1e-10)
			gl[0] += Math.abs(temp.getChangeAmount());
		else
			gl[1] += Math.abs(temp.getChangeAmount());

		return gl;
	}

	public static float[] CalATR(List<TransactionHistory> stockTransaction,
			int day) {
		log.debug("calculate ATR" + day + "begin");

		float lastClose = stockTransaction.get(0).getCloPrice();
		float TR = 0f;
		float[] ATR = new float[stockTransaction.size()];
		TransactionHistory temp = null;

		for (int i = 1; i < stockTransaction.size(); i++) {

			temp = stockTransaction.get(i);
			if (i <= day) {
				ATR[i] = 0f;
				TR += Math.max(temp.getHighPrice(), lastClose)
						- Math.min(temp.getLowPrice(), lastClose);
				lastClose = temp.getCloPrice();
				ATR[i] = TR / day;
			}

			if (i > day) {
				TR = Math.max(temp.getHighPrice(), lastClose)
						- Math.min(temp.getLowPrice(), lastClose);
				ATR[i] = (ATR[i - 1] * (day - 1) + TR) / day;
			}
		}

		//log.info("ATR" + day + ":" + Arrays.toString(ATR));
		log.debug("calculate ATR" + day + "end");
		return ATR;

	}

	public static float[] CalMACD(List<TransactionHistory> stockTransaction) {
		log.debug("calculate MACD begin");

		int total = stockTransaction.size();
		float[] cloPriceList = new float[total];
		float[] day12EMA = new float[total];
		float[] day26EMA = new float[total];
		float[] difference = new float[total];
		float[] signal = new float[total];
		float[] MACD = new float[total];

		for (int i = 0; i < total; i++) {
			cloPriceList[i] = stockTransaction.get(i).getCloPrice();
		}

		day12EMA = CalEMA(cloPriceList, 12);
		day26EMA = CalEMA(cloPriceList, 26);

		for (int i = 0; i < total; i++) {
			if (day26EMA[i] > -(1e-10) && day26EMA[i] < 1e-10 )
				difference[i] = 0f;
			else
				difference[i] = day12EMA[i] - day26EMA[i];
		}

		signal = CalEMA(difference, 9);
		
		for (int i = 0; i < total; i++) {
			
			if (signal[i] > -(1e-10) && signal[i] < 1e-10)
				MACD[i] = 0f;
			else
				MACD[i] = difference[i] - signal[i];
		}

		/*
		 * log.info("day12EMA:" + Arrays.toString(day12EMA));
		 * log.info("day26EMA:" + Arrays.toString(day26EMA));
		 * log.info("difference:" + Arrays.toString(difference));
		 * log.info("signal:" + Arrays.toString(signal)); log.info("MACD:" +
		 * Arrays.toString(MACD));
		 */
		
		//log.info("MACD:"+Arrays.toString(MACD));
		log.debug("calculate MACD end");
		return MACD;
	}

	// 计算是否应该发出trade，从负跳正为买入1，从正跳负为卖出-1，其余为0
	public static int[] CalTrade(float[] MACD) {

		log.debug("calculate trade begin");

		int[] trade = new int[MACD.length];
		float temp = MACD[0];
		for (int i = 1; i < MACD.length; i++) {
			if (temp > -(1e-10) && temp < 1e-10) {
				trade[i] = 0;
			} else if (temp < -(1e-10) && MACD[i] > 1e-10) {
				trade[i] = 1;
			} else if (temp > 1e-10 && MACD[i] < -(1e-10)) {
				trade[i] = -1;
			} else
				trade[i] = 0;

			temp = MACD[i];
		}

		//log.info("Trade : " + Arrays.toString(trade));
		log.debug("calculate trade end");

		return trade;
	}

	// 计算过去5天是否发出trade=1信号
	public static int[] CalIfTrade(int[] trade,int symbol) {
		int len = trade.length;
		int[] ifTrade = new int[len];
		for (int i = 0; i < len; i++) {
			if (trade[i] == symbol) {
				int temp = 5;
				for (int j = 0; j < 5 && (j+i) < len; j++) {
					ifTrade[j + i] = symbol;
					if (trade[j + i] == symbol)
						temp = j;
				}
				i += temp;
				continue;
			}
		}
		return ifTrade;

	}

	//day12EMA出现0值的原因是cloPrice有0值，之前判断时没有 i <= number，所以遇到0值就把EMA置为0，number也自增，结果错误
	//现在是0值也直接按公式参与计算，需要询问cloprice的0值是跳过还是怎么处理
	//day12EMA和day26EMA无零值的话，difference不会有0值，后面的signal也不会在中途出现0值
	public static float[] CalEMA(float[] list, int day) {

		float[] EMA = new float[list.length];
		float SMA = 0f;
		float multiplier = (float) 2 / (day + 1);
		int number = 0;

		for (int i = 0; i < list.length; i++) {
			if (list[i] < (1e-10) && list[i] > -(1e-10) && i <= number) {
				EMA[i] = 0;
				number++;
				continue;
			}
			if (i < day - 1  + number) {
				EMA[i] = 0f;
				SMA += list[i];
			}
			if (i >= day - 1  + number) {
				if (i == day - 1 + number){
					SMA += list[i];
					EMA[i] = SMA / day;
				}else
					EMA[i] = (list[i] - EMA[i - 1]) * multiplier + EMA[i - 1];
			}
		}

		//log.info("EMA : " + Arrays.toString(EMA));
		return EMA;

	}
}
