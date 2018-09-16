package tools.util;

import java.io.PrintWriter;
import java.io.Writer;

/**
 * Created by Saeed on 12/8/2016.
 */
public class PrintWriterWithBuffer extends PrintWriter {

    StringBuilder tempBuffer;
    boolean enable;

    public PrintWriterWithBuffer(Writer out) {
        super(out);
        this.tempBuffer = new StringBuilder();
        enable=true;
    }

    public void disable(){
        this.enable=false;
    }

    public void enable(){
        this.enable=true;
    }

    @Override
    public void println() {
        if(enable) {
            super.println();
            this.tempBuffer.append('\n');
        }
    }

    public void printlnWithConsole(String x) {
        if(enable) {
            println(x);
            System.out.println(x);
        }
    }

    @Override
    public void println(String x) {
        if(enable) {
            super.println(x);
            super.flush();
//        this.tempBuffer.append(x);
//        this.tempBuffer.append('\n');
        }
    }

    @Override
    public void print(String x) {
        if(enable) {
            super.print(x);
            this.tempBuffer.append(x);
        }
    }

    public String getAndClearTempBuffer(){
        if(enable) {
            String bufferValue = this.tempBuffer.toString();
            this.tempBuffer.setLength(0);
            super.flush();
            return bufferValue;
        }
        else{
            return "";
        }
    }
}
