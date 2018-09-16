package tools.util.file;


import tools.util.collection.PairValue;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Saeed on 5/29/2016.
 */
public class SparkTextReader implements Iterator<PairValue<String,String>>, Closeable {

    TextReader textReader;

    public SparkTextReader(String textFileAddress) throws IOException {
        textReader=new TextReader(textFileAddress);
    }

    @Override
    public void close() throws IOException {
        this.textReader.close();
    }

    @Override
    public boolean hasNext() {
        return this.textReader.hasNext();
    }

    @Override
    public PairValue<String,String> next() {
        String newLine = this.textReader.next();
        String[] splits = newLine.substring(1, newLine.length() - 1).split(",");
        if(splits.length==2) {
            return new PairValue<String, String>(splits[0],splits[1]);
        }
        else{
            if(splits.length==3) {
                if(splits[0].length()==0) {
                    return new PairValue<String, String>(',' + splits[1], splits[2]);
                }
//                else if(splits[1].length()==0) {
//                    return new PairValue<String, String>(splits[1], ',' + splits[2]);
//                }
                else if(splits[2].length()==0) {
                    return new PairValue<String, String>(splits[1], splits[1]+',');
                }
            }
                StringBuilder value=new StringBuilder();
                for (int i = 1; i < splits.length; i++) {
                    value.append(splits[i]);
                }
                return new PairValue<>(splits[0],value.toString());
        }
//            throw new ExceptionInInitializerError("can't handle "+newLine);
    }

    public static boolean isInSparkFormat(String line){
        if(line.charAt(0)=='(' && line.charAt(line.length()-1)==')' && line.indexOf(',')>0 ){
            return true;
        }
        return false;
    }

    public static PairValue<String,String> getLineValue(String line){
        if(line.charAt(0)=='(' && line.charAt(line.length()-1)==')' && line.indexOf(',')>0 ){
            int middleIndex = line.indexOf(',');
            return new PairValue<String, String>(line.substring(1,middleIndex),line.substring(middleIndex+1,line.length()-1));
        }
        return null;
    }

    public static List<PairValue<String,String>> getLines(String textFileAddress) throws IOException {
        List<PairValue<String,String>> result=new ArrayList<PairValue<String,String>>();
        SparkTextReader textReader=new SparkTextReader(textFileAddress);
        while (textReader.hasNext()){
            try {
                result.add(textReader.next());
            }
            catch (ExceptionInInitializerError e){

            }
        }
        return result;
    }

//    public static List<String> getLines(String textFileAddress, PairValue.PairType pairType) throws FileNotFoundException {
//        List<String> result=new ArrayList<String>();
//        SparkTextReader textReader=new SparkTextReader(textFileAddress);
//        while (textReader.hasNext()){
//            if(pairType == PairValue.PairType.X){
//                result.add(textReader.next().getX());
//            }
//            else{
//                result.add(textReader.next().getY());
//            }
//        }
//        return result;
//    }

    public static List<PairValue<String, String>> getMapIfInSparkFormat(String textFileAddress) throws IOException {
        if(isInSparkFormat(Reader.getTextFileLimitLines(textFileAddress,1).get(0))){
            return getLines(textFileAddress);
        }
        return null;
    }
}
