package tools.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Scanner;

public class ConsuleInput {
	static Scanner scanner=new Scanner(System.in);
	/**
	 * @param args
	 * @throws java.io.UnsupportedEncodingException
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		Logger.log(readStringUTF8());
	}

	public static String readStringUTF8() throws UnsupportedEncodingException{
		return (new String(scanner.nextLine().getBytes("UTF-8"), Charset
				.forName("UTF-8")));
	}
	
}
