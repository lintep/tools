package tools.util;

import java.io.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

public class Str {

	static HashMap<String, Integer> tf=new HashMap<String, Integer>();
	static StringBuilder stringBuilder=new StringBuilder();

	public static String getTermFrequency(String text) {
		tf.clear();
		for (String term : text.split("\t| ")) {
			if(!tf.containsKey(term))
				tf.put(term, 1);
			else
				tf.put(term, tf.get(term)+1);
		}
		stringBuilder.setLength(0);
		for (Entry<String, Integer> entry : tools.util.sort.Collection.mapSortedByKeyIncremental(tf)) {
			stringBuilder.append(entry.getKey());
			stringBuilder.append(" ");
			stringBuilder.append(entry.getValue());
			stringBuilder.append("`");
		}
		stringBuilder.setLength(stringBuilder.length()-1);
		return stringBuilder.toString();
	}
	
	static HashSet<String> distinctWord=new HashSet<String>();
	public static int getDictinctWordCount(String text) {
		distinctWord.clear();
		for (String term : text.split(" |\t")) {
			distinctWord.add(term);
		}
		return distinctWord.size();
	}
	
	public static String format( double val, int n){
		String pattern="#.";
		for (int i = 0; i < n; i++) {
			pattern+="#";
		}
		return new DecimalFormat(pattern).format(val);
	}
	
	public static String format( int val, int n){
		String pattern="";
		for (int i = 0; i < n; i++) {
			pattern+="0";
		}
		return new DecimalFormat(pattern).format(val);
	}
	
	static final String ZEROES = "000000000000";
	static final String BLANKS = "            ";
	public static String format( double val, int n, int w) 
	{
	//	rounding			
		double incr = 0.5;
		for( int j=n; j>0; j--) incr /= 10; 
		val += incr;
		
		
		String s = Double.toString(val);//new DecimalFormat("#.########").format(val);;
		int n1 = s.indexOf('.');
		int n2 = s.length() - n1 - 1;
		
		if (n>n2)      s = s+ZEROES.substring(0, n-n2);
		else if (n2>n) s = s.substring(0,n1+n+1);

		if( w>0 & w>s.length() ) s = BLANKS.substring(0,w-s.length()) + s;
		else if ( w<0 & (-w)>s.length() ) {
			w=-w;
			s = s + BLANKS.substring(0,w-s.length()) ;
		}
		return s;
	}

	public static void replace(String srcFileAddress,String destFileAddress,HashMap<String, String> convertPattern) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(srcFileAddress));
		BufferedWriter writer = new BufferedWriter(new FileWriter(destFileAddress));
		String newLine;
		while ((newLine=reader.readLine()) != null)
		{
			for (Entry<String, String> entry : convertPattern.entrySet()) {
				newLine=newLine.replaceAll(entry.getKey(), entry.getValue());
			}
				writer.append(newLine+"\n");
		}
		reader.close();
		writer.close();
	}
	
	public static String replaceAll(String inString,String searchString,String replaceString) throws IOException{
		return inString.replace(searchString, replaceString);
	}
	
	public static boolean isEmpty(String string) {
		return (string==null || string.isEmpty() || string=="")?true:false;
	}
	
	public static String removeStartEnd(String inString,String startStr,String endStr) throws Exception{		
		return removeStartEnd(new StringBuilder(inString), startStr, endStr).toString();
	}
	
	
	public static StringBuilder removeStartEnd(StringBuilder inString,String startStr,String endStr){
		stringBuilder.setLength(0);
		boolean tagStarted = false;
		int currentIndex = 0;
		int startIndex = 0;
		int prevStartIndex = 0;
		while (startIndex<inString.length()) {
			if(!tagStarted){
				startIndex = inString.indexOf(startStr, currentIndex);
				if(startIndex<0){
					stringBuilder.append(inString.substring(currentIndex));
					break;
				}
				else{
					stringBuilder.append(inString.substring(currentIndex,startIndex));
					tagStarted=true;					
				}
				currentIndex=startIndex+startStr.length();
			}
			else{
				prevStartIndex=startIndex;
				startIndex = inString.indexOf(endStr, startIndex);
				if(startIndex<0){
					Logger.log("can not find "+endStr);//throw new Exception("can not find "+endStr);
					stringBuilder.append(inString.substring(prevStartIndex));
					break;
				}
				else{
					tagStarted=false;	
					currentIndex=startIndex+endStr.length();
				}
			}
		}
		return stringBuilder;
	}
	
	public static String getCharHex(char ch) {
	    return String.format("\\u%04x", (int) ch);
	}
	

	public static int writeSentencesToFile(String text,
			HashSet<Character> delimiters,String resultFileAddress) throws IOException {
		stringBuilder.setLength(0);
		char ch=' ';
		int sentenceCounter=0;
		PrintWriter fileBufferWriter = tools.util.file.Write.getPrintWriter(resultFileAddress,true);
		for (int i = 0; i < text.length(); i++) {
			ch=text.charAt(i);
			if(delimiters.contains(ch)){
				if(stringBuilder.toString().trim().length()>0){
					fileBufferWriter.println(stringBuilder.toString());
					sentenceCounter++;
					stringBuilder.setLength(0);
				}
			}
			else				
				stringBuilder.append(ch);
		}
		if(stringBuilder.toString().trim().length()>0){
			fileBufferWriter.println(stringBuilder.toString());
			sentenceCounter++;
			stringBuilder.setLength(0);
		}
		fileBufferWriter.close();
		return sentenceCounter;
	}
	
	public static void main(String[] args) throws Exception {
		Logger.log(format(11,5));
	}
}
