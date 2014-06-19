package cn.whu.zl.util;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class CrawlerUtil {
	public static void changeCharset(String str, String nCharset)
			throws UnsupportedEncodingException {
		changeCharset(str, ISO_8859_1, nCharset);
	}

	public static void changeCharset(String str, String oCharset,
			String nCharset) throws UnsupportedEncodingException {
		str = new String(str.getBytes(oCharset), nCharset);
	}

	public static float strToFloat(String s) {
		if (null == s || s.length() == 0)
			return 0.0f;
		if (s.matches("[-\\s]+"))
			return 0.0f;
		else
			return Float.parseFloat(new String(s));
	}

	public static long strToLong(String s) {
		if (null == s || s.length() == 0)
			return 0l;
		if (s.matches("[-\\s]+"))
			return 0l;
		else
			return Long.parseLong(new String(s));
	}

	public static int strToInt(String s) {
		if (null == s || s.length() == 0)
			return 0;
		if (s.matches("[-\\s]+"))
			return 0;
		else
			return Integer.parseInt(new String(s));
	}

	public static int getIndexFromArr(String str, String[] strArr) {
		int index = -1;
		if (null == str || str.length() != 0)
			for (int i = 0; i < strArr.length; i++)
				if (str.equals(strArr[i])) {
					index = i;
					break;
				}
		return index;
	}

	public static int sumList(List<Integer> list) {
		int result = 0;
		for (int f : list)
			result += f;
		return result;
	}

	public static float minE0(List<Float> list) {
		float min = Float.MAX_VALUE;
		for (float f : list)
			if ((f - 0.0f) > 1e-10 && (f - min) < 1e-10)
				min = f;
		return min;
	}

	public static String[] getMarket(String stockID) {
		String[] market = null;
		String symbol = stockID.substring(0, 3);
		switch (symbol) {

		case "002":
			market = new String[] { "中小板", "399005" };
			break;
		case "300":
			market = new String[] { "创业板", "399006" };
			break;
		case "000":
			market = new String[] { "深市A股", "000300" };
			break;
		case "600":
			market = new String[] { "沪市A股", "000300" };
			break;
		case "601":
			market = new String[] { "沪市A股", "000300" };
			break;
		case "603":
			market = new String[] { "沪市A股", "000300" };
			break;
		case "001":
			market = new String[] { "深市A股", "000300" };
			break;
		default:
			market = new String[] { "未知", "000300" };
			break;

		}
		return market;
	}

	public static String[] getMarketDummy(String stockID) {
		String[] market = null;
		String symbol = stockID.substring(0, 3);
		switch (symbol) {

		case "002":
			market = new String[] { "3", "399005" };
			break;
		case "300":
			market = new String[] { "4", "399006" };
			break;
		case "000":
			market = new String[] { "2", "000300" };
			break;
		case "600":
			market = new String[] { "1", "000300" };
			break;
		case "601":
			market = new String[] { "1", "000300" };
			break;
		case "603":
			market = new String[] { "1", "000300" };
			break;
		case "001":
			market = new String[] { "2", "000300" };
			break;
		default:
			market = new String[] { "-1", "000300" };
			break;
		}
		return market;
	}

	public static final int getGChangeDummy(String s) {
		int gradeChangeDummy = -1;
		switch (s) {

		case "首次":
			gradeChangeDummy = 1;
			break;
		case "调高":
			gradeChangeDummy = 2;
			break;
		case "维持":
			gradeChangeDummy = 4;
			break;
		case "调低":
			gradeChangeDummy = 5;
			break;
		default:
			break;
		}
		return gradeChangeDummy;
	}

	public static final int getJrjGradeDummy(String s) {
		int i = -1;
		switch (s) {
		case "买入":
			i = 1;
			break;
		case "增持":
			i = 2;
			break;
		case "中性":
			i = 3;
			break;
		case "减持":
			i = 4;
			break;
		case "卖出":
			i = 5;
			break;
		default:
			break;
		}
		return i;
	}

	public static final int getJrjGChangeDummy(String s) {
		int i = -1;
		switch (s) {
		case "调高":
			i = 1;
			break;
		case "首次":
			i = 2;
			break;
		case "第一次维持":
			i = 3;
			break;
		case "维持":
			i = 4;
			break;
		case "调低":
			i = 5;
			break;
		case "无":
			i = 6;
			break;
		default:
			break;
		}
		return i;
	}

	public static int getMFIDummy(float a, float b) {
		int dummy = -1;
		if (b > 0 && b <= a - 20)
			dummy = 1;
		else if (b <= a - 13)
			dummy = 2;
		else if (b <= a - 8)
			dummy = 3;
		else if (b <= a - 5)
			dummy = 4;
		else if (b <= a - 3)
			dummy = 5;
		else if (b <= a - 1.75)
			dummy = 6;
		else if (b <= a - 0.75)
			dummy = 7;
		else if (b <= a - 0.25)
			dummy = 8;
		else if (b <= a + 0.25)
			dummy = 9;
		else if (b <= a + 0.75)
			dummy = 10;
		else if (b <= a + 1.75)
			dummy = 11;
		else if (b <= a + 3)
			dummy = 12;
		else if (b <= a + 5)
			dummy = 13;
		else if (b <= a + 8)
			dummy = 14;
		else if (b <= a + 13)
			dummy = 15;
		else if (b <= a + 20)
			dummy = 16;
		else if (b <= 100)
			dummy = 17;

		return dummy;
	}
	
	public static int getMFIMainDummy(float a, float b) {
		int dummy = -1;
		if (b > 0 && b <= a - 9)
			dummy = 1;
		else if (b <= a - 6)
			dummy = 2;
		else if (b <= a - 4)
			dummy = 3;
		else if (b <= a - 2.5)
			dummy = 4;
		else if (b <= a - 1.5)
			dummy = 5;
		else if (b <= a - 0.8)
			dummy = 6;
		else if (b <= a - 0.3)
			dummy = 7;
		else if (b <= a - 0.1)
			dummy = 8;
		else if (b <= a + 0.1)
			dummy = 9;
		else if (b <= a + 0.3)
			dummy = 10;
		else if (b <= a + 0.8)
			dummy = 11;
		else if (b <= a + 1.5)
			dummy = 12;
		else if (b <= a + 2.5)
			dummy = 13;
		else if (b <= a + 4)
			dummy = 14;
		else if (b <= a + 6)
			dummy = 15;
		else if (b <= a + 9)
			dummy = 16;
		else if (b <= 100)
			dummy = 17;

		return dummy;
	}
	
	public static int getRSI6Dummy(float a, float b) {
		int dummy = -1;
		if (b > 0 && b <= a - 20)
			dummy = 1;
		else if (b <= a - 13)
			dummy = 2;
		else if (b <= a - 8)
			dummy = 3;
		else if (b <= a - 5)
			dummy = 4;
		else if (b <= a - 3)
			dummy = 5;
		else if (b <= a - 1.75)
			dummy = 6;
		else if (b <= a - 0.75)
			dummy = 7;
		else if (b <= a - 0.25)
			dummy = 8;
		else if (b <= a + 0.25)
			dummy = 9;
		else if (b <= a + 0.75)
			dummy = 10;
		else if (b <= a + 1.75)
			dummy = 11;
		else if (b <= a + 3)
			dummy = 12;
		else if (b <= a + 5)
			dummy = 13;
		else if (b <= a + 8)
			dummy = 14;
		else if (b <= a + 13)
			dummy = 15;
		else if (b <= a + 20)
			dummy = 16;
		else if (b <= 100)
			dummy = 17;

		return dummy;
	}
	
	public static int getRSI12Dummy(float a, float b) {
		int dummy = -1;
		if (b > 0 && b <= a - 20)
			dummy = 1;
		else if (b <= a - 13)
			dummy = 2;
		else if (b <= a - 8)
			dummy = 3;
		else if (b <= a - 5)
			dummy = 4;
		else if (b <= a - 3)
			dummy = 5;
		else if (b <= a - 1.75)
			dummy = 6;
		else if (b <= a - 0.75)
			dummy = 7;
		else if (b <= a - 0.25)
			dummy = 8;
		else if (b <= a + 0.25)
			dummy = 9;
		else if (b <= a + 0.75)
			dummy = 10;
		else if (b <= a + 1.75)
			dummy = 11;
		else if (b <= a + 3)
			dummy = 12;
		else if (b <= a + 5)
			dummy = 13;
		else if (b <= a + 8)
			dummy = 14;
		else if (b <= a + 13)
			dummy = 15;
		else if (b <= a + 20)
			dummy = 16;
		else if (b <= 100)
			dummy = 17;

		return dummy;
	}
	
	public static int getRSI24Dummy(float a, float b) {
		int dummy = -1;
		if (b > 0 && b <= a - 9)
			dummy = 1;
		else if (b <= a - 6)
			dummy = 2;
		else if (b <= a - 4)
			dummy = 3;
		else if (b <= a - 2.5)
			dummy = 4;
		else if (b <= a - 1.5)
			dummy = 5;
		else if (b <= a - 0.8)
			dummy = 6;
		else if (b <= a - 0.3)
			dummy = 7;
		else if (b <= a - 0.1)
			dummy = 8;
		else if (b <= a + 0.1)
			dummy = 9;
		else if (b <= a + 0.3)
			dummy = 10;
		else if (b <= a + 0.8)
			dummy = 11;
		else if (b <= a + 1.5)
			dummy = 12;
		else if (b <= a + 2.5)
			dummy = 13;
		else if (b <= a + 4)
			dummy = 14;
		else if (b <= a + 6)
			dummy = 15;
		else if (b <= a + 9)
			dummy = 16;
		else if (b <= 100)
			dummy = 17;

		return dummy;
	}
	
	public static int getEFAMCDummy(Float EFAMC){
		Float TrueEFAMC = EFAMC / (float)Math.pow(10, 8);
		int dummy;
		if(TrueEFAMC > 0 && TrueEFAMC <= 5)
			dummy = 1;
		else if(TrueEFAMC <= 15)
			dummy = 2;
		else if(TrueEFAMC <= 35)
			dummy = 3;
		else if(TrueEFAMC <= 75)
			dummy = 4;
		else if(TrueEFAMC <= 155)
			dummy = 5;
		else if(TrueEFAMC <= 315)
			dummy = 6;
		else if(TrueEFAMC <= 635)
			dummy = 7;
		else if(TrueEFAMC <= 955)
			dummy = 8;
		else if(TrueEFAMC <= 1275)
			dummy = 9;
		else if(TrueEFAMC <= 1595)
			dummy = 10;
		else if(TrueEFAMC <= 3000)
			dummy = 11;
		else if(TrueEFAMC <= 6000)
			dummy = 12;
		else if(TrueEFAMC <= 9000)
			dummy = 13;
		else if(TrueEFAMC <= 12000)
			dummy = 14;
		else
			dummy = 15;
		
		return dummy;
	}

	public static final String UTF_8 = "utf-8";
	public static final String GBK = "gbk";
	public static final String US_ASCII = "US-ASCII";
	public static final String ISO_8859_1 = "ISO-8859-1";
}
