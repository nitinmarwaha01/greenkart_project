package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigDataProvider 
{
	private String configPath;
	private FileInputStream fis;
	private Properties properties;
	
	public ConfigDataProvider() {
		setConfigPath("./resources/configuration/config.properties");
		
		if(this.getConfigPath()!=null) {
			try {
				setFIS(new FileInputStream(new File(this.getConfigPath())));
				
				if(getFIS()!=null) {
					properties = new Properties();
					properties.load(this.getFIS());
				}else {
					System.out.println("Not able to get properties file input stream.");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					this.getFIS().close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {
			System.out.println("Not able to get properties file path.");
		}
	}
	
	public String getConfigPath() {
		return configPath;
	}
	
	public void setConfigPath(String path) {
		this.configPath = path;
	}
	
	public FileInputStream getFIS() {
		return fis;
	}
	
	public void setFIS(FileInputStream fis) {
		this.fis = fis;
	}
	
	public String getBrowser() {
		return properties.getProperty("browser");
	}
	
	public String getApplicationURL() {
		return properties.getProperty("applicationURL");
	}
	
	public String getBrowserVersion() {
		return properties.getProperty("browserVersion");
	}
	
	public String getOS() {
		return properties.getProperty("OS");
	}
}
