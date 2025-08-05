package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtility {

		public String togetDataFromPropertiesFile(String key) throws IOException
		{
			FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commondata.properties");
			Properties prop=new Properties();
			prop.load(fis);
			String value = prop.getProperty(key);
			return value;
	}

}
