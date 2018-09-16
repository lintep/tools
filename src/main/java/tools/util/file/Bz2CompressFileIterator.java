package tools.util.file;

import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

import java.io.*;
import java.util.Iterator;

/**
 * Created by Saeed on 7/10/15.
 */
public class Bz2CompressFileIterator implements Iterator<String>, Closeable {

    String bz2FileAddress;
    String startLineDelim;
    String endLineDelim;
    BufferedReader bz2BufferReader;

    public static void main(String[] args) throws IOException, CompressorException {
        String fileAddress="L:/data set/wiki/enwiki-20141008-pages-meta-current.xml.bz2";
//        String fileAddress="k:/temp/fawiki-20140615-pages-meta-current.xml.bz2";

        String startBreaker="<page>";
        String endBreaker="</page>";

        Bz2CompressFileIterator bz2CompressFileIterator=new Bz2CompressFileIterator(fileAddress,startBreaker,endBreaker);

        while (bz2CompressFileIterator.hasNext()){
            String newXmlDocument=bz2CompressFileIterator.next();
//            System.out.println();
            if(bz2CompressFileIterator.getItemCounter()==1000){
                break;
            }
        }

        bz2CompressFileIterator.close();

        System.out.println("ItemCounter:"+bz2CompressFileIterator.getItemCounter()+"\tLineNumber:"+bz2CompressFileIterator.getLineCounter());

        System.out.println("Operation complete.");
    }

    public static BufferedReader getBZ2BufferedReader(String bz2FileAddress) throws FileNotFoundException, CompressorException {
        FileInputStream fin = new FileInputStream(bz2FileAddress);
        BufferedInputStream bis = new BufferedInputStream(fin);
        CompressorInputStream input = new CompressorStreamFactory().createCompressorInputStream(bis);
        BufferedReader br2 = new BufferedReader(new InputStreamReader(input));
        return br2;
    }

    StringBuilder newItem;
    int itemCounter=-1;

    String newLine;
    int lineCounter=-1;
    boolean startState;
    boolean endState;
    String currentItemString="";

    public Bz2CompressFileIterator(String bz2FileAddress, String startLineDelim, String endLineDelim) throws FileNotFoundException, CompressorException {
        this.bz2FileAddress=bz2FileAddress;
        this.startLineDelim=startLineDelim;
        this.endLineDelim=endLineDelim;
//        try {
            this.bz2BufferReader=getBZ2BufferedReader(this.bz2FileAddress);
//        } catch (CompressorException e) {
//            e.printStackTrace();
//            throw new IOException("illegal file type: "+e.getMessage());
//        }

        newItem=new StringBuilder();;
        itemCounter=0;
        newLine="";
        startState = false;
        endState = false;
        currentItemString="";
    }

    @Override
    public boolean hasNext() {

        try {
            while((newLine=bz2BufferReader.readLine())!=null){

                lineCounter++;

                if(newLine.trim().equals(startLineDelim)){
                    startState=true;
                    endState=false;
                }
                else if(newLine.trim().equals(endLineDelim)){
                    endState=true;
                    startState=false;
                }


                if(startState){
                    newItem.append(newLine);
                }
                else if(endState){
                    newItem.append(newLine);
                    itemCounter++;
                    currentItemString = newItem.toString();
                    newItem.setLength(0);
//                    System.out.println("Item "+newItemCounter+" read completed in line "+lineCounter+".");
                    break;
                }
                else{
                    //Log WHAT IS THIS FORMAT
                    continue;
                }

            }
        } catch (IOException e) {
            return false;
        }

        if(endState){
            endState=false;
            startState=false;
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public String next() {
        return currentItemString;
    }

    @Override
    public void remove() {
        try {
            throw new Exception("Not Implemented");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void close() throws IOException {
        this.bz2BufferReader.close();
    }

    public int getLineCounter() {
        return lineCounter;
    }

    public int getItemCounter() {
        return itemCounter;
    }
}
