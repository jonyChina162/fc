package cn.whu.zl.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter {

	@Override
	public String format(LogRecord log) {
		StringBuilder sb = new StringBuilder(200);
		sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(
				log.getMillis())));
		sb.append("\t");
		sb.append(log.getSourceClassName());
		sb.append("[" + log.getSourceMethodName() + "]\t");
		sb.append("[" + log.getLevel() + "] :　");
		sb.append(log.getMessage());
		sb.append("\r\n");
		return sb.toString();
	}

}
