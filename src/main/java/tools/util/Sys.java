package tools.util;


public class Sys {

	public static float getJavaVersion() {
		return Float.valueOf(System.getProperties()
				.getProperty("java.version").substring(0, 3));
	}
	
        
}
