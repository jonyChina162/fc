package cn.whu.zl.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.whu.zl.dao.InstitutionNameDao;
import cn.whu.zl.entity.InstitutionName;
import cn.whu.zl.util.CrawlerUtil;

@Service("InstitutionNameSer")
@Transactional
public class InstitutionNameService extends BasicCrawServiceImp {

	@Autowired
	private InstitutionNameDao institutionNameDao;

	private static final Logger log = Logger
			.getLogger(InstitutionNameService.class.getName());

	public InstitutionNameService() {
		log.debug("Crawl Institution Name initialization begin");

		seed = "http://app.jrj.com.cn/appdata/data.php";

		try {
			uriBuilder = new URIBuilder(seed);
		} catch (URISyntaxException uso) {
			log.error("Fatal error : " + uso);
			Runtime.getRuntime().exit(-1);
		}

		uriBuilder.setParameter("cid", "117").setParameter("vname", "ycjg");

		initiaHttpClient();

		log.debug("Institution crawler initialization end");
	}

	@Override
	public void start() {
		CrawInstitutionName();
	}

	@Override
	public void run() {

	}

	@Override
	public void close() {

	}

	public List<InstitutionName> getAllList(int recordNo, int pageSize) {
		return institutionNameDao.getAllList(recordNo, pageSize);
	}

	private void CrawInstitutionName() {
		CrawInstitutionName(CrawlerUtil.GBK);
	}

	private void CrawInstitutionName(String charset) {
		log.info("Craw Institution Name begin");
		HttpGet httpget = null;
		try {
			httpget = new HttpGet(uriBuilder.build());
			HttpResponse response = client.execute(httpget);
			StatusLine status = response.getStatusLine();
			int reTry = 0;
			while (status.getStatusCode() != HttpStatus.SC_OK) {
				reTry++;

				if (reTry == 10) {
					log.error("Request failed : " + status);
					throw new ClientProtocolException(status.toString());
				}
				httpget.releaseConnection();
				response = client.execute(httpget);
				status = response.getStatusLine();
			}

			HttpEntity entity = response.getEntity();
			String ycjg = EntityUtils.toString(entity, charset);
			String subycjg = ycjg.substring(16, ycjg.length() - 3);
			String[] insNames = subycjg.split("]");

			String insID = null;
			String insName = null;

			for (int i = 2; i <= insNames.length - 1; i++) {
				int size = insNames[i].length();
				insID = insNames[i].substring(3, 12);
				insName = insNames[i].substring(15, size - 1);
				if (insName == null || insName.length() == 0)
					continue;
				// System.out.println(insID+insName);
				saveToDB(insID, insName);
			}


		} catch (ClientProtocolException cpe) {
			log.error("Fatal protocol violation : " + cpe);
		} catch (IOException ioe) {
			log.error("Fatal IO error : " + ioe);
		} catch (Exception e) {
			log.error("Fatal transport error: " + e.getMessage());
		} finally {
			httpget.releaseConnection();
		}
	}

	@Transactional
	private void saveToDB(String ID, String name) {
		try {
			InstitutionName iname = new InstitutionName(ID, name);
			log.debug("write " + iname.toString() + " to DB begin");
			institutionNameDao.save(iname);
			log.debug("write " + iname.toString() + " to DB end");
		} catch (Exception e) {
			log.error(e); 
		}
	}

}