package com.tangxiaotang.dataMagnet.util;

import org.apache.commons.lang.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Properties;

public class ResourceUtil {

	/**
     * 加载classpath路径下的文件到流中。
	 */
	public static InputStream loadInputStreamFromClassPath(String filePath)
			throws Exception {
		return Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(filePath);
	}

	/**
	 * 加载classpath路径的属性文件。
	 */
	public static Properties loadPropertiesFromClassPath(String filePath)
			throws Exception {
		Properties pc = new Properties();
		pc.load(loadInputStreamFromClassPath(filePath));
		return pc;
	}


}