package tools.util.file;

import java.io.*;
import java.io.Reader;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BufferedIterator implements Iterator<String>,Closeable{
	
	final private BufferedReader bufferedReader;
	String nextLine;

	public BufferedIterator(String fileAddress) throws FileNotFoundException {		
		bufferedReader = new BufferedReader(new FileReader(fileAddress));		
	}
	
	public BufferedIterator(Reader reader) {
		bufferedReader = new BufferedReader(reader);		
	}
	
	@Override
	public boolean hasNext() {
		try {
			this.nextLine=bufferedReader.readLine();
		} catch (IOException e) {
			throw new RuntimeException("BufferedReader.readLine() encounterd an error", e);
		}		
		return this.nextLine!=null;
	}

	@Override
	public String next() {
		if(this.nextLine==null)
			throw new NoSuchElementException();
		return this.nextLine;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("remove is not supported.");
		
	}

	@Override
	public void close() throws IOException {
		bufferedReader.close();
	}


}
