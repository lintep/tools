package tools.util;

import java.io.*;
import java.nio.channels.FileChannel;

public class File {

	public static String getNameWithoutExtension(String fileAddress) {
		String fileName = fileAddress;
		java.io.File file = new java.io.File(fileName);
		int index = file.getName().lastIndexOf('.');
		if (index > 0 && index <= file.getName().length() - 2)
			return file.getName().substring(0, index);
		else
			return "";
	}
	
	public static String getName(String fileAddress) {
		String fileName = fileAddress;
		java.io.File file = new java.io.File(fileName);
		return file.getName();
	}
	
	public static int getTextFileLineCount(String textFileAddress,
			Boolean printMessage) {
		int lines = 0;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(textFileAddress));
			while (reader.readLine() != null)
				lines++;
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (printMessage)
			Logger.log(getName(textFileAddress) + "  have "
					+ lines + " Line");
		return lines;
	}
	
	public static BufferedReader getBufferedReader(String fileAddress) {
		try {
			return new BufferedReader(new FileReader(fileAddress));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String getCurrentDirectory() {
		class TMPCLASS {}
		TMPCLASS tmpclass=new TMPCLASS();
		String absolutePath = tmpclass.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        return absolutePath.substring(0, absolutePath.lastIndexOf("/"));
	}
	
	public static String getParent(String text_file_address) {
        String fileName = text_file_address;
        java.io.File file = new java.io.File(fileName);
        return file.getParent();
	}

	public static void saveTextFileValueAfterString(String inputTextFileAddress,String outputTextFileAddress,String beforeString,
			Boolean printMessage) throws IOException {
		int lines = 0;
		BufferedReader reader;
		PrintWriter out = new PrintWriter(new FileWriter(outputTextFileAddress));			
		try {
			reader = new BufferedReader(new FileReader(inputTextFileAddress));
			String newLine;
			while ((newLine=reader.readLine()) != null)
			{
				String str = newLine.substring(newLine.indexOf(beforeString)+beforeString.length()).split("\\s")[0];
				out.println(str);
				
				lines++;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.close();
		if (printMessage)
			Logger.log(getName(outputTextFileAddress) + "  have "
					+ lines + " Line");
	}

//	public static void copy(String sourceFileAddress, String destFileAddress) {
//		    OutputStream out;
//			Path source=;
//			Files.copy(source, out);//( sourceFileAddress, destFileAddress );
//	}
	public static void copy(String sourceFileAddress, String destFileAddress) throws IOException {
//	    if(!destFile.exists()) {
//	        destFile.createNewFile();
//	    }

	    FileChannel source = null;
	    FileChannel destination = null;

	    try {
	        source = new FileInputStream(sourceFileAddress).getChannel();
	        destination = new FileOutputStream(destFileAddress).getChannel();
	        destination.transferFrom(source, 0, source.size());
	    }
	    finally {
	        if(source != null) {
	            source.close();
	        }
	        if(destination != null) {
	            destination.close();
	        }
	    }
	}

	public static boolean rename(String sourceFileAddress, String destFileAddress) throws IOException {
//	    if(!destFile.exists()) {
//	        destFile.createNewFile();
//	    }

	    java.io.File source = new java.io.File(sourceFileAddress);
	    java.io.File destination = new java.io.File(destFileAddress);
        if(source.renameTo(destination)){
        	Logger.log("file "+sourceFileAddress+" renamed to "+destFileAddress);
        	return true;
        }else{
        	Logger.log("can not to rename file "+sourceFileAddress);
        	return false;
        }
	}
	public static boolean delete(String fileAddress) throws IOException {

	    	java.io.File file=new java.io.File(fileAddress);
	        if(file.delete()){
	        	Logger.log("file "+fileAddress+" deleted");
	        	return true;
	        }else{
	        	Logger.log("can not delete file "+fileAddress);
	        	return false;
	        }
	}
	public static boolean exist(String fileAddress) throws IOException {

        return (new java.io.File(fileAddress)).exists();
	}
	
	public static void getFileIntersection(){
		
	}
}
