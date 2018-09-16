package tools.util;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

public class Str {

	static HashMap<String, Integer> tf=new HashMap<String, Integer>();
	static StringBuilder staticStringBuilder =new StringBuilder();

	public static String getTermFrequency(String text) {
		tf.clear();
		for (String term : text.split("\t| ")) {
			if(!tf.containsKey(term))
				tf.put(term, 1);
			else
				tf.put(term, tf.get(term)+1);
		}
		staticStringBuilder.setLength(0);
		for (Entry<String, Integer> entry : tools.util.sort.Collection.mapSortedByKeyIncremental(tf)) {
			staticStringBuilder.append(entry.getKey());
			staticStringBuilder.append(" ");
			staticStringBuilder.append(entry.getValue());
			staticStringBuilder.append("`");
		}
		staticStringBuilder.setLength(staticStringBuilder.length()-1);
		return staticStringBuilder.toString();
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
	
	public static String getFormatedLong(long number) throws UnsupportedEncodingException{
		return new DecimalFormat("#,###").format(number);
	}
	
	public static String format( int val, int n){
		String pattern="";
		for (int i = 0; i < n; i++) {
			pattern+="0";
		}
		return new DecimalFormat(pattern).format(val);
	}

    public static String format( String val, int n){
        String result=val;
        for (int i = 0; i < n-val.length(); i++) {
            result+=" ";
        }
        return result;
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
	
	public static String removeStartEndLabel(String inString,String startStr,String endStr) throws Exception{
		return removeStartEndLabel(new StringBuilder(inString), startStr, endStr).toString();
	}

    public static String removeBetweenStartEndLabel(String inString,String startStr,String endStr) throws Exception{
        return removeBetweenStartEndLabel(new StringBuilder(inString), startStr, endStr).toString();
    }
	
	public static StringBuilder removeBetweenStartEndLabel(StringBuilder inString,String startLabel,String endLabel) throws Exception {
		staticStringBuilder.setLength(0);
		boolean tagStarted = false;
		int currentIndex = 0;
		int startIndex = 0;
		int prevStartIndex = 0;
		while (startIndex<inString.length()) {
			if(!tagStarted){
				startIndex = inString.indexOf(startLabel, currentIndex);
				if(startIndex<0){
					staticStringBuilder.append(inString.substring(currentIndex));
					break;
				}
				else{
					staticStringBuilder.append(inString.substring(currentIndex, startIndex));
					tagStarted=true;					
				}
				currentIndex=startIndex+startLabel.length();
			}
			else{
				prevStartIndex=startIndex+startLabel.length();
				startIndex = inString.indexOf(endLabel, prevStartIndex);
				if(startIndex<0){
                    throw new Exception("can not find "+endLabel);//Logger.log("can not find "+endStr);//throw new Exception("can not find "+endStr);
//					staticStringBuilder.append(inString.substring(prevStartIndex));
//					break;
				}
				else{
                    if(!startLabel.equals(endLabel)){
                        int startLabelSecondIndex=inString.indexOf(startLabel, prevStartIndex);
                        if(startLabelSecondIndex>0 && startIndex>startLabelSecondIndex){
                            throw new IOException("Inner "+startLabel+" tag occurred");
                        }
                    }
					tagStarted=false;
					currentIndex=startIndex+endLabel.length();
				}
			}
		}
		return new StringBuilder(staticStringBuilder);
	}

    public static ArrayList<String> returnBetweenStartEndLabel(String inString,String startLabel,String endLabel) throws Exception {
        return returnBetweenStartEndLabel(new StringBuilder(inString),startLabel,endLabel);
    }

    public static ArrayList<String> returnBetweenStartEndLabel(StringBuilder inString,String startLabel,String endLabel) throws Exception {
        ArrayList<String> result = new ArrayList<String>();
        boolean tagStarted = false;
        int currentIndex = 0;
        int startIndex = 0;
        int prevStartIndex = 0;
        while (startIndex<inString.length()) {
            if(!tagStarted){
                startIndex = inString.indexOf(startLabel, currentIndex);
                if(startIndex<0){
                    break;
                }
                else{
                    tagStarted=true;
                }
                currentIndex=startIndex+startLabel.length();
            }
            else{
                prevStartIndex=startIndex+startLabel.length();
                startIndex = inString.indexOf(endLabel, prevStartIndex);
                if(startIndex<0){
                    throw new Exception("can not find "+endLabel);
                }
                else{
                    if(!startLabel.equals(endLabel)){
                        int startLabelSecondIndex=inString.indexOf(startLabel, prevStartIndex);
                        if(startLabelSecondIndex>0 && startIndex>startLabelSecondIndex){
                            throw new IOException("Inner "+startLabel+" tag occurred");
                        }
                    }
                    tagStarted=false;
                    result.add(inString.substring(currentIndex, startIndex));
                    currentIndex=startIndex+endLabel.length();
                }
            }
        }
        return result;
    }

    public static ArrayList<String> returnBetweenStartEndLabelRecursive(String inString, String startLabel, String endLabel) throws Exception {
        return returnBetweenStartEndLabelRecursive(new StringBuilder(inString), startLabel, endLabel);
    }

    public static ArrayList<String> returnBetweenStartEndLabelRecursive(StringBuilder inString,String startLabel,String endLabel) throws Exception {

        ArrayList<String> result = new ArrayList<String>();
        if(startLabel.equals(endLabel)){
            throw new Exception("Start and End label is equal !!!");
//                        if(startLabelSecondIndex>0 && startIndex>startLabelSecondIndex){
//                            throw new IOException("Inner "+startLabel+" tag occurred");
//                        }
        }

//        boolean tagStarted = false;
        int currentIndex = 0;
        int startIndex = -1;
        int endIndex = 0;
//        int prevStartIndex = 0;

        int startTagIndexCount=0;

        while (currentIndex<inString.length()) {

            if(startTagIndexCount==0){

                if(startIndex>=0){
                    result.add(inString.substring(startIndex+startLabel.length(),currentIndex-endLabel.length()));
                }

                startIndex = inString.indexOf(startLabel, currentIndex);
                endIndex = inString.indexOf(endLabel, currentIndex);

                if(startIndex<0){
                    if(endIndex>=0){
                        throw new Exception("End tag\""+endLabel+"\" not have start tag \""+startLabel+"\" index."+"\n"+inString+"\n\n");
                    }
                    else{
                        break;
                    }
                }
                else{
                    if(endIndex<0){
                        throw new Exception("Start tag\""+startLabel+"\" not have end tag \""+endLabel+"\" index."+"\n"+inString+"\n\n");
                    }
                    else if(endIndex<startIndex){
                        throw new Exception("Start tag\""+startLabel+"\" not found but End tag \""+endLabel+"\" exist."+"\n"+inString+"\n\n");
                    }
                    else{
                        startTagIndexCount++;
                    }
                }
                currentIndex = startIndex + startLabel.length();
            }
            else{
                int nextStartIndex = inString.indexOf(startLabel, currentIndex);
                int nextEndIndex = inString.indexOf(endLabel, endIndex+endLabel.length());

                if(nextStartIndex<0 || endIndex<nextStartIndex){
                    startTagIndexCount--;
                    currentIndex=endIndex+endLabel.length();
                    endIndex=nextEndIndex;
                    continue;
                }
                else{

                    startTagIndexCount++;
                    currentIndex=nextStartIndex+startLabel.length();
                    continue;
                }
            }
        }

        if(startTagIndexCount>0){
            throw new Exception("End tag \""+endLabel+"\" absent count -> "+startTagIndexCount+"\n"+inString+"\n\n");
        }

        if(startIndex>=0){
            result.add(inString.substring(startIndex+startLabel.length(),currentIndex-endLabel.length()));
        }

        return result;
    }


    public static ArrayList<String> returnRemovedStartEndLabelRecursiveAsList(String inString, String startLabel, String endLabel) throws Exception {
        return returnRemovedStartEndLabelRecursiveAsList(new StringBuilder(inString), startLabel, endLabel);
    }

    public static ArrayList<String> returnRemovedStartEndLabelRecursiveAsList(StringBuilder inString,String startLabel,String endLabel) throws Exception {

        ArrayList<String> result = new ArrayList<String>();

        if(startLabel.equals(endLabel)){
            throw new Exception("Start and End label is equal !!!");
        }

        int currentIndex = 0;
        int startIndex = 0;
        int endIndex = 0;

        final int maxIteration=1000;

        int whileIterationCount=0;

        int startTagIndexCount=0;


        while (currentIndex<inString.length()) {

            whileIterationCount++;

            if(whileIterationCount>maxIteration){
                return result;
            }

            if(startTagIndexCount==0){

                startIndex = inString.indexOf(startLabel, currentIndex);
                endIndex = inString.indexOf(endLabel, currentIndex);

                if(startIndex<0){
                    if(endIndex>=0){
                        throw new Exception("End tag\""+endLabel+"\" not have start tag \""+startLabel+"\" index.");
                    }
                    else{
                        break;
                    }
                }
                else{
                    if(endIndex<0){
                        throw new Exception("Start tag\""+startLabel+"\" not have end tag \""+endLabel+"\" index.");
                    }
                    else if(endIndex<startIndex){
                        throw new Exception("Start tag\""+startLabel+"\" not found but End tag \""+endLabel+"\" exist.");
                    }
                    else{
                        if(startIndex>0){
                            result.add(inString.substring(currentIndex,startIndex));
                        }
                        startTagIndexCount++;
                    }
                }
                currentIndex = startIndex + startLabel.length();
            }
            else{
                //  *** { } ** {     {    }    } ***  {  {   {  }  }   } ***

                int nextStartIndex = inString.indexOf(startLabel, currentIndex);
                int nextEndIndex = inString.indexOf(endLabel, endIndex+endLabel.length());

                if(nextStartIndex<0 || endIndex<nextStartIndex){
                    startTagIndexCount--;
                    currentIndex=endIndex+endLabel.length();
                    endIndex=nextEndIndex;
                    continue;
                }
                else{

                    startTagIndexCount++;
                    currentIndex=nextStartIndex+startLabel.length();
                    continue;
                }
            }
        }

        if(startTagIndexCount>0){
            throw new Exception("En tag \""+endLabel+"\" absent count -> "+startTagIndexCount);
        }

        if(currentIndex<inString.length()){
            result.add(inString.substring(currentIndex));
        }

        return result;
    }

    public static StringBuilder returnRemovedStartEndLabelRecursive(StringBuilder inString,String startLabel,String endLabel) throws Exception{
        StringBuilder stringBuilder=new StringBuilder();
        for(String item:returnRemovedStartEndLabelRecursiveAsList(inString,startLabel,endLabel)){
            stringBuilder.append(item);
        }
        return stringBuilder;
    }

    public static StringBuilder removeStartEndLabel(StringBuilder inString,String startLabel,String endLabel) throws Exception {
        staticStringBuilder.setLength(0);
        boolean tagStarted = false;
        int currentIndex = 0;
        int startIndex = 0;
        int prevStartIndex = 0;
        while (startIndex<inString.length()) {
            if(!tagStarted){
                startIndex = inString.indexOf(startLabel, currentIndex);
                if(startIndex<0){
                    staticStringBuilder.append(inString.substring(currentIndex));
                    break;
                }
                else{
                    staticStringBuilder.append(inString.substring(currentIndex, startIndex));
                    tagStarted=true;
                }
                currentIndex=startIndex+startLabel.length();
            }
            else{
                prevStartIndex=startIndex+startLabel.length();
                startIndex = inString.indexOf(endLabel, prevStartIndex);
                if(startIndex<0){
                    throw new Exception("can not find "+endLabel+" after "+staticStringBuilder.toString());//Logger.log("can not find "+endStr);//throw new Exception("can not find "+endStr);
//					staticStringBuilder.append(inString.substring(prevStartIndex));
//					break;
                }
                else{
                    if(!startLabel.equals(endLabel)){
                        int startLabelSecondIndex=inString.indexOf(startLabel, prevStartIndex);
                        if(startLabelSecondIndex>0 && startIndex>startLabelSecondIndex){
                            throw new IOException("Inner "+startLabel+" label occurred");
                        }
                    }
                    tagStarted=false;
                    staticStringBuilder.append(inString.substring(currentIndex, startIndex));
                    currentIndex=startIndex+endLabel.length();
                }
            }
        }
        return new StringBuilder(staticStringBuilder);
    }

	public static String getCharHex(char ch) {
	    return String.format("\\u%04x", (int) ch);
	}
	

	public static int writeSentencesToFile(String text,
			HashSet<Character> delimiters,String resultFileAddress) throws IOException {
		staticStringBuilder.setLength(0);
		char ch=' ';
		int sentenceCounter=0;
		PrintWriter fileBufferWriter = tools.util.file.Write.getPrintWriter(resultFileAddress,true);
		for (int i = 0; i < text.length(); i++) {
			ch=text.charAt(i);
			if(delimiters.contains(ch)){
				if(staticStringBuilder.toString().trim().length()>0){
					fileBufferWriter.println(staticStringBuilder.toString());
					sentenceCounter++;
					staticStringBuilder.setLength(0);
				}
			}
			else				
				staticStringBuilder.append(ch);
		}
		if(staticStringBuilder.toString().trim().length()>0){
			fileBufferWriter.println(staticStringBuilder.toString());
			sentenceCounter++;
			staticStringBuilder.setLength(0);
		}
		fileBufferWriter.close();
		return sentenceCounter;
	}

    public static ArrayList<String> getClosedTags(String text) throws Exception {
        ArrayList<String> result = new ArrayList<String>();

        try {



            int currentIndex = 0;
            while (true) {

                if (currentIndex >= text.length()) {
                    break;
                }

                if (text.charAt(currentIndex) == '<') {

                    int tempCurrentIndex = currentIndex + 1;
                    StringBuilder tempStringBuilder = new StringBuilder();
                    while (text.charAt(tempCurrentIndex) != '>' && text.charAt(tempCurrentIndex) != ' ') {
                        tempStringBuilder.append(text.charAt(tempCurrentIndex));
                        tempCurrentIndex++;
                        if (tempCurrentIndex >= text.length()) {
                            return result;
                        }
                    }
                    while (text.charAt(tempCurrentIndex) != '>') {
                        tempCurrentIndex++;
                        if (tempCurrentIndex >= text.length()) {
                            return result;
                        }
                    }

                    String tagName = tempStringBuilder.toString();

                    tempStringBuilder.setLength(0);

                    int endTagIndex = text.indexOf("</" + tagName + ">", tempCurrentIndex + 1);

                    if (endTagIndex > 0) {
                        result.add(tagName);
                        currentIndex += endTagIndex + 3 + tagName.length();
                    } else {
                        currentIndex++;
                    }
                } else {
                    currentIndex++;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        return result;
    }

    public static void print(Map map,int floatPoint){
        int i=0;
        for(Object entryObj:tools.util.sort.Collection.mapSortedByValuesDecremental(map)){
            Entry entry = (Entry) entryObj;
            System.out.println(++i +")\t"+entry.getKey().toString()+"\t"+ Str.format((Double)entry.getValue(),
                    floatPoint));
        }
    }

	public static void main(String[] args) throws Exception {
		Logger.log(format(11.01123532152,5));
	}
}
