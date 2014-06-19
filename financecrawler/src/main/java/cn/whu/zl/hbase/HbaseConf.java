/**
 * 
 */
package cn.whu.zl.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;

/**
 * @author B506-13-1
 * 
 */
public class HbaseConf {
	public static Configuration conf = HBaseConfiguration.create();

	static {
<<<<<<< .mine
		conf.addResource(new Path("\\hbase-site.xml"));
		// conf.set("hbase.zookeeper.property.clientPort", "2181");
		// conf.set("hbase.zookeeper.quorum",
		// "192.168.1.128,192.168.1.137,192.168.1.138,192.168.1.140,192.168.1.142,192.168.1.143");
		 conf.set("hbase.master", "ubuntu00:60000");
=======
//		 conf.addResource(new Path("\\hbase-site.xml"));
		conf.set("hbase.zookeeper.property.clientPort", "2181");
		conf.set("hbase.zookeeper.quorum", "192.168.1.118");
		conf.set("hbase.master", "192.168.1.118:600000");
>>>>>>> .r74
	}

	public static void main(String[] args) {

		try {
			HBaseAdmin admin = new HBaseAdmin(conf);
			HTableDescriptor desc = new HTableDescriptor("test");
			if (admin.tableExists("test")) {
				System.out.println("test is exists");
				admin.deleteTable("test");
			}
			desc.addFamily(new HColumnDescriptor("test"));
			admin.createTable(desc);
			admin.close();

		} catch (Exception e) {
		}
	}

}
