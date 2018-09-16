package tools.util;


public class Sys {

	public static float getJavaVersion() {
		return Float.valueOf(System.getProperties()
				.getProperty("java.version").substring(0, 3));
	}
	
    public static String getOsVersion(){
		return System.getProperty("os.name");
	}


	public static boolean osIsWin() {
    	return getOsVersion().toLowerCase().indexOf("win")>=0;
	}


	public static void main(String[] args) {
		System.out.println(getOsVersion());
		System.out.println(osIsWin());
	}
}
