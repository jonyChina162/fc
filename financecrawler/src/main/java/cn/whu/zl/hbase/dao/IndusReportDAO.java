package cn.whu.zl.hbase.dao;

import static cn.whu.zl.util.MD5Util.str2MD516;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

import cn.whu.zl.hbase.model.IndusReports;

public class IndusReportDAO {

	public static final byte[] TABLE_NAME = Bytes.toBytes("ids");
	public static final byte[] INFO_FAM = Bytes.toBytes("if");
	public static final byte[] CONTENT_FAM = Bytes.toBytes("ct");

	public static final byte[] ORGNAME_COL = Bytes.toBytes("oname");
	public static final byte[] INDUSNAME_COL = Bytes.toBytes("iname");
	public static final byte[] PCR_COL = Bytes.toBytes("pcr");
	public static final byte[] CONTENT_COL = Bytes.toBytes("rc");
	public static final byte[] NUM_COL = Bytes.toBytes("num");

	private static final Logger log = Logger.getLogger(IndusReportDAO.class);

	private HTablePool pool;

	public IndusReportDAO(HTablePool pool) {
		this.pool = pool;
	}

	private static Get mkGet(String id) throws IOException {
		log.debug(String.format("Creating Get for %s", id));

		Get g = new Get(Bytes.toBytes(id));

		return g;
	}

	private static Put mkPut(IndusReports ir) {
		log.debug(String.format("Creating Put for %s", ir));

		StringBuilder sb = new StringBuilder(37);
		sb.append(ir.time).append("_").append(ir.grade).append("_")
				.append(ir.graChange).append("_").append(ir.orgImpact)
				.append("_");
		sb.append(str2MD516(ir.url));

		Put p = new Put(Bytes.toBytes(sb.toString()));
		p.add(INFO_FAM, ORGNAME_COL, Bytes.toBytes(ir.orgImpact));
		p.add(INFO_FAM, INDUSNAME_COL, Bytes.toBytes(ir.indusName));
		p.add(INFO_FAM, PCR_COL, Bytes.toBytes(ir.priceCR));

		p.add(CONTENT_FAM, CONTENT_COL, Bytes.toBytes(ir.reContent));
		return p;
	}

	public static Put mkPut(long time, int grade, int graChange, int orgImpact,
			String url, String fam, String qual, String val) {
		StringBuilder sb = new StringBuilder(37);
		sb.append(time).append("_").append(grade).append("_").append(graChange)
				.append("_").append(orgImpact).append("_");
		sb.append(str2MD516(url));
		return mkPut(sb.toString(), Bytes.toBytes(fam), Bytes.toBytes(qual), Bytes.toBytes(val));
	}

	public static Put mkPut(String key, byte[] fam, byte[] qual, byte[] val) {
		Put p = new Put(Bytes.toBytes(key));
		p.add(fam, qual, val);
		return p;
	}

	private static Delete mkDel(String ir) {
		log.debug(String.format("Creating Delete for %s", ir));

		Delete d = new Delete(Bytes.toBytes(ir));
		return d;
	}

	private static Scan mkScan() {
		Scan s = new Scan();
		return s;
	}
	
	private static Scan infoScan(){
		Scan s = new Scan();
		s.addFamily(INFO_FAM);
		return s;
	}

	public void add(long time, int grade, int graChange, int orgImpact,
			String url, byte[] fam, byte[] qual, byte[] val)
			throws IOException {

		HTableInterface t = pool.getTable(TABLE_NAME);
		
		StringBuilder sb = new StringBuilder(37);
		sb.append(time).append("_").append(grade).append("_").append(graChange)
				.append("_").append(orgImpact).append("_");
		sb.append(str2MD516(url));

		Put p = mkPut(sb.toString(), fam, qual, val);
		t.put(p);

		t.close();
	}

	public IndusReports get(String key)
			throws IOException {
		HTableInterface t = pool.getTable(TABLE_NAME);

		Get g = mkGet(key);
		Result result = t.get(g);
		if (result.isEmpty()) {
			log.info(String.format("indusReport %s not found.", key));
			return null;
		}

		IndusReports irs = new IndusReports(key) ;
		return irs;
	}

	public void delete(String key) throws IOException {
		HTableInterface t = pool.getTable(TABLE_NAME);

		Delete d = mkDel(key);
		t.delete(d);

		t.close();
	}

	public List<IndusReports> getAll()
			throws IOException {
		HTableInterface t = pool.getTable(TABLE_NAME);

		ResultScanner results = t.getScanner(mkScan());
		ArrayList<IndusReports> ret = new ArrayList<IndusReports>();
		//TODO
		for (Result r : results) {
			ret.add(null);
		}

		t.close();
		return ret;
	}
	
	public List<IndusReports> getInfoList()
			throws IOException {
		HTableInterface t = pool.getTable(TABLE_NAME);

		ResultScanner results = t.getScanner(infoScan());
		ArrayList<IndusReports> ret = new ArrayList<IndusReports>();
		//TODO
		for (Result r : results) {
			ret.add(null);
		}

		t.close();
		return ret;
	}

	public long incReportsCount(String key) throws IOException {
		HTableInterface t = pool.getTable(TABLE_NAME);

		long ret = t.incrementColumnValue(Bytes.toBytes(key), INFO_FAM,
				NUM_COL, 1L);

		t.close();
		return ret;
	}
}
