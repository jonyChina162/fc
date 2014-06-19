/**
 * 
 */
package cn.whu.zl.hbase;

import java.io.IOException;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

import cn.whu.zl.hbase.dao.IndusReportDAO;

/**
 * @author B506-13-1
 *
 */
public class InitTables {
	private static Logger log = Logger.getLogger("initTables"); 
	
	public static void deleteAllTables(HBaseAdmin admin) throws IOException{
		log.info("drop all hbase tables,so pool");
		
		if (admin.tableExists(IndusReportDAO.TABLE_NAME)) {
	        log.info("Deleting " + Bytes.toString(IndusReportDAO.TABLE_NAME));
	        if (admin.isTableEnabled(IndusReportDAO.TABLE_NAME))
	          admin.disableTable(IndusReportDAO.TABLE_NAME);
	        admin.deleteTable(IndusReportDAO.TABLE_NAME);
	      }
	}
	
	public static void createAllTables(HBaseAdmin admin) throws IOException{
		
		if (admin.tableExists(IndusReportDAO.TABLE_NAME)) {
	        log.info("Followed table already exists.");
	      } else {
	        log.info("Creating Followed table...请叫我真命天子圣大人");
	        HTableDescriptor desc = new HTableDescriptor(IndusReportDAO.TABLE_NAME);
	        HColumnDescriptor c1 = new HColumnDescriptor(IndusReportDAO.INFO_FAM);
	        HColumnDescriptor c2 = new HColumnDescriptor(IndusReportDAO.CONTENT_FAM);
	        c1.setMaxVersions(1);
	        desc.addFamily(c1);
	        c2.setMaxVersions(1);
	        desc.addFamily(c2);
	        admin.createTable(desc);
	        log.info("I had to go,but you will always in my mind");
	      }
	}
	
	public static void main(String[] args) throws Exception{
		HBaseAdmin admin = new HBaseAdmin(HbaseConf.conf);
		
		deleteAllTables(admin);
		createAllTables(admin);
		
		
		
		admin.close();

	}

}