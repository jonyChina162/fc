package cn.whu.zl.util;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

@Deprecated
public class CrawLogger {

	public CrawLogger(String name) {
		log = Logger.getLogger(name);
		logLevel = Level.INFO;
		filePath = System.getProperty("user.dir")
				+ "\\src\\main\\resources\\log\\log.log";

		 consoleHandler = new ConsoleHandler();
		 consoleHandler.setLevel(logLevel);
		 log.addHandler(consoleHandler);
		try {
			fileHandler = new FileHandler(filePath);
			fileHandler.setLevel(logLevel);
			fileHandler.setFormatter(new LogFormatter());
			log.addHandler(fileHandler);
		} catch (IOException ioe) {
			log.fine(ioe.getMessage());
		}
	}

	public void warning(String paramString) {
		log.warning(paramString);
	}

	public void info(String paramString) {
		log.info(paramString);
	}

	public void fine(String paramString) {
		log.fine(paramString);
	}

	public void config(String paramString) {
		log.config(paramString);
	}

	public void finer(String paramString) {
		log.finer(paramString);
	}

	public void finest(String paramString) {
		log.finest(paramString);
	}

	public Level getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(Level logLevel) {
		this.logLevel = logLevel;

		// consoleHandler.setLevel(logLevel);
		log.removeHandler(fileHandler);
		fileHandler.setLevel(logLevel);
		log.addHandler(fileHandler);
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
		log.removeHandler(fileHandler);
		try {
			fileHandler = new FileHandler(filePath);
			fileHandler.setFormatter(new LogFormatter());
			log.addHandler(fileHandler);
		} catch (IOException ioe) {
			log.fine(ioe.getMessage());
		}
	}

	public static void main(String[] args) {
		CrawLogger log = new CrawLogger(CrawLogger.class.getName());
		log.info("rubbish");
		log.setFilePath(System.getProperty("user.dir")
				+ "\\src\\main\\resources\\log\\log1.log");
		log.info("haha");
	}

	private Logger log;
	private Level logLevel;
	private String filePath;
	private Handler consoleHandler;
	private Handler fileHandler;
}
