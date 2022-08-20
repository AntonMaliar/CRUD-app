package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	private static final Properties PROPERTIES = new Properties();
	private PropertiesUtil() {}
	
	static {
		loadProperties();
	}
	
	private static void loadProperties(){ 		
		try (InputStream stream = PropertiesUtil.class.getClassLoader().getResourceAsStream("database.properties")){
			PROPERTIES.load(stream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String getProperties(String key) {
		return PROPERTIES.getProperty(key);
	}
}
