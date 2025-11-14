package utilities;

import java.io.*;
import java.util.Properties;

public class ConfigReader {
	private static Properties prop;

	public static void loadProperties() {
		try {
			prop = new Properties();
			FileInputStream input = new FileInputStream(".\\src\\test\\resources\\Config.Properties");
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		if (prop == null) {
			loadProperties();
		}
		return prop.getProperty(key);
	}
}
