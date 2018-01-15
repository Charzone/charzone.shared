package com.duapp.heart2heart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 资源文件工具类
 * @author ccz
 *
 */
public class PropertiesUtil {
	
	/**
	 * 根据提供的资源路径返回资源对象
	 * @param proPath
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Properties getInstance(String proPath) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(proPath));
		return properties;
	}
	
}
