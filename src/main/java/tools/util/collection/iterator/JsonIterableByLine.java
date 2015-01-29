package tools.util.collection.iterator;

import tools.util.file.BufferedIterator;

import java.io.*;
import java.util.Iterator;

public class JsonIterableByLine<T extends JsonUpdatable> implements Iterable<T>, Closeable {
	final private Reader reader;
	private T instance;
	
	public JsonIterableByLine(String jsonFileName,T instance) throws FileNotFoundException {
		this(new File(jsonFileName),instance);
	}

	public JsonIterableByLine(File jsonFile,T instance) throws FileNotFoundException {
		this(new FileReader(jsonFile),instance);
	}

	public JsonIterableByLine(Reader reader,T instance) {
		this.instance=instance;
		this.reader = reader;
	}
	
	@Override
	public JsonIterator iterator() {
		return new JsonIterator(reader);
	}

	public class JsonIterator implements Iterator<T> {
		
		final BufferedIterator bufferedIterator;
		
		public JsonIterator(Reader reader) {
			bufferedIterator=new BufferedIterator(reader);
		}

		@Override
		public boolean hasNext() {
			return bufferedIterator.hasNext();
		}

		@Override
		public T next() {
			instance.update(bufferedIterator.next());
			//instance.clone()
			return instance;
		}

		@Override
		public void remove() {
			bufferedIterator.remove();
		}
	}

	@Override
	public void close() throws IOException {
		reader.close();
	}
}
