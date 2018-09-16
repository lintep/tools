package tools.util.file;

import java.io.*;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class TextReader implements Iterator<String>,Closeable{
	
	private BufferedReader bufferedReader;
	String nextLine;

	int nextIndex;
	List<String> fileAddresses;

	public TextReader(String fileAddress) throws IOException {
		this(new FileReader(fileAddress));
	}

	public TextReader(List<String> fileAddresses) throws IOException {
		this(new FileReader(fileAddresses.get(0)));
		nextIndex=1;
		this.fileAddresses=fileAddresses;
		System.out.println("start to read "+fileAddresses.get(nextIndex-1));
	}
	
	public TextReader(Reader reader) throws IOException {
		bufferedReader = new BufferedReader(reader);
		this.nextLine = bufferedReader.readLine();
	}
	
	@Override
	public boolean hasNext() {
		return this.nextLine!=null;
	}

	@Override
	public String next() {
		String line = this.nextLine;

		if(line!=null) {
			try {
				this.nextLine = bufferedReader.readLine();
				if(this.nextLine==null && (fileAddresses!=null && nextIndex<fileAddresses.size())){
					try {
						String fileAddress = fileAddresses.get(nextIndex);
						nextIndex++;
						bufferedReader = new BufferedReader(new FileReader(fileAddress));
						this.nextLine = bufferedReader.readLine();
						if(this.nextLine!=null) {
							System.out.println("start to read " + fileAddress);
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				throw new RuntimeException("BufferedReader.readLine() encountered an error", e);
			}
		}

		if(line==null) {
				throw new NoSuchElementException();
		}
		return line;
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
